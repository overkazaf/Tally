<%@page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<ul id="navigator" class="nav nav-tabs" style="margin-top: 5px;">
		<li class="active"><a href="#" _href="mainpage"> <span
				class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;首&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;页
		</a></li>
		<li><a href="#" _href="mapdata"><span
				class="glyphicon glyphicon-flag" aria-hidden="true"></span>&nbsp;&nbsp;地图信息</a></li>
		<li><a href="#" _href="currentinfo"><span
				class="glyphicon glyphicon-signal" aria-hidden="true"></span>&nbsp;&nbsp;实时信息</a></li>
		<li><a href="#" _href="dataanalysis"><span
				class="glyphicon glyphicon-th" aria-hidden="true"></span>&nbsp;&nbsp;数据统计</a></li>
		<li><a href="#" _href="setting"><span
				class="glyphicon glyphicon-cog" aria-hidden="true"></span>&nbsp;&nbsp;后台管理</a></li>
		<li><a href="#" _href="jfconfig"><span
				class="glyphicon glyphicon-menu-hamburger" aria-hidden="true"></span>&nbsp;&nbsp;机房管理</a></li>
		<li><a href="#" _href="join"><span
				class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;&nbsp;加入我们</a></li>

		<li class="dropdown" style="margin-top: 7px;">
			<button type="button" class="btn btn-primary btn-sm dropdown-toggle"
				data-toggle="dropdown" aria-expanded="false"
				style="margin-left: 20px; width: 110px;">
				机房1 <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" role="menu"
				style="margin-left: 20px; width: 110px;">
				<li><a href="#">机房1</a></li>
				<li><a href="#">机房2</a></li>
				<li><a href="#">机房3</a></li>
				<li><a href="#">机房4</a></li>
			</ul>
		</li>
	</ul>

</body>
</html>