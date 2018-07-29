$(function () {
  var infoEdit = {
    bn1: function () {
      $(".b_more_txt span").click(function () {
        $(".over_content").removeClass("none");
        $(".page_container").addClass("page_container6");
        $(".page_outer").addClass("page_outer6");
        $("#w_myHead").addClass("w_myHead_change");
        $(".hChange_button").removeClass("none");
        $(".over_wallpaper").removeClass("none");
        $(".b_UserActions").removeClass("none");
      })
      $(".UserAction_cancel").click(function(){
        $(".over_content").addClass("none");
        $(".page_container").removeClass("page_container6");
        $(".page_outer").removeClass("page_outer6");
        $("#w_myHead").removeClass("w_myHead_change");
        $(".hChange_button").addClass("none");
        $(".over_wallpaper").addClass("none");
        setTimeout(function(){$(".b_UserActions").addClass("none")},100);
      })
    }
  }
  infoEdit.bn1();





})