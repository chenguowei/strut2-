<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		
		<style>
			.blackbg_confirm { 
				width: 100%;
				height: 100%;
				background-color: #02285F;
				position: fixed;
				z-index: 30 !important;
				opacity: 0.3;
				filter:Alpha(opacity=30);
				display: none;
			}
		</style>
		
		<div class="blackbg_confirm">
		</div>
		
		<div class="header">
			<div class="header_w overflowH">
				<div class="header_f">
					<p>板车后台</p>
				</div>
				<ul class="navi1">
					<li type="bc_Driver_driverList" <s:if test='#uri.startsWith("/BanChe/bc_Driver_")'>class="bold"</s:if> >司机管理</li>
					<li type="bc_Agency_agencyList" <s:if test='#uri.startsWith("/BanChe/bc_Agency_")'>class="bold"</s:if> >用户管理</li>
					<li type="bc_Order_orderModule" <s:if test='#uri.startsWith("/BanChe/bc_Order_")'>class="bold"</s:if> >货单管理</li>
					<li type="bc_Banner_bannerList" <s:if test='#uri.startsWith("/BanChe/bc_Banner_")'>class="bold"</s:if> >轮播图</li>
					<li type="bc_Statistics_orderCount" <s:if test='#uri.startsWith("/BanChe/bc_Statistics_")'>class="bold"</s:if> >数据统计</li>
				</ul>
				<div class="header_r">
					<!-- 
					<span>信息提示</span>
					<s:if test='#hint.msg>0'>
						<div class="imgbox1"><div class="topnavnews">${hint.msg }</div></div>
						
					</s:if>	 -->
					<div class="imgbox2"><img src="img/2.png"/></div>				
					<span>${user.account }</span>
					<div class="imgbox3"><img src="img/3.png"/></div>
				</div>
				<!--下拉菜单-->
				<div class="down_menu">
					 <div class="menubg overflowH"><img src="img/6.png"></div>
					<div class="overflowH ">
						<a class="menu menubg1 infoBtn">我的账号</a>
					</div>
					<div class="overflowH settingBtn">
					<a class="menu menubg2">系统设置</a>
					</div>
					<div class="overflowH exitBtn">
					<a class="menu menubg3">退出</a>
					</div>
				</div>
				<!--[if IE 7]><style>div.down_menu{margin-left: 348px;}</style><![endif]-->
				<!--end下拉菜单-->
				<!--下拉消息框-->
				<div class="down_news">
					<div class="menubg4 overflowH"><img src="img/06.png"></div>
					<div class="menubg5">
						<div class="line_h">
							<a class="menu2 overflowH">我是消息我是消息</a>
							<div class="news2">2</div>
						</div>
						<div class="line_h">
						<a class="menu2 overflowH">消息内容消息内容消息</a>
						</div>
						<div class="line_h">
						<a class="menu2 overflowH">消息内容消息内容消息</a>
						<div class="news2">2</div>
						</div>
						<div class="line_h">
						<a class="menu2 overflowH">消息内容消息内容消息</a>
						<div class="news2">2</div>
						</div>
					</div>
				</div>
				<!--end下拉消息框-->
				
			</div>
		</div>
		<!--提示弹窗部分-->
		<div class="prompt dj_confirm" style="display: none;">
			<h1>确认删除该成员？</h1>
			<div class="line4"></div>
			<div class="button_cancel">取消</div>
			<div class="button_true">确认</div>
		</div>
		<!--end提示弹窗部分-->
		<!-- 提示音 -->
		<!-- <div style="display:none;">
			<embed id="music" src="" loop="11" autostart="true"/>
		</div> -->
		<audio width="320" height="240" controls="controls" style="display:none;">
		  <source id="ogg" src="music/notice.mp3" type="audio/mpeg">
		</audio>
		<!-- 提示音end -->
		
<script type="text/javascript">
	$('.navi1 li').click(function(){
		var url = $(this).attr('type');
		window.location.href = url;
	});
	
	$('.exitBtn').click(function(){
		window.location.href = "userLogout";
	});
	
	$('.settingBtn').click(function(){
		window.location.href = "bc_User_userList";
	});
	
	$('.infoBtn').click(function(){
		window.location.href = "bc_User_userPage";
	});
	
	var unpassedNo = -1;
	var pricingNo = -1;
	
	setInterval(function(){
		try{
			$.getJSON("bc_Poll_refreshHint", function(data){
				if(data.status==1){
					var result = data.result;
					//alert(JSON.stringify(result));
					if(result.msg > 0){
						$('.topnavnews').html(result.msg);
						$('.topnavnews').show();
					}else{
						$('.topnavnews').hide();
					}
					
					if(result.unpassed > 0){
						$('.unpassed').html(result.unpassed);
						$('.unpassed').show();
					}else{
						$('.unpassed').hide();
					}
					/*添加*/
					if(result.info == 1){
						var v = document.getElementsByTagName("audio")[0];
						v.play();
					}
					/*添加end*/
					if(unpassedNo==-1){
						
					}else{
						if(unpassedNo != result.unpassed && '<s:property value="#actionName" />'=='bc_Driver_uncheckedList'){
							location.reload();
						}
					}
					unpassedNo = result.unpassed;
					
					if(result.pricing > 0){
						$('.pricing').html(result.pricing);
						$('.pricing').show();
					}else{
						$('.pricing').hide();
					}
					if(pricingNo==-1){
						
					}else{
						if(pricingNo != result.pricing && ('<s:property value="#actionName" />'=='bc_Order_pricingList' 
								|| '<s:property value="#actionName" />'=='bc_Order_orderModule')){
							location.reload();
						}
					}
					pricingNo = result.pricing;
				}				
			});
		}catch(e){
			alert(e);
		}		
	}, 3000);
	
	$.DJconfirm = function(){
		
		var info = arguments[0];
		var callback = arguments[1];
		var confirmInfo = arguments[2] ? arguments[2] : "确定";
		var cancelInfo = arguments[3] ? arguments[3] : "取消";
		var callbackCancel = arguments[4] ? arguments[4] : function(){};
		
		$('.dj_confirm').children('h1').html(info);
		$('.dj_confirm').children('.button_cancel').html(cancelInfo);
		$('.dj_confirm').children('.button_true').html(confirmInfo);
		//$(".mode_b2 input").show();
		$(".dj_confirm").show();
		$(".blackbg_confirm").show();
		//$(".hide").hide();
		
		
		$('.button_true').unbind();
		$('.button_true').click(callback);
		$('.button_true').click(function(){
			//$(".mode_b2 input").hide();
			//$(".hide").show();
			$(".dj_confirm").hide();
			$(".blackbg_confirm").hide();
		});
		
		$('.button_cancel').unbind();
		$('.button_cancel').click(callbackCancel);
		$('.button_cancel').click(function(){
			//$(".mode_b2 input").hide();
			//$(".hide").show();
			$(".dj_confirm").hide();
			$(".blackbg_confirm").hide();
		});
	};
	
	
</script>		

