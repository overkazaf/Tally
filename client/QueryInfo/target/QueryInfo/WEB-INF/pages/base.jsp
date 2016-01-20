<%@page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<!-- JS -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/webresource/js/jquery-1.11.3.min.js">
	
</script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=C49e8f255ee2d21c74e84b701f8ef4f9"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/webresource/js/bootstrap.js"></script>
<script type="text/javascript"
	src="http://echarts.baidu.com/build/dist/echarts.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/webresource/js/base.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/webresource/js/bootstrapValidator.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/webresource/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/webresource/js/highlight.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/webresource/js/bootstrap-switch.js"></script>


<!-- CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/webresource/css/bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/webresource/css/bootstrap-theme.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/webresource/css/base.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/webresource/css/bootstrapValidator.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/webresource/css/bootstrap-datetimepicker.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/webresource/css/bootstrap-switch.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/webresource/css/highlight.css">
<title>机房信息查询</title>
</head>
<body>
	<div class="container" style="margin-top: 5px">
		<div class="row">
			<div class="col-sm-8 col-xs-4">
				<img
					src="${pageContext.request.contextPath}/webresource/images/logo.jpg"
					alt="查信息" />
			</div>
			<div class="col-sm-4 col-xs-8">
				<div class="col-sm-6 col-xs-6">
					<button class="btn btn-info btn-xs">
						<span class="glyphicon glyphicon-user"></span>&nbsp; <span
							id="usrlab" userrole="${role}">${username}</span>&nbsp;&nbsp;
					</button>
					<button id="btnexit" class="btn btn-danger btn-xs"
						style="height: 18px; padding-bottom: 5px;">
						<span class="glyphicon glyphicon-log-out"></span>
					</button>
				</div>
				<div class="col-sm-6 col-xs-6">
					<span class="glyphicon glyphicon-phone-alt pull-right"
						aria-hidden="true" style="margin: 5px; color: red">:152-6273-8667</span>
				</div>
			</div>
		</div>
		<jsp:include page="navigator.jsp"></jsp:include>
		<div id="content"></div>

		<div id="copyright" class="row">Copyright ©1996-2015 Nantong
			University Unit Corporation, All Rights Reserved</div>
	</div>
</body>
<script type="text/javascript">
	var it1;
	var it2;
	var it3;
	function exit() {
		var user = {};
		var username = $("#usrlab").html();
		user.name = username;
		var url = "${pageContext.request.contextPath}/logout";
		$
				.ajax({
					type : 'post',
					url : url,
					data : JSON.stringify(user),
					dataType : "text",
					contentType : "application/json; charset=utf-8",
					success : function(result) {
						console.log(result);
						window.location.href = '${pageContext.request.contextPath}/login';
					},
					error : function() {
						console.log("error");
					}
				});
	}
	$(function() {
		var userrole = $("#usrlab").attr("userrole");
		if (userrole == 'USER') {
			$("li:contains('后台管理')").remove();
			$("li:contains('机房管理')").remove();
		}
		$("#navigator a").on("click", navButtonClick);
		$(".active").find("a").click();

		$("#btnexit").on("click", exit);
		$(window).unload(exit);
	});
</script>

</html>