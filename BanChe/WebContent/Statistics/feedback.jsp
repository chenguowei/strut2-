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
		<script src="js/jquery.js"></script>
		<script src="js/common.js"></script>
		<script src="js/win.js"></script>
		<title>意见反馈</title>
	</head>
	<body>
		<div class="blackbg"></div>
		<div class="blackbg2"></div>
		<!--导航条-->
		<%@ include file="/header.jsp"%>
		<!--end导航条-->
		<!--第二导航条-->
		<%@ include file="statistics_header.jsp"%>
		<!--end第二导航条-->

		<!--中间部分-->
		<div class="mianbox">
		<div class="main overflowH">
			
			<!--中间左侧导航-->
			<%@ include file="statistics_left.jsp" %>
			<!--end中间左侧导航-->
			<div class="table_2">
				<!--表头-->
				<div class="line_H overflowH listbg">
					<div class="list_1">用户</div>
					<div class="list_width">email</div>
					<div class="list_width1">反馈内容</div>
					<div class="list_width3">反馈时间</div>
				</div>
				<!--end表头-->
				<s:iterator id="feedback" value="#pageVO.getData()">
				<!--行-->
				<div class="line_H overflowH">
					<div class="list_1 textstyle">${feedback.userName }</div>
					<div class="list_width textstyle">${feedback.email }</div>
					<div class="list_width1 textstyle">${feedback.content }</div>
					<div class="list_width3 textstyle02">${feedback.timeVO }</div>
				</div>
				<!--end行-->
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
		<%@ include file="/footer.jsp" %>
		<!--end页脚部分-->
		</body>
</html>
