<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/webresource/js/jfconfig.js"></script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/webresource/css/jfconfig.css">

</head>
<body>
	<div style="height: 600px; margin-top: 10px;">
		<div class="row">
			<div class="col-sm-3">
				<div class="tree well">
					<div class="mtitle">
						<span style="padding-left: 10px;">机房管理</span>
					</div>
					<ul style="padding-left: 10px;">
						<li><span><i class="icon-folder-open"></i>机房&nbsp;&nbsp;<i
								class="glyphicon glyphicon-menu-down"></i></span>
							<ul id="jfmenu">
								<c:forEach items="${omputerroomList}" var="eachItem"
									varStatus="i">
									<li><span jfid="${eachItem.jfid}" style="cursor: pointer;"><i
											class="glyphicon glyphicon-modal-window"></i>&nbsp;&nbsp;${eachItem.address}</span></li>
								</c:forEach>
							</ul></li>
					</ul>
				</div>
			</div>
			<div class="col-sm-9" style="padding: 0px; min-width: 200px;">
				<div class="toolpanel">
					<div class="mtitle">
						<span style="padding-left: 10px;">参数配置</span>
					</div>
					<table class="table table-bordered table-striped">
						<thead></thead>
						<tbody>
							<tr>
								<td style="width: 30%">机房地址</td>
								<td><input id="jfadd" type="text" disabled="disabled"></td>
							</tr>
							<tr>
								<td style="width: 30%">IP地址</td>
								<td style="width: 70%"><select id="jfip"
									style="height: 26px; width: 174px;">
										<option>${hostip}</option>
										<option>127.0.0.1</option>
								</select></td>
							</tr>
							<tr>
								<td style="width: 30%">端口号</td>
								<td style="width: 70%"><input id="jfport" type="text"
									disabled="disabled"></td>
							</tr>
							<tr>
								<td style="width: 30%">监听模式</td>
								<td style="width: 70%"><input style="cursor: pointer;"
									type="radio" id="model1" name="model" value="TCP"> <label
									for="model1" style="cursor: pointer;">TCP&nbsp;&nbsp;&nbsp;</label>
									<input style="cursor: pointer;" type="radio" id="model2"
									name="model" value="UDP"> <label for="model2"
									style="cursor: pointer;">UDP</label></td>
							</tr>
							<tr>
								<td style="width: 30%">开始监听</td>
								<td style="width: 70%">
									<div id="btnstart" class="btn btn-info"
										onclick="startstop('start');">
										<i class="glyphicon glyphicon-play"></i>&nbsp;开始
									</div>&nbsp;&nbsp;&nbsp;
									<div id="btnstop" class="btn btn-danger"
										onclick="startstop('stop');">
										<i class="glyphicon glyphicon-stop"></i>&nbsp;停止
									</div>

								</td>
							</tr>
							<tr>
								<td style="width: 30%">短信开关</td>
								<td style="width: 70%" id="msgsendtd">
								  <div id="myswitch" class="switch has-switch" style="width:106px">
									<input id="testcheck" type="checkbox" checked />
								  </div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<div id="showstate" class="modal hade" style="width: 350px">
		<div class="text-center"
			style="height: 40px; line-height: 40px; border: 0px; padding: 1px; margin: 0px 0px; background-image: linear-gradient(to bottom, #337ab7 0%, #265a88 100%);">
			<span style="color: white">提示</span>
			<button type="button" class="close" data-dismiss="modal"
				onclick="freshpage();" aria-hidden="true" style="margin: 4px 4px;"
				onclick="freshpage();">&times;</button>
		</div>
		<div class="modal-body" style="margin-top: 0px; padding: 0px;">
			<ul class="list-group">
				<li class="list-group-item text-center"><span>请输入密码：<input
						type="password"></span></li>
				<li class="list-group-item">
					<div class="row">
						<div id="btnok" class="btn btn-primary btn-sm col-sm-offset-8">确定</div>
						<div id="cancel5" class="btn btn-primary btn-sm"
							data-dismiss="modal" onclick="freshpage();">取消</div>
					</div>
				</li>
				<li id="mtipsdelete" class="list-group-item text-center  fade"><div
						class="alert alert-success" style="margin: 0px; padding: 0px;"></div></li>
			</ul>
		</div>
	</div>
