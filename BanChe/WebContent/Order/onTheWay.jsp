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
		<script src="js/win2.js"></script>
		<script src="js/win.js"></script>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=Phr0pbhYGaH0s8jiX15NUhgi"></script>
		<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
		
		<title>运输途中</title>
	</head>
	<body>
		<div class="blackbg">
		</div>
		<!--导航条-->
		<%@ include file="/header.jsp" %>
		<!--end导航条-->
		<!--第二导航条-->
		<%@ include file="order_header.jsp" %>
		<!--end第二导航条-->
		<!--中间部分-->
		<div class="mianbox">
		<div class="main overflowH">
			<!--弹窗部分-->
			<div class="dialongbox win3">
				<div class="cancel_dialong"><img src="img/5_2.png"></div>
				<!--行-->
				<div class="text1 overflowH" id="win_header">
					<h1>订单号：</h1>
					<p class="width hc_show"><span flag="sn">0103567891111</span></p>
					<h1>定金数：</h1>
					<P class="width2 hc_show"><span flag="earnest">1,000</span></P>
					<h1>总费用：</h1>
					<P class="textcolor hc_show"><span flag="totalFee">1,000</span></P>
					<div class="textstyle1 hc_show">(原：<span flag="originalPrice">2000</span>)</div>
				</div>
				<!--end行-->	
				<!--end行-->	
				<div class="text01 overflowH">
					<h1 class="text_margin1" >司机端价格：</h1>
					<P class="width hc_show"><span flag="driverShowPrice"></span></p>	
					<h1 class="text_margin2" >差钱金额：</h1>
					<p class="textcolor2 hc_show"><span flag="different"></span></p>
				</div>
				<!--end行-->
				<div class="cancel"><img src="img/5.png"></div>	
				<div class="line"></div>
				<!--滑动条-->
				<div class="takebox">
					<div class="showbox">
						<div class="car">
							<img src="img/car01.png" class="button_car1">
							<img src="img/car02.png" class="button_car2">
						</div>
					</div>
				</div>
				<!--end滑动条-->
				<!--行-->
				<div class="text2 overflowH imgH">
					<div class="mapbox" id="allmap"></div>
					<div class="text2 overflowH">
						<div class="textstyle3">发货联系人：</div>
						<div class="textstyle4 hc_show"><span flag="sendContact">王宝宝</span></div>
						<div class="textstyle3">收货联系人：</div>
						<div class="textstyle4 hc_show"><span flag="receiveContact">王宝宝</span></div>
				</div>
				<!--end行-->	
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle3">发货人电话：</div>
						<div class="textstyle4 hc_show"><span flag="sendTel">12345678910</span></div>
						<div class="textstyle3">收货人电话：</div>
						<div class="textstyle4 hc_show"><span flag="receiveTel">12345678910</span></div>
				</div>
				<!--end行-->	
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle3">发&nbsp;货&nbsp;公&nbsp;司：</div>
						<div class="textstyle4 hc_show"><span flag="sendCompany">速度加快速度加快速度公司</span></div>
						<div class="textstyle3">收&nbsp;货&nbsp;公&nbsp;司：</div>
						<div class="textstyle4 hc_show"><span flag="receiveCompany">速度加快速度加快速度公司</span></div>
				</div>
				<!--end行-->	
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle3">发&nbsp;货&nbsp;地&nbsp;址：</div>
						<div class="line_style hc_show"><span flag="sendAddr" class="src_addr">地址在这里显示地址在这里显示地址在这里显示</span></div>
						<div class="textstyle3">收&nbsp;货&nbsp;地&nbsp;址：</div>
						<div class="line_style hc_show"><span flag="receiveAddr" class="dst_addr">地址在这里显示</span></div>
				</div>
				<!--end行-->	
				<%@ include file="order_info.jsp" %>	
				<div class="line2"></div>
				<!--行-->
				<div class="text2 overflowH">
					<ul>
						<li class="hc_show">承接车型：<span flag=carTypeVO>xxxxxxxxxxxxxx</span></li>
						<li class="hc_show">承接车牌：<span flag="carNumber">xxxxxxxxxxxxxx</span></li>
					</ul>
					<ul>
						<li><a href="javascript:void;" style="color: #a8d3c5;" class="hc_show button_a">承接司机：<span flag="name">王宝宝</span></a></li>
						<li class="hc_show">联系电话：<span flag="tel">12345678910</span></li>
					</ul>
				</div>
				</div>
				<!--end行-->
				<div class="line2"></div>
				<!--行-->
				<form id="editNote_form" action="bc_Order_editNote">
				<div class="hc_edit" style="display: none;"><input flag="id" type="hidden" name="orderId"></div>
				<div class="text2 overflowH hc_edit">
						 <textarea flag="note" name="note" class="areastyle"></textarea>
						<div class="button_add">添加备忘</div>
				</div>
				</form>
				<!--end行-->	
			</div>
			<!--end弹窗部分-->	
			
			<%@ include file="driver_info.jsp" %>	
			
			<!--中间左侧-->
			<%@ include file="order_left.jsp" %>
			<!--end中间左侧-->
			<!--中间右侧-->
			<div class="table_2">
				<!--表头-->
				<div class="line_H overflowH listbg">
					<div class="list_1">单号</div>
					<div class="list_width">发货联系人</div>
					<div class="list_width1">备忘信息</div>
					<div class="list_width2">状态</div>
					<div class="list_width2">查看详情</div>
				</div>
				<!--end表头-->
				<s:iterator id="order" value="#pageVO.getData()">
					<!--行-->
					<div class="line_H overflowH">
						<div class="list_1 textstyle">${order.sn }</div>
						<div class="list_width textstyle">${order.sendContact }</div>
						<div class="list_width1 textstyle">${order.note }</div>
						<div class="list_width2 textstyle02">${order.statusVO }</div>
						<div class="list_width02 view_detail view_h" auth="bc_Order_viewOnTheWay">
							<div class="hc" id="${order.id }" style="display: none;">
								${order.parseEntityJson() }
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
</html>

	<script type="text/javascript">
		// 百度地图API功能
		var map = new BMap.Map("allmap");
		var start = "南宁火炬大厦";
		var end = "南宁国际会展中心";
		map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
		//三种驾车策略：最少时间，最短距离，避开高速
		var routePolicy = [BMAP_DRIVING_POLICY_LEAST_TIME,BMAP_DRIVING_POLICY_LEAST_DISTANCE,BMAP_DRIVING_POLICY_AVOID_HIGHWAYS];
				
		$('.button_car1').click(function(){
			
			start = $('.src_addr').html();
			end = $('.dst_addr').html();
			
			map.clearOverlays(); 
			search(start,end,BMAP_DRIVING_POLICY_LEAST_DISTANCE); 
			function search(start,end,route){ 
				var driving = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true},policy: route});
				driving.search(start,end);
			}
		});
		
		$('.imgH').click(function(e){
			e.stopPropagation();
		});
	</script>
	
