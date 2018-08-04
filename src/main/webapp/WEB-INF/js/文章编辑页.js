$(function () {
    Local_list = {
        local_blog_coverimg: 0
    }
    locallist = {
        Local_user_Id: localStorage.getItem("IDuser"),//存放用户的id
        Local_connection: "http://www.onepi.top:8080/blog",
        local_blog_coverimg: 0,
        PageCount: 0,
        Local_list: 0,
        blog_Id:0,//博客id
        blog_detailed_thinging:0

    }
    var i = {
        i1: ".x-category_choose_inblog",
        i3: ".x-category_choose_inblog_list",
        i4: "x-category_dsplay_block",
        i5: ".choose_right_tags",
        i6: ".x-footer_tags_choose",
        i7: ".x-circle_pra",
        i8: ".x-Title_of_article",
        i2: function () {
            var t = this;
            $(t.i1).click(function () {

                if ($(t.i3).hasClass(t.i4)) {
                    $(t.i3).removeClass(t.i4);
                } else {
                    $(t.i3).addClass(t.i4);
                }
                /*$(".x-footer_tags_choose").on("click", function(e){
                    if( $(t.i3).is(":hidden")){
                        $(t.i3).fadeIn();
                    }else{
                        $(t.i3).fadeOut();
                    }
                
                    $(document).one("click", function(){
                        $(t.i3).fadeOut();
                    });
                
                    e.stopPropagation();
                });
                $(t.i3).on("click", function(e){
                    e.stopPropagation();
                });
            })*/
            })
            $($(".x-category_choose_inblog_list").children("ul").children("li")).on("click", $("a"), function () {
                var index = $(this).children("a").html();
                $(t.i7).children("p").html(index);
                $(t.i5).html(index);
                switch (index) {
                    case 0:
                        $(t.i5).html("前端");
                        break;
                    case 1:
                        $(t.i5).html("后台");
                        break;
                    case 2:
                        $(t.i5).html("安卓");
                        break;
                    case 3:
                        $(t.i5).html("大数据");
                        break;
                }
            })
            /*$(t.i3).children("li").click(function(){
                alert("1");
                var index = $(this).children("a").html();
                $(t.i5).html(index);
            })*/
        },
        i9:function(){

            $.ajax({
                type: "get",
                dataType: "json",
                async:false,
                url: locallist.Local_connection + "/blog/blog/"+blogId+"/0" ,
                success:function(data,xhr){
                    console.log(data);
                    locallist.blog_detailed_thinging=data;

                },
                error:function(data,xhr){
                    console.log(data);
                }
            })
            funciton putting_things_ineditoring(){
                var main =locallist.blog_detailed_thinging.message;
                main = main.blog;
                $("#x-editor_1").children("p").html(main.title);
                $("#x-editor").children("p").html(main.article);
                $("#x-editor_2").html(main.coverImg);

            }

        }
    }
    i.i2();
    i.i9();


    var E = window.wangEditor
    var editor1 = new E('#x-editor');//内容

    var editor2 = new E('#x-editor_1');//标题

    var editor3 = new E('#x-editor_2');//封面图
    

    // 自定义菜单配置
    editor1.customConfig.menus = [
        'head',  // 标题
        'bold',  // 粗体
        'fontSize',  // 字号
        'fontName',  // 字体
        'italic',  // 斜体
        'underline',  // 下划线
        'strikeThrough',  // 删除线
        'foreColor',  // 文字颜色
        'backColor',  // 背景颜色
        'link',  // 插入链接
        'list',  // 列表
        'justify',  // 对齐方式
        'quote',  // 引用
        'emoticon',  // 表情
        'image',  // 插入图片
        'table',  // 表格
        'code',  // 插入代码
        'undo',  // 撤销
        'redo'  // 重复
    ]
    editor1.customConfig.showLinkImg = false
    editor3.customConfig.showLinkImg = false


    editor2.customConfig.menus = [
        'head', // 标题
        'bold', // 粗体
        'fontSize', // 字号
        'fontName', // 字体
        'italic', // 斜体
        'underline', // 下划线
        'strikeThrough', // 删除线
        'foreColor', // 文字颜色
        'backColor', // 背景颜色
        'justify', // 对齐方式
        'quote', // 引用
        'emoticon', // 表情
        'code', // 插入代码
        'undo', // 撤销
        'redo' // 重复
    ]
    editor3.customConfig.menus = [
        'image' // 插入图片
    ]

    // 或者 var editor = new E( document.getElementById('editor') )
    editor1.customConfig.debug = location.href.indexOf('wangeditor_debug_mode=1') > 0 //debug模式
    editor1.customConfig.onfocus = function () {

    }
    editor1.customConfig.colors = [
        '#000000',
        '#eeece0',
        '#1c487f',
        '#4d80bf',
        '#c24f4a',
        '#8baa4a',
        '#7b5ba1',
        '#46acc8',
        '#f9963b',
        '#ffffff'
    ]

    // 表情面板可以有多个 tab ，因此要配置成一个数组。数组每个元素代表一个 tab 的配置
    editor1.customConfig.emotions = [{
            // tab 的标题
            title: '默认',
            // type -> 'emoji' / 'image'
            type: 'image',
            // content -> 数组
            content: [{
                    alt: '[坏笑]',
                    src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/50/pcmoren_huaixiao_org.png'
                },
                {
                    alt: '[舔屏]',
                    src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/40/pcmoren_tian_org.png'
                }
            ]
        },
        {
            // tab 的标题
            title: 'emoji',
            // type -> 'emoji' / 'image'
            type: 'emoji',
            // content -> 数组
            content: ['😀', '😃', '😄', '😁', '😆']
        }
    ]

    editor1.customConfig.fontNames = [
        '宋体',
        '微软雅黑',
        'Arial',
        'Tahoma',
        'Verdana'
    ]
    editor2.customConfig.onblur = function (html) {
        // html 即编辑器中的内容
        if (($(".x-Title_of_article").children("p").html() == "") || ($(".x-Title_of_article").children("p").html() == "请添加标题！")) {
            $(".x-Title_of_article").children("p").html("无标题！");
        } else {
            $(".x-Title_of_article").children("p").html(html);
        }

    }


    editor1.customConfig.uploadImgServer = locallist.Local_connection + "/blog/imgUpload";
    editor1.customConfig.uploadFileName = 'file';
    editor3.customConfig.uploadImgServer = locallist.Local_connection + "/blog/imgUpload";
    editor3.customConfig.uploadFileName = 'file';
    editor1.customConfig.uploadImgHooks = {
        before: function (xhr, editor, files) {
            // 图片上传之前触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，files 是选择的图片文件

            // 如果返回的结果是 {prevent: true, msg: 'xxxx'} 则表示用户放弃上传
            // return {
            //     prevent: true,
            //     msg: '放弃上传'
            // }
        },
        success: function (xhr, editor, result) {
            // 图片上传并返回结果，图片插入成功之后触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
        },
        fail: function (xhr, editor, result) {
            // 图片上传并返回结果，但图片插入错误时触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
        },
        error: function (xhr, editor) {
            // 图片上传出错时触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
        },
        timeout: function (xhr, editor) {
            // 图片上传超时时触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
        },
        customInsert: function (insertImg, result, editor) {
            // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
            // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果

            // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
            console.log(result);
            var url = locallist.Local_connection + "/img/" + result.message;
            insertImg(url)

            // result 必须是一个 JSON 格式字符串！！！否则报错
        }
    }
    editor3.customConfig.uploadImgHooks = {
        before: function (xhr, editor, files) {
            // 图片上传之前触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，files 是选择的图片文件

            // 如果返回的结果是 {prevent: true, msg: 'xxxx'} 则表示用户放弃上传
            // return {
            //     prevent: true,
            //     msg: '放弃上传'
            // }
        },
        success: function (xhr, editor, result) {
            // 图片上传并返回结果，图片插入成功之后触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
        },
        fail: function (xhr, editor, result) {
            // 图片上传并返回结果，但图片插入错误时触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
        },
        error: function (xhr, editor) {
            // 图片上传出错时触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
        },
        timeout: function (xhr, editor) {
            // 图片上传超时时触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
        },
        customInsert: function (insertImg, result, editor) {
            // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
            // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果

            // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
            console.log(result);
            var url = locallist.Local_connection + "/img/" + result.message;
            Local_list.local_blog_coverimg =  result.message;
            insertImg(url)

            // result 必须是一个 JSON 格式字符串！！！否则报错
        }
    }
    editor3.customConfig.onfocus = function () {
        var text = editor3.txt.text();
        console.log(text);
        if((text!="请在这里加封面图！")||(text=="")){
            editor3.txt.html('<p><br></p>');
            alert("请不要在那里输入文字！！！")
        }
    }
    
    editor1.create();
    editor2.create();
    editor3.create();

    

    $(".x-Content_of_the_article_footer_Login_send_comment").click(function () {
        console.log($(".choose_right_tags").html());

        if ($(".choose_right_tags").html().trim == "") {
            alert("请先选择标签！")
        } else if (editor2.txt.html().trim == "") {
            alert("请先添加标题！");
        } else if (editor1.txt.html().trim == "") {
            alert("请先添加正文！");
        } else {
            console.log(editor2.txt.html());
            console.log(editor1.txt.html());
            console.log(Local_list.local_blog_coverimg);
            console.log($(".choose_right_tags").html());
            $.ajax({
                dataType: "json",
                type: "post",
                url: locallist.Local_connection + "/blog/modifyBlog",
                data: {
                    id:blogId,
                    title: editor2.txt.html(),
                    article: editor1.txt.html(),
                    coverImg: Local_list.local_blog_coverimg,
                    category[]: $(".choose_right_tags").html()
                },
                success: function(data,xhr) {
                    alert("修改成功！");
                    console.log(data);
                },
                error: function () {
                    alert("gg");
                    console.log(data);
                }
            })
        }


    })
    //动态显示时间
    var k = {
        k1:".x-author_detailed_data",
        k2: function () {
            var t = this;

            function getFormatDate() {
                var nowDate = new Date();
                var year = nowDate.getFullYear();
                var month = nowDate.getMonth() + 1 < 10 ? "0" + (nowDate.getMonth() + 1) : nowDate.getMonth() + 1; //这里month是从0开始的！    
                var date = nowDate.getDate() < 10 ? "0" + nowDate.getDate() : nowDate.getDate();
                var hour = nowDate.getHours() < 10 ? "0" + nowDate.getHours() : nowDate.getHours();
                var minute = nowDate.getMinutes() < 10 ? "0" + nowDate.getMinutes() : nowDate.getMinutes();
                var second = nowDate.getSeconds() < 10 ? "0" + nowDate.getSeconds() : nowDate.getSeconds();
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

                return month + " " + date + ", " + year; //拼接！！！    
            }
            $(t.k1).children("div").children("p").html(getFormatDate());
            /*Date.prototype.format = function (fmt) { //网上拷的所以有点不懂！
                var o = {
                    "M+": this.getMonth() + 1, //月份
                    "d+": this.getDate(), //日
                    "h+": this.getHours(), //小时
                    "m+": this.getMinutes(), //分
                    "s+": this.getSeconds(), //秒
                    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                    "S": this.getMilliseconds() //毫秒
                };
                if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
                for (var k in o)
                    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                return fmt;
            };

            //日期时间转换
            function judgeDate(oldDate) { // 判断是否是今天还是昨天

                //昨天的时间
                var day1 = new Date();
                day1.setDate(day1.getDate() - 1); //设置一个月的某一天！奇怪那如果是一的话怎么减掉啊？
                var yesterday = day1.format("yyyy-MM-dd");

                //今天的时间
                var day2 = new Date();
                day2.setTime(day2.getTime());
                var today = day2.format("yyyy-MM-dd");

                if (oldDate.split(" ")[0] == today) {
                    return "今天 " + oldDate.split(" ")[1];
                } else if (oldDate.split(" ")[0] == yesterday) {
                    return "昨天 " + oldDate.split(" ")[1];
                } else {
                    return oldDate;
                }

            }*/
            //将日期时间转化为早中晚凌晨
            /*function jugeDate_indeeed(datetiming) {
                var stu = datetiming.split(" ")[1];
                var stu1 = datetiming.split(" ")[0];
                if ((stu1 != "今天") && (stu1 != "昨天")) {

                    if (stu.split(":")[0] < 6) {
                        return stu1.split("-")[0] + "年" + stu1.split("-")[1] + "月" + stu1.split("-")[2] + "日" + "凌晨 " + datetiming.split(" ")[1];
                    } else if (stu.split(":")[0] < 9) {
                        return stu1.split("-")[0] + "年" + stu1.split("-")[1] + "月" + stu1.split("-")[2] + "日" + "早上 " + datetiming.split(" ")[1];
                    } else if (stu.split(":")[0] < 12) {
                        return stu1.split("-")[0] + "年" + stu1.split("-")[1] + "月" + stu1.split("-")[2] + "日" + "上午 " + datetiming.split(" ")[1];
                    } else if (stu.split(":")[0] < 14) {
                        return stu1.split("-")[0] + "年" + stu1.split("-")[1] + "月" + stu1.split("-")[2] + "日" + "中午 " + datetiming.split(" ")[1];
                    } else if (stu.split(":")[0] < 17) {
                        return stu1.split("-")[0] + "年" + stu1.split("-")[1] + "月" + stu1.split("-")[2] + "日" + "下午 " + datetiming.split(" ")[1];
                    } else if (stu.split(":")[0] < 24) {
                        return stu1.split("-")[0] + "年" + stu1.split("-")[1] + "月" + stu1.split("-")[2] + "日" + "晚上 " + datetiming.split(" ")[1];
                    }

                } else {
                    if (stu.split(":")[0] < 6) {
                        return stu1 + "凌晨 " + datetiming.split(" ")[1];
                    } else if (stu.split(":")[0] < 9) {
                        return stu1 + "早上 " + datetiming.split(" ")[1];
                    } else if (stu.split(":")[0] < 12) {
                        return stu1 + "上午 " + datetiming.split(" ")[1];
                    } else if (stu.split(":")[0] < 14) {
                        return stu1 + "中午 " + datetiming.split(" ")[1];
                    } else if (stu.split(":")[0] < 17) {
                        return stu1 + "下午 " + datetiming.split(" ")[1];
                    } else if (stu.split(":")[0] < 24) {
                        return stu1 + "晚上 " + datetiming.split(" ")[1];
                    }

                }

            }*/
        }
    }
    k.k2();
});