</body>
<script type="text/javascript">
	var currentjfid; //global var

	function freshpage() {
		$("#navigator a[_href=jfconfig]").click();
	}

	function getcfgparm(jfid) { //send the ajax to get configuration parameters
		var url = "${pageContext.request.contextPath}/nettyconfig/getconfig";
		$.ajax({
			type : 'post',
			url : url,
			data : jfid,
			dataType : 'json',
			contentType : "application/json; charset=utf-8",
			success : function(result) {
				$("#jfadd").val(result.jfaddress);
				$("#jfport").val(result.port);
				$("#jfip").val(result.ip);
				if (result.start.trim() == 'YES') {
					$("input:radio").removeAttr("disabled");
					if (result.model.trim() == 'TCP') {
						$("#model1").click();
					} else {
						$("#model2").click();
					}
					$("#btnstart").attr("disabled", "disabled");
					$("select").attr("disabled", "disabled");
					$("input:radio").attr("disabled", "disabled");
					$("#btnstop").removeAttr("disabled");
				} else {
					$("#btnstop").attr("disabled", "disabled");
					$("#btnstart").removeAttr("disabled");
					$("select").removeAttr("disabled");
					$("input:radio").removeAttr("disabled");
					if (result.model.trim() == 'TCP') {
						$("#model1").click();
					} else {
						$("#model2").click();
					}
				}
				if (result.sendmsg == 'YES') {
					$("#testcheck").bootstrapSwitch('state',true);
				} else {
					$("#testcheck").bootstrapSwitch('state',false);
				}
			},
			error : function() {
				console.log("error");
			}
		});
	}
	function actionstartstop(startstop) {
		var data = {};
		data.jfid = currentjfid;
		data.ip = $("#jfip").val();
		data.model = $("input:radio:checked").val();
		data.port = $("#jfport").val();
		if (startstop == 'start') {
			data.isstart = 'start';
		} else {
			data.isstart = 'stop';
		}
		var url = "${pageContext.request.contextPath}/nettyconfig/startstop";
		$.ajax({
			type : 'post',
			url : url,
			data : JSON.stringify(data),
			contentType : "application/json; charset=utf-8",
			success : function(result) {
				$("span[jfid='" + currentjfid + "']").click();
			},
			error : function() {
				console.log("error");
			}

		});
	}
	function startstop(startstop) {
		$("#btnok").removeAttr("disabled");
		$("input[type='password']").val("");
		$("#btnok").unbind("click");
		$("#showstate").css({
			"margin" : "auto",
			"top" : "150px"
		});
		$("#showstate").modal("show");
		$("#btnok").on("click", function() {
			var user = {};
			var url = "${pageContext.request.contextPath}";
			user.name = $("#usrlab").html();
			user.passwd = $("input[type='password']").val();
			console.log(user);
			$.ajax({
				type : 'post',
				url : url + "/loginvalid",
				data : JSON.stringify(user),
				dataType : 'json',
				contentType : "application/json; charset=utf-8",
				success : function(result) {
					console.log(result);
					if (result.success == 'haslogin') {
						actionstartstop(startstop);
						$("#btnok").attr("disabled", "disabled");
					}
				},
				error : function() {
					console.log("error");
				}
			});
		});
	}

	function liclick() {
		var jfid = $(this).attr("jfid");
		currentjfid = jfid;
		$("#jfmenu").find("li span").css({
			"background-color" : "",
			"cursor" : "pointer"
		});
		$(this).css({
			"background-color" : "white",
			"cursor" : "pointer"
		});
		getcfgparm(jfid);
	}
	function cfgsendmsg(e,data){
        var url = "${pageContext.request.contextPath}/nettyconfig/cfgsendmsg";
        var senddata = {};
        senddata.jfid = currentjfid;
        senddata.sendmsg = ""+data;
        senddata.port = $("#jfport").val();
        senddata.model = $("input:radio:checked").val();
	    $.ajax({
				type : 'post',
				url : url,
				data : JSON.stringify(senddata),
				dataType : 'json',
				contentType : "application/json; charset=utf-8",
				success : function(result) {
					if(result.state = 'error'){
						
					}
				},
				error:function(){
					console.log("error");
				}
		});
	}
	$(function() {
		$("#testcheck").bootstrapSwitch();
		$("#testcheck").on("switchChange.bootstrapSwitch",cfgsendmsg);
		initMenuClick(); //init the menu click
		$("#jfmenu li span").on("click", liclick);
		$("#jfmenu").find("li:eq(0) span").click();
	});
</script>
</html>