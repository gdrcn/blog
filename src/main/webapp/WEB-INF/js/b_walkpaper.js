$(function () {
  //滚动效果
  var at = document.getElementsByClassName('header')[0].getBoundingClientRect().top;//元素离页面顶部的高度
  var ht = parseInt($(".wallpaper").css("height"))
  if(at < ht){at =ht;}
  $(window).scroll(function (event) {
    var st = document.documentElement.scrollTop;//滚去的高度
    var ht = $('.header').height();
    var v = at - st;
    if (v < 0) {
      $(".header").css({ "position": "fixed", "top": 0 });
      $("#w_wordPressLine").addClass("b_none");
      $("#b_wordPressLine").removeClass("b_none");
    }
    if (v >= 0) {
      $(".header").css("position", "static");
      $("#w_wordPressLine").removeClass("b_none");
      $("#b_wordPressLine").addClass("b_none");
    }
  })

  //导航栏效果
  $(".header_list").mouseenter(function () {
    var idx = $(this).index();
    $(".header_list .list_underline").eq(idx).show().animate({ top: "35px" }, 100);
  })
  $(".header_list").mouseleave(function () {
    var idx = $(this).index();
    if (!$(".header_list .list_underline").eq(idx).hasClass("b_block"))
      $(".header_list .list_underline").eq(idx).hide().animate({ top: "20px" }, 100);
  })
  $(".header_list").click(function () {
    var idx = $(this).index();
    $(".header_list .list_underline").eq(idx).addClass("b_block").css({ top: "35px", color: "#3479d8" });
    $(".header_list").eq(idx).addClass("header_list_active");
    $(".header_list .list_underline").not($(".header_list .list_underline").eq(idx)).removeClass("b_block").hide().css({ top: "20px", color: "#66757f" });
    $(".header_list").not($(".header_list").eq(idx)).removeClass("header_list_active");
  })

  //下拉菜单
  $(".b_personalsetting").click(function(event){
    event.stopPropagation();
    $(".b_dropdown-menu").toggleClass("none");
  })
  $(document).click(function () {
    $(".b_dropdown-menu").addClass("none");
  });
  $(".b_dropdown-menu").click(function(event){
    event.stopPropagation();
  })
});