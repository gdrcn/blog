$(function () {
  locallist = {
    Local_user_Id: localStorage.getItem("IDuser"),//存放用户的id
    Local_connection: "http://www.onepi.top:8080/blog",
    Local_category_things: "前端",
    PageCount: 0,
    Local_list: 0,
    userid:0,//存放修改用blogid
    userid_2:localStorage.getItem("thirdId")//主页用于存放他人的用户id
  }

  var a = {
    a1: ".x-tag_only",
    a2: ".x-real_List_of_articles_head_line h2",
    a3: ".x-Sidebar",
    a4: ".x-List_of_articles",
    a5: ".x-Each_page_2",
    a6: ".x-Each_page",
    a7: ".x-slider_part_1",
    a8: "x-slider_part_1_click",
    a9: ".x-tag_only",
    a10: "x-tag_only_click",

    //文章页面的hover鼠标事件js
    fn: function () {

      var t = this;

      $(t.a1).mouseover(function () {
        $(this).css("background-color", "#362be2");
      })
      $(t.a1).mouseout(function () {
        $(this).css("background-color", "blueviolet");
      })

      $(t.a1).mousedown(function () {
        $(this).css({
          "background-color": "#362be2",
          "width": "28px",
          "height": "17px"
        });
      })
      $(t.a1).mouseup(function () {
        $(this).css({
          "background-color": "blueviolet",
          "width": "26px",
          "height": "15.27px"
        });
      })

      var at = document.getElementsByClassName("x-Sidebar")[0].offsetTop;

      $(window).scroll(function () {
        if ($(window).scrollTop() + 60 > at) {
          $(t.a3).addClass("x-Sidebar_1");
          $(t.a4).addClass("x-List_of_articles_1");
        } else {
          $(t.a3).removeClass("x-Sidebar_1");
          $(t.a4).removeClass("x-List_of_articles_1")

        }
        $(t.a3).css({
          "height": at + $(window).scrollTop()
        })
      })
    },
    fn1: function () {
      var t = this;

      /* $(t.a6).mousedown(function () {
         $(this).addClass("x-Each_page_2");
       })
       $(t.a6).mouseup(function () {
         $(this).siblings().removeClass("x-Each_page_2");
       })*/
      $(t.a6).mouseover(function () {
        $(t.a6).removeClass("x-Each_page_1");
        $(this).addClass("x-Each_page_1");
      })
      /*$(t.a6).click(function () {
        $(t.a6).removeClass("x-Multi_page_2");
        $(t.a6).removeClass("x-Multi_page_1");
        $(this).addClass("x-Multi_page_1");
        $(this).addClass("x-Multi_page_2");
        var mm = $(this);

        $(t.a6).on("mouseover", mm, function () {
          $(t.a6).removeClass("x-Multi_page_1");
        })

      })*/

    },
    fn2: function () {
      var t = this;
      $(t.a7).click(function () { //点击事件左边侧栏还有标题的Tags
        $(t.a7).removeClass(t.a8);
        $(this).addClass(t.a8);
        var index = $(this).index();
        $(t.a9).removeClass(t.a10);
        $(t.a9).eq(index).addClass(t.a10);
      })

    }

  };
  a.fn();
  a.fn1();
  a.fn2();

  //装饰用js结束
  //ajax实例化发送表单开始
  //首先是一开始打开列表页的前端类别的第一页的显示

  b = {


    Send_a_page_number_first: function (userid, page_numbers) {
      var t = this;

      PageCount_1 = 0;
      $.ajax({
        type: "get", //方法类型
        dataType: "json", //预期服务器返回的数据类型
        async: false,
        url: locallist.Local_connection + "/blog/blogByUser/" +userid + "/" + page_numbers, //url
        error: function () {
          alert("gg");
        },
        success: function (data, status, xhr) {
          $(".x-real_List_of_articles_only").css("display", "block"); //让列表中十个项先全部显示
          var mini = data.message;
          console.log(data);
          locallist.Local_list = data;

          After_opening(mini.blogBeans);
          var m = 0;
          for (var i = 0; i < 5; i++) {
            if ($(".x-tag_only").eq(i).hasClass("x-tag_only_click")) {
              m = 1;
            }
          }
          if (m != 1) {
            $(".x-tag_only").eq(0).addClass("x-tag_only_click");
            $(".x-slider_part_1").eq(0).addClass("x-slider_part_1_click");
          }
          var messageNum = mini.blogBeans;
          var index = messageNum.length;
          if (index < 10) {
            for (var i = index; i < 10; i++) {
              $(".x-real_List_of_articles_only").eq(i).css("display", "none"); //将不必要的项隐藏
            }
          }

        }

      })

      function After_opening(blog_numbers, PageCount) {

        for (var i = 0; i < blog_numbers.length; i++) {
          var blognum = blog_numbers[i].blog;
          var str = blognum.title;
          str = str.replace(/&lt;/g, '<');
          str = str.replace(/&gt;/g, '>');
          str = str.replace(/&acute;/g, "\'");
          str = str.replace(/&quot;/g, '"');
          str = str.replace(/&brvbar;/g, "\n");
          blognum.title = str;

          $(".x-real_List_of_articles_head_line").eq(i).children("h2").html(blognum.title); //更改标题
          $(".x-real_List_of_articles_date").eq(i).children("div").html(changedata(blognum.finishTime)); //更改完成时间
          var peoplename = blognum.userBean; //引用进userBean里面
          $(".x-real_List_of_articles_people").eq(i).children("div").html(peoplename.username); //作者名字
          if (blognum.coverImg != "") { //博客封面图url拼接
            var url_blog_coverimg = locallist.Local_connection + "/img/" + blognum.coverImg;
            $(".x-real_List_of_articles_logo").eq(i).html("");
            $(".x-real_List_of_articles_logo").eq(i).css({
              "background": "url(" + url_blog_coverimg + ") ",
              "background-size": "80px 80px"
            });
          }
          $(".x-real_List_of_articles_commend").eq(i).children("div").html(blog_numbers[i].commentCount); //评论数
          $(".x-real_List_of_articles_viewed").eq(i).children("div").html(blog_numbers[i].collectionCount);
          $(".x-real_List_of_articles_liked").eq(i).children("div").html(blog_numbers[i].upCount);
          if (blog_numbers[i].isUp == true) { //判断之后写点赞数
            $(".x-real_List_of_articles_liked").eq(i).children("i").removeClass("fa-heart-o").addClass("fa-heart").addClass("x-color_red");
            $(".x-real_List_of_articles_liked").eq(i).children("i").removeClass("fa-heart").addClass("fa-heart-o").addClass("x-color_red");

          } else {
            $(".x-real_List_of_articles_liked").eq(i).children("i").removeClass("fa-star").addClass("fa-star-o").removeClass("x-color_red");
          }
          if (blog_numbers[i].isCollect == true) { //判断之后携手藏书写在小眼睛那里
            $(".x-real_List_of_articles_viewed").eq(i).children("i").removeClass("fa-star-o").addClass("fa-star").addClass("x-color_red");

          } else {
            $(".x-real_List_of_articles_viewed").eq(i).children("i").removeClass("x-color_red");
          }
        }

      }

      function changedata(blog_time) {

        var dating = blog_time.split(" ")[0];

        var month = dating.split("-")[1];
        var year = dating.split("-")[0];
        var day = dating.split("-")[2];
        switch (month) {
          case "01":
            month = "January";
            break;
          case "02":
            month = "February";
            break;
          case "03":
            month = "Match";
            break;
          case "04":
            month = "April";
            break;
          case "05":
            month = "May";
            break;
          case "06":
            month = "June";
            break;
          case "07":
            month = "July";
            break;
          case "08":
            month = "August";
            break;
          case "09":
            month = "Septempber";
            break;
          case "10":
            month = "Obctober";
            break;
          case "11":
            month = "November";
            break;
          case "12":
            month = "December";
            break;

        }

        return month + " " + day + ", " + year; //拼接！！！    
      }

    }


  }

  b.Send_a_page_number_first(locallist.userid_2, 0);




  //翻页操作

  c = {
    c1: ".x-Previous_page",
    c2: ".x-next_page",
    c3: ".x-Each_page",
    c4: "x-Dipaly",
    c5: "x-Each_page_2",
    c6: 1, //当前页

    Paging_first: function (locallist_Local_list) {
      var t = this;
      var message = locallist_Local_list;

      var PageCount_1 = message.message;

      var PageCount = PageCount_1.blogCount;
      PageCount = Math.ceil(PageCount / 10);
      $(t.c3).eq(2).html(3);
      $(t.c3).eq(1).html(2);
      $(t.c3).eq(0).html(1);
      $(t.c3).eq(3).html(4);
      $(t.c3).eq(4).html(5);
      $(t.c3).removeClass(t.c4);
      for (var i = 0; i < 5; i++) { //给那个档期那页面的按钮标颜色
        if (parseInt($(t.c3).eq(i).html()) == t.c6) {

          $(t.c3).eq(i).addClass(t.c5);
        }
      }
      select_up_and_down();
      if (Math.ceil(PageCount) <= 5) { //如果总页数小于或者等于5
        $(t.c3).removeClass(t.c4);
        for (var i = Math.ceil(PageCount); i < 5; i++) {
          $(t.c3).eq(i).addClass(t.c4);
          $(t.c3).eq(i).html(i + 1);

        }
      }

      $(t.c3).click(function () { //首先是中间的按钮的点击事件
        t.c6 = parseInt($(this).html());

        $(t.c3).removeClass(t.c5);
        if (t.c6 < 5) { //总体分为大于5页和小于五页判断
          $(t.c3).eq(2).html(3);
          $(t.c3).eq(1).html(2);
          $(t.c3).eq(0).html(1);
          $(t.c3).eq(3).html(4);
          $(t.c3).eq(4).html(5);


        } else if ((t.c6 >= 5) && (t.c6 <= PageCount - 4)) {
          $(t.c3).eq(2).html(t.c6);
          $(t.c3).eq(1).html(t.c6 - 1);
          $(t.c3).eq(0).html(t.c6 - 2);
          $(t.c3).eq(3).html(t.c6 + 1);
          $(t.c3).eq(4).html(t.c6 + 2);

        } else if ((t.c6 >= 5) && (t.c6 > PageCount - 4)) {
          $(t.c3).eq(2).html(PageCount - 2);
          $(t.c3).eq(1).html(PageCount - 3);
          $(t.c3).eq(0).html(PageCount - 4);
          $(t.c3).eq(3).html(PageCount - 1);
          $(t.c3).eq(4).html(PageCount);

        }
        for (var i = 0; i < 5; i++) { //给那个档期那页面的按钮标颜色
          if (parseInt($(t.c3).eq(i).html()) == t.c6) {
            $(t.c3).eq(i).addClass(t.c5);
          }
        }
        select_up_and_down();
        b.Send_a_page_number_first(locallist.userid_2, t.c6-1);
      })
      //点击上一个页面
      $(t.c1).click(function () {
        t.c6 = t.c6 - 1;
        if (t.c6 <= 5) { //总体分为大于5页和小于五页判断
          $(t.c3).eq(2).html(3);
          $(t.c3).eq(1).html(2);
          $(t.c3).eq(0).html(1);
          $(t.c3).eq(3).html(4);
          $(t.c3).eq(4).html(5);
        } else if (t.c6 < 5) {


        } else if (t.c6 > 5) {
          $(t.c3).eq(2).html(t.c6);
          $(t.c3).eq(1).html(t.c6 - 1);
          $(t.c3).eq(0).html(t.c6 - 2);
          $(t.c3).eq(3).html(t.c6 + 1);
          $(t.c3).eq(4).html(t.c6 + 2);

        }
        for (var i = 0; i < 5; i++) { //给那个档期那页面的按钮标颜色
          if (parseInt($(t.c3).eq(i).html()) == t.c6) {

            $(t.c3).removeClass(t.c5);
            $(t.c3).eq(i).addClass(t.c5);

          }
        }
        select_up_and_down()
        b.Send_a_page_number_first(locallist.userid_2, t.c6-1);
      })
      $(t.c2).click(function () { //点击下一页的时候发生的事情
        t.c6 = t.c6 + 1; //当前页面变成下一页
        if (t.c6 < 5) {

        } else if ((t.c6 >= 5) && (t.c6 + 4 < PageCount)) { //如果当前页面不是最后五个页面的话
          $(t.c3).eq(2).html(t.c6);
          $(t.c3).eq(1).html(t.c6 - 1);
          $(t.c3).eq(0).html(t.c6 - 2);
          $(t.c3).eq(3).html(t.c6 + 1);
          $(t.c3).eq(4).html(t.c6 + 2);
        } else if ((t.c6 >= 5) && (t.c6 + 4 >= PageCount)) { //如果当前页面是最后五个页面的话
          $(t.c3).eq(2).html(PageCount - 2);
          $(t.c3).eq(1).html(PageCount - 3);
          $(t.c3).eq(0).html(PageCount - 4);
          $(t.c3).eq(3).html(PageCount - 1);
          $(t.c3).eq(4).html(PageCount);
        }
        for (var i = 0; i < 5; i++) { //给那个档期那页面的按钮标颜色
          if (parseInt($(t.c3).eq(i).html()) == t.c6) {
            $(t.c3).removeClass(t.c5);
            $(t.c3).eq(i).addClass(t.c5);
          }
        }
        select_up_and_down()
        b.Send_a_page_number_first(locallist.userid_2, t.c6-1);
      })

      function select_up_and_down() {
        if ((t.c6 == 1) || (PageCount == 0)) { //如果当前页到第一页的话那么就将上一页的按钮隐藏
          $(t.c1).addClass(t.c4);
        } else {
          $(t.c1).removeClass(t.c4);
        }

        if ((t.c6 == PageCount) || (PageCount == 0)) { //如果当前页到最后一页的话那么就将下一页的按钮隐藏
          $(t.c2).addClass(t.c4);
        } else {
          $(t.c2).removeClass(t.c4);
        }
      }


    }
  }
  c.Paging_first(locallist.Local_list);

  //翻页操作结束

  var d = {
    d1: ".x-classification div",


    Sidebar_jump: function () {
      var t = this;
      $(t.d1).click(function () {

        var index = $(this).index();


        switch (index) {
          case 0:
            b.Send_a_page_number_first("前端", 0), locallist.Local_category_things = "前端";
            c.Paging_first(locallist.Local_list);
            break;
          case 1:
            b.Send_a_page_number_first("后台", 0), locallist.Local_category_things = "后台";
            c.Paging_first(locallist.Local_list);
            break;
          case 2:
            b.Send_a_page_number_first(locallist.userid_2, 0), locallist.Local_category_things = "安卓";
            c.Paging_first(locallist.Local_list);
            break;
          case 3:
            b.Send_a_page_number_first("大数据", 0), locallist.Local_category_things = "大数据";
            c.Paging_first(locallist.Local_list);
            break;
          case 4:
            b.Send_a_page_number_first("热门", 0), locallist.Local_category_things = "热门";
            c.Paging_first(locallist.Local_list);
            break;
        }
      })

      function Sidebar_jump() {

      }
    }
  }
  d.Sidebar_jump();
  var e = {
    e2: ".x-real_List_of_articles_head_line",
    e1: function () {
      var t = this;
      $(t.e2).click(function () {

        var index = $(this).parents(".x-real_List_of_articles_only").index();
        var index_thingking = locallist.Local_list.message;
        var index_thinking_thinking = index_thingking.blogBeans[index];
        console.log(index);
        console.log(index_thinking_thinking);
        var blog_id_first = index_thinking_thinking.blog;
        var blog_id = blog_id_first.id;
        
        localStorage.setItem("This_Id", blog_id);
        $.ajax({
          type: "get",
          dataType: "json",
          url: locallist.Local_connection + "/blog/blog/" + blog_id + "/0",
          success: function (data, xhr) {
            
            var obj = JSON.stringify(data);
            localStorage.setItem("This_Id_article", obj);
            console.log(data);
            alert("ok");
            window.location.replace("http://www.onepi.top:8080/blog/html/%E6%96%87%E7%AB%A0%E8%AF%A6%E6%83%85%E9%A1%B5.html");//文章重定向跳转页面
          },
          error: function () {
            alert("再见");
          }
        })

      })


    }
  }
  e.e1();
  var r ={
    r1:".x-home_page",
    r2:0,
    r3:function(){
      var t = this;
      $(t.r1).click(function(){
        window.location.replace("http://www.onepi.top:8080/blog/html/home.html");//跳转到首页__要修改
      })
    }
  }
  r.r3();


});