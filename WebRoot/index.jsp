<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
  </head>
  
  <body>
  <script type="text/javascript">
  var a="asdasd";
  var b="asaaaaa";
      $(document).ready(function() {
            $.ajax({
                type: "POST",
                url: "servlet/ServletDemo",
                dataType: "json",
                data: {
                    "title": a,
                    "text": b
                },
                success: function(jsonResult) {
                    alert(jsonResult.msg);
                },
                error: function(jqXHR, textStatus) {
                    alert(jqXHR+"      "+textStatus);
                }
            });
    });
  </script>
  </body>
</html>