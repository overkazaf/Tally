<%@page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<div id="jfrow" style="margin-top: 5px; height: 300px; background-color: #B0E0E6;">
	</div>
	<div style="height: 260px; background-color: #B0E0E6;">
		<div class="col-sm-4"></div>
		<div class="col-sm-4 text-center" style="margin-top: 40px;">
			<span>监控中心</span><br> <br> <a href="#"><img
				src="${pageContext.request.contextPath}/webresource/images/worker.png"
				alt="监控中心" title=""></a><br>
		</div>
		<div class="col-sm-4"></div>
	</div>
</body>
<script type="text/javascript">
    function getjfstate(){
    	var state = "";
		var url = "${pageContext.request.contextPath}/main/getjfstate"
			$.ajax({
				type:'post',
			    url:url,
			    dataType:'json',
			    success:function(result){
			    	var currentInfo = result.currentInfo;
			    	var threshold = result.threshold;
			    	
			    	if(currentInfo.sensorTemp > threshold.temphold){
			    		state += "温度过高";
			    	}
			    	if(currentInfo.sensorHumlity > threshold.humilityhold){
			    		state += "#湿度过高";
			    	}
			    	if(currentInfo.humansensor > threshold.humanhold){
			    		state += "#有人体接触";
			    	}
			    	if(currentInfo.shake > threshold.shakehold){
			    		state += "#有震动产生";
			    	}
			    },
			    error:function(){
			    	console.log("error");
			    }
			});
		return state;
    }
    function spark() {
		var labellist = $(".label");		
		for (i = 0; i < labellist.length; i++) {
			var labclass = $(labellist[i]).attr("class");
			var csss = labclass.split(" ");
			if (csss[1] != 'label-default') {
				$(labellist[i]).removeClass(csss[1]);
				$(labellist[i]).addClass("label-default");
			} else {
				$(labellist[i]).removeClass(csss[1]);
				var newstate = "label-" + $(labellist[i]).attr("state");
				$(labellist[i]).addClass(newstate);
			}
		}
	}
    function initjf(){
    	var url = "${pageContext.request.contextPath}/main/getjfs"
			$.ajax({
				type:'post',
			    url:url,
			    dataType:'json',
			    success:function(result){
			    	var jfnum = result.length;
			    	var colsize = Math.round(12/jfnum);
			    	var jfrow = $("#jfrow");
			    	for(i =0;i<jfnum;i++){
			    		var info = result[i].labAdmin["name"]+"#"+result[i].labAdmin["phone"];
			    		var jf = $('<div class="col-sm-'+colsize+' text-center" style="margin-top: 40px;">'+
			    		           '<span>'+result[i].address+'</span><br>'+
			    		           '<a href="#"><img src="${pageContext.request.contextPath}/webresource/images/center.png" title="" alt="机房" info="'+result[i].jfid+"#"+info+'"></a><br> '+
			    		           '<span class="label label-success" state="success"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>'+
			    		           '</div>');
			    		jfrow.append(jf);
			    	}
			    },
			    error:function(){
			    	console.log("error");
			    }
			});
    }
	$(function() {
		initjf();
		$(".infobox").hide();
		$("body").on("mouseover",'img[title]',roomadmininfo);
		$("body").on("mouseout",'img[title]',onleavpicture);
		clearInterval(it1);
		it1 = setInterval(spark, 1000);
		
	});
</script>
<div class="infobox">
	<div class="text-center alert alert-info"style="height: 25px; padding: 0px; margin: 2px;">管理员信息</div>
	<div >
		<div class="col-sm-4"  style="border: 1px solid #BEBEBE; height: 60px;margin-left:2px;padding:0px;"><img alt="" src="${pageContext.request.contextPath }/webresource/images/photo.png"></div>
		<div class="col-sm-7"  style="margin-left:4px;padding:0px;">
		<div style="margin:3px 0px;"><span id="name" style="font-size:12px;">姓名：潘飞</span></div>
		<div style="margin:3px 0px;"><span style="font-size:12px;">性别：男</span></div>
		<div style="margin:3px 0px;"><span id="phone" style="font-size:12px;"></span></div>
		</div>
	</div>
</div>
</html>