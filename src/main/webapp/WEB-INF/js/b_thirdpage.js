$(function () {

  function Assignment(a, b) {
    if (a != null) {
      b.text(a);
    }
  }


  var a = {
    thirdId: localStorage.getItem("thirdId"),
    leadAddress: "http://www.onepi.top:8080/blog/",
    IDuser: localStorage.getItem("IDuser"),

    //初始化用户信息
    init: function () {
      var ini = this;
      $.ajax({
        url: this.leadAddress + "blog/otherHomepage/" + a.thirdId,
        type: 'get',
        async: false,
        dataType: 'json',
        error: function () {
          $(".warning .dec_txt").text("服务器错误");
          errorRender();
        },
        success: function (data) {
          if (data.result == "success") {
            var t = data.message;
            Assignment(t.username, $(".b_initName"));//用户名
            Assignment(t.username, $(".b_personalIfmt").eq(0));
            Assignment(t.id, $("#b_personal_InfName b,.b_UserEdit_id b"));//id
            $(".b_personalIfmt").eq(1).val(t.sex);//性别
            Assignment(t.idols, $(".W_f12").eq(0));//关注
            Assignment(t.fans, $(".W_f12").eq(1));//粉丝数
            Assignment("0", $(".W_f12").eq(2));//文章数
            Assignment(t.school, $(".b_infos span").eq(0));//学校
            Assignment(t.school, $(".b_personalIfmt").eq(6));
            Assignment(t.address, $(".b_infos span").eq(1));//地址
            Assignment(t.address, $(".b_personalIfmt").eq(7));
            Assignment(t.email, $(".b_personalIfmt").eq(8));//邮箱
            Assignment(t.phone, $(".b_personalIfmt").eq(9));//手机号码
            Assignment(t.qq, $(".b_personalIfmt").eq(10));//qq号码
            Assignment(t.wechat, $(".b_personalIfmt").eq(11));//微信号
            Assignment(t.birthday, $(".b_infos span").eq(2));//生日
            Assignment(t.birthday, $(".b_personalIfmt").eq(2));
            Assignment(t.myblog, $(".b_personalIfmt").eq(3));
            Assignment(t.direct, $(".b_personalIfmt").eq(4));//方向
            Assignment(t.signature, $(".b_infos span").eq(3));//个性签名
            Assignment(t.signature, $(".b_personalIfmt").eq(5));
            Assignment(t.email, $(".b_infos span").eq(4));//邮箱
            var h_url = ini.leadAddress + "img/" + t.faceImg;
            $("#w_myHead,#b_myHead").css("background-image", "url(" + h_url + ")");//用户头像
            var w_url = ini.leadAddress + "img/" + t.backgroundPhoto;
            $(".wallpaper").css("background-image", "url(" + w_url + ")");//背景图
            //获取最新文章
            var num = t.blogList.length;
            if (num != 0) {
              for (var i = 1; i <= num && i < 6; i++) {
                $(".b_counter_article").append($(".b_new_article").eq(0).clone(true))
                $(".b_new_article").eq(i).removeClass("none");
                $(".b_new_article").eq(i).data("id", t.blogList[i - 1].id);
                Assignment(t.blogList[i - 1].title, $(".b_new_article .b_new_title_content").eq(i));
                Assignment(t.blogList[i - 1].article, $(".b_new_article .b_new_article_content").eq(i));
                Assignment(t.blogList[i - 1].finishTime, $(".b_new_article .b_new_sign_time").eq(i));
                Assignment(t.blogList[i - 1].category, $(".b_new_article .b_new_tag").eq(i));
                if (t.blogList[i - 1].coverImg != null) {
                  var b_url = ini.leadAddress + "img/" + t.blogList[i - 1].coverImg;
                  $(".b_new_article .b_new_article_pic").eq(i).css("background-image", "url(" + b_url + ")");//封面
                }
              }
              if (num < 6) {
                $(".b_new_underline").removeClass("none");
              }
            }

            //获取最新照片
            var num2 = t.photoWallList.length;
            if (num2 != 0) {
              for (var i = 0; i < num2 && i < 6; i++) {
                $(".b_new_picture img").eq(i).data("id", t.photoWallList[i].id);
                $(".b_new_picture img").eq(i).data("albumId", t.photoWallList[i].albumId);
                if (t.photoWallList[i].photoHash) {
                  var b_src = ini.leadAddress + "img/" + t.photoWallList[i].photoHash;
                  $(".b_new_picture img").eq(i).attr("src", b_src);//图片
                }
              }
            }

          }
        }
      })
    },

    //页面切换跳转
    fn1: function () {
      $("#b_RDClogo").click(function () {
        window.location.replace('http://www.onepi.top:8080/blog/html/home.html')
      })
      $(".header_list").eq(3).click(function () {
        localStorage.setItem("thirdId", a.thirdId);
        window.location.replace('http://www.onepi.top:8080/blog/html/thirdInfo.html')
      })
      $(".header_list").eq(0).click(function () {
        localStorage.setItem("thirdId", a.thirdId);
        window.location.replace('http://www.onepi.top:8080/blog/html/thirdpage.html')
      })
      $(".header_list").eq(1).click(function () {
        localStorage.setItem("thirdId", a.thirdId);
        window.location.replace('http://www.onepi.top:8080/blog/html/文章他人列表页.html')
      })
      $(".header_list").eq(2).click(function () {
        localStorage.setItem("thirdId", a.thirdId);
        window.location.replace('http://www.onepi.top:8080/blog/html/picWallOthers.html')
      })
      $(".b_recent_sign").eq(0).click(function(){
        localStorage.setItem("thirdId", a.thirdId);
        window.location.replace('http://www.onepi.top:8080/blog/html/picWallOthers.html')
      })
      $(".b_personalsetting").click(function () {
        window.location.replace('http://www.onepi.top:8080/blog/html/mypage.html')
      })
      $(".b_recent_sign").eq(1).click(function(){
        window.location.replace('http://www.onepi.top:8080/blog/html/文章他人列表页.html')
      })
      $(".b_more_txt").click(function () {
        localStorage.setItem("thirdId", a.thirdId);
        window.location.replace('http://www.onepi.top:8080/blog/html/thirdInfo.html')
      })
      $(".b_article_jump").click(function(){
        var articleID = $(this).parents(".b_new_article").data("id");
        localStorage.setItem("This_Id",articleID);
        window.location.replace('http://www.onepi.top:8080/blog/html/文章详情页.html')
      })
    },

    //粉丝关注跳转
    fn2: function () {
      $(".S_txt1").eq(0).click(function () {

        $(this).css("color", "#3479d8");
        $(".S_txt1 span").eq(0).css("color", "#3479d8");
        $(".S_txt1").eq(1).css("color", "#333");
        $(".S_txt1 span").eq(1).css("color", "#808080");
        $(".content_hide1").addClass("none");
        $(".relative_page_wide").removeClass("none");
        Assignment("全部关注", $(".relative_title"));
        a.get2();
        $(".follow_page_wide").removeClass("none");
        $(".fans_page_wide").addClass("none");
      })
      $(".S_txt1").eq(1).click(function () {
        $(this).css("color", "#3479d8");
        $(".S_txt1 span").eq(1).css("color", "#3479d8");
        $(".S_txt1").eq(0).css("color", "#333");
        $(".S_txt1 span").eq(0).css("color", "#808080");
        $(".content_hide1").addClass("none");
        $(".relative_page_wide").removeClass("none");
        Assignment("全部粉丝", $(".relative_title"));
        a.get1();
        $(".follow_page_wide").addClass("none");
        $(".fans_page_wide").removeClass("none");
      })
    },

    //获取粉丝列表
    get1: function () {
      $.ajax({
        dataType: 'json',
        type: "get",
        url: a.leadAddress + "blog/showUserFans/" + a.thirdId,
        error: function () {
          $(".warning .dec_txt").text("服务器错误");
          errorRender();
        },
        success: function (data) {
          if (data.result == "success") {
            var t = data.message;
            console.log(t);
            var num3 = t.length;
            var size = $(".b_fanss").size() - 1;
            if (num3 > size) {
              for (var i = 1; i <= num3; i++) {
                $(".fans_page_wide").append($(".b_fanss").eq(0).clone(true));
                $(".b_fanss").eq(i).removeClass("none");
                $(".b_fanss").eq(i).data("id", t[i - 1].id);
                $(".b_fanss").eq(i).data("canFollow", t[i - 1].beUpStatus);
                if (t[i - 1].beUpStatus == 0) {
                  Assignment("Following", $(".b_fanss .relative_button").eq(i));
                  $(".b_fanss .relative_button").eq(i).css({ "background-color": "#1DA1F2", "color": "#fff" });
                }
                else {
                  Assignment("Follow", $(".b_fanss .relative_button").eq(i));
                  $(".b_fanss .relative_button").eq(i).css({ "background-color": "#fff", "color": "#1DA1F2" });
                }
                Assignment(t[i - 1].username, $(".b_fanss .profileCard-name a").eq(i));
                Assignment(t[i - 1].signature, $(".b_fanss .profileCard-signature").eq(i));
                if (t[i - 1].background) {
                  var f_url = a.leadAddress + "img/" + t[i - 1].background;
                  $(".b_fanss .profileCard_waller").eq(i).css("background-image", "url(" + f_url + ")");//壁纸
                }
                if (t[i - 1].face) {
                  var f_src = a.leadAddress + "img/" + t[i - 1].face;
                  $(".b_fanss .profileCard-header img").eq(i).attr("src", f_src);//头像
                }
              }
            }
          }
        },
      });
    },

    //获取关注列表
    get2: function () {
      $.ajax({
        dataType: 'json',
        type: "get",
        url: a.leadAddress + "blog/showUserIdols/" + a.thirdId,
        error: function () {
          $(".warning .dec_txt").text("服务器错误");
          errorRender();
        },
        success: function (data) {
          if (data.result == "success") {
            var t = data.message;
            console.log(t);
            var num3 = t.length;
            var size = $(".b_follows").size() - 1;
            if (num3 > size) {
              for (var i = 1; i <= num3; i++) {
                console.log(t[i - 1].id)
                $(".follow_page_wide").append($(".b_follows").eq(0).clone(true));
                $(".b_follows").eq(i).removeClass("none");
                $(".b_follows").eq(i).data("id", t[i - 1].id);
                $(".b_follows").eq(i).data("canFollow", t[i - 1].beUpStatus);
                if (t[i - 1].beUpStatus == 0) {
                  Assignment("Following", $(".b_follows .relative_button").eq(i));
                  $(".b_follows .relative_button").eq(i).css({ "background-color": "#1DA1F2", "color": "#fff" });
                }
                else {
                  Assignment("Follow", $(".b_follows .relative_button").eq(i));
                  $(".b_follows .relative_button").eq(i).css({ "background-color": "#fff", "color": "#1DA1F2" });
                }
                Assignment(t[i - 1].username, $(".b_follows .profileCard-name a").eq(i));
                Assignment(t[i - 1].signature, $(".b_follows .profileCard-signature").eq(i));
                if (t[i - 1].background) {
                  var f_url = a.leadAddress + "img/" + t[i - 1].background;
                  $(".b_follows .profileCard_waller").eq(i).css("background-image", "url(" + f_url + ")");//壁纸
                }
                if (t[i - 1].face) {
                  var f_src = a.leadAddress + "img/" + t[i - 1].face;
                  $(".b_follows .profileCard-header img").eq(i).attr("src", f_src);//头像
                }
              }
            }
          }
        },
      })
    },

    //关注用户
    userWatch: function () {
      $(".relative_button").click(function (event) {
        event.preventDefault();
        var t = this;
        var obj = $(t).parents(".b_doubles").data("id");
        var power = $(t).parents(".b_doubles").data("canFollow");
        $.ajax({
          dataType: 'json',
          type: "get",
          url: a.leadAddress + "blog/userWatch/" + a.IDuser + "/" + obj,
          cache: false,
          processData: false,
          contentType: false,
          error: function () {
            $(".warning .dec_txt").text("服务器错误");
            errorRender();
          },
          success: function (data) {
            var result = data.result;
            if (result == "error") {
              //表示原来已关注，beupstatus由0变为1
              $(t).parents(".b_doubles").data("canFollow", "1");
              $(t).text("Follow");
              $(t).css({ "background-color": "#fff", "color": "#1DA1F2" });
              a.init();
            }
            if (result == "success"){
              //表示原来未关注，beupstatus由1变为0
              $(t).parents(".b_doubles").data("canFollow", "0");
              $(t).text("Following");
              $(t).css({ "background-color": "#1DA1F2", "color": "#fff" });
              a.init();
            }

          },

        })
      })
    },

  }
  if (localStorage.getItem("thirdId") && localStorage.getItem("IDuser")) {
    a.init();
    a.fn1();
    a.fn2();
    a.userWatch();
  }
  else {
    $(".warning .dec_txt").text("请先登录");
    errorRender();
    a.fn1();
  }
})