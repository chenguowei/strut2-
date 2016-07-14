<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
			<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
		<link rel="stylesheet" type="text/css" href="css/common.css"/>
		<link rel="stylesheet" type="text/css" href="css/2_1css.css"/>
		<script src="js/jquery.js"></script>
		<script src="js/common.js"></script>
		<script src="js/extend.js" ></script>
		<script src="js/win.js" ></script>
		<script type="text/javascript" src="js/jquery.form.js"></script>
		<title>司机信息</title>
	</head>
	<body>
		<div class="blackbg"></div>
		<div class="blackbg2"></div>
		<!--导航条-->
		<%@ include file="/header.jsp" %>
		<!--end导航条-->
		<!--第二导航条-->
		<%@ include file="driver_header.jsp" %>
		<!--end第二导航条-->

		<!--中间部分-->
		<div class="mianbox">
		<div class="main overflowH">
			<%@ include file="driver_popup.jsp" %>
			<!--中间左侧导航-->
			<%@ include file="driver_left.jsp" %>
			<!--end中间左侧导航-->
			<!--中间右侧-->
			<div class="table_2">
				<!--表头-->
				<div class="line_H overflowH listbg">
					<div class="list_1">ID</div>
					<div class="list_2">
						<div class="list_01">司机名称</div>
						<div class="list_02">状态</div>
					</div>
					<div class="list_03">查看详情</div>
				</div>
				<!--end表头-->
				<s:iterator id="driver" value="#pageVO.getData()">
					<div class="line_H overflowH">
						<div class="list_1 textstyle">${driver.id }</div>
						<div class="list_2">
							<div class="list_01 textstyle">${driver.name }</div>
							<div class="list_02 textstyle01">${driver.statusVO }</div>
						</div>
						<div class="list_3 view_detail view_h" auth="bc_Driver_viewInfo">
							<div class="hc" id="${driver.id }" style="display: none;">
								${driver.parseEntityJson() }
							</div>
						</div>
					</div>
				</s:iterator>
				
			</div>
			<!--end中间右侧-->
			<!--中间底部-->
			<%@ include file="/page.jsp" %>
			<!--end中间底部-->
		</div>
		</div>
		<!--end中间部分-->
		<!--页脚部分-->
		<div class="footerbox">
			<div class="foonter">©2016 咔咔汽车 All rights reserved版权所有</div>
		</div>
		<!--end页脚部分-->
		</body>
		
		<script type="text/javascript">
			$('.view_h').click(function(){
				$('.button_h').show();
				$('.button_cz').show();
				$('.button_t').hide();
				$('.button_j').hide();
			});
		</script>
</html>

