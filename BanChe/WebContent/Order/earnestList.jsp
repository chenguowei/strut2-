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
		<link rel="stylesheet" type="text/css" href="css/extends.css"/>
		<script src="js/jquery.js"></script>
		<script src="js/common.js"></script>
		<script src="js/extend.js" ></script>
		<script type="text/javascript" src="js/jquery.form.js"></script>
		<script src="js/sendPrice.js" ></script>
		<title>等待付定金</title>
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
			<div class="dialongbox">
				<div class="cancel_dialong"><img src="img/5_2.png"></div>
				<form id="changePrice" action="bc_Order_changePrice">
				<!--行-->
				<div class="text1 overflowH">
					<h1 style="margin-left:10px;">订单号：</h1>
					<p class="width02 hc_show"><span flag="sn">0103567891111</span></p>
					<h1 style="margin-left:0px;">定金数：</h1>

					
					<P class="width2 hc_show">
						<span flag="earnest">1,000</span>
						<span class="fr hc_edit" <s:if test="!#modifyRate">style="display: none;"</s:if> ><input flag="rate" value="30" name="rate" /><span>%</span></span>
					</P>
					<h1 style="margin-left:0px;">总费用：</h1>
					<P class="textcolor hc_show hide"><span flag="totalFee">1,000</span></P>
					<div class="textstyle1 hc_show hide">(原:<span flag="originalPrice">2000</span>)</div>
					
					<div style="display: none;"><input type="reset" id="reset" ></div>
					<div class="hc_edit" style="display: none;"><input flag="id" type="hidden" name="orderId" ></div>
					<!-- <div class="hc_edit"><input flag="totalFee" name="price" class="inputstyle buttonshow"></div> -->
							<div class="inputstyle4 buttonshow">
								<div class="hc_edit"><input type="text" flag="totalFee" class="inputstyle01" name="price"></div>								
								<!--<div class="fr">
									<span>+</span><input value="30" name="rate" /><span>%</span>
								</div>-->
							</div>
					
					<div class="button_bg hide">变更</div>
					<div class="button_fb buttonshow hc_show">发布<span flag="id" style="display: none;"></span></div>
				</div>
				<!--行-->				
				<!--end行-->	
				<div class="text01 overflowH">
					<h1 class="text_margin" >司机端价格：</h1>
					<P class="width hc_show"><span flag="driverShowPrice"></span></p>
					<div class="hc_edit"><input flag="driverShowPrice" type="hidden" name="driverShowPrice"></div>
					<h1>差钱金额：</h1>
					<p class="hc_show ">
						<span class="fr hc_edit" ><input flag="different" id="different"></span>
					</p>
					<!-- <div class="hc_edit"><input flag="different" class="inputstyle3 buttonshow" id="different"></div> -->
				</div>
				<!--end行-->	
				</form>
				<div class="cancel"><img src="img/5.png"></div>	
				<div class="line"></div>
				<!--行-->
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
						<div class="line_style hc_show"><span flag="sendAddr">地址在这里显示地址在这里显示地址在这里显示</span></div>
						<div class="textstyle3">收&nbsp;货&nbsp;地&nbsp;址：</div>
						<div class="line_style hc_show"><span flag="receiveAddr">地址在这里显示</span></div>
				</div>
				<!--end行-->	
				<%@ include file="order_info.jsp" %>
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
						<div class="list_width02 view_detail view_h" auth="bc_Order_viewEarnest">
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
	
	<script type="text/javascript">	
		
		$('.cancel_dialong').click(function(){
			$('#reset').trigger('click');
		});
	
		$('.button_fb').click(function(){
			var id = $(this).children('span').html();
			$('#changePrice').ajaxSubmit({
				type:'post',
				dataType: 'json',
				success: function (data) {
					if(data.status==1){
						$('#' + id).html(JSON.stringify(data.result));
		        		bindDataToBox(data.result);
		        		
		        		$(".mode_b2 input").hide();
						$(".hide").show();
						$(".buttonshow").hide();
						
						alert(data.info);
					}else{
						alert(data.info);
					}
				},
				error: function (XMLResponse) {
					//alert(XMLResponse.responseText);
	                var ss =JSON.stringify(XMLResponse);
	                alert('操作失败！'+ss);
				}
			});
			
		});
	</script>
</html>
