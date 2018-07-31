$(function () {
  var a = {
    UserID: localStorage.getItem("IDuser"),
    leadAddress: "http://www.onepi.top:8080/blog/",

    //初始化用户信息
    init: function () {
      var i = this;
      $.ajax({
        url: this.leadAddress + "blog/myhomepage",
        type: 'get',
        async: false,
        dataType: 'json',
        error: function () {
          $(".warning .dec_txt").text("服务器错误");
          errorRender();
        },
        success: function (data) {
          var t = data.message;
          $(".b_initName").text(t.username);//用户名
          $(".b_UserEdits").eq(0).val(t.username);
          $(".b_personalIfmt").eq(0).text(t.username);
          $("#b_personal_InfName b,.b_UserEdit_id b").text(t.id);//id
          $(".b_personalIfmt").eq(1).val(t.sex);//性别
          $(".W_f12").eq(0).text(t.idols);//关注
          $(".W_f12").eq(1).text(t.fans);//粉丝数
          $(".W_f12").eq(2).text("0");//文章数
          $(".b_infos span").eq(0).text(t.school);//学校
          $(".b_personalIfmt").eq(6).text(t.school);
          $(".b_infos span").eq(1).text(t.address);//地址
          $(".b_personalIfmt").eq(7).text(t.address);
          $(".b_personalIfmt").eq(8).text(t.email);//邮箱
          $(".b_personalIfmt").eq(9).text(t.phone);//手机号码
          $(".b_personalIfmt").eq(10).text(t.qq);//qq号码
          $(".b_personalIfmt").eq(11).text(t.wechat);//微信号
          $(".b_infos span").eq(2).text(t.birthday);//生日
          $(".b_personalIfmt").eq(2).text(t.birthday);
          $(".b_personalIfmt").eq(3).text(t.myblog);
          $(".b_personalIfmt").eq(4).text(t.direct);//方向
          $(".b_infos span").eq(1).text(t.signature);//个性签名
          $(".b_UserEdits").eq(4).val(t.signature);
          $(".b_personalIfmt").eq(5).text(t.signature);
          $(".b_infos span").eq(1).text(t.email);//邮箱
          var h_url = i.leadAddress+ "img/" + t.faceImg;
          $("#w_myHead,#b_myHead").css("background-image", "url(" + h_url + ")");//用户头像
          var w_url = i.leadAddress+ "img/" + t.background;
          $(".wallpaper").css("background-image", "url(" + w_url + ")");//背景图
          window.abc();
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

  window.myLib = {
    a:a
  }

})