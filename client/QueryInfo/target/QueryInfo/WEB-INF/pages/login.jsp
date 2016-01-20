<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/webresource/js/jquery-1.11.3.min.js">
	
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/webresource/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/webresource/css/bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/webresource/css/login.css">
<title>机房信息查询</title>
</head>
<body>
	<div class="container">
		<div class="row" style="margin-bottom: 120px;">
			<div class="col-sm-8 col-xs-4">
				<img
					src="${pageContext.request.contextPath}/webresource/images/logo.jpg"
					alt="查信息" />
			</div>
			<div class="col-sm-4 col-xs-8">
				<span class="glyphicon glyphicon-phone-alt pull-right"
					aria-hidden="true" style="margin: 5px; color: red;">:152-6273-8667</span>
			</div>
		</div>
	</div>
	<div id="themodal" class="login">
		<table class="table table-bordered"
			style="margin: auto; width: 300px; margin-top: 40px;">
			<thead>
			</thead>
			<tr>
				<td colspan="2"><h4 style="color: white;">机房信息查询系统</h4></td>
			</tr>
			<tr>
				<td style="color: white;">用户名</td>
				<td><input type="text" maxlength="20" name="name"></td>
			</tr>
			<tr>
				<td style="color: white;">密码</td>
				<td><input type="password" maxlength="20" name="passwd"></td>
			</tr>
			<tr>
				<td colspan="2" class="text-center"
					style="padding-top: 15px; padding-bottom: 15px;"><input
					onclick="msubmit('login');" type="submit" class="btn btn-info" value="登录">&nbsp;&nbsp;&nbsp;&nbsp;
					<div class="btn btn-info" onclick="msubmit('logout');" >注销</div></td>
			</tr>
			<tr><td colspan="2"><span style="color:red;" id="logintip"></span></td></tr>
		</table>
	</div>
</body>
<script type="text/javascript">
	function msubmit(state) {
		var url = "${pageContext.request.contextPath}";
		var user = {};
		user.name = $("input[type='text']").val();
		user.passwd = $("input[type='password']").val();
		var addurl = state == 'login'?"/loginvalid":"/validatelogout";
		$.ajax({
			type : 'post',
			url : url + addurl,
			data : JSON.stringify(user),
			dataType : 'json',
			contentType : "application/json; charset=utf-8",
			success : function(result) {
				console.log(result);
				if(result.success == 'success'){
					if(state == 'login'){
						window.location.href = '${pageContext.request.contextPath}/base/main';	
					}else{
						$("#logintip").html("强制注销成功....");
					}	
				}else{
					if(result.success == 'haslogin'){
						$("#logintip").html("用户已经登录...");	
					 }
					if(result.success == 'null'){
						$("#logintip").html("用户名或者帐号错误...");	
					 }
				}
				
			},
			error : function() {
				console.log("error");
			}
		});
	}
</script>
</html>