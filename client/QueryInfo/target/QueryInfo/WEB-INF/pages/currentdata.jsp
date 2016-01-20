<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<div style="height:600px">
<div id="chart" style="height:400px">
</div>
</div>
<script type="text/javascript">
var currentdata = {};
function drawchart(){
	require.config({
	    paths: {
	        echarts: 'http://echarts.baidu.com/build/dist'
	    }
	});
	 require(
	            [
	                'echarts',
	                'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
	            ],
	            function (ec) {
	                // 基于准备好的dom，初始化echarts图表
	                var myChart = ec.init(document.getElementById('chart'));
	                var option = {
	                		tooltip:{show:true},
	                		legend:{data:['实时数据']},
	                		animation:false,
	                		xAxis:{
	                			type:'category',
	                			data:['背板温度','室内温度','室内湿度','室内光照','人体感应','振动感应']
	                		},
	                		yAxis:{
	                			type:'value'
	                		},
	                		series:[{'name':'实时数据',
	                			     'type':'line',
	                			     'data':[currentdata.backTemp,
	                			             currentdata.sensorTemp,
	                			             currentdata.sensorHumlity,
	                			             currentdata.light,
	                			             currentdata.humansensor,
	                			             currentdata.shake]
	                		}]
	                };
	                myChart.setOption(option);
	            });
}
function getcurrentdata(){
	var murl = "${pageContext.request.contextPath}/mapdata/querydata";
	$
			.ajax({
				type : "post",
				url : murl,
				dataType : "json",
				success : function(result) {
					currentdata = result;
					drawchart();
				},
				error:function(){
				   console.log("error");
				}
			});
}
$(function(){
	getcurrentdata();
	it3 = setInterval(getcurrentdata,6000);
});
</script>

</body>
</html>