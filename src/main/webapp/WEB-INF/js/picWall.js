function w_step1(){
    w={ 
        userMessage:undefined,
        albumList:new Array(),
        albumTime:undefined,
        onloadPicNum:0,
        makePicNum:0,
        uploadAlbumNum:undefined, //上传到哪个相册
        picWallOnload:function(){ //一些基本操作 不涉及数据交流
            $("body").click(function(){
                $("div#w_newAlbumAlert").fadeOut(233);
            })
            $("div#w_newAlbum").click(function(){
                $("div#w_newAlbumAlert").fadeIn(233);
                return false;
            })
            $("div#w_newAlbumAlert").click(function(){
                return false;
            })
            $("div#w_newAlbumAccept").click(function(){
                var name=$("input#w_newAlbumInput").val();
                if (name!=""){
                    w.newAlbum(name);
                    $("div#w_newAlbumAlert").fadeOut(233);
                }
                else{
                    $("input#w_newAlbumInput").val("请输入相册名");
                }
            });
            $("div#w_picView").click(function(event){
                $("div#w_picView").fadeOut(200);
            });
            $("div.w_centerContain").click(function(event){
                return false;
            });
            $("i#w_closePicView").click(function(){
                $("div#w_picView").hide();
            })
            $("a#w_uploadButton").click(function(){
                $("input#w_uploadPic").fadeToggle(233);
                $("input#w_uploadPicButton").fadeToggle(233);
            })
            $("input#w_uploadPicButton").click(function(){
                $("div#w_uploadAlert").slideDown(233);
            })
            $("div#w_uploadCancel").click(function(){
                $("div#w_uploadAlert").slideUp(233);
            })
            function uploadAccept(){ //上传图片的确认
                $("div#w_uploadAccept").click(function(){ //防止多次上传
                    $("div#w_uploadAccept").off("click");
                    $("div#w_uploadAlert").slideUp(233);
                    picFile = $("input#w_uploadPic")[0].files[0];
                    var aFormData=new FormData();
                    aFormData.append("id",w.uploadAlbumNum);
                    aFormData.append("file",picFile);
                    var1=aFormData;
                    $.ajax({
                        type:"POST",
                        url:domain + "/blog/uploadPhotos",
                        data:aFormData,
                        dataType:"json",
                        cache: false,//上传文件无需缓存
                        processData: false,//用于对data参数进行序列化处理 这里必须false
                        contentType: false, //必须
                        success:function(json){  //上传之后
                            $("div.w_album").remove();
                            w.picWallPicLoad();
                            uploadAccept();  //重新绑定一次确认函数
                        },
                        error:function(){
                        }
                    })
                })
            }
            uploadAccept();
        },
        waterFall:function(obj){  //相簿page,obj瀑布流化
            if (obj==null){
                var albumPageList=$("div.w_albumPage");
                for (var i=0;i<albumPageList.length;i+=1){j
                    w.waterFall($(albumPageList[i]));
                }
            }
            var columnNum=parseInt($(obj).width()/170); //算出一行有多少个，即为列的个数
            var rowHeight=new Array(columnNum);  //记录每列的长度
            var maxHeight=0;  //记录最大长度,将其赋值给obj防止高度崩塌。
            var minHeightNum=0;  //记录最小长度的那列
            var albumPicFrameList=$(obj).children("div.w_albumPicFrame");
            for (var i=0;i<columnNum;i+=1){
                $(albumPicFrameList[i]).css({"position":"absolute","top":"0","left":i * 170 +"px"})
                rowHeight[i]=$(albumPicFrameList[i]).height();
                if (rowHeight[i] < rowHeight[minHeightNum]){  //把最小高度那列记下来
                    minHeightNum=i;
                }
            }
            for (var i=columnNum;i<albumPicFrameList.length;i+=1){
                $(albumPicFrameList[i]).css({
                    "position":"absolute",
                    "left":minHeightNum * 170 + "px",  //排到最短的列
                    "top":17 + rowHeight[minHeightNum] + "px"  //最短列长度
                })
                rowHeight[minHeightNum] += (17 + $(albumPicFrameList[i]).height() );
                for (var j=0;j<columnNum;j+=1){
                    if (rowHeight[j] < rowHeight[minHeightNum]){  //更改最小列
                        minHeightNum=j;
                    }
                }
            }
            for (var i=0;i<columnNum;i+=1){
                if (maxHeight<rowHeight[i]){
                    maxHeight=rowHeight[i];
                }
            }
            $(obj).height(maxHeight);
        },
        newAlbum:function(albumName){ //创建新标签相册
            $.ajax({
                type:"POST",
                url:domain + "/blog/newAlbum",
                data:{
                    userId:w.userMessage.id,
                    albumName:albumName,
                },
                dataType:"JSON",
                success:function(json){
                }
            })
        }
    };
    w.wordPressAnimate=function(){
        $("a.w_wordPressButton").mouseover(function(){
            $(this).css("height","70px");
            $(this).animate({height:"50px"},233);
        });
    };
    w_window={
        resize:function(){
            $(window).resize(function(){
                $("div.w_albumPage").width($("div.w_album").eq(0).width()-180);  //更改albumPage宽度
                var albumPageList=$("div.w_albumPage");
                for (var i=0;i<albumPageList.length;i+=1){
                    w.waterFall($(albumPageList[i]));
                }
                $("div#w_picCommentArea").height($("div#w_picViewRight").height()-90-30);
            })
        }
    };
}
