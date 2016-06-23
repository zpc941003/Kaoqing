<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">

<head>
    <title>考勤系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link href="css/bootstrap-table.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap-datetimepicker.min.css">
    <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-table.js"></script>
    <script type="text/javascript" src="js/time.js"></script>
    <script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-datetimepicker.zh-CN.js"></script>
</head>

<body>
    <script type="text/javascript" src="js/sidebar.js"></script>
    <script type="text/javascript">
    $("#index6").addClass('active');
    </script>
        <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-top: 30px">
                <div style="float:right;margin:30px 10px 20px 0px">
                    <p>用户</p>
                    <b><p id="username"><%
                    if(session.getAttribute("username")==null){
                    response.sendRedirect("login.jsp");
                    } else{
                        out.print(session.getAttribute("username")); 
                    }
                    %></p></b>
                </div>
                <div style="float:right;margin:30px 20px 20px 0px">
                    <p>时间 </p>
                    <b><p id="timer"></p></b>
                </div>
                <!-- Page Heading -->
                <div id="reportTableDiv" class="span10">
                    <table id="Table" data-toggle="table" data-height="450" data-side-pagination="server" data-pagination="true" data-page-list="[5, 10, 20, 50, 100, 200]">
                        <thead>
                            <tr>
                                <th data-field="radio" data-checkbox="true"></th>
                                <th data-field="id" data-visible="false"></th>
                                <th data-field="name" data-align="center">员工姓名</th>
                                <th data-field="kq" data-align="center">签卡(次)</th>
                                <th data-field="jq" data-align="center">请假(次)</th>
                                <th data-field="cc" data-align="center">出差(次)</th>
                                <th data-field="jb" data-align="center">加班(次)</th>
                                <th data-field="tx" data-align="center">调休(次)</th>
                                <th data-field="operate" data-align="center">操作</th>
                            </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <hr>
            <div class="row" style="margin-top: 15px">
                <div class="col-md-1"></div>
                <div class="col-md-4">
                    <div class="form-group" style="margin-left: -15px">
                        <div style="">
                            <label for="dtp_input2" class="col-md-3 control-label">时间从:</label>
                        </div>
                        <div id="form_date1" class="input-group date form_date col-md-9" data-date="" data-date-format="yyyy MM dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                            <input class="form-control" id="dtp_input1" size="16" type="text" value="" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                        <input type="hidden" id="dtp_input2" value="" />
                        <br/>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group" style="margin-left: -15px">
                        <div style="">
                            <label for="dtp_input4" class="col-md-3 control-label">至:</label>
                        </div>
                        <div id="form_date2" class="input-group date form_date col-md-9" data-date="" data-date-format="yyyy MM dd" data-link-field="dtp_input4" data-link-format="yyyy-mm-dd">
                            <input class="form-control" id="dtp_input3" size="16" type="text" value="" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                        <input type="hidden" id="dtp_input4" value="" />
                        <br/>
                    </div>
                </div>
                <div class="col-md-1">
                    <button type="button" style="width: 100px" class="btn btn-primary" data-dismiss="modal" id="btn">查询</button>
                </div>
                <div class="col-md-1">
                    <button type="button" style="width: 100px" id="exit" class="btn btn-default" data-dismiss="modal">退出</button>
                </div>
                <div class="col-md-1"></div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        $('.form_date').datetimepicker({
            language: 'zh-CN',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0
        });
    </script>
</body>
</html>