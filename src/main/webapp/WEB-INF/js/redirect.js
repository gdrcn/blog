$(function () {
  //跳转弹出页面
  $(".profileCard_waller, .profileCard-header, .profileCard-name a").click(function jumpThird() {
    var idx = $(this).index();
    var thirdId = $(this).parents(".b_doubles").data("id");
    if (thirdId != localStorage.getItem("IDuser")) {
      localStorage.setItem("thirdId", thirdId);//记录被访问第三者的id
      window.open('http://www.onepi.top:8080/blog/html/thirdpage.html');
    }
    else {
      window.open('http://www.onepi.top:8080/blog/html/mypage.html');
    }
  })

  //follow悬浮效果
  $(".relative_button").mouseenter(function () {
    if ($(this).text() == "Follow") {
      $(this).css({"background-color":"#E8F5FD","border-color":"#1DA1F2"})
    }
    else{
      $(this).text("Unfollow");
      $(this).css({"background-color":"#e0245e","border-color":"#e0245e"})
    }
  })
  $(".relative_button").mouseleave(function () {
    if ($(this).text() == "Follow") {
      $(this).css({"background-color":"#fff","border-color":"#1DA1F2"})
    }
    else{
      $(this).text("Following");
      $(this).css({"background-color":"#1DA1F2","border-color":"#1DA1F2"})
    }
  })
})