<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cn">

<head>
    <title>考勤登陆</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>

<body>
    <div class="form-style form-horizontal">
        <h1>考勤登录</h1>
        <div class="form-group">
            <label for="admin_name" class="control-label col-sm-2">用户名:
            </label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="admin_name" placeholder="请输入用户名">
            </div>
        </div>
        <div class="form-group">
            <label for="admin_password" class="control-label col-sm-2">密码:
            </label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="admin_password" placeholder="请输入密码">
            </div>
        </div>
        <div class="btn_box">
            <button id="btn_admin_login" class="btn btn-info btn-lg">登录</button>
        </div>
    </div>

    <script type="text/javascript">
        $(document).ready(function() {
            $("#btn_admin_login").bind("click", function() {
                $.ajax({
                    type: "POST",
                    url: "servlet/ServletDemo",
                    dataType: "json",
                    data: {
                        "username": $("#admin_name").val(),
                        "password": $("#admin_password").val()
                    },
                    success: function(jsonResult) {
                        if(jsonResult.msg=="success"){
                            window.location.href="kqjl.jsp";
                        }else{
                            alert("登录失败");
                        }
                    },
                    error: function(jqXHR, textStatus) {
                        alert("login error");
                    }
                });
            })
        });
    </script>
</body>

