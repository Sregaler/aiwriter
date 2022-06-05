<%--
  Created by IntelliJ IDEA.
  User: QiTianM425
  Date: 2021/12/14
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>

<%--<%--%>
<%--    $('#submitBtn').click(function () {--%>
<%--        $.post("/user/login",{--%>
<%--                'username':$('#username').val(),--%>
<%--                'password':$('#password').val(),--%>
<%--                'code':$('#code').val()--%>
<%--        },function (data) {--%>
<%--            if(!data.success == 1){--%>

<%--            }--%>
<%--        },'json');--%>
<%--    });--%>
<%--%>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//    <base href="<\%=basePath%\>">或者 “${pageContext.request.contextPath}/images/title.gif”
%>
<html>
<head>
    <title>Title</title>
    <base href="<%=basePath%>">
</head>

<body>
    <form id="myForm1" method="post" action="/index">
        <input type="submit" id="submitB" value="转跳到blog" />
    </form>
    <p>素材库基本功能测试#######################################################</p>
    <form id="myForm1" enctype="multipart/form-data" method="post" action="/material/save">
        素材上传:<input type="file" name="files"  multiple="multiple"/><br/>
        <input type="submit" id="submitBtn1" value="上传" />
    </form>
    <form id="myForm2" enctype="multipart/form-data" method="post" action="/material/deleteById">
        素材删除:<input type="text" name="mid"/><br/>
        <input type="submit" id="submitBtn4" value="删除" />
    </form>
    <form method="post" class="navbar-form navbar-right" role="search" action="/material/list">
        <div class="input-group">
            搜索当前用户的素材(不输入表示搜索全部，输入0~4表示搜索不同类型的素材)：</br>
            <input type="text" name="m_type" class="form-control" autocomplete="off" placeholder="输入搜索类型" maxlength="15">
            <input type="hidden" name="page" value="1" />
            <input type="hidden" name="pageSize" value="10" />
            <span class="input-group-btn">
                <button class="btn btn-default" type="submit">搜索</button>
            </span>
        </div>
    </form>
    <p>公共材料基本功能测试#######################################################</p>
    <form id="myForm2" enctype="multipart/form-data" method="post" action="/filesUpload2TempPublicLib">
        上传材料到公共库，上传后返回url，无法在用户的素材查询中查到:</br>
        <input type="file" name="files"  multiple="multiple"/><br/>
        <input type="submit" id="submitBtn2" value="上传" />
    </form>
    <p>AI成稿基本功能测试@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@</p>
    <form id="myForm3" enctype="multipart/form-data" method="post" action="/write/ai">
        上传材料也是到公共库中做临时存储，上传后返回url:</br>
        <input type="file" name="files"  multiple="multiple"/><br/>
        <input type="submit" id="submitBtn3" value="上传" />
    </form>
    <p>静态文件测试：</p>
<%--    <div id="box">--%>
<%--        <video id="video1" controls preload="auto" width="400px" height="300px">--%>
<%--            <source src="upload/public/2021-12-31/16409397249183-2-org-30-36-2.mp4" type="video/mp4">--%>
<%--        </video>--%>
<%--    </div>--%>
    <div id="img_box">
        <img src="/img/other/music_def.png">
    </div>
    <script>
        layer.open({
            type: 1,
            title: false,
            shadeClose: true,
            area: ['400px', '350px'],
            content: $('#box'),
            success: function(layero){
                //layer样式layer-anim导致全屏样式错乱，移除该样式即可
                setTimeout(function() {
                    $(layero).removeClass('layer-anim');
                }, 0);
            }
        });
    </script>
</body>

</html>
