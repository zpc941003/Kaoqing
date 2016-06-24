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
                                <th data-field="username" data-align="center">员工姓名</th>
                                <th data-field="kq" data-align="center">签卡(次)</th>
                                <th data-field="jq" data-align="center">请假(次)</th>
                                <th data-field="cc" data-align="center">出差(次)</th>
                                <th data-field="jb" data-align="center">加班(次)</th>
                                <th data-field="tx" data-align="center">调休(次)</th>
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
    <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">详细信息</h4>
                </div>
                <div class="modal-body">
                    <table id="Table1" data-toggle="table" data-height="400" data-side-pagination="server" data-pagination="true" data-page-list="[5, 10, 20, 50, 100, 200]">
                        <thead>
                            <tr>
                                <th data-field="id" data-visible="false"></th>
                                <th data-field="time" data-align="center">考勤时间</th>
                                <th data-field="category" data-align="center">类别</th>
                                <th data-field="name" data-align="center">员工姓名</th>
                                <th data-field="period" data-align="center">考勤时段</th>
                                <th data-field="state" data-align="center">考勤说明</th>
                            </tr>
                        </thead>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">详细信息</h4>
                </div>
                <div class="modal-body">
                    <table id="Table2" data-toggle="table" data-height="400" data-side-pagination="server" data-pagination="true" data-page-list="[5, 10, 20, 50, 100, 200]">
                        <thead>
                            <tr>
                                <th data-field="id" data-visible="false"></th>
                                <th data-field="starttime" data-align="center">开始时间</th>
                                <th data-field="endtime" data-align="center">结束时间</th>
                                <th data-field="period" data-align="center">假期时长</th>
                                <th data-field="name" data-align="center">员工姓名</th>
                                <th data-field="category" data-align="center">假期类别</th>
                                <th data-field="state" data-formatter="operateFormatter" data-align="center">假期原因</th>
                            </tr>
                        </thead>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">详细信息</h4>
                </div>
                <div class="modal-body">
                    <table id="Table3" data-toggle="table" data-height="400" data-side-pagination="server" data-pagination="true" data-page-list="[5, 10, 20, 50, 100, 200]">
                        <thead>
                            <tr>
                                <th data-field="id" data-visible="false"></th>
                                <th data-field="time" data-align="center">出差时间</th>
                                <th data-field="period" data-align="center">出差天数(天)</th>
                                <th data-field="name" data-align="center">员工姓名</th>
                                <th data-field="friend" data-align="center">同行人员</th>
                                <th data-field="place" data-align="center">目的地</th>
                                <th data-field="transport" data-align="center">出行方式</th>
                                <th data-field="state" data-align="center">出差事由</th>
                            </tr>
                        </thead>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <div class="modal fade" id="myModal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">详细信息</h4>
                </div>
                <div class="modal-body">
                    <table id="Table4" data-toggle="table" data-height="400" data-side-pagination="server" data-pagination="true" data-page-list="[5, 10, 20, 50, 100, 200]">
                        <thead>
                            <tr>
                                <th data-field="id" data-visible="false"></th>
                                <th data-field="time" data-align="center">申请时间</th>
                                <th data-field="jbtime" data-align="center">加班时间</th>
                                <th data-field="period" data-align="center">加班时长(天)</th>
                                <th data-field="name" data-align="center">员工姓名</th>
                                <th data-field="state" data-align="center">加班原因</th>
                            </tr>
                        </thead>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <div class="modal fade" id="myModal5" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">详细信息</h4>
                </div>
                <div class="modal-body">
                    <table id="Table5" data-toggle="table" data-height="400" data-side-pagination="server" data-pagination="true" data-page-list="[5, 10, 20, 50, 100, 200]">
                        <thead>
                            <tr>
                                <th data-field="id" data-visible="false"></th>
                                <th data-field="time" data-align="center">申请时间</th>
                                <th data-field="starttime" data-align="center">调休时间(起)</th>
                                <th data-field="endtime" data-align="center">调休时间(止)</th>
                                <th data-field="period" data-align="center">调休时长</th>
                                <th data-field="name" data-align="center">员工姓名</th>
                                <th data-field="state" data-align="center">调休原因</th>
                            </tr>
                        </thead>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <a href="javascript:void(0)" class="kq" data-toggle='modal' data-target='#myModal'>asdasasdasdasd</a>
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

        function dokq(e){
            console.log(e);
            $.ajax({
                type: "POST",
                url: "servlet/ServletKqcx",
                dataType: "json",
                data: {
                    "operate": "kq",
                    "name":e.getAttribute("title"),
                },
                success: function(jsonResult) {
                    if(jsonResult.msg=="success"){
                        $("#Table1").bootstrapTable('refresh', {
                            url: "servlet/ServletXxxx?operate=kq"
                        });
                    }
                },
                error: function(jqXHR, textStatus) {}

            })
        }

        function dojq(e){
            console.log(e);
            $.ajax({
                type: "POST",
                url: "servlet/ServletKqcx",
                dataType: "json",
                data: {
                    "operate": "jq",
                    "name":e.getAttribute("title"),
                },
                success: function(jsonResult) {
                    if(jsonResult.msg=="success"){
                        $("#Table2").bootstrapTable('refresh', {
                            url: "servlet/ServletXxxx?operate=jq"
                        });
                    }
                },
                error: function(jqXHR, textStatus) {}

            })
        }

        function docc(e){
            console.log(e);
            $.ajax({
                type: "POST",
                url: "servlet/ServletKqcx",
                dataType: "json",
                data: {
                    "operate": "cc",
                    "name":e.getAttribute("title"),
                },
                success: function(jsonResult) {
                    if(jsonResult.msg=="success"){
                        $("#Table3").bootstrapTable('refresh', {
                            url: "servlet/ServletXxxx?operate=cc"
                        });
                    }
                },
                error: function(jqXHR, textStatus) {}

            })
        }
        function dojb(e){
            console.log(e);
            $.ajax({
                type: "POST",
                url: "servlet/ServletKqcx",
                dataType: "json",
                data: {
                    "operate": "jb",
                    "name":e.getAttribute("title"),
                },
                success: function(jsonResult) {
                    if(jsonResult.msg=="success"){
                        $("#Table4").bootstrapTable('refresh', {
                            url: "servlet/ServletXxxx?operate=jb"
                        });
                    }
                },
                error: function(jqXHR, textStatus) {}

            })
        }
        function dotx(e){
            console.log(e);
            $.ajax({
                type: "POST",
                url: "servlet/ServletKqcx",
                dataType: "json",
                data: {
                    "operate": "tx",
                    "name":e.getAttribute("title"),
                },
                success: function(jsonResult) {
                    if(jsonResult.msg=="success"){
                        $("#Table5").bootstrapTable('refresh', {
                            url: "servlet/ServletXxxx?operate=tx"
                        });
                    }
                },
                error: function(jqXHR, textStatus) {}

            })
        }


        $(document).ready(function() {
            
            $("#btn").bind("click", function() {
                if ($("#dtp_input2").val() == '') {
                    alert("请选择起始时间");
                    return false;
                }
                if ($("#dtp_input4").val() == '') {
                    alert("请选择终止时间");
                    return false;
                }
                $("#Table").bootstrapTable('refresh', {
                    url: "servlet/ServletKqcx?date1="+$("#dtp_input2").val()+"&date2="+$("#dtp_input4").val()
                });
            })

            $("#exit").bind("click", function() {
                $.ajax({
                    type: "POST",
                    url: "servlet/ServletKqcx",
                    dataType: "json",
                    data: {
                        "operate": "exit"
                    },
                    success: function(jsonResult) {
                        if(jsonResult.msg=="success"){
                            window.location.href="login.jsp";
                        }
                    },
                    error: function(jqXHR, textStatus) {}

                })
            })
        })
    </script>
</body>
</html>