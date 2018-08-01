$(function () {

  window.abc = function () {

    var infoEdit = {

      UserID: localStorage.getItem("IDuser"),
      leadAddress: "http://192.168.43.6:8080/blog/",

      pre_header: $("#w_myHead,#b_myHead").attr("style"),
      pre_waller: $(".wallpaper").attr("style"),
      //关闭更改
      bn2: function (event) {
        // event.stopPropagation();
        $("#w_myHead,#b_myHead").attr("style", infoEdit.pre_header);//profile_header
        $(".wallpaper").attr("style", infoEdit.pre_waller);//profile_waller
      },
      bn3: function () {
        $(".over_content").addClass("none");
        $(".page_container").removeClass("page_container6");
        $(".page_outer").removeClass("page_outer6");
        $("#w_myHead").removeClass("w_myHead_change");
        $(".hChange_button").addClass("none");
        $(".over_wallpaper").addClass("none");
        $(".b_UserEdit").addClass("none");
        setTimeout(function () { $(".b_UserActions").addClass("none") }, 100);
      },
      //点击事件
      bn1: function () {
        //更改信息悬浮页
        $(".b_more_txt span,.b_change_name").click(function () {
          $(".over_content").removeClass("none");
          $(".page_container").addClass("page_container6");
          $(".page_outer").addClass("page_outer6");
          $("#w_myHead").addClass("w_myHead_change");
          $(".hChange_button").removeClass("none");
          $(".over_wallpaper").removeClass("none");
          $(".b_UserActions").removeClass("none");
          $(".b_UserEdit").removeClass("none");
          $(".b_UserEdits1").focus();
        })
        //取消更改
        $(".UserAction_cancel").click(function () {
          infoEdit.bn2();
          infoEdit.bn3();
        });
        //更改头像
        $(".hChange_button").click(function () {
          $(".w_myHead_change").css("opacity", "1");
          $(".choose-header-container").eq(0).toggleClass("none");
          $(".choose-header-container").eq(1).addClass("none");
        })
        //更改背景
        $(".wChange_button").click(function () {
          $(".choose-header-container").eq(1).toggleClass("none");
          $(".choose-header-container").eq(0).addClass("none");
        })
        //取消更改背景
        $(".wChange_cancel").click(function () {
          $(".choose-header-container").addClass("none");
        })
        //取消更改头像
        $(".hChange_cancel").click(function () {
          $(".choose-header-container").addClass("none");
        })
        $(".wChange_input").mouseenter(function () {
          $(".wChange_remove1").css({ background: "#34a8f0", color: "#fff" })
        })
        $(".wChange_input").mouseout(function () {
          $(".wChange_remove1").css({ background: "#fff", color: "#66757f" })
        })
        $(".hChange_input").mouseenter(function () {
          $(".wChange_remove2").css({ background: "#34a8f0", color: "#fff" })
        })
        $(".hChange_input").mouseout(function () {
          $(".wChange_remove2").css({ background: "#fff", color: "#66757f" })
        })
      },
      //关闭弹出的小窗口
      closeEdit: function () {
        $(document).click(function () {
          $(".choose-header-container").addClass("none");
        });
        $(".stop").click(function (event) {
          event.stopPropagation();
        })
      },
      //修改图片
      changePic: function () {
        function handleFileSelect(evt) {
          var t = this;
          var files = evt.target.files; // FileList object
          // Loop through the FileList and render image files as thumbnails.
          for (var i = 0, f; f = files[i]; i++) {
            console.log(files[i])
            // Only process image files.
            if (!f.type.match('image.*')) {
              continue;
            }
            var reader = new FileReader();
            // Closure to capture the file information.
            reader.onload = (function (theFile) {
              return function (e) {
                // Render thumbnail.
                var url = e.target.result;
                if (t == document.getElementById('hChange_input')) {
                  $("#w_myHead,#b_myHead").css("background-image", "url(" + url + ")");//profile_header
                }
                if (t == document.getElementById('wChange_input')) {
                  $(".wallpaper").css("background-image", "url(" + url + ")");//wallpaper
                }
                // document.getElementById('list').insertBefore(span, null);
              };
            })(f);

            // Read in the image file as a data URL.
            reader.readAsDataURL(f);
          }
        }

        document.getElementById('hChange_input').addEventListener('change', handleFileSelect, false);
        document.getElementById('wChange_input').addEventListener('change', handleFileSelect, false);
      },
      // 更改信息
      saveInfo: function () {
        $(".UserAction_save").click(function () {
          var key = [1, 1, 1];
          var oMyForm = new FormData();//信息
          var oMyForm2 = new FormData();//头像
          var oMyForm3 = new FormData();//背景

          oMyForm.append("id", infoEdit.UserID);
          oMyForm.append("username", $(".b_UserEdits").eq(0).val());
          oMyForm.append("sex", $(".b_UserEdits").eq(1).val());
          oMyForm.append("born", $(".b_UserEdits").eq(2).val());
          oMyForm.append("myblog", $(".b_UserEdits").eq(3).val());
          oMyForm.append("signature", $(".b_UserEdits").eq(4).val());
          oMyForm.append("direction", $(".b_UserEdits").eq(5).val());
          oMyForm.append("school", $(".b_UserEdits").eq(6).val());
          oMyForm.append("adress", $(".b_UserEdits").eq(7).val());
          oMyForm.append("email", $(".b_UserEdits").eq(8).val());
          oMyForm.append("phone", $(".b_UserEdits").eq(9).val());
          oMyForm.append("qq", $(".b_UserEdits").eq(10).val());
          oMyForm.append("wechat", $(".b_UserEdits").eq(11).val());

          if ($("#hChange_input")[0].files[0]) {
            oMyForm2.append("myFaceImg", $("#hChange_input")[0].files[0])
            console.log(oMyForm2);
            $.ajax({
              dataType: 'json',
              type: "post",
              url: infoEdit.leadAddress + "blog/updateFaceImg",
              data: oMyForm2,
              cache: false,
              processData: false,
              contentType: false,
              error: function () {
                $(".warning .dec_txt").text("服务器错误");
                key[0] = 0;
                errorRender();
              },
              success: function (data) {
                if(data.result == "error"){
                  $(".lose .dec_txt").text("更改头像失败");
                  failRender();
                }
                else{
                if (key[0] + key[1] + key[2] == 3) {
                  $(".success .dec_txt").text("更改头像成功");
                  successRender();
                  infoEdit.bn3();
                }
              }
              },
            })
          }

          if ($("#wChange_input")[0].files[0]) {
            oMyForm3.append("file", $("#wChange_input")[0].files[0])
            console.log(oMyForm3);
            $.ajax({
              dataType: 'json',
              type: "post",
              url: infoEdit.leadAddress + "blog/imgUpload",
              data: oMyForm3,
              cache: false,
              processData: false,
              contentType: false,
              error: function () {
                $(".warning .dec_txt").text("服务器错误");
                key[1] = 0;
                errorRender();
              },
              success: function (data) {
                if(data.result == "error"){
                  $(".lose .dec_txt").text("更改背景失败");
                  failRender();
                }
                else{
                if (key[0] + key[1] + key[2] == 3) {
                  $(".success .dec_txt").text("更改背景成功");
                  successRender();
                  infoEdit.bn3();
                }
              }
              },
            })
          }
          $.ajax({
            dataType: 'json',
            type: "post",
            url: infoEdit.leadAddress + "blog/updateUserInfo",
            data: oMyForm,
            cache: false,
            processData: false,
            contentType: false,
            error: function () {
              $(".warning .dec_txt").text("服务器错误");
              key[2] = 0;
              errorRender();
            },
            success: function (data) {
              if(data.result == "error"){
                $(".lose .dec_txt").text("更改信息失败");
                failRender();
              }
              else{
              if (key[0] + key[1] + key[2] == 3) {
                $(".success .dec_txt").text("更改信息成功");
                successRender();
                infoEdit.bn3();
              }
            }
            },
          })
          if (key[0] + key[1] + key[2] == 3) {
          window.myLib.a.init();
          }

        })
      }
    }

    infoEdit.bn1();
    infoEdit.closeEdit();
    infoEdit.changePic();
    infoEdit.saveInfo();

  }



})