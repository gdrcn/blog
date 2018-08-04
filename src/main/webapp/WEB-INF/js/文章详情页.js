$(function () {
  locallist = {
    Local_user_Id: JSON.parse(localStorage.getItem("name")), //用local_storage拿取当前电机的人的id
    Local_connection: "http://www.onepi.top:8080/blog",
    PageCount: 0,
    Local_list: 0,
    Local_blog_Id: JSON.parse(localStorage.getItem("This_Id")),//博客id
    Local_blog_page: 0,
    Local_blog_comment_Id: 0,
    Local_blog_x_data: JSON.parse(localStorage.getItem("This_Id_article")),//文章列表页请求的文章数据
    Local_users_detailed_things: JSON.parse(localStorage.getItem("detailed_things_name")),
    Local_coment_on_comment:0,//二级评论临时数据；
    Local_coment_Id_id:0//一级评论临时id
    
  }


  var i ={
    i1:function(){
      $.ajax({
        type: "get",
        dataType: "json",
        url: locallist.Local_connection + "/blog/blog/" + locallist.Local_blog_Id + "/0",
        async: false,
        success: function (data, xhr) {
          
          console.log(locallist.Local_blog_Id);
          console.log(data);
          alert("ok");
          locallist.Local_blog_x_data=data;
          var obj = JSON.stringify(data);
          localStorage.setItem("This_Id_article", obj);
         
        },
        error: function () {
          alert("再见");
        }
      })
    }
  }
  i.i1();


 var f = {
    f2: ".x-author_detailed_loved",
    f3: ".x-author_detailed_talked",
    f4: ".x-author_detailed_eyes",
    f5: ".x-author_detailed_data",
    f6: ".x-Title_of_article",
    f7: ".x-Authors_name",
    f8: ".x-circle_pra",
    f9: ".x-Content_of_the_article",
    f10: ".x-Content_of_the_article_footer_comment_only_name_1",
    f11: ".x-Content_of_the_article_footer_comment_only_name_2",
    a12: ".x-Content_of_the_article_footer_comment_only_content",
    a13: ".x-Content_of_the_article_footer_comment_only_hidden",
    a14: "x-display_block_2",
    a15:".x-Content_of_the_article_footer_comment_more",

    f1: function () {
      var t = this;
      /*$.ajax({//再发一次请求查看博客？之前其实已经存了下来
        type: "get", //方法类型
        dataType: "json", //预期服务器返回的数据类型
        url:locallist.Local_connection+"/blog/blog/"+ locallist.Local_blog_Id+"/"+ locallist.Local_blog_page,//这里有一个页数是用来分页的
        success: function(data,xhr){
            put_blog_detailed_things(data.message);

            
            var length_1 =data.comments;
            if(length_1.length!=0){
              putting_comment_things(data.message);
            }
            
            locallist.Local_blog_comment_Id = data.message;
        },
        erroe: function(){
            alert("gg");
        }
      })*/

      

      put_blog_detailed_things(locallist.Local_blog_x_data);
      
      var length_1 = locallist.Local_blog_x_data.message;
      var length_2 = length_1.comments;
      if (length_2.length != 0) {
        putting_comment_things(locallist.Local_blog_x_data);
      }

      function put_blog_detailed_things(detailed_things) { //将详细信息放入页面

       
        var dating_1 = detailed_things.message; //将采集到的信息细致化到blog
        dating_1 = dating_1.blogBean;
       
        var dating = dating_1.blog;
       
        $(t.f2).children("div").html(dating_1.upCount); //点赞数放置
        $(t.f3).children("div").html(dating_1.commentCount); //评论数
        $(t.f4).children("div").html(dating_1.collectionCount); //收藏数
     
        $(t.f5).children("div").html(changedata(dating.finishTime)); //时间为转化
        $(t.f6).children("p").html(dating.title); //标题
        var dating_bean = dating.userBean; //将采集到的信息细致化到blog.userbean
        $(".x-The_portrait_of_the_author").css("background","url("+locallist.Local_connection+"/img/"+dating_bean.face+") 0% 0% / 100%");//头像图片
        $("#w_myHead").css("background","url("+locallist.Local_connection+"/img/"+dating_bean.face+") 0% 0% / 100%");//头像图片
        $("#b_personal_InfName").children("h2").html(dating_bean.username);
        $(t.f7).children("p").html(dating_bean.username); //作者名字
        $(t.f8).children("p").html(dating.category); //装类别
        var str = dating.title;
        str = str.replace(/&lt;/g, '<');
        str = str.replace(/&gt;/g, '>');
        str = str.replace(/&acute;/g, "\'");
        str = str.replace(/&quot;/g, '"');
        str = str.replace(/&brvbar;/g, "\n");
        dating.title = str;
        $(t.f6).children("p").html(dating.title);
        var str1 = dating.article;
        str1 = str1.replace(/&lt;/g, '<');
        str1 = str1.replace(/&gt;/g, '>');
        str1 = str1.replace(/&acute;/g, "\'");
        str1 = str1.replace(/&quot;/g, '"');
        str1 = str1.replace(/&brvbar;/g, "\n");
        dating.article = str1;
        $(t.f9).html(dating.article);
      };

      function putting_comment_things(detailed_comment_thing) { //将评论放入页面
        $(t.a15).html("");//现将原来的评论清空
        console.log("babab")
        var obj = detailed_comment_thing.message;
        console.log(detailed_comment_thing);
        detailed_comment_thing = obj.comments;
    
        for (var i = 0; i < detailed_comment_thing.length; i++) { //将页面中的评论项动态拼接后插入
          var index_message_comment_things = detailed_comment_thing[i];
          //如果回复数大于0的话就出现隐藏回复框
      
          if (index_message_comment_things.replyCount != 0) {
           
            $(t.a13).eq(i).addClass(t.a14);
           
            console.log($(t.a13).eq(i).children("a"));
            
          }
          //选进数据评论里面
          var index_index = index_message_comment_things.fromUserBean;
          var message_comment_things = detailed_comment_thing.message;
      
          var Splicing = "<div class='x-Content_of_the_article_footer_comment_only'> <div class='x-Content_of_the_article_footer_comment_only_img'>" + "<img src='" + locallist.Local_connection + "/img/" + index_index.face + "'>" + "</div><div class='x-Content_of_the_article_footer_comment_only_only'><div class='x-Content_of_the_article_footer_comment_only_name'><p class='x-Content_of_the_article_footer_comment_only_name_1'>"; //动态拼接技术
          Splicing = Splicing + index_index.username + "</p><p class='x-Content_of_the_article_footer_comment_only_name_2'>" + changedata(index_message_comment_things.time) + "</p><i class='fa fa-comments-o fa-2x  x-comments_logo1'></i></div><div class='x-Content_of_the_article_footer_comment_only_content'><p>";
          Splicing = Splicing + index_message_comment_things.comments + "</p></div><div class='x-Content_of_the_article_footer_comment_only_hidden'><a>";
          Splicing = Splicing + "</a></div></div></div>";
  
          $(".x-Content_of_the_article_footer_comment_more").append(Splicing);
          
          
          if (index_message_comment_things.replyCount != 0) {
            
            $(t.a13).eq(i).addClass(t.a14);
            $(t.a13).eq(i).html("已隐藏"+index_message_comment_things.replyCount+"条评论！");
          }
          
        }
       

        /*for (var i = 0; i < detailed_comment_thing.length; i++) { //这一段的功能和上一段有重复

          $(t.f10).eq(i).html(index_index.username);
          $(t.f11).eq(i).html(changedata(index_message_comment_things.time));
          $(t.f12).eq(i).html(index_message_comment_things.comments);
          if (index_message_comment_things.replyCount != 0) {
            $(t.a13).eq(i).addClass("x-display_none"); //让这个准备显示回复的按钮出现,这里倒是不是重复的
          }
        }*/


      };

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

      function putting_moredetailed_thuings_about_comments() {

      }
    }


  }
  f.f1();
  //查看评论回复：二级评论
  var g = {
    g1: ".x-Content_of_the_article_footer_comment_only_hidden",
    g4: ".x-comments_logo1",
    g5: ".x-send_comments",
    g6: ".x-ready_tocomments",
    g7: "x-comments_things_redy_to_send",
    g8: ".x-Content_of_the_article_footer_comment_only",
    g9: ".x-comments_things_redy_to_send",
    g10:".x-Content_of_the_article_footer_comment_only_content_second",
    g11:".x-Content_of_the_article_footer_comment_only",
    g2: function () {
      var t = this;

      $(t.g1).click(function () {
        $(t.g1).removeClass("x-Dipaly");
        $(this).addClass("x-Dipaly");
        $(t.g10).remove();
        var index = $(this).parents(t.g8).index();

        console.log(locallist.Local_blog_x_data);
        var comments_ids = locallist.Local_blog_x_data;
        comments_ids=comments_ids.message;
        console.log(comments_ids);
        var comments_id_ids = comments_ids.comments[index];//这是之前的data
        locallist.Local_coment_Id_id=comments_id_ids.id;
       var comment_second_thingsthings=comments_ids.comments;
       
        var message_blog_comment_message=comments_ids.comments[index];
        var comments_id=comments_id_ids.id;

        $.ajax({
          dataType: "json",
          type: "get",
          url: locallist.Local_connection + "/blog/reply/" + comments_id_ids.id,
          success: function (data, xhr) {
            console.log(data);
            console.log(comments_id_ids.id);
            locallist.Local_coment_on_comment=data;
            console.log(locallist.Local_coment_on_comment);
            var commeng_second_thingsthings = data.message;
            console.log(commeng_second_thingsthings);
            for (var i = 0; i < commeng_second_thingsthings.length; i++) {

              var comment_second_thingsthingsthings = commeng_second_thingsthings[i].fromUserBean;
 
              var obj_3=commeng_second_thingsthings[i].toUserBean;
              var second_commemnts_things_1 = "<div class='x-Content_of_the_article_footer_comment_only_content_second'><div class='x-Content_of_the_article_footer_comment_only_img'>" + "<img src='"+locallist.Local_connection+"/img/"+comment_second_thingsthingsthings.face+"'>";
              second_commemnts_things_1 = second_commemnts_things_1 + "</div><div class='x-Content_of_the_article_footer_comment_only_only_1'><div class='x-Content_of_the_article_footer_comment_only_name'>";
              if( obj_3.username!=$(".x-Content_of_the_article_footer_comment_only").eq(index).children(".x-Content_of_the_article_footer_comment_only_only").children(".x-Content_of_the_article_footer_comment_only_name").children(".x-Content_of_the_article_footer_comment_only_name_1").html()){
                second_commemnts_things_1 = second_commemnts_things_1 +"<div class='x-Reply_to_it'><div class='x-replying'>回复</div>"+obj_3.username+"</div>";
                
              }
 
              second_commemnts_things_1 = second_commemnts_things_1 +"<p class='x-Content_of_the_article_footer_comment_only_name_1'>"+ comment_second_thingsthingsthings.username;
              second_commemnts_things_1 = second_commemnts_things_1 + "</p><p class='x-Content_of_the_article_footer_comment_only_name_2'>";
              second_commemnts_things_1 = second_commemnts_things_1 + changedata(commeng_second_thingsthings[i].time)+"<i class='fa fa-comments-o fa-2x  x-comments_logo1'></i>";
              second_commemnts_things_1 = second_commemnts_things_1 + "</p></div><div class='x-Content_of_the_article_footer_comment_only_content'><p>";
              second_commemnts_things_1 = second_commemnts_things_1 + commeng_second_thingsthings[i].comments + "</p></div></div></div>";
              $(".x-Content_of_the_article_footer_comment_only_only").eq(index).append(second_commemnts_things_1);
  
            }

          }
        }); //ajx完了
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


      }); //click函数完了


    },
    //回复一级评论
    g3: function () {
      var t = this;
      $(t.g4).click(function () {

        $(this).children(t.g5).show();
        var index = $(this).parents(".x-Content_of_the_article_footer_comment_only").index();
        var obj = locallist.Local_blog_x_data
        var message_blog_datebase = obj.message;
        var message_blog_comment_message = message_blog_datebase.comments[index];
        var comments_id = message_blog_comment_message.id;
        var apendthing = "<div class='x-comments_things_redy_to_send'><input class='x-ready_tocomments'><button class='x-send_comments'>发送评论</button></div>";
        //如果有了发表评论的input就不用再添加，这里用于判断！！！
        if (!($(this).parents(".x-Content_of_the_article_footer_comment_only").find("div").hasClass(t.g7))) {
          $(apendthing).insertAfter($(this).parents(".x-Content_of_the_article_footer_comment_only_only").children(".x-Content_of_the_article_footer_comment_only_content"));
        } else {
          $(this).parents(".x-Content_of_the_article_footer_comment_only").find(t.g9).remove();
        }
        if (!($(this).parents(t.g10).find("div").hasClass(t.g7))) {
          alert("1");
          $(apendthing).insertAfter($(this).parents(t.g10).find(".x-Content_of_the_article_footer_comment_only_content"));
        } else {
          alert("2");
          $(this).parents(".x-Content_of_the_article_footer_comment_only_only_1").find(t.g9).remove();
        }
        $(t.g5).on("click", $(t.g6), function () {
          var index_1 = $(this).parents(".x-Content_of_the_article_footer_comment_only").index();
          var index_only_index=$(this).parent(".x-comments_things_redy_to_send").siblings(t.g1);
          
          console.log(index_only_index);
          $.ajax({
            dataType: "json",
            type: "post",
            url: locallist.Local_connection + "/blog/commentReply",
            data: {
              comments: $(this).siblings("input").val(),
              fromCommentId: comments_id
            },
            success: function (data, xhr) {
              console.log(data);
              if (data.result == "succcess") {
                alert("评论成功！");

              }
            }
          })
        
          $.ajax({ //发完之后立刻展示回复
            dataType: "json",
            type: "get",
            url: locallist.Local_connection + "/blog/reply/" + comments_id,
            success: function (data, xhr) {
              console.log(data);

              console.log($(this));
              var commeng_second_thingsthings = data.message;
              index_only_index.html("已隐藏"+commeng_second_thingsthings.length+"条评论！");
              index_only_index.addClass("x-Diplay");
              locallist.Local_coment_on_comment=data;
              console.log(locallist.Local_coment_on_comment);
              for (var i = 0; i < commeng_second_thingsthings.length; i++) {
                var back_comments_thging = commeng_second_thingsthings[i];
                var comment_second_thingsthingsthings = back_comments_thging.fromUserBean;
                var second_commemnts_things = "<div class='x-Content_of_the_article_footer_comment_only_content_second'><div class='x-Content_of_the_article_footer_comment_only_img'>" + "<img src='" + locallist.Local_connection + "/img/" + comment_second_thingsthingsthings.face + "'>";
                second_commemnts_things = second_commemnts_things + "</div><div class='x-Content_of_the_article_footer_comment_only_only_1'><div class='x-Content_of_the_article_footer_comment_only_name'><p class='x-Content_of_the_article_footer_comment_only_name_1'>";
                second_commemnts_things = second_commemnts_things + comment_second_thingsthingsthings.username;
                second_commemnts_things = second_commemnts_things + "</p><p class='x-Content_of_the_article_footer_comment_only_name_2'>";
                second_commemnts_things = second_commemnts_things + changedata(back_comments_thging.time)+"<i class='fa fa-comments-o fa-2x  x-comments_logo1'></i>";
                second_commemnts_things = second_commemnts_things + "</p></div><div class='x-Content_of_the_article_footer_comment_only_content'><p>";
                second_commemnts_things = second_commemnts_things + back_comments_thging.comments + "</p></div></div></div>";
                
                $(".x-Content_of_the_article_footer_comment_only_only").eq(index_1).append(second_commemnts_things);
              }
            }
          }); //ajx完了

        })

      })


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
  g.g2();
  g.g3();
  //发表评论开始？怎么知道他是不是在登录状态？游客登陆
  var h = {
    h1: ".x-Content_of_the_article_footer_Login_send_comment",
    h2: ".x-Content_of_the_article_footer_Login_comment1",
    h4: ".x-author_detailed_loved",
    h5: "fa-heart-o",
    h6: "fa-heart",
    h7: "x-color_red",
    h8: ".x-author_detailed_eyes", //点赞之后变鸿red
    h9:"star",
    h10:"star-o",
    h11:"x-color_yellow",
    h2: function () {
      var t = this;

      $(t.h1).click(function () {
        var blog_comments_1 = $("#a").val();
        $("#a").val("");
        $.ajax({
          dataType: 'json',
          type: "post",
          url: locallist.Local_connection + "/blog/blogComment",
          data: {
            comments: blog_comments_1,
            fromId: locallist.Local_blog_Id
          },
          success: function (data, xhr) {
            //这里要做一个暗牧！！！先模仿再自己写吧！！！
            console.log(data);
            alert("ok");
            location.reload();
          },
          error: function () {
            alert("gg");
          }
        })
      })
    },
    //博客点赞,收藏
    h3: function () {
      var t = this;
      $(t.h4).children("i").click(function () {
        if ($(t.h4).children("i").hasClass(t.h5)) {
          $(t.h4).children("i").removeClass(t.h5).addClass(t.h6).addClass(t.h7);
        
          $(this).siblings("div").html(parseInt($(this).siblings("div").html())+1);
        } else {
          $(t.h4).children("i").removeClass(t.h6).removeClass(t.h7).addClass(t.h5);
          $(this).siblings("div").html(parseInt($(this).siblings("div").html())-1);

        }
       
        $.ajax({
          dataType: "json",
          type: "post",
          url: locallist.Local_connection + "/blog/blogUp",
          data: {
            blogId: locallist.Local_blog_Id
          },
          success: function (data, xhr) {
            console.log(data);
            alert("ok");
          },
          error: function (data, xhr) {
            alert("gg");
          }

        })

      })
      $(t.h8).children("i").click(function () {
        if (!$(t.h8).children("i").hasClass(t.h11)) {
          $(t.h8).children("i").removeClass(t.h10).addClass(t.h9).addClass(t.h11);
          $(this).siblings("div").html(parseInt($(this).siblings("div").html())+1);
        } else {
          $(t.h8).children("i").removeClass(t.h11).addClass(t.h10).removeClass(t.h9);
          $(this).siblings("div").html(parseInt($(this).siblings("div").html())-1);

        }
        $.ajax({
          dataType: "json",
          type: "post",
          url: locallist.Local_connection + "/blog/blogCollect",
          data: {
            blogId: locallist.Local_blog_Id
          },
          success: function (data, xhr) {
            console.log(data);
            alert("ok");
          },
          error: function (data, xhr) {
            alert("gg");
          }

        })
      })

    }


  }
  h.h2();
  h.h3();


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
      
      var PageCount = PageCount_1.blogBean;
      PageCount = PageCount.commentCount;
    
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
        Handoff_page(locallist.Local_blog_Id, t.c6);
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
        Handoff_page(locallist.Local_blog_Id,t.c6);
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
        Handoff_page(locallist.Local_blog_Id,t.c6);
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

      function Handoff_page(blog_id_id, pagenumber) {
        console.log(pagenumber);
        pagenumber=pagenumber-1;
        typeof(t.c6);
        $.ajax({
          dataType: "json",
          type: "get",
          url: locallist.Local_connection + "/blog/blog/" + blog_id_id +"/"+ pagenumber,
          
          success: function (data, xhr) {
            console.log(blog_id_id);
            console.log(data);
            if (data.result == "success") {
              console.log("bab")
              locallist.Local_blog_x_data = data;
              console.log(locallist.Local_blog_x_data);

              f.f1();
              g.g2();
              g.g3();

            }
          }
        })
      }


    }
  }
  c.Paging_first(locallist.Local_blog_x_data);


  var m ={
    m4: ".x-comments_logo1",
    m7: "x-comments_things_redy_to_send",   
    m9: ".x-comments_things_redy_to_send",
    m10:".x-Content_of_the_article_footer_comment_only_content_second",
    m11:".x-send_comments",
    m1:function(){
      var t =this;
      $(".x-Content_of_the_article_footer_comment_only").off("click").on("click",t.m4,function(){
        
        var apendthing = "<div class='x-comments_things_redy_to_send'><input class='x-ready_tocomments'><button class='x-send_comments'>发送评论</button></div>";
        if (!($(this).parents(t.m10).find("div").hasClass(t.m7))) {
          
          $(apendthing).insertAfter($(this).parents(t.m10).find(".x-Content_of_the_article_footer_comment_only_content"));
          $(".x-Content_of_the_article_footer_comment_only").on("click",t.m11,function(){
            event.stopPropagation();

            var index =$(this).parents(t.m10).index();//这个index好恶心,这个找的是统计的并不是按照对象来找的！！！


            var obj_5= locallist.Local_coment_on_comment;

            var obj_5=obj_5.message;
            

            var mony=obj_5[index-3].id;
            $.ajax({
              dataType: "json",
              type: "post",
              url: locallist.Local_connection + "/blog/commentReply",
              data: {
                comments: $(this).siblings("input").val(),
                fromReplyId: mony,
                fromCommentId :locallist.Local_coment_Id_id

              },
              success: function (data, xhr) {
                console.log(data);
                if (data.result == "succcess") {
                  alert("评论成功！");
      
                }
              }
            })
            var index_1 =$(this).parents(".x-Content_of_the_article_footer_comment_only").index();
            $(this).parents(".x-Content_of_the_article_footer_comment_only").find(t.m10).remove();
            $.ajax({ //发完之后立刻展示回复
              dataType: "json",
              type: "get",
              url: locallist.Local_connection + "/blog/reply/" +locallist.Local_coment_Id_id,
              success: function (data, xhr) {
                console.log(data);
                console.log(index_1);
                var commeng_second_thingsthings = data.message;
                locallist.Local_coment_on_comment=data;
                for (var i = 0; i < commeng_second_thingsthings.length; i++) {
                  var back_comments_thging = commeng_second_thingsthings[i];
                  var comment_second_thingsthingsthings = back_comments_thging.fromUserBean;
                  var second_commemnts_things = "<div class='x-Content_of_the_article_footer_comment_only_content_second'><div class='x-Content_of_the_article_footer_comment_only_img'>" + "<img src='" + locallist.Local_connection + "/img/" + comment_second_thingsthingsthings.face + "'>";
                  second_commemnts_things = second_commemnts_things + "</div><div class='x-Content_of_the_article_footer_comment_only_only_1'><div class='x-Content_of_the_article_footer_comment_only_name'><p class='x-Content_of_the_article_footer_comment_only_name_1'>";
                  second_commemnts_things = second_commemnts_things + comment_second_thingsthingsthings.username;
                  second_commemnts_things = second_commemnts_things + "</p><p class='x-Content_of_the_article_footer_comment_only_name_2'>";
                  second_commemnts_things = second_commemnts_things + back_comments_thging.time+"<i class='fa fa-comments-o fa-2x  x-comments_logo1'></i>";
                  second_commemnts_things = second_commemnts_things + "</p></div><div class='x-Content_of_the_article_footer_comment_only_content'><p>";
                  second_commemnts_things = second_commemnts_things + back_comments_thging.comments + "</p></div></div></div>";
                  
                  $(".x-Content_of_the_article_footer_comment_only_only").eq(index_1).append(second_commemnts_things);
                }
              }
            }); //ajx完了
          })
         
        } else {
          
          $(this).parents(".x-Content_of_the_article_footer_comment_only_only_1").find(t.g9).remove();
        }
      })

    }
  }
  m.m1();




  //翻页操作结束
})