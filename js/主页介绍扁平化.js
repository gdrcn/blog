$(function () {
    var i = 0;
    var m = 0;
    var n = 0;
    var h = 0;
    var a = {
        a1: "#x-slider-pannel-left-carouse",
        a2: "",

        fn: function () {


            var t = this;
            if (i <= 3) {
                $("#x-slider-pannel-left-carouse").animate({"margin-left": (-i * 800) + "px"});
                i++;
                if (i == 4) {
                    $(".x-slider-dot").css("background-color", "transparent");
                    $(".x-slider-dot").eq(0).css("background-color", "black");
                } else {

                    $(".x-slider-dot").css("background-color", "transparent");
                    $(".x-slider-dot").eq(i - 1).css("background-color", "black");
                }
            } else if (i == 4) {

                $(".x-slider-dot").css("background-color", "transparent");
                $(".x-slider-dot").eq(1).css("background-color", "black");
                i = 0;
                $("#x-slider-pannel-left-carouse").css("margin-left", (-i * 800) + "px");
                i = 1;
                $("#x-slider-pannel-left-carouse").animate({"margin-left": (-i * 800) + "px"});

            }
            return t;
        }
    };
    settime1 = setInterval(a.fn, 3000);

    var b = {

        a1: "#x-slider-pannel-center1-carouse",
        a2: "",

        fn: function () {
            var t = this;
            if (m <= 3) {
                $("#x-slider-pannel-center1-carouse").animate({"margin-top": (-m * 600) + "px"});
                m++;
                if (m == 4) {
                    $(".x-slider-dot").css("background-color", "transparent");
                    $(".x-slider-dot").eq(0).css("background-color", "black")
                } else {
                    $(".x-slider-dot").css("background-color", "transparent");
                    $(".x-slider-dot").eq(m - 1).css("background-color", "black")
                }
            } else if (m == 4) {
                m = 0;
                $("#x-slider-pannel-center1-carouse").css("margin-top", (-m * 600) + "px");
                $(".x-slider-dot").css("background-color", "transparent");
                $(".x-slider-dot").eq(1).css("background-color", "black");
                m = 1;
                $("#x-slider-pannel-center1-carouse").animate({"margin-top": (-m * 600) + "px"});
            }
            return t;
        }
    };
    settime2 = setInterval(b.fn, 3000);

    var c = {
        a1: "#x-slider-pannel-center2-carouse",
        a2: "",

        fn: function () {
            var t = this;
            if (n <= 3) {
                $("#x-slider-pannel-center2-carouse").animate({"margin-top": (n * 600 - 1800) + "px"});
                n++;
                if (n == 4) {
                    $(".x-slider-dot").css("background-color", "transparent");
                    $(".x-slider-dot").eq(0).css("background-color", "black");
                } else {
                    $(".x-slider-dot").css("background-color", "transparent");
                    $(".x-slider-dot").eq(n - 1).css("background-color", "black");
                }

            } else if (n == 4) {
                $(".x-slider-dot").css("background-color", "transparent");
                $(".x-slider-dot").eq(1).css("background-color", "black");
                n = 0;
                $("#x-slider-pannel-center2-carouse").css("margin-top", (n * 600 - 1800) + "px");
                n = 1;
                $("#x-slider-pannel-center2-carouse").animate({"margin-top": (n * 600 - 1800) + "px"});

            }
            return t;
        }
    };
    settime3 = setInterval(c.fn, 3000);
    var d = {
        a1: "#x-slider-pannel-right-carouse",
        a2: "",

        fn: function () {
            var t = this;
            if (h <= 3) {
                $("#x-slider-pannel-right-carouse").animate({"margin-left": (h * 800 - 3000) + "px"});
                h++;
                if (h == 4) {
                    $(".x-slider-dot").css("background-color", "transparent");
                    $(".x-slider-dot").eq(0).css("background-color", "black");
                } else {
                    $(".x-slider-dot").css("background-color", "transparent");
                    $(".x-slider-dot").eq(h - 1).css("background-color", "black");
                }

            } else if (h == 4) {
                $(".x-slider-dot").css("background-color", "transparent");
                $(".x-slider-dot").eq(1).css("background-color", "black");
                h = 0;
                $("#x-slider-pannel-right-carouse").css("margin-left", (h * 800 - 3000) + "px");
                h = 1;
                $("#x-slider-pannel-right-carouse").animate({"margin-left": (h * 800 - 3000) + "px"});

            }
            return t;
        }
    };
    settime4 = setInterval(d.fn, 3000);

    var e = {
        b1: ".x-slider-dot",
        b2: ".x-slider-pannel",
        b3: "#x-slider-pannels-next",
        b4: "#x-slider-pannels-previous",
        b5: ".x-slider-pannel,#x-slider-pannels-next,#x-slider-pannels-previous",
        fn2: function () {
            var t = this;
            $(t.b1).mouseover(function () {
                clearInterval(settime1);
                clearInterval(settime2);
                clearInterval(settime3);
                clearInterval(settime4);
                $(t.b1).css("background", "transparent");
                $(this).css("background-color", "black");

            });
            $(t.b1).mouseout(function () {
                settime4 = setInterval(d.fn, 3000);
                settime3 = setInterval(c.fn, 3000);
                settime2 = setInterval(b.fn, 3000);
                settime1 = setInterval(a.fn, 3000);
            });
            $(t.b1).click(function () {
                var index = $(this).index();
                if (index == 3) {
                    index = 0;
                }
                i = index;
                $("#x-slider-pannel-left-carouse").animate({"margin-left": (-i * 800) + "px"});
                m = index;
                $("#x-slider-pannel-center1-carouse").animate({"margin-top": (-m * 600) + "px"});
                h = index;
                $("#x-slider-pannel-right-carouse").animate({"margin-left": (h * 800 - 3000) + "px"});
                n = index;
                $("#x-slider-pannel-center2-carouse").animate({"margin-top": (n * 600 - 1800) + "px"});

            });
            $(t.b5).mouseover(function () {

                $(t.b3).css({"display": "block", "background": "transparent"});
                $(t.b4).css({"display": "block", "background": "transparent"});

            });
            $(t.b5).mouseout(function () {
                $(t.b3).css({"display": "none", "background": "transparent"});
                $(t.b4).css({"display": "none", "background": "transparent"});

            });
            $(t.b3).click(function () {
                if (i <= 3) {
                    i++;
                    m++;
                    n++;
                    h++;
                } else if (i == 4) {
                    i = 0;
                    m = 0;
                    n = 0;
                    h = 0;

                    $("#x-slider-pannel-left-carouse").css("margin-left", (-i * 800) + "px");

                    $("#x-slider-pannel-center1-carouse").css("margin-top", (-m * 600) + "px");

                    $("#x-slider-pannel-right-carouse").css("margin-left", (h * 800 - 3000) + "px");

                    $("#x-slider-pannel-center2-carouse").css("margin-top", (n * 600 - 1800) + "px");
                    $(".x-slider-dot").css("background-color", "transparent");
                    $(".x-slider-dot").eq(1).css("background-color", "black");

                    i = 1;
                    m = 1;
                    n = 1;
                    h = 1;
                }
                if (i == 4) {
                    $(".x-slider-dot").css("background-color", "transparent");
                    $(".x-slider-dot").eq(0).css("background-color", "black");
                    i = 0;
                    m = 0;
                    n = 0;
                    h = 0;

                    $("#x-slider-pannel-left-carouse").css("margin-left", (-i * 800) + "px");

                    $("#x-slider-pannel-center1-carouse").css("margin-top", (-m * 600) + "px");

                    $("#x-slider-pannel-right-carouse").css("margin-left", (h * 800 - 3000) + "px");

                    $("#x-slider-pannel-center2-carouse").css("margin-top", (n * 600 - 1800) + "px");
                    i = 1;
                    m = 1;
                    n = 1;
                    h = 1;
                } else {
                    $(".slider-dot").css("background-color", "transparent");
                    $(".slider-dot").eq(i - 1).css("background-color", "black");
                }

                $("#x-slider-pannel-left-carouse").animate({"margin-left": (-i * 800) + "px"});

                $("#x-slider-pannel-center1-carouse").animate({"margin-top": (-m * 600) + "px"});

                $("#x-slider-pannel-right-carouse").animate({"margin-left": (h * 800 - 3000) + "px"});

                $("#x-slider-pannel-center2-carouse").animate({"margin-top": (n * 600 - 1800) + "px"});

            });
            $(t.b4).click(function () {
                if ((i > 0) && (i < 4)) {
                    i--;
                    m--;
                    n--;
                    h--;
                } else if (i == 0) {
                    i = 4;
                    m = 4;
                    n = 4;
                    h = 4;
                    $(".x-slider-dot").css("background-color", "transparent");
                    $(".x-slider-dot").eq(1).css("background-color", "black");
                    $("#x-slider-pannel-left-carouse").css("margin-left", (-i * 800) + "px");

                    $("#x-slider-pannel-center1-carouse").css("margin-top", (-m * 600) + "px");

                    $("#x-slider-pannel-right-carouse").css("margin-left", (h * 800 - 3000) + "px");

                    $("#x-slider-pannel-center2-carouse").css("margin-top", (n * 600 - 1800) + "px");
                    i = 2;
                    m = 2;
                    n = 2;
                    h = 2;
                } else if (i == 4) {
                    i = 3;
                    m = 3;
                    n = 3;
                    h = 3;
                    $("#x-slider-pannel-left-carouse").css("margin-left", (-i * 800) + "px");

                    $("#x-slider-pannel-center1-carouse").css("margin-top", (-m * 600) + "px");

                    $("#x-slider-pannel-right-carouse").css("margin-left", (h * 800 - 3000) + "px");

                    $("#x-slider-pannel-center2-carouse").css("margin-top", (n * 600 - 1800) + "px");
                    i = 2;
                    m = 2;
                    n = 2;
                    h = 2;
                }


                $("#x-slider-pannel-left-carouse").animate({"margin-left": (-i * 800) + "px"});

                $("#x-slider-pannel-center1-carouse").animate({"margin-top": (-m * 600) + "px"});

                $("#x-slider-pannel-right-carouse").animate({"margin-left": (h * 800 - 3000) + "px"});

                $("#x-slider-pannel-center2-carouse").animate({"margin-top": (n * 600 - 1800) + "px"});
                $(".x-slider-dot").css("background-color", "transparent");
                $(".x-slider-dot").eq(i).css("background-color", "black");
            })

        }
    }.fn2();
    //轮播图上的文字介绍：
    var f = {
        f1: ".x-slider-pannel",
        f2: ".x-description2,.x-triangle2",
        f3: ".x-description3,.x-triangle3",
        f4: ".x-description1,.x-triangle1",
        f5: ".x-description4,.x-triangle4",
        fn: function () {
            var t = this;
            $(t.f1).mouseover(function () {
                var index = $(this).index();
                $(this).find("img").css("opacity", "0.1");
                $(this).css("transform", "rotateY(540deg)");
                switch (index) {
                    case 0:
                        $(t.f4).css({"display": "block", "transform": "rotateY(540deg)"});
                        break;
                    case 1:
                        $(t.f2).css({"display": "block", "transform": "rotateY(540deg)"});
                        break;
                    case 2:
                        $(t.f3).css({"display": "block", "transform": "rotateY(540deg)"});
                        break;
                    case 3:
                        $(t.f5).css({"display": "block", "transform": "rotateY(540deg)"});
                        break;
                }
            });
            $(t.f1).mouseout(function () {
                var index = $(this).index();
                $(this).css("transform", "rotateY(0deg)");
                $(this).find("img").css("opacity", "1");
                switch (index) {
                    case 0:
                        $(t.f4).css({"display": "none", "transform": "rotateY(0deg)"});
                        break;
                    case 1:
                        $(t.f2).css({"display": "none", "transform": "rotateY(0deg)"});
                        break;
                    case 2:
                        $(t.f3).css({"display": "none", "transform": "rotateY(0deg)"});
                        break;
                    case 3:
                        $(t.f5).css({"display": "none", "transform": "rotateY(0deg)"});
                        break;
                }
            })
        }

    }.fn();
    var f = {
        f1: ".x-name,.x-email,.x-text",
        f2: ".x-name",
        f3: ".x-email",
        f4: ".x-text",
        fn1: function () {

            var t = this;
            $(t.f2).focus(function () {
                $(t.f2).css({"background": "transparent", "color": "white"});
            });
            $(t.f2).blur(function () {
                $(t.f2).css({"background": "transparent", "color": "white"});
            });
            $(t.f3).focus(function () {
                $(t.f3).css({"background": "transparent", "color": "white"});
            });
            $(t.f3).blur(function () {
                $(t.f3).css({"background": "transparent", "color": "white"});
            });
            $(t.f4).focus(function () {
                $(t.f4).css({"background": "transparent", "color": "white"});
            });
            $(t.f4).blur(function () {
                $(t.f4).css({"background": "transparent", "color": "white"});
            })
        }
    }.fn1();
    var g = {
        g1: ".x-head",
        g2: ".x-right_hand",
        g3: ".x-left_part",
        g4: ".x-center_part",
        g5: ".x-right_part",
        gn1: function () {
            var t = this;

            $(window).scroll(function () {
                if ($(window).scrollTop() > 1700) {
                    $(t.g1).css({"margin-top": "0", "opacity": "1"});
                    $(t.g2).css({"margin-left": "50px", "opacity": "1"});
                }
                if ($(window).scrollTop() < 1700) {
                    $(t.g1).css({"margin-top": "-100px", "opacity": "0"});
                    $(t.g2).css({"margin-left": "80px", "opacity": "0"});
                }
                if ($(window).scrollTop() > 2000) {
                    $(t.g3).css({"margin-left": "0", "opacity": "1"});
                    $(t.g4).css("opacity", "1");
                    $(t.g5).css({"opacity": "1", "transform": "rotateY(0deg)"});
                }
                if ($(window).scrollTop() < 2000) {
                    $(t.g3).css({"margin-left": "-300px", "opacity": "0"});
                    $(t.g4).css("opacity", "0");
                    $(t.g5).css({"opacity": "0", "transform": "rotateY(540deg)"});
                }
            });
        }
    }.gn1();


});
    