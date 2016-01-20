<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<div style="height: 600px;">
		<div class="row" style="margin-top: 10px;">
			<div class="col-sm-4">
				<form action="" class="form-horizontal" role="form">
					<fieldset>
						<div class="form-group">
							<div class="col-sm-4 control-label">开始时间：</div>
							<div id="pickerstart"
								class="input-group date form_datetime col-md-7"
								data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
								<input id="starttime" class="form-control" size="16" type="text"
									value="" readonly> <span class="input-group-addon"><span
									class="glyphicon glyphicon-remove"></span></span> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-th"></span></span>
							</div>
							<input type="hidden" id="dtp_input1" value="" /><br />
						</div>
					</fieldset>
				</form>
			</div>
			<div class="col-sm-4">
				<form action="" class="form-horizontal" role="form">
					<fieldset>
						<div class="form-group">
							<div class="col-sm-4 control-label">结束时间：</div>
							<div id="pickerend"
								class="input-group date form_datetime col-md-7"
								data-date-format="yyyy-mm-dd  hh:ii"
								data-link-field="dtp_input1">
								<input id="endtime" class="form-control" size="16" type="text"
									value="" readonly> <span class="input-group-addon"><span
									class="glyphicon glyphicon-remove"></span></span> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-th"></span></span>
							</div>
							<input type="hidden" id="dtp_input1" value="" /><br />
						</div>
					</fieldset>
				</form>
			</div>
			<div class="col-sm-2">
				<div class="dropdown">
					<button class="btn btn-info" id="dropdownMenu1"
						style="padding: 5px; width: 140px;" type="button"
						id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
						<span id="btnspan">温度</span></span> <span class="caret"></span>
					</button>
					<ul id="ul1" class="dropdown-menu" role="menu"
						aria-labelledby="dropdownMenu1">
						<li role="presentation"><a role="menuitem" tabindex="-1" href="#">温度</a></li>
						<li role="presentation"><a role="menuitem" tabindex="-1" href="#">湿度</a></li>
						<li role="presentation"><a role="menuitem" tabindex="-1" href="#">光照</a></li>
						<li role="presentation"><a role="menuitem" tabindex="-1" href="#">人体感应</a></li>
						<li role="presentation"><a role="menuitem" tabindex="-1" href="#">震动</a></li>
					</ul>
				</div>
			</div>
			<div class="col-sm-2">
				<div class="btn btn-primary btn-sm " style="width: 70px;padding:5px;"
					onclick="querydata();"><i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;<span >查询</span></span></div>
			</div>
		</div>
		<div id="chart" style="height: 400px"></div>
	</div>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/webresource/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
    function liclick(){
    	var livalue = $(this).find("a").html();
    	$(this).siblings().removeClass("list-group-item-info");
    	$(this).addClass("list-group-item-info");
    	$("#btnspan").html(livalue);
    }
	var datas = [];
	var dates = [];
	function drawtable() {
		require.config({
			paths : {
				echarts : 'http://echarts.baidu.com/build/dist'
			}
		});
		require([ 'echarts', 'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
		], function(ec) {
			// 基于准备好的dom，初始化echarts图表
			var myChart = ec.init(document.getElementById('chart'));
			var option = {
				tooltip : {
					show : true
				},
				legend : {
					data : [ '实时温度' ]
				},
				animation : false,
				xAxis : {
					type : 'category',
					data : dates
				},
				yAxis : {
					type : 'value'
				},
				series : [ {
					'name' : '实时数据',
					'type' : 'line',
					'data' : datas
				} ]
			};
			myChart.setOption(option);
		});
	}
	function getcurrentdata() {
		var murl = "${pageContext.request.contextPath}/mapdata/querydata";
		$.ajax({
			type : "post",
			url : murl,
			dataType : "json",
			success : function(result) {
				currentdata = result;
				drawchart();
			},
			error : function() {
				console.log("error");
			}
		});
	}
    var queryobject = {"温度":"sensortemp","湿度":"sensrohumlity","光照":"light","人体感应":"humansensor","震动":"shake"};
	function querydata() {
		var querytype = queryobject[$("#btnspan").html()];
		console.log(querytype);
		var starttime = $("#starttime").val();
		var endtime = $("#endtime").val();
		var url = "${pageContext.request.contextPath}/analysis/getdata";
		var dateobj = {};
		dateobj.starttime = starttime;
		dateobj.endtime = endtime;
		dateobj.querytype = querytype;
		$.ajax({
			type : "post",
			url : url,
			data : JSON.stringify(dateobj),
			contentType : "application/json; charset=utf-8",
			dataType : 'json',
			success : function(result) {
				datas = [];
				dates = [];
				for (var i = 0; i < result.length; i++) {
					datas[i] = result[i].data;
					dates[i] = result[i].date + " " + result[i].time;
				}
				drawtable();
			},
			error : function() {

			}
		});
	}
	$(function() {
		var date = new Date();
		var m = (date.getMonth() + 1) < 10 ? "0" + (date.getMonth() + 1)
				: (date.getMonth() + 1);
		var fulld = date.getFullYear() + "-" + m + "-" + date.getDate();
		$("div[id^='picker']").attr("data-date", fulld);
		$('.form_datetime').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			forceParse : 0,
			pickerPosition : "bottom-left",
			format : "yyyy-mm-dd  hh:ii"
		});
		$("#starttime").val(fulld + " 00:00");
		$("#endtime").val(
				fulld + " " + date.getHours() + ":" + date.getMinutes());
		querydata();
		$("#ul1").find("li:eq(0)").addClass("list-group-item-info");
		$("#ul1 li").on("click",liclick);
	});
</script>
</html>