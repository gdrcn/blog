window.onload = function () {
    new_element = document.createElement("script");
    new_element.setAttribute("type", "text/javascript");
    new_element.setAttribute("src", "../js/changalert.js");// 在这里引入了changealert.js
    document.body.appendChild(new_element);

    if (localStorage.getItem("IDuser")) {
        $("#w_welcomeStart").text("退出登录");
        $("#w_welcomeStart").click(function () {
            localStorage.removeItem("IDuser");
        })
    }
    else {
        //页面登录框效果
        var w_homePage = {
            toSign: function () {
                $("div#w_welcomeStart").click(function () {
                    $("div#w_welcomeStart").animate({left: "-500%"}, 1024);
                    $("div#w_welcome").animate({left: "30%"}, 512, function () {
                    });
                    $("div#w_login").fadeIn(512);
                })
            },
            signIn: function () {
                $("div#w_signIn").click(function () {
                    var account = $("input#w_accountInput").val();
                    var password = $("input#w_passwordInput").val();

                })
            }
        };
        w_homePage.toSign();
        w_homePage.signIn();


//登录接口
        var a = {
            leadAddress: "http://192.168.43.6:8080/blog/",

            // 设置cookie
            setCookie: function (c_name, value, expiremMinutes) {
                var exdate = new Date();
                exdate.setTime(exdate.getTime() + expiremMinutes * 60 * 1000);  //设置日期毫秒数
                document.cookie += c_name + "=" + escape(value) + ((expiremMinutes == null) ? "" : ";expires=" + exdate.toGMTString());
            },
            // 读取cookie
            getCookie: function (c_name) {
                if (document.cookie.length > 0) {
                    var c_start = document.cookie.indexOf(c_name + "=");  //找到c_name=首次出现的地方
                    if (c_start != -1)  //说明c_name=存在
                    {
                        c_start = c_start + c_name.length + 1;  //找到=的下一个位置
                        var c_end = document.cookie.indexOf(";", c_start);  //从上一步的位置开始查找，直到";"的位置
                        if (c_end == -1) {
                            c_end = document.cookie.length;
                        }
                        return unescape(document.cookie.substring(c_start, c_end));
                    }
                }
                return "";
            },
            // 删除cookie
            delCookie: function (c_name) {
                var exp = new Date();
                exp.setTime(exp.getTime() - 1);
                var cval = this.getCookie(c_name);
                if (cval != null) {
                    document.cookie = c_name + "=" + cval + ";expires=" + exp.toGMTString();
                }
            },

            fn1: function () {
                var t = this;

                //初始化登录窗口
                if (window.localStorage) {
                    $("#w_accountInput").val(localStorage.getItem("lastLoginName"));//显示上次记住密码时的登录账号
                    var pas = localStorage.getItem($.trim($("#w_accountInput").val()));//获取账号对应的密码值
                    $("#w_passwordInput").val(pas);  //赋值给password
                    if (pas == "" || pas == undefined || pas == null) {
                        $("#passwordkeeping").prop("checked", false)
                    } else {
                        $("#passwordkeeping").prop("checked", true)
                    }
                }

                //获取密码
                $("#w_accountInput").keyup(function () {
                    if (window.localStorage) {
                        var pas = localStorage.getItem($.trim($("#w_accountInput").val()));//获取账号对应的密码值
                        $("#w_passwordInput").val(pas);  //赋值给password
                        if (pas == "" || pas == undefined || pas == null) {
                            $("#passwordkeeping").prop("checked", false)
                        } else {
                            $("#passwordkeeping").prop("checked", true)
                        }
                    }
                    else {
                        var pas = getCookie($.trim($("#account").val()));//获取cookie
                        $("#password").val(pas);  //赋值给password
                    }
                });


                //登录接口
                $("#w_signIn").click(function (event) {
                    event.preventDefault();

                    if ($("#w_accountInput").val() != "" && $("#w_passwordInput").val() != "") {
                        var i = $("#w_accountInput").val();
                        var p = $("#w_passwordInput").val();
                        $.ajax({
                            url: t.leadAddress + "blog/login",
                            type: "post",
                            dataType: 'json',
                            data: {username: i, password: p},
                            beforeSend: function () {
                                if ($("#passwordkeeping").prop('checked') == true) {
                                    if (window.localStorage) {
                                        localStorage.setItem("lastLoginName", $("#w_accountInput").val()); //记住账号
                                        localStorage.setItem($("#w_accountInput").val(), $("#w_passwordInput").val())//记住密码
                                    }
                                    else {
                                        if (t.getCookie($("#w_accountInput").val())) {
                                            t.delCookie($("#w_accountInput").val());
                                        }
                                        t.setCookie($("#w_accountInput").val(), $("#w_passwordInput").val(), 1440);
                                    }
                                }//记住密码
                                else {
                                    if (window.localStorage) {
                                        localStorage.setItem($("#w_accountInput").val(), "")
                                    }//不记住密码
                                    else {
                                        delCookie($("#w_accountInput").val());
                                    }
                                }
                                loadRender();
                            },
                            error: function () {
                                $(".warning .dec_txt").text("服务器错误");
                                setTimeout("finishLoadingRender4()", 100);
                            },
                            success: function (data) {
                                console.log(data);
                                var result = data.result;
                                var message = data.message;
                                if (result == "success") {
                                    setTimeout("finishLoadingRender1()", 100);
                                    localStorage.setItem("IDuser", message);//记录登录状态
                                    window.location.replace('file:///F:/gittes/rdc/Michelangelo/html/mypage.html')
                                }
                                if (result == "error") {
                                    $(".lose .dec_txt").text(data.message);
                                    setTimeout("finishLoadingRender2()", 100);
                                }

                            }
                        })
                    }
                    else {
                        $(".warning .dec_txt").text("请输入账号和密码");
                        errorRender();
                    }
                });

                //注册接口
                //验证
                $(".w_loginInput").blur(function () {
                    var password;
                    //输入验证
                    if ($(this).val() == "") {
                        $(this).siblings(".login_after_fail").show();
                        $(this).siblings(".login_after_right").hide();
                    }
                    else {
                        $(this).siblings(".login_after_fail").hide();
                        $(this).siblings(".login_after_right").show();
                    }
                    //邮箱
                    if ($(this).is("#b_emailInput")) {
                        //使用正则表达式验证邮箱
                        if ((!/^[a-z0-9]+@[a-z0-9]{2,3}(\.[a-z]{2,3}){1,2}$/.test(this.value))) {
                            $(this).siblings(".login_after_fail").show();
                            $(this).siblings(".login_after_right").hide();
                        }
                        else {
                            $(this).siblings(".login_after_fail").hide();
                            $(this).siblings(".login_after_right").show();
                        }
                    }

                    //确认密码
                    if ($(this).is("#b_rePasswordInput")) {
                        if ($(this).val() == $("#b_passwordInput").val()) {
                            $(this).siblings(".login_after_fail").hide();
                            $(this).siblings(".login_after_right").show();
                        }
                        else {
                            $(this).siblings(".login_after_fail").show();
                            $(this).siblings(".login_after_right").hide();
                        }
                    }
                });

                //获取验证码
                $("#b_signVerification").click(function (event) {
                    event.preventDefault();
                    var b = [$("#b_accountInput").val(), $("#b_emailInput").val(), $("#b_passwordInput").val(), $("#b_rePasswordInput").val()];
                    $.ajax({
                        url: t.leadAddress + "blog/registe",
                        dataType: 'json',
                        type: 'post',
                        data: {username: b[0], password: b[2], confirmPassword: b[3], email: b[1]},
                        beforeSend: function () {
                            loadRender();
                        },
                        error: function () {
                            $(".warning .dec_txt").text("服务器错误");
                            setTimeout("finishLoadingRender4()", 100);
                        },
                        success: function (data) {
                            var message = data.message;
                            var result = data.result;
                            if (result == "success") {
                                $("#b_Verification").removeAttr("readonly");
                                $(".success .dec_txt").text("成功发送验证码");
                                setTimeout("finishLoadingRender3()", 100);
                                $(".b_login3_wrap").hide();
                                $(".b_login4_wrap").fadeIn(1000);
                            }
                            else {
                                $(".lose .dec_txt").text(message);
                                setTimeout("finishLoadingRender2()", 100);
                            }
                        }
                    })
                });

                //注册
                $("#b_signRegister").click(function (event) {
                    event.preventDefault();
                    $.ajax({
                        url: t.leadAddress + "blog/validate",
                        dataType: 'json',
                        type: 'post',
                        data: {checkcode: $("#b_Verification").val()},
                        error: function () {
                            $(".warning .dec_txt").text("服务器错误");
                            setTimeout("finishLoadingRender4()", 100);
                        },
                        success: function (data) {
                            var message = data.message;
                            var result = data.result;
                            if (result == "success") {
                                $(".success .dec_txt").text("注册成功");
                                successRender();
                                $("#b_Verification").attr("readonly", "true");
                                $(".b_login4_wrap").hide();
                                $(".b_login_wrap").fadeIn(1000);
                            }
                            else {
                                $(".lose .dec_txt").text(message);
                                failRender();
                            }
                        }
                    })
                });

                $("#w_loginAlert").click(function () {
                    $(".b_login_wrap").hide();
                    $(".b_login2_wrap").fadeIn(1000);
                });
                $("#b_signNext").click(function () {
                    $(".b_login2_wrap").hide();
                    $(".b_login3_wrap").fadeIn(1000);
                });
                $("#b_signlast1").click(function () {
                    $(".b_login_wrap").fadeIn(1000);
                    $(".b_login2_wrap").hide();
                });
                $("#b_signlast2").click(function () {
                    $(".b_login3_wrap").hide();
                    $(".b_login2_wrap").fadeIn(1000);
                });
                $("#b_signlast3").click(function () {
                    $(".b_login4_wrap").hide();
                    $(".b_login3_wrap").fadeIn(1000);
                })

            }
        };
        a.fn1();
    }

//插入最新动态


};