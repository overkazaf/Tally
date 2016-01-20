<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
</head>
<body>
	<div style="height: 600px;">
		<div>
			<div class="col-sm-10 col-sm-offset-1" style="margin-top:10px;">
				<div class="row" style=" background-image: linear-gradient(to bottom, #337ab7 0%, #265a88 100%);padding:0px;color:white;margin:0px 0px;">
					<div class="col-sm-9 col-xs-9" style="line-height:30px;">
						<strong>机房信息配置</strong>
					</div>
					<div class="col-sm-3 col-xs-3 text-center">
					<div onclick='adddata({"modal":"#themodal","url":0});' class="btn btn-default btn-sm" style="height:25px;margin-top:2px;"><i class="glyphicon glyphicon glyphicon-plus"></i>&nbsp;Add</div>
					</div>
				</div>
				<table id="jfinfotabel" class="table table-bordered table-striped">
					<thead>
						<tr>
							<th class="text-center">机房ID</th>
							<th class="text-center">端口号</th>
							<th class="text-center">管理员</th>
							<th class="text-center">联系方式</th>
							<th class="text-center">机房地址</th>
							<th class="text-center">设置</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${omputerroomList}" var="eachItem" varStatus="i">
							<tr>
								<td>${eachItem.jfid}</td>
								<td>${eachItem.port}</td>
								<td adminid="${eachItem.labAdmin.adminid}">${eachItem.labAdmin.name}</td>
								<td>${eachItem.labAdmin.phone}</td>
								<td>${eachItem.address}</td>
								<td nowrap="nowrap" style="text-align: center"><button
										class="btn-xs btn btn-default "
										onclick="jfinfomodify('${i.index+1}')" class="navbar-link">
										<i class="glyphicon glyphicon-pencil"></i> Modify
									</button>
									<button class="btn-xs btn btn-default"
										href="javascript:void(0)"
										onclick="deletedata({'row':${i.index+1},'tabel':'#jfinfotabel','url':0});"
										class="navbar-link">
										<i class="glyphicon glyphicon-trash"></i> Delete
									</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div>
				<div class="col-sm-10 col-sm-offset-1" style="margin-top:10px;">
				<div class="row" style=" background-image: linear-gradient(to bottom, #337ab7 0%, #265a88 100%);padding:0px;color:white;margin:0px 0px;">
					<div class="col-sm-9 col-xs-9" style="line-height:30px;">
						<strong>机房阈值配置</strong>
					</div>
					<div class="col-sm-3 col-xs-3 text-center">
					<div class="btn btn-default btn-sm" onclick='adddata({"modal":"#addthreshold","url":1});'style="height:25px;margin-top:2px;"><i class="glyphicon glyphicon glyphicon-plus"></i>&nbsp;Add</div>
					</div>
				</div>
				<table id="thresholdtab" class="table table-bordered table-striped">
					<thead>
						<tr>
							<th class="text-center">机房ID</th>
							<th class="text-center">机房地址</th>
							<th class="text-center">温度阈值</th>
							<th class="text-center">湿度阈值</th>
							<th class="text-center">光照阈值</th>
							<th class="text-center">震动阈值</th>
							<th class="text-center">人体感应</th>
							<th class="text-center">设置</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listthreshold}" var="eachItem" varStatus="i">
							<tr>
								<td>${eachItem.jfid}</td>
								<td>${eachItem.jfaddress}</td>
								<td>${eachItem.temthresHold}</td>
								<td>${eachItem.humlitythresHold}</td>
								<td>${eachItem.ligghtthresHold}</td>
								<td>${eachItem.shakethresHold}</td>
								<td>${eachItem.humanthresHold}</td>
								<td nowrap="nowrap" style="text-align: center">
									<button class="btn-xs btn btn-default "
										onclick="thresholdmodify('${i.index+1}')" class="navbar-link">
										<i class="glyphicon glyphicon-pencil"></i> Modify
									</button>
									<button class="btn-xs btn btn-default"
										onclick="deletedata({'row':${i.index+1},'tabel':'#thresholdtab','url':1});"
										class="navbar-link">
										<i class="glyphicon glyphicon-trash"></i> Delete
									</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="col-sm-10 col-sm-offset-1" style="margin-top:10px;">
				<div class="row" style=" background-image: linear-gradient(to bottom, #337ab7 0%, #265a88 100%);padding:0px;color:white;margin:0px 0px;">
					<div class="col-sm-9 col-xs-9" style="line-height:30px;">
						<strong>管理员信息配置</strong>
					</div>
					<div class="col-sm-3 col-xs-3 text-center">
					<div class="btn btn-default btn-sm" onclick='adddata({"modal":"#themodaadmin","url":2});' style="height:25px;margin-top:2px;"><i class="glyphicon glyphicon glyphicon-plus"></i>&nbsp;Add</div>
					</div>
				</div>
			<table id="admintab" class="table table-bordered table-striped">
				<thead>
					<tr>
						<th class="text-center">管理员ID</th>
						<th class="text-center">管理员</th>
						<th class="text-center">性别</th>
						<th class="text-center">联系方式</th>
						<th class="text-center">职位</th>
						<th class="text-center">设置</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${labadminList}" var="eachItem" varStatus="i">
						<tr>
							<td>${eachItem.adminid}</td>
							<td>${eachItem.name}</td>
							<td>${eachItem.sex}</td>
							<td>${eachItem.phone}</td>
							<td>${eachItem.position}</td>

							<td nowrap="nowrap" style="text-align: center"><button
									class="btn-xs btn btn-default "
									onclick="adminmodify('${i.index+1}');" class="navbar-link">
									<i class="glyphicon glyphicon-pencil"></i> Modify
								</button>
								<button class="btn-xs btn btn-default" href="javascript:void(0)"
									onclick="deletedata({'row':${i.index+1},'tabel':'#admintab','url':2});"
									class="navbar-link">
									<i class="glyphicon glyphicon-trash"></i> Delete
								</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<!-- Mod modal -->
	<div id="themodal" class="modal hade" style="width:350px">

		<div class="text-center"
			style="height: 40px; line-height: 40px; border: 0px; padding: 1px; margin: 0px 0px; background-image: linear-gradient(to bottom, #337ab7 0%, #265a88 100%);">
			<span style="color: white">配置机房信息</span>
			<button type="button" class="close" data-dismiss="modal"
				onclick='freshpage();' aria-hidden="true" style="margin: 4px 4px;">&times;</button>
		</div>
		<form id="modFieldContainerForm">
			<div class="modal-body" style="margin-top: 0px; padding: 0px;">
				<ul class="list-group">
				    <li class="list-group-item">
						<div class="row">
							<div class="col-sm-4"
								style="padding-left: 40px; padding-right: 0px; line-height:26px;">机房编号：</div>
							<div class="col-sm-8" style="padding-left: 0px;">
								<input type="text" name="jfid">
								<div id="modajfid"></div>
							</div>
						</div>
					</li>
					   <li class="list-group-item">
						<div class="row">
							<div class="col-sm-4"
								style="padding-left: 53px; padding-right: 0px; line-height:26px;">端口号：</div>
							<div class="col-sm-8" style="padding-left: 0px;">
								<input type="text" name="port">
								<div id="modIdCtn"></div>
							</div>
						</div>
					</li>
					
					<li class="list-group-item" style="padding: 0px">
						<div class="row" style="padding: 8px 0px; margin: 0px;">
							<div class="col-xs-4"
								style="padding-left: 29px; padding-right: 0px; hight: 32px; line-height: 32px; margin: 0px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;管理员：</div>
							<div class="col-xs-8" style="padding: 0px;">
								<div class="dropdown">
									<button class="btn btn-primary" id="dropdownMenu1"
										style="padding: 5px; width: 140px;" type="button"
										id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
										<span id="adminname">管理员</span></span> <span class="caret"></span>
									</button>
									<ul id="ul1" class="dropdown-menu" role="menu"
										aria-labelledby="dropdownMenu1">
									</ul>
								</div>
							</div>
						</div>
					</li>
					 <li class="list-group-item">
						<div class="row">
							<div class="col-sm-4"
								style="padding-left: 40px; padding-right: 0px; line-height:26px;">机房地址：</div>
							<div class="col-sm-8" style="padding-left: 0px;">
								<input type="text" name="address">
								<div id="modaport"></div>
							</div>
						</div>
					</li>
					<li class="list-group-item text-center">
						<button type="submit" id="jfcfgok" onclick="modal1submit();" class="btn btn-primary btn-sm">提交</button>&nbsp;&nbsp;&nbsp;&nbsp;
						<div id="cancel1" class="btn btn-primary btn-sm">取消</div>
					</li>
					<li id="mtips" class="list-group-item text-center  fade"><div
							class="alert alert-success" style="margin: 0px; padding: 0px;"></div></li>
				</ul>
			</div>
		</form>
	</div>
	<!--  modal2 -->
	<div id="themoda2" class="modal hade" style="width: 350px">

		<div class="text-center"
			style="height: 40px; line-height: 40px; border: 0px; padding: 1px; margin: 0px 0px; background-image: linear-gradient(to bottom, #337ab7 0%, #265a88 100%);">
			<span style="color: white">配置阈值信息</span>
			<button type="button" class="close" data-dismiss="modal"
				onclick="freshpage();" aria-hidden="true" style="margin: 4px 4px;">&times;</button>
		</div>
		<form id="thresholdform">
			<div class="modal-body" style="margin-top: 0px; padding: 0px;">
				<ul class="list-group">
					<li class="list-group-item text-center">机房编号：<input
						type="text"></li>
					<li class="list-group-item text-center">机房地址：<input
						type="text"></li>
					<li class="list-group-item text-center">温度阈值：<input
						name="temp" type="text">
						<div id="modtemp"></div></li>
					<li class="list-group-item text-center">湿度阈值：<input
						name="humlity" type="text">
						<div id="modhumilty"></div></li>
					<li class="list-group-item text-center">光照阈值：<input
						name="light" type="text">
						<div id="modlight"></div></li>
					<li class="list-group-item text-center">震动阈值：<input
						name="shake" type="text">
						<div id="modshake"></div></li>
					<li class="list-group-item text-center">人体感应：<input
						name="human" type="text">
						<div id="modhuman"></div></li>
					<li class="list-group-item text-center">
						<button id="thresholodcfgok" class="btn btn-primary btn-sm" onclick="thresholdsubmit();">提交</button>&nbsp;&nbsp;&nbsp;&nbsp;
						<div id="cancel2" class="btn btn-primary btn-sm">取消</div>
					</li>
					<li id="mtipsthreshold" class="list-group-item text-center  fade"><div
							class="alert alert-success" style="margin: 0px; padding: 0px;"></div></li>
				</ul>
			</div>

		</form>
	</div>
	
	<!--  modal3 -->
	<div id="themodaadmin" class="modal hade" style="width: 350px">

		<div class="text-center"
			style="height: 40px; line-height: 40px; border: 0px; padding: 1px; margin: 0px 0px; background-image: linear-gradient(to bottom, #337ab7 0%, #265a88 100%);">
			<span style="color: white">设置管理员信息</span>
			<button type="button" class="close" data-dismiss="modal"
				onclick="freshpage();" aria-hidden="true" style="margin: 4px 4px;">&times;</button>
		</div>
		<form id="thresholdform">
			<div class="modal-body" style="margin-top: 0px; padding: 0px;">
				<ul class="list-group">
					<li class="list-group-item">
						<div class="row">
							<div class="col-sm-3"
								style="padding-left: 40px; padding-right: 0px;">工号：</div>
							<div class="col-sm-9" style="padding-left: 0px;">
								<input type="text" name="adminid">
								<div id="modadminid"></div>
							</div>
						</div>
					</li>
					<li class="list-group-item"><div class="row">
							<div class="col-sm-3"
								style="padding-left: 40px; padding-right: 0px;">姓名：</div>
							<div class="col-sm-9" style="padding-left: 0px;">
								<input type="text" name="name">
								<div id="modname"></div>
							</div>
						</div></li>
					<li class="list-group-item">
						<div class="row">
							<div class="col-sm-3"
								style="padding-left: 40px; padding-right: 0px; line-height: 32px;">性别：</div>
							<div class="col-sm-9" style="padding-left: 0px;">
								<div>
									<button class="btn btn-primary" id="dropdownMenu1"
										style="padding: 5px; width: 140px;" type="button"
										id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
										<span id="adminsex">男</span></span> <span class="caret"></span>
									</button>
									<ul id="ulsex" class="dropdown-menu" role="menu"
										aria-labelledby="dropdownMenu1">
										<li role="presentation"><a role="menuitem" tabindex="-1"
											href="#"> 男</a></li>
										<li role="presentation"><a role="menuitem" tabindex="-1"
											href="#"> 女</a></li>
									</ul>
								</div>
							</div>
						</div>
					</li>
					<li class="list-group-item"><div class="row">
							<div class="col-sm-3"
								style="padding-left: 12px; padding-right: 0px;">联系方式：</div>
							<div class="col-sm-9" style="padding-left: 0px;">
								<input type="text" name="phone">
								<div id="modphone"></div>
							</div>
						</div></li>
					<li class="list-group-item"><div class="row">
							<div class="col-sm-3"
								style="padding-left: 40px; padding-right: 0px;">职位：</div>
							<div class="col-sm-9" style="padding-left: 0px;">
								<input type="text" name="position">
								<div id="modposition"></div>
							</div>
						</div></li>
					<li class="list-group-item text-center">
						<button id="admincfgok" type="submit" class="btn btn-primary btn-sm" onclick="adminsubmit();">提交</button>&nbsp;&nbsp;&nbsp;&nbsp;
						<div id="cancel3" class="btn btn-primary btn-sm">取消</div>
					</li>
					<li id="mtipsadmin" class="list-group-item text-center  fade"><div
							class="alert alert-success" style="margin: 0px; padding: 0px;"></div></li>
				</ul>
			</div>
		</form>
	</div>
	
	<!--  modal4 -->
	<div id="addthreshold" class="modal hade" style="width: 350px">
		<div class="text-center"
			style="height: 40px; line-height: 40px; border: 0px; padding: 1px; margin: 0px 0px; background-image: linear-gradient(to bottom, #337ab7 0%, #265a88 100%);">
			<span style="color: white">配置阈值信息</span>
			<button type="button" class="close" data-dismiss="modal"
				onclick="freshpage();" aria-hidden="true" style="margin: 4px 4px;">&times;</button>
		</div>
		<form id="addthresholdform">
			<div class="modal-body" style="margin-top: 0px; padding: 0px;">
				<ul class="list-group">
					<li class="list-group-item">
					<div class="row">
					        <div class="col-sm-4"
								style="padding-left: 30px; padding-right: 0px;line-height:32px;">机房地址：</div>
							<div class="col-sm-8" style="padding-left: 0px;">
								<button class="btn btn-primary" id="dropdownMenu1"
										style="padding: 5px; width: 175px;" type="button"
										id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
										<span id="jfaddr">选择机房</span></span> <span class="caret"></span>
								</button>
									<ul id="uljfaddress" class="dropdown-menu" role="menu"
										aria-labelledby="dropdownMenu1">
							       </ul>
							</div>
					</div>
					</li>
					<li class="list-group-item">
					   <div class="row">
					        <div class="col-sm-4"
								style="padding-left: 30px; padding-right: 0px;">温度阈值：</div>
							<div class="col-sm-8" style="padding-left: 0px;">
							  <input name="temp" type="text">
						      <div id="modtemp1"></div>
							</div>
					   </div>
					</li>
					<li class="list-group-item">
					   <div class="row">
					        <div class="col-sm-4"
								style="padding-left: 30px; padding-right: 0px;">湿度阈值：</div>
							<div class="col-sm-8" style="padding-left: 0px;">
							  <input name="humlity" type="text">
						      <div id="modhumilty1"></div>
							</div>
					   </div>
					</li>
					<li class="list-group-item">
					    <div class="row">
					        <div class="col-sm-4"
								style="padding-left: 30px; padding-right: 0px;">光照阈值：</div>
							<div class="col-sm-8" style="padding-left: 0px;">
							  <input name="light" type="text">
						      <div id="modlight1"></div>
							</div>
					   </div>
					<li class="list-group-item">
					   <div class="row">
					        <div class="col-sm-4"
								style="padding-left: 30px; padding-right: 0px;">震动阈值：</div>
							<div class="col-sm-8" style="padding-left: 0px;">
							  <input name="shake" type="text">
						      <div id="modshake1"></div>
							</div>
					   </div>
					</li>
					<li class="list-group-item">
					    <div class="row">
					        <div class="col-sm-4"
								style="padding-left: 30px; padding-right: 0px;">人体感应：</div>
							<div class="col-sm-8" style="padding-left: 0px;">
							  <input name="human" type="text">
						      <div id="modhuman1"></div>
							</div>
					   </div>
					</li>
					<li class="list-group-item text-center">
						<button id="thredholdaddcfgok" type="submit" class="btn btn-primary btn-sm" onclick="thresholdsubmit();">提交</button>&nbsp;&nbsp;&nbsp;&nbsp;
						<div id="cancel4" class="btn btn-primary btn-sm">取消</div>
					</li>
					<li id="mtipsthreshold1" class="list-group-item text-center  fade"><div
							class="alert alert-success" style="margin: 0px; padding: 0px;"></div></li>
				</ul>
			</div>

		</form>
	</div>
	<!--  modal5 -->
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
				<li class="list-group-item text-center"><span>确定删除该条数据？</span></li>
				<li class="list-group-item">
					<div class="row">
						<div id="btnok" class="btn btn-primary btn-sm col-sm-offset-8"
							onclick="dodelete();">确定</div>
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
<script type = "text/javascript">
var BASEURL = "${pageContext.request.contextPath}/";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/webresource/js/setting.js"></script>
</html>