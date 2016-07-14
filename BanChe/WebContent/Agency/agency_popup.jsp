<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

			<!--添加经销商弹窗部分-->
			<div class="dialongbox5">
			<form id="addAgencyForm" action="bc_Agency_addAgency">
				<div class="cancel_dialong"><img src="img/5_2.png"></div>
				<div class="text1 overflowH">
					<!-- <h1>ID：</h1>
					<p id="agencyId">010356789</p> -->
					<h1>经销商名称：</h1>
					<P><input name="agencyForm.contact" style="margin-top:24px;"/></P>
				</div>
				<div class="line"></div>
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle04">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：</div>
						<div class="textstyle_b"><input name="agencyForm.contactTel" /></div>
				</div>
				<!--end行-->	
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle04">常用发货地址：</div>
						<div class="textstyle_b"><input name="agencyForm.sendAddr" /></div>
				</div>
				<!--end行-->	
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle04">常用收货地址：</div>
						<div class="textstyle_b"><input name="agencyForm.receiveAddr" /></div>
				</div>
				<!--end行-->	
				<div class="line3"></div>
				<!--行-->
				<div class="text3 overflowH">
						<div class="textstyle04">登记日期：</div>
						<div class="textstyle05" id="registerTime">2016-7-9</div>
						<div class="button_w2 addAgency">完成</div>
				</div>
				<!--end行-->	
				<div style="display: none;"><input id="reset" type="reset"></div>
			</form>
			</div>
			<!--end添加经销商弹窗部分-->	

<script type="text/javascript">

	$(document).ready(function(){
		
		$('.button_b').click(function(){
			$.getJSON("bc_Agency_checkEdit", function(data){
				if(data.status==1){
					$(".button_b,.button_s,.img_x").hide();
					$(".mode_b input").show();
					$(".cancel,.button_w,.sex,.ratingbox").show();
					
					$(".mode_b2 input").show();
					$(".buttonshow").show();
					$(".hide").hide();
				}else{
					alert(data.info);
				}
			});
		});
		
		$('.editBtn').click(function(){
			var id = $(this).children('span').html();
				
			$.DJconfirm("确定保存？", function(){
				$('#edit_agency').ajaxSubmit({
					type:'post',
					dataType: 'json',
			        success: function (data) {
			        	if(data.status==1){
			        		//location.reload();
			        		//alert(JSON.stringify(data.result));
			        		$('#' + id).html(JSON.stringify(data.result));
			        		bindDataToBox(data.result);
			        		
			        		$(".mode_b2 input").hide();
			        		$(".hide").show();
			        		$(".buttonshow").hide();
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
		});
		
		$('.addAgency').click(function(){
			$('#addAgencyForm').ajaxSubmit({
				type:'post',
				dataType: 'json',
				success: function (data) {
					if(data.status==1){
						$(".dialongbox2,.blackbg").hide();
						$('#reset').click();
						location.reload();
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
		
		//拉黑
		$('.button_lh').click(function(){
			var agencyId = $('#agencyIdVO').html();
			
			$.DJconfirm("确定要将经销商拉黑？", function(){
				$.getJSON("bc_AgencyBlackList_add?agencyId="+agencyId, function(data){
					if(data.status==1){
						location.reload();
						//$(".blackbg,.dialongbox").hide();
					}else{
						alert(data.info);
					}
				});
			});
		});
		
		//解禁
		$('.button_j').click(function(){
			var agencyId = $('#agencyIdVO').html();
				
			$.DJconfirm("确定要解禁经销商？", function(){
				$.getJSON("bc_AgencyBlackList_remove?agencyId="+agencyId, function(data){
					if(data.status==1){
						location.reload();
						//$(".blackbg,.dialongbox").hide();
					}else{
						alert(data.info);
					}
				});
			});
		});
		
		//删除
		$('.button_s1, .button_s2').click(function(){
			var agencyId = $('#agencyIdVO').html();
			
			$.DJconfirm("确定删除经销商信息？", function(){
				$.getJSON("bc_Agency_deleteAgency?agencyId=" + agencyId, function(data){
					if(data.status==1){
						$(".blackbg,.dialongbox3,.dialongbox").hide();
						location.reload();
					}else{
						alert(data.info);
					}
				});
			});
		});
		
		//重置密码
		$('.button_cz').click(function(){
			var agencyId = $('#agencyIdVO').html();
			
			$.DJconfirm("确定要重置经销商的密码？", function(){
				$.getJSON("bc_Agency_resetPassword?agencyId=" + agencyId, function(data){
					alert(data.info);
				});
			});
		});
		
	});
	
</script>
		
		
