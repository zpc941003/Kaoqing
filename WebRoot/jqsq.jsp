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
    $("#index2").addClass('active');
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
                                <th data-field="radio" data-radio="true"></th>
                                <th data-field="id" data-visible="false"></th>
                                <th data-field="starttime" data-align="center">开始时间</th>
                                <th data-field="endtime" data-align="center">结束时间</th>
                                <th data-field="period" data-align="center">假期时长</th>
                                <th data-field="name" data-align="center">员工姓名</th>
                                <th data-field="category" data-align="center">假期类别</th>
                                <th data-field="state" data-formatter="operateFormatter" data-align="center">假期原因</th>
                                <th data-field="operate" data-formatter="operateFormatter" data-align="center">操作</th>
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
                            <label for="dtp_input2" class="col-md-3 control-label">开始日期:</label>
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
                            <label for="dtp_input4" class="col-md-3 control-label">结束日期:</label>
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
                <div class="col-md-2">
                    <div class="row">
                        <div class="col-md-5">
                            <label for="sel">假期类别:</label>
                        </div>
                        <div class="col-md-7">
                            <select id="sel" class="form-control">
                                <option value="病假" selected="selected">病假</option>
                                <option value="事假">事假</option>
                                <option value="产假">产假</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-md-1"></div>
            </div>
            <div class="row" style="margin: 5px 0px 50px 0px">
                <div class="col-md-1"></div>
                <div class="col-md-6" style="margin-left: -15px">
                    <label class="control-label">假期原因:</label>
                    <textarea type="text" class="form-control" id="state" autocomplete="off"></textarea>
                </div>
                <div class="col-md-1" style="margin: 30px 0px 0px 20px">
                    <button type="button" style="width: 100px" class="btn btn-primary" data-dismiss="modal" id="btn">添加</button>
                </div>
                <div class="col-md-1" style="margin: 30px 0px 0px 20px">
                    <button type="button" style="width: 100px" id="alter" class="btn btn-success" data-dismiss="modal">修改</button>
                </div>
                <div class="col-md-1" style="margin: 30px 0px 0px 20px">
                    <button type="button" style="width: 100px" id="save" class="btn btn-warning" data-dismiss="modal">保存修改</button>
                </div>
                <div class="col-md-1" style="margin: 30px 0px 0px 20px">
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

        function operateFormatter(value, row, index) {
            return [
                '<a href="javascript:void(0)" title="删除" onclick=deleterecord("' + row.id + '")>',
                '<i class="fa fa-times"></i>',
                '</a>'
            ].join('');
        }

        function deleterecord(id){
            $.ajax({
                type: "POST",
                url: "servlet/ServletJqsq",
                dataType: "json",
                data: {
                    "operate": "delete",
                    "id":id,
                },
                success: function(jsonResult) {
                    if(jsonResult.msg=="success"){
                        $("#Table").bootstrapTable('refresh', {
                            url: "servlet/ServletJqsq"
                        });
                    }
                },
                error: function(jqXHR, textStatus) {}

            })
        }

        $(document).ready(function() {

        $("#Table").bootstrapTable('refresh', {
            url: "servlet/ServletJqsq"
        });

        $("#btn").bind("click", function() {//--------------------添加
            if ($("#dtp_input2").val() == '') {
                alert("请选择开始时间");
                return false;
            }
            if ($("#dtp_input4").val() == '') {
                alert("请选择结束时间");
                return false;
            }
            if ($("#state").val() == '') {
                alert("请填写假期原因");
                return false;
            }

            var date1 = new Date($("#dtp_input2").val())
            var date2 = new Date($("#dtp_input4").val())
            var s1 = date1.getTime();
            var s2 = date2.getTime();
            var total = (s2 - s1)/(1000*3600*24);
            var totalday=total+"天";
            $.ajax({
                type: "POST",
                url: "servlet/ServletJqsq",
                dataType: "json",
                data: {
                    "operate": "add",
                    "starttime": $("#dtp_input2").val(),
                    "endtime": $("#dtp_input4").val(),
                    "category":$("#sel").val(),
                    "name":$("#username").html(),
                    "period":totalday,
                    "state":$("#state").val()
                },
                success: function(jsonResult) {
                    console.log(jsonResult);
                    if(jsonResult.msg=="success"){
                        $("#Table").bootstrapTable('refresh', {
                            url: "servlet/ServletJqsq"
                        });
                        $("#state").val("");
                        $("#dtp_input1").val("");
                        $("#dtp_input2").val("");
                        $("#dtp_input3").val("");
                        $("#dtp_input4").val("");
                        $("#sel option:first").prop("selected", 'selected');
                    }else if(jsonResult.msg=="error"){
                        alert("添加失败");
                    }

                },
                error: function(jqXHR, textStatus) {
                    alert(jqXHR+" "+textStatus);
                }
            });
        })

        $("#exit").bind("click", function() {
            $.ajax({
                type: "POST",
                url: "servlet/ServletJqsq",
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

        var id=0;
        $("#alter").bind("click", function() {
            console.log($("#Table").bootstrapTable('getSelections'));
            var json=$("#Table").bootstrapTable('getSelections');
            if(json.length==0){
                alert("请先选择记录")
            }else{
            $("#sel option[value='"+json[0].category+"']").prop("selected",'selected');
            $('#form_date1').datetimepicker('update', json[0].starttime);
            $('#form_date2').datetimepicker('update', json[0].endtime);
            $("#state").val(json[0].state);
            id=json[0].id;
            }
        })

        $("#save").bind("click", function() {//--------------------修改
            if ($("#dtp_input2").val() == '') {
                alert("请选择开始时间");
                return false;
            }
            if ($("#dtp_input4").val() == '') {
                alert("请选择结束时间");
                return false;
            }
            if ($("#state").val() == '') {
                alert("请填写假期原因");
                return false;
            }

            var date1 = new Date($("#dtp_input2").val())
            var date2 = new Date($("#dtp_input4").val())
            var s1 = date1.getTime();
            var s2 = date2.getTime();
            var total = (s2 - s1)/(1000*3600*24);
            var totalday=total+"天";
            $.ajax({
                type: "POST",
                url: "servlet/ServletJqsq",
                dataType: "json",
                data: {
                    "operate": "alter",
                    "id":id,
                    "starttime": $("#dtp_input2").val(),
                    "endtime": $("#dtp_input4").val(),
                    "category":$("#sel").val(),
                    "name":$("#username").html(),
                    "period":totalday,
                    "state":$("#state").val()
                },
                success: function(jsonResult) {
                    console.log(jsonResult);
                    if(jsonResult.msg=="success"){
                        $("#Table").bootstrapTable('refresh', {
                            url: "servlet/ServletJqsq"
                        });
                        id=0;
                        $("#state").val("");
                        $("#dtp_input1").val("");
                        $("#dtp_input2").val("");
                        $("#dtp_input3").val("");
                        $("#dtp_input4").val("");
                        $("#sel option:first").prop("selected", 'selected');
                    }else if(jsonResult.msg=="error"){
                        alert("修改失败");
                    }

                },
                error: function(jqXHR, textStatus) {
                    alert(jqXHR+" "+textStatus);
                }
            });
        })
    });
    </script>
</body>
</html>