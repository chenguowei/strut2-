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
		<link rel="stylesheet" type="text/css" href="css/2_1css.css"/>
		<link rel="stylesheet" type="text/css" href="css/common.css"/>
		<script src="js/jquery.js"></script>
		<script src="js/common.js"></script>
		<script src="js/extend.js" ></script>
		<script type="text/javascript" src="js/jquery.form.js"></script>
		<title>黑名单</title>
	</head>
	<body>
		<div class="blackbg">
		</div>
		<!--导航条-->
		<%@ include file="/header.jsp" %>
		<!--end导航条-->
		<!--第二导航条-->
		<%@ include file="agency_header.jsp" %>
		<!--end第二导航条-->
		<!--中间部分-->
		<div class="mianbox">
		<div class="main overflowH">
			<%@ include file="agency_popup.jsp" %>
			<!--弹窗部分-->
			<div class="dialongbox3">
			<form id="edit_agency" action="bc_Agency_editAgency">
				<div class="cancel_dialong"><img src="img/5_2.png"></div>
				<div class="text1 overflowH">
					<h1>ID：</h1>
					<p class="hc_show"><span id="agencyIdVO" flag="id">010356789</span></p>
					<div class="hc_edit" style="display: none;"><input flag="id" name="agencyForm.id" /></div>
					<h1>经销商名称：</h1>
					<P class="hc_show"><span flag="contact">王宝宝</span></P>
					<div class="hc_edit" style="display: none;"><input flag="contact" name="agencyForm.contact" /></div>
					<div class="button_b hide">编辑</div>
				</div>
				<div class="cancel3 buttonshow"><img src="img/5.png"></div>	
				<div class="line"></div>
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle04">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话:</div>
						<div class="textstyle_b hc_show"><span flag="contactTel">12345678910</span></div>
						<div class="mode_b2 hc_edit"><input flag="contactTel" type="text" name="agencyForm.contactTel" value="12345678910"/></div>
				</div>
				<!--end行-->	
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle04">常用发货地址：</div>
						<div class="textstyle_b hc_show"><span flag="sendAddr">北京市海淀区·····························</span></div>
						<div class="mode_b2 hc_edit"><input flag="sendAddr" name="agencyForm.sendAddr" type="text" value="北京市海淀区·····························"/></div>
				</div>
				<!--end行-->	
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle04">常用收货地址：</div>
						<div class="textstyle_b hc_show"><span flag="receiveAddr">北京市海淀区·····························</span></div>
						<div class="mode_b2 hc_edit"><input flag="receiveAddr" name="agencyForm.receiveAddr" type="text" value="北京市海淀区·····························"/></div>
				</div>
				<!--end行-->	
				<div class="line3"></div>
				<!--行-->
				<div class="text3 overflowH">
						<div class="textstyle3">登记日期：</div>
						<div class="textstyle05 hc_show"><span flag="registerVO">2016-7-9</span></div>
						<div class="button_j">解禁</div>
						<div class="button_s2 hide">删除</div>
						<div class="button_w buttonshow editBtn hc_show">完成<span flag="id" style="display: none;"></span></div>
				</div>
				<!--end行-->	
			</form>
			</div>
			<!--end弹窗部分-->
			<!--中间左侧-->
			<%@ include file="agency_left.jsp" %>
			<!--end中间左侧-->
			<!--中间右侧-->
			<div class="table_2">
				<!--表头-->
				<div class="line_H overflowH listbg">
					<div class="list_1">ID</div>
					<div class="list_2">
						<div class="list_01">经销商名称</div>
						<div class="list_02">状态</div>
					</div>
					<div class="list_03">查看详情</div>
				</div>
				<!--end表头-->
				<s:iterator id="agency" value="#pageVO.getData()">
					<!--行-->
					<div class="line_H overflowH">
						<div class="list_1 textstyle">${agency.id }</div>
						<div class="list_2">
							<div class="list_01 textstyle">${agency.contact }</div>
							<div class="list_02 textstyle03">${agency.statusVO }</div>
						</div>
						<div class="list_3 view_detail view_h" auth="bc_Agency_viewInfo">
							<div class="hc" id="${agency.id }" style="display: none;">
								${agency.parseEntityJson() }
							</div>
						</div>
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
		<div class="footerbox">
			<div class="foonter">©2016 咔咔汽车 All rights reserved版权所有</div>
		</div>
		<!--end页脚部分-->
	</body>
	
	<script type="text/javascript">
		$('.view_h').click(function(){
			$('.normal_list').hide();
			$('.black_list').show();
		});
	</script>
	
</html>
