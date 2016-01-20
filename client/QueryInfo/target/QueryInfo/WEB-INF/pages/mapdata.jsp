<%@page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>机房信息查询</title>
</head>
<body>
<div id="allmap" class="col-sm-12 col-xs-12"></div>	
</body>
<script type="text/javascript">
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(120.914912, 31.977903);
	var marker = new BMap.Marker(point); // 创建标注
	map.addOverlay(marker); // 将标注添加到地图中
	map.centerAndZoom(point, 15); //地图聚焦在point点上以15级显示
	map.enableScrollWheelZoom(); //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom(); //启用地图惯性拖拽，默认禁用
	function querynowData() {
		var murl = "${pageContext.request.contextPath}/mapdata/querydata";
		$
				.ajax({
					type : "post",
					url : murl,
					dataType : "json",
					success : function(result) {
						if(result.hasOwnProperty("backTemp")){
							var mymessage = "背板温度: ";
							mymessage += result.backTemp;
							mymessage += "</br>室内温度: ";
							mymessage += result.sensorTemp;
							mymessage += "</br>室内湿度: ";
							mymessage += result.sensorHumlity;
							mymessage += "</br>室内光照: ";
							mymessage += result.light;
							mymessage += "</br>人体感应: ";
							mymessage += result.humansensor;
							mymessage += "</br>振动感应: ";
							mymessage += result.shake;
						}else{
							mymessage = '<br>当前没有接收到信息!';
						}
					    var opts = {
									width : 20, // 信息窗口宽度
									height : 160, // 信息窗口高度
									title : "<span style='color: red; font-size: 15px; font-family:SimSun;'>机房-1：</br></span>", // 信息窗口标题
									enableMessage : false, //设置允许信息窗发送短息
									message : mymessage
								}
						var infoWindow = new BMap.InfoWindow(mymessage, opts); // 创建信息窗口对象
						map.openInfoWindow(infoWindow, point); //开启信息窗口
						
					},
					error : function() {
						console.log("error");
					}
				});
	}

	$(function() {
		querynowData();
		clearInterval(it2);
		it2 = setInterval(querynowData,60000);
	});
</script>
</html>