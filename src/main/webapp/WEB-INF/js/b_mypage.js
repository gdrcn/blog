$(function () {
  var a = {
    UserID: localStorage.getItem("IDuser"),
    leadAddress: "http://192.168.43.186:8080/rdc/",

    //初始化用户信息
    init: function () {
      var i = this;
      $.ajax({
        url: this.leadAddress + "blog/myhomepage",
        type: 'get',
        dataType: 'json',
        error: function () {
          $(".warning .dec_txt").text("服务器错误");
          errorRender();
        },
        success: function (data) {
          var t = data.message;
          $(".b_initName").text(t.username);//用户名
          $("#b_personal_InfName p").text(t.id);//id
          $(".W_f12").eq(0).text(t.idols);//关注
          $(".W_f12").eq(1).text(t.fans);//粉丝数
          $(".W_f12").eq(2).text("0");//文章数
          // $(".b_infos span").eq(0).text(t.school);//学校
          $(".b_infos span").eq(1).text(t.address);//地址
          $(".b_infos span").eq(2).text(t.birthday);//生日
          $(".b_infos span").eq(1).text(t.signature);//个人签名
          $(".b_infos span").eq(1).text(t.email);//邮箱
          var url = i.leadAddress+ "img/" + t.faceImg;
          $("#w_myHead,#b_myHead").css("background-image", "url(" + url + ")");//用户头像

        }
      })
    },

    fn1: function () {
      $("#b_RDClogo").click(function () {
        window.location.replace('file:///F:/gittes/rdc/blog/src/main/webapp/WEB-INF/html/home.html')
      })
      $(".header_list").eq(3).click(function(){
        window.location.replace('file:///F:/gittes/rdc/blog/src/main/webapp/WEB-INF/html/myInfo.html')
      })
      $(".header_list").eq(0).click(function(){
        window.location.replace('file:///F:/gittes/rdc/blog/src/main/webapp/WEB-INF/html/mypage.html')
      })
    },


  }
  a.init();
  a.fn1();


})