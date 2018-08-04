//全部模块化(不执行只定义)
function w_step2(){
    w.genesis=function(message){  //构筑网页的全过程
        if (message==undefined){  //是本人的页面
            $.ajax({  //先获取用户信息
                type:"GET",
                url:domain + "/blog/myhomepage",
                dataType:"json",
                success:function(json){
                    w.userMessage=json.message;
                    photoWallMessage(); //获取照片墙信息以及执行之后的操作
                }
            })
        }
        else{  //不是本人的页面
            w.userMessage=message;
            photoWallMessage();
            $("div")
        }
        function photoWallMessage(){
            $.ajax({
                type:"GET",
                url:domain + "/blog/photoWall/" + w.userMessage.id,
                dataType:"JSON",
                success:function(json){
                    w.person=json.result;
                    w.photoWallMessage=json.message;  //获取照片墙信息
                    w.userMessage.photoWallList=w.photoWallMessage.photoWallList;
                    w.uploadAlbumNum=w.photoWallMessage.albumList[0].id;  //默认上传到第一个相册
                    w.picWallPicLoad();  //获取完信息后加载图片
                    w.addLabel();  //添加标签相册按钮
                    w.getFans();  //获取好友们
                    w.wordPressAnimate();
                    w_window.resize();
                    w.pressLine();
                }
            })

        }
        w.picWallOnload();
    };
    w.pressLine=function(){
        if (w.person=="me"){
            $("a#w_myPage").attr("href","mypage.html");
            $("a#w_blog").attr("href","文章个人列表页.html");
            $("a#w_about").attr("href","myInfo.html");
        }
        else{
            $("a#w_myPage").attr("href","thirdpage.html");
            $("a#w_blog").attr("href","文章他人列表页.html");
            $("a#w_about").attr("href","thirdInfo.html");
        }
    }
    w.picWallPicLoad=function(){  //加载图片及壁纸等并且绑定功能的函数,根据userMessage
        w.albumList=new Array();
        w.albumTime=undefined;
        w.onloadPicNum=0;
        w.makePicNum=0;
        w.uploadAlbumNum=undefined;
        $("div#w_myHeadName").text(w.userMessage.username);
        $("div.w_album").remove();
        $("div#w_wallPaper").css("background-image","url(" + domain + "/img/" + w.userMessage.bakcgroundPhoto + ")")
        $("div#w_myHead").css("background-image","url(" + domain + "/img/" + w.userMessage.faceImg + ")"); //修改头像
        var anAlbum;
        function addPicToAlbum(obj,i){  //给相簿添加照片的函数,obj为相簿,i为第几张图片
            var aAlbumPicFrame;
            aAlbumPicFrame=$("<div class=w_albumPicFrame><div class=w_albumPic><div class=w_albumPicInfo></div></div></div>");
            var aImg=$("<img class=w_albumPicImg>");
            $(aImg).attr("src",domain + "/img/" + w.userMessage.photoWallList[i].photoHash);
            $(aImg).attr("href",w.userMessage.photoWallList[i].id);
            var aImgId=w.userMessage.photoWallList[i].id;
            var aImgComment=w.userMessage.photoWallList[i].commentArrayList||0;
            var1=aImgComment;
            $(aImg).load(function(){  //图片加载完毕以后的操作，绑定picView出现，计算加载了多少，全加载完就现形
                w.onloadPicNum+=1; //已加载数量+1；
                $(this).addClass("pointer");
                $(aImg).parent().parent().fadeIn(233);  //加载完后出现
                $(this).click(function(){  //图片点击后出现查看大图
                    $("div#w_commentPages").text("/" + Math.ceil(aImgComment.length||0/10));
                    $("div#w_picView").fadeIn(200);
                    $("img#w_realPic").attr("src",$(this).attr("src"));
                    if ($(this).hasClass("w_delete")){
                        var theAlbumPage=$(this).parents("div.w_albumPage");
                        $(this).parents("div.w_albumPicFrame").remove();
                        w.waterFall(theAlbumPage);
                        $("div#w_picView").hide();
                        $.ajax({
                            type:"GET",
                            url:domain + "/blog/deletePhoto/" + aImgId,
                            dataType:"JSON",
                            success:function(){
                                w_alert("删除成功");
                            }
                        })
                        return false;
                    }
                    // 添加评论
                    w.addComment(0,aImgId);
                    $("div#w_picCommentPageChange").off("click");
                    $("div#w_picCommentPageChange").click(function(){  //点击"跳转"后跳转页面 
                        var thePage;
                        thePage=$("input#w_picCommentPageInput").val();
                        thePage=parseInt(thePage);
                        w.addComment(thePage,aImgId);
                    })
                    // 更改评论栏目标
                    $("input#w_picWriteComment").off("keydown");
                    $("input#w_picWriteComment").keydown(function(event){
                        if (event.which==13){
                            var theComment;
                            theComment=$("input#w_picWriteInput").val();
                            $.ajax({
                                type:"POST",
                                url:domain + "/blog/discussPhoto",
                                data:{
                                    userId:w.userMessage.id,
                                    photoId:aImgId,
                                    comments:theComment
                                },
                                dataType:"JSON",
                                success:function(json){
                                    if (json.result=="success"){
                                        w.addComment(Math.ceil(aImgComment.length/10),aImgId); //直接拉到最后一页
                                    }
                                }
                            })
                        }
                    })
                })                       
            })
            $(aAlbumPicFrame).children("div.w_albumPic").append(aImg);
            $(aAlbumPicFrame).children("div.w_albumPic").css("background-image","url(" + domain + "/img/" + w.userMessage.photoWallList[i].photoHash + ")");
            $(obj).append(aAlbumPicFrame); //下一行 添加图片点赞和评论数量
            var commentNum;
            if (w.userMessage.photoWallList[i].commentArrayList!=null){
                commentNum=w.userMessage.photoWallList[i].commentArrayList.length;
            }
            else{
                commentNum=0;
            }
            $(aImg).prev().html(w.userMessage.photoWallList[i].beUpNum||0 + '<i class="fa fa-heart-o"></i> ' + commentNum + '<i class="fa fa-comments-o"></i>'); 
        }
        w.makeAlbum=function(start){  //加载图片的函数，start表示从图片列表第几张开始
            var newAlbumNum=0;
            for (var i=start||0;newAlbumNum<=3&&i<w.userMessage.photoWallList.length;i+=1){  //新相册数为三个或者照片轮完
                w.makePicNum+=1;
                var picTime=w.userMessage.photoWallList[i].pushTime.substr(0,10);
                if (w.albumTime!=picTime){  //如果照片的日期不是前一张照片的日期，新建相簿
                    newAlbumNum+=1;
                    w.albumTime=picTime;
                    anAlbum=$("<div class=w_album><div class=w_albumDate></div><div class=w_albumPage></div></div>");
                    w.albumList.push(anAlbum);
                    $("div#w_underWall").append(anAlbum);
                    $(anAlbum).children("div.w_albumDate").text(picTime);
                    $(anAlbum).children("div.w_albumPage").width($("div.w_album").eq(0).width()-180);  //更改albumPage宽度);
                    addPicToAlbum($(anAlbum).children("div.w_albumPage"),i);
                }
                else{
                    addPicToAlbum($(anAlbum).children("div.w_albumPage"),i);
                }
            };
        }
        w.makeAlbum(0); //从第0图片开始
        w.morePic();  //然后给更多图片按钮绑定函数
    }
    w.morePic=function(){  //给更多图片绑定函数
        $("div#w_morePic").off("click");
        $("div#w_morePic").click(function(){
            w.makeAlbum(w.makePicNum); //从先前制造的图片数量开始(即未制造的第一位)
            w.morePic();
        });
    }
    w.addComment=function(page,imgId){  //imgId为图片id，page为页数
        $("div.w_picComment").remove();
        var commentArray;
        if (page==undefined){
            page=0;
        }
        $.ajax({
            type:"GET",
            url:domain + "/blog/showPhotoComments/" + imgId + "/" + page,
            data:{
                photoId:imgId,
                pageId:page,
            },
            dataType:"JSON",
            success:function(json){
                commentArray=json.message;
                for (var i=0;i<10&&i<commentArray.length;i+=1){
                    var aPicComment;
                    aPicComment=$("<div class=w_picComment><div calss=w_picCommentHead></div><div calss=w_picCommentText></div></div>");
                    $("div#w_picCommentArea").append(aPicComment);
                    $(aPicComment).children("div.w_picCommentHead").css("background","url(" + domain + "/img/" + commentArray[i].fromUserBean.face + ")");
                    $(aPicComment).children("div.w_picCommentText").text(commentArray[i].fromUserBean.username + ":" + commentArray[i].comments);
                }
            }
        })
    }
    w.addLabel=function(){  //添加标签
        function addLabel(objNum){  //添加label到obj里面，如果objNum是0那就是到页面，1就是到上传页面的s
            var labelAlbumList=w.photoWallMessage.albumList;
            for (var i=0;i<labelAlbumList.length;i+=1){
                if (objNum==0){
                    (function(){
                        var aLabel;
                        aLabel=$("<div class=w_label>");
                        $("div#w_newAlbum").before(aLabel);
                        $(aLabel).text(labelAlbumList[i].albumName);
                        $(aLabel).addClass("pointer");
                        aLabel.id=labelAlbumList[i].id;
                        $(aLabel).click(function(){
                            $.ajax({
                                type:"GET",
                                url:domain + "/blog/photoSign/" + aLabel.id + "/" + w.userMessage.id,
                                dataType:"JSON",
                                data:{
                                    albumId:aLabel.id,
                                    userId: w.userMessage.id,
                                },
                                success:function(json){  //偷天换日加载法!!!
                                    var albumPhotoList=json.message;
                                    w.userMessage.photoWallList=json.message;  //把用户信息里面的照片墙直接改为相册里面的照片墙
                                    if (albumPhotoList.length==0){
                                        w_alert("相册为空");
                                    }
                                    //再加载一次
                                    w.picWallPicLoad();  //此时的用户信息的照片墙已被偷天换日
                                }
                            })
                        })
                    })();
                }
                else{  //上传图片地方的标签

                }
            }
        }
        if ($("div.w_label").length<=1){  //如果只有一个"创建"标签,直接添加
            addLabel(0);
            addLabel(1);
        }
        else{
            
        }
    };
    w.getFans=function(){
        $("div.w_albumFriendFrame").remove();  //把框架全删了
        fansList=w.photoWallMessage.niceFriendsList;
        for (var i=0;i<fansList.length;i+=1){
            var anA=$("<a>");
            $("div#w_albumFriends").append(anA);
            var anAlbumFriendFrame=$("<div class=w_albumFriendFrame><div class=w_albumFriendHead></div></div>");
            $(anAlbumFriendFrame).addClass("pointer");
            $(anAlbumFriendFrame).children("div.w_albumFriendHead").css("background-image","url(" + domain + "/" + fansList[i].face + ")");
            $(anAlbumFriendFrame).append(fansList[i].username);
            $(anA).append(anAlbumFriendFrame);
        }
    };
}