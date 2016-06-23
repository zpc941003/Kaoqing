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
    <link rel="stylesheet" type="text/css" href="css/bootstrap-multiselect.css">
    <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-table.js"></script>
    <script type="text/javascript" src="js/time.js"></script>
    <script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
</head>

<body>
    <script type="text/javascript" src="js/sidebar.js"></script>
    <script type="text/javascript">
    $("#index3").addClass('active');
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
                                <th data-field="time" data-align="center">出差时间</th>
                                <th data-field="period" data-align="center">出差天数(天)</th>
                                <th data-field="name" data-align="center">员工姓名</th>
                                <th data-field="friend" data-align="center">同行人员</th>
                                <th data-field="place" data-align="center">目的地</th>
                                <th data-field="transport" data-align="center">出行方式</th>
                                <th data-field="state" data-align="center">出差事由</th>
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
                            <label for="dtp_input2" class="col-md-3 control-label">出差日期:</label>
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
                <div class="col-md-3">
                    <div class="row">
                    <div class="col-md-4">
                        <label for="period">出差天数:</label>
                    </div>
                    <div class="col-md-8">
                        <input type="text" id="period" class="form-control">
                    </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="row">
                        <div class="col-md-3">
                            <label>同行人员:</label>
                        </div>
                        <div class="col-md-9">
                            <select id="mulsel" multiple="multiple">
<!--                                 <option value="cheese">Cheese</option>
                                <option value="tomatoes">Tomatoes</option>
                                <option value="mozarella">Mozzarella</option>
                                <option value="mushrooms">Mushrooms</option>
                                <option value="pepperoni">Pepperoni</option>
                                <option value="onions">Onions</option> -->
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row" style="margin-top: 15px">
                <div class="col-md-1"></div>
                <div class="col-md-5">
                    <div class="row">
                        <div class="col-md-2">
                            <label for="period">目的地:</label>
                        </div>
                        <div class="col-md-8">
                            <input type="text" id="place" class="form-control">
                        </div>
                    </div>
                </div>
                <div class="col-md-5">
                    <div class="row">
                        <div class="col-md-2">
                            <label>出行方式:</label>
                        </div>
                        <div class="col-md-8">
                            <select id="sel" class="form-control">
                                <option value="大巴" selected="selected">大巴</option>
                                <option value="飞机">飞机</option>
                                <option value="火车">火车</option>
                                <option value="自驾">自驾</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-md-1"></div>
            </div>
            <div class="row" style="margin: 15px 0px 50px 0px">
                <div class="col-md-1"></div>
                <div class="col-md-6" style="margin-left: -15px">
                    <label class="control-label">出差事由:</label>
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
    function isNumber(number){//判断是否是数字
        return typeof number === 'number'
    }

    function getSelectedstr() {
        var selected =$('#mulsel option:selected');
        var arr=[];
        for(var i=0;i<selected.length;i++){
            arr.push(selected[i].getAttribute("value"));
        }
        var str=arr.join(",");
        return str
    }

    function deleterecord(id){
        $.ajax({
            type: "POST",
            url: "servlet/ServletCcsq",
            dataType: "json",
            data: {
                "operate": "delete",
                "id":id,
            },
            success: function(jsonResult) {
                if(jsonResult.msg=="success"){
                    $("#Table").bootstrapTable('refresh', {
                        url: "servlet/ServletCcsq"
                    });
                }
            },
            error: function(jqXHR, textStatus) {}

        })
    }
    function operateFormatter(value, row, index) {
        return [
            '<a href="javascript:void(0)" title="删除" onclick=deleterecord("' + row.id + '")>',
            '<i class="fa fa-times"></i>',
            '</a>'
        ].join('');
    }
    $(document).ready(function() {
        $("#Table").bootstrapTable('refresh', {
            url: "servlet/ServletCcsq"
        });

        $('#mulsel').multiselect();
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
        $.ajax({
            type: "POST",
            url: "servlet/ServletCcsq",
            dataType: "json",
            data: {
                "operate": "getpeople"
            },
            success: function(jsonResult) {
                console.log(jsonResult);
                $('#mulsel').empty();
                for(var i=0;i<jsonResult.length;i++){
                    $('#mulsel').append('<option value="' + jsonResult[i] + '">' + jsonResult[i] + '</option>');
                }
                $('#mulsel').multiselect('rebuild');
            },
            error: function(jqXHR, textStatus) {}

        })

        $("#btn").bind("click", function() {//--------------------添加
            if ($("#dtp_input2").val() == '') {
                alert("请选择出差时间");
                return false;
            }
            if ($("#period").val() == '') {
                alert("请填写出差天数");
                return false;
            }
            if ($("#place").val() == '') {
                alert("请填写目的地");
                return false;
            }
            if ($("#state").val() == '') {
                alert("请填写出差事由");
                return false;
            }
            $.ajax({
                type: "POST",
                url: "servlet/ServletCcsq",
                dataType: "json",
                data: {
                    "operate": "add",
                    "time": $("#dtp_input2").val(),
                    "friend":getSelectedstr(),
                    "name":$("#username").html(),
                    "period":$("#period").val(),
                    "state":$("#state").val(),
                    "place":$("#place").val(),
                    "transport":$("#sel").val()
                },
                success: function(jsonResult) {
                    console.log(jsonResult);
                    if(jsonResult.msg=="success"){
                        $("#Table").bootstrapTable('refresh', {
                            url: "servlet/ServletCcsq"
                        });
                        $("#state").val("");
                        $("#place").val("");
                        $("#period").val("");
                        $('#mulsel').multiselect('deselectAll', false);
                        $('#mulsel').multiselect('updateButtonText');
                        $("#dtp_input1").val("");
                        $("#dtp_input2").val("");
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
                url: "servlet/ServletCcsq",
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
            var str=json[0].friend;
            var arr=str.split(",");
            $('#mulsel').multiselect('select', arr);
            $('.form_date').datetimepicker('update', json[0].time);
            $("#sel option[value='"+json[0].transport+"']").prop("selected", 'selected');
            $("#period").val(json[0].period);
            $("#place").val(json[0].place);
            $("#state").val(json[0].state);
            id=json[0].id;
            }
        })


        $("#save").bind("click", function() {//--------------------修改
            if ($("#dtp_input2").val() == '') {
                alert("请选择出差时间");
                return false;
            }
            if ($("#period").val() == '') {
                alert("请填写出差天数");
                return false;
            }
            if ($("#place").val() == '') {
                alert("请填写目的地");
                return false;
            }
            if ($("#state").val() == '') {
                alert("请填写出差事由");
                return false;
            }
            $.ajax({
                type: "POST",
                url: "servlet/ServletCcsq",
                dataType: "json",
                data: {
                    "operate": "alter",
                    "id":id,
                    "time": $("#dtp_input2").val(),
                    "friend":getSelectedstr(),
                    "name":$("#username").html(),
                    "period":$("#period").val(),
                    "state":$("#state").val(),
                    "place":$("#place").val(),
                    "transport":$("#sel").val()
                },
                success: function(jsonResult) {
                    console.log(jsonResult);
                    if(jsonResult.msg=="success"){
                        $("#Table").bootstrapTable('refresh', {
                            url: "servlet/ServletCcsq"
                        });
                        id=0;
                        $("#state").val("");
                        $("#place").val("");
                        $("#period").val("");
                        $('#mulsel').multiselect('deselectAll', false);
                        $('#mulsel').multiselect('updateButtonText');
                        $("#dtp_input1").val("");
                        $("#dtp_input2").val("");
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