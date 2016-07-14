<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


			<!--查看详情弹窗部分-->
			<div class="dialongbox" id="win">
			<form id="edit_driver" action="bc_Driver_editDriver">
				<div class="cancel_dialong"><img src="img/5_2.png"></div>
				<!--行-->
				<div class="text1 overflowH">
					<h1>ID：</h1>
					<p class="width hc_show"><span id="showDriverId" flag="id" >010356789</span></p>
					<div class="hc_edit" style="display: none;"><input flag="id" name="driverForm.id" type="hidden" value="王宝宝"/></div>
					<!-- <h1>物流名称：</h1>
					<P class="width2 hc_show"><span flag="logistics">王宝宝</span></P>
					<div class="hc_edit" style="display: none;"><input flag="logistics" name="driverForm.logistics" type="hidden" value="王宝宝"/></div>
					 -->
					<!--星级选择部分-->
					<div class="img_x level_star xing2 hc_edit"><input type="hidden" flag="level" name="xing" value="2">
					</div>
					<!--编辑状态星级选择-->
					
 					<!--end星级选择部分-->
					<div class="button_b">编辑</div>
				</div>
				<!--end行-->	
				<div class="cancel"><img src="img/5.png"></div>	
				<div class="line"></div>
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle3" style="letter-spacing:1px;">联&nbsp;系&nbsp;人：</div>
						<div class="textstyle4 hc_show"><span flag="name">王宝宝</span></div>
						<div class="mode_b hc_edit"><input flag="name" name="driverForm.name" type="text" value="王宝宝"/></div>
				</div>
				<!--end行-->	
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle3">身份证号：</div>
						<div class="textstyle4 hc_show"><span flag="identity">110123456789012345</span></div>
						<div class="mode_b hc_edit"><input flag="identity" name="driverForm.identity" type="text" value="110123456789012345"/></div>
						<div class="textstyle3">身份证照片：</div>
						<div class="textstyle6 view_ps showImg">查看大图
							<div class="hc_show"><span flag="identityFront" style="display: none;">img/001.jpg</span></div>
							<div class="hc_show"><span flag="identityBack" style="display: none;">img/001.jpg</span></div>
						</div>
						<div class="textstyle8 hc_edit reupload" id="identityFront">重新上传(正面)<input flag="identityFront" type="hidden" name="driverForm.identityFront" /></div>
						<div class="textstyle8 hc_edit reupload" id="identityBack">重新上传(背面)<input flag="identityBack" type="hidden" name="driverForm.identityBack" /></div>
				</div>
				<!--end行-->	
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle3">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</div>
						<div class="textstyle4 hc_show"><span flag="gender" class="gender_show"></span></div>
						<div class="sex gender_edit hc_edit">
							<input flag="gender" type="radio" name="driverForm.gender" value="男" style="cursor:pointer;"/>男
							<input flag="gender" type="radio" name="driverForm.gender" value="女" checked style="cursor:pointer;"/>女
						</div>
						<div class="textstyle3">&nbsp;车&nbsp;&nbsp;&nbsp;牌&nbsp;&nbsp;&nbsp;号：</div>
						<div class="textstyle4 hc_show"><span flag="carNumber">京X24567</span></div>
						<div class="mode_b hc_edit"><input flag="carNumber" name="driverForm.carNumber" type="text" value="京X24567"/></div>
				</div>
				<!--end行-->	
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle3">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：</div>
						<div class="textstyle4 hc_show"><span flag="tel">12345678910</span></div>
						<div class="mode_b hc_edit"><input flag="tel" name="driverForm.tel" type="text" value="12345678910"/></div>
						<div class="textstyle3">驾驶证照片：</div>
						<div class="textstyle7 view_ps showImg">查看大图
							<div class="hc_show"><span flag="drivingLicense" style="display: none;">img/001.jpg</span></div>
							<div class="hc_show"><span flag="drivingLicenseBack" style="display: none;">img/001.jpg</span></div>
						</div>
						<div class="textstyle8 hc_edit reupload" id="drivingLicense">重新上传(正面)<input flag="drivingLicense" type="hidden" name="driverForm.drivingLicense" /></div>
						<div class="textstyle8 hc_edit reupload" id="drivingLicenseBack">重新上传(背面)<input flag="drivingLicenseBack" type="hidden" name="driverForm.drivingLicenseBack" /></div>
				</div>
				<!--end行-->	
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle3">收款账号：</div>
						<div class="textstyle4 hc_show"><span flag="paymentAccount">123456789023454576</span></div>
						<div class="mode_b hc_edit"><input flag="paymentAccount" name="driverForm.paymentAccount" type="text" value="123456789023454576"/></div>
						<div class="textstyle3">行驶证照片：</div>
						<div class="textstyle8 view_ps showImg">查看大图
							<div class="hc_show"><span flag="vehicleLicense" style="display: none;">img/001.jpg</span></div>
							<div class="hc_show"><span flag="vehicleLicenseBack" style="display: none;">img/001.jpg</span></div>
						</div>
						<div class="textstyle8 hc_edit reupload" id="vehicleLicense">重新上传(正面)<input flag="vehicleLicense" type="hidden" name="driverForm.vehicleLicense" /></div>
						<div class="textstyle8 hc_edit reupload" id="vehicleLicenseBack">重新上传(背面)<input flag="vehicleLicenseBack" type="hidden" name="driverForm.vehicleLicenseBack" /></div>
				</div>
				<!--end行-->	
				<div class="line2"></div>
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle3">历史接单：</div>
						<div class="textstyle4 hc_show"><span flag="orderNumber">166</span></div>
				</div>
				<!--end行-->	
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle3">登记日期：</div>
						<div class="textstyle4 hc_show"><span flag="time">2016-7-9</span></div>
						<div class="button_t" style="display: none;">通过</div>
						<div class="button_h" style="display: none;">拉黑</div>
						<div class="button_j" style="display: none;">解禁</div>
						<div class="button_cz" style="display: none;">重置密码</div>
						<div class="button_s">删除</div>
						<div class="button_w editBtn hc_show">完成<span flag="id" style="display: none;"></span></div>
				</div>
				<!--end行-->	
			</form>
			</div>
			<!--end查看详情弹窗部分-->
			<!--end查看大图部分-->
			<div class="photo doubleP win4">
				<div class="bg"></div>
				<div class="idphoto photo_r id_doubleP"><img src="" onerror='this.src="img/default.png"' onload="AutoResizeImage500(this)"></div>
				<div class="idphoto id_doubleP">
					<div class="cancel_photo"></div>
					<img src="" onerror='this.src="img/default.png"' onload="AutoResizeImage500(this)">
				</div>
				<div class="phototext photo_r">正面</div>
				<div class="phototext">反面</div>
			</div>
			<div class="photo singleP win4">
				<div class="bg"></div>
				<div class="idphoto photo_m id_singleP">
					<div class="cancel_photo"></div>
					<img src="" onerror='this.src="img/default.png"' onload="AutoResizeImage500(this)">
				</div>
			</div>
			<!--end查看大图部分-->
			<!--添加司机弹窗部分-->
			<div class="dialongbox2">
				<form id="addDriverForm" action="bc_Driver_addDriver">
				<!--行-->
				<div class="text1 overflowH">
					<h1>物流名称：<input name="driverForm.logistics" /></h1>
					<!-- <h1>ID：</h1>
					<p id="driverId">010356789</p> -->
				</div>
				<!--end行-->	
				<div class="cancel2"><img src="img/5.png"></div>	
				<div class="line"></div>
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle3" style="letter-spacing:1px;">联&nbsp;系&nbsp;人：</div>
						<div class="textstyle4"><input name="driverForm.name" /></div>
				</div>
				<!--end行-->	
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle3">身份证号：</div>
						<div class="textstyle4"><input name="driverForm.identity" /></div>
						<div class="textstyle3">身份证照片：</div>
						<div class="textstyle5 upload" id="identityFrontU"><span>待用户上传</span>(正面)<input type="hidden" name="driverForm.identityFront" /></div>
						<div class="textstyle5 upload" id="identityBackU" style="margin-left: 5px;"><span>待用户上传</span>(背面)<input type="hidden" name="driverForm.identityBack" /></div>
				</div>
				<!--end行-->	
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle3">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</div>
						<div class="textstyle4">
							<input type="radio" name="driverForm.gender" value="男" checked/>男
							<input type="radio" name="driverForm.gender" value="女" />女
						</div>
						<div class="textstyle3">&nbsp;车&nbsp;&nbsp;&nbsp;牌&nbsp;&nbsp;&nbsp;号：</div>
						<div class="textstyle4"><input name="driverForm.carNumber" /></div>
				</div>
				<!--end行-->	
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle3">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：</div>
						<div class="textstyle4"><input name="driverForm.tel" /></div>
						<div class="textstyle3">驾驶证照片：</div>
						<div class="textstyle5 upload" id="drivingLicenseU"><span>待用户上传</span>(正面)<input type="hidden" name="driverForm.drivingLicense" /></div>
						<div class="textstyle5 upload" id="drivingLicenseBackU" style="margin-left: 5px;"><span>待用户上传</span>(背面)<input type="hidden" name="driverForm.drivingLicenseBack" /></div>
				</div>
				<!--end行-->	
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle3">收款账号：</div>
						<div class="textstyle4"><input name="driverForm.paymentAccount" /></div>
						<div class="textstyle3">行驶证照片：</div>
						<div class="textstyle5 upload" id="carId"><span>待用户上传</span>(正面)<input type="hidden" name="driverForm.vehicleLicense" /></div>
						<div class="textstyle5 upload" id="carBackId" style="margin-left: 5px;"><span>待用户上传</span>(背面)<input type="hidden" name="driverForm.vehicleLicenseBack" /></div>
				</div>
				<!--end行-->	
				<div class="line3"></div>
				<!--行-->
				<!--end行-->	
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle3">登记日期：</div>
						<div class="textstyle4" id="registerTime">2016-7-9</div>
						<div class="button_w2 addDriver">完成</div>
				</div>
				<!--end行-->	
				<div style="display: none;"><input id="reset" type="reset"></div>
				</form>
			</div>
			<!--end添加司机弹窗部分-->
			
			<form style="display:none" id="import_form" action="bc_File_uploadFile" enctype="multipart/form-data">
				<input id="importFile" type="file" name="upfile" />
				<input id="importFileFlag" type="hidden" name="flag" />
			</form>
			
			<form style="display:none" id="edit_img_form" action="bc_File_uploadFile" enctype="multipart/form-data">
				<input id="editImg" type="file" name="upfile" />
				<input id="editImgFlag" type="hidden" name="flag" />
			</form>
			
			<style type="text/css">
				.addView { margin-left: 10px;}
				.reupload { display: none; margin-left: 10px;}
			</style>

<script type="text/javascript">

	$('.editBtn').click(function(){
		var id = $(this).children('span').html();
		
		$.DJconfirm("确定保存？", function(){
			$('#edit_driver').ajaxSubmit({
				type:'post',
				dataType: 'json',
		        success: function (data) {
		        	if(data.status==1){
		        		//location.reload();
		        		//alert(JSON.stringify(data.result));
		        		try{
		        			$('#' + id).html(JSON.stringify(data.result));
			        		bindDataToBox(data.result);
			        		showMode();
		        			$('.reupload').hide(); 
		        		}catch(e){
		        			alert(e);
		        		}
		        		      		
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
	

	$('.button_b').click(function(){
		$.getJSON("bc_Driver_checkEdit", function(data){
			if(data.status==1){
				$('.reupload').show();
				
				$(".button_b,.button_s").hide();
				$(".mode_b input").show();
				$(".cancel,.button_w,.sex").show();
				
				$(".mode_b2 input").show();
				$(".buttonshow").show();
				$(".hide").hide();
			}else{
				alert(data.info);
			}
		});		
	});
	$('.cancel').click(function(){
		$('.reupload').hide();
	});
	
	$('.reupload').click(function(){
		var id = $(this).attr('id');
		$('#editImgFlag').val(id);
		$('#editImg').trigger('click');
	});
	
	$('#editImg').change(function(){
		$('#edit_img_form').ajaxSubmit({
			type:'post',
			dataType: 'json',
	        success: function (data) {
	        	if(data.status==1){
	        		//location.reload();
	        		//alert(JSON.stringify(data.result));
	        		var flag = data.result.flag;
	        		var path = data.result.path;	
	        		$('#' + flag).children('input').val(path);
	        		var parent = $('#' + flag).parent();
	        		parent.children('.showImg').children('span').html(path); 
	        		parent.children('.showImg').find('span').each(function(){
	        			if($(this).attr('flag')==flag){
	        				$(this).html(path);
	        			}
	        		});
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
	
	//添加司机弹窗中的上传按钮
	$('.upload').click(function(){
		var id = $(this).attr('id');
		$('#importFileFlag').val(id);
		$('#importFile').trigger('click');		
	});
	
	$('#importFile').change(function(){
		$("#import_form").ajaxSubmit({
			type:'post',
			dataType: 'json',
	        success: function (data) {
	        	if(data.status==1){
	        		//location.reload();
	        		//alert(JSON.stringify(data.result));
	        		var flag = data.result.flag;
	        		var path = data.result.path;
	        		$('#' + flag).children('span').html('重新上传');
	        		$('#' + flag).children('input').val(path);	        		
	        		var parent = $('#' + flag).parent();
	        		if(parent.children('.addView').length == 0){
	        			var temp = '&nbsp;&nbsp;<div class="textstyle8 addView" path="' + path + '"><span>查看大图</span></div>';
	        			parent.append(temp);
	        			parent.children('.addView').attr(flag, path);
        				parent.children('.addView').click(function(){
        					var arr = parent.children('.upload');
        					if(arr.length != 2){
        						alert('界面元素异常');
        						return;
        					}
        					var img1 = $(this).attr(arr.eq(0).attr('id'));
	        				var img2 = $(this).attr(arr.eq(1).attr('id'));
	        				showImgs(img1, img2, 'dialongbox2');
        				});
        			//	parent.children('.addView').click(function(){
	        		//		var t = $(this).attr('path');
	        		//		showImg(t, 'dialongbox2');
	        		//	});
	        			
	        		}else{
	        			parent.children('.addView').attr(flag, path);	        			
	        		}	        		
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
	
	$('.addDriver').click(function(){
		$('#addDriverForm').ajaxSubmit({
			type:'post',
			dataType: 'json',
			success: function (data) {
				if(data.status==1){
					$(".dialongbox2,.blackbg").hide();
					$('#reset').click();
					$('.upload').children('a').html('待用户上传');
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
	
	$('.gender_show').change(function(){
		alert('ffff');
		var value = $(this).html();
		alert(value);
		$('.gender_edit').children('input').each(function(){
			if($(this).val()==value){
				$(this).prop('checked', true);
				return false;
			}
		});
	});
	
	//通过
	$('.button_t').click(function(){
		var driverId = $('#showDriverId').html();
		
		$.DJconfirm("司机信息审核通过？", function(){
			$.getJSON("bc_Driver_checkPass?driverId="+driverId, function(data){
				if(data.status==1){
					location.reload();
					//$(".blackbg,.dialongbox").hide();
				}else{
					alert(data.info);
				}
			});
		});
	});
	
	//拉黑
	$('.button_h').click(function(){
		var driverId = $('#showDriverId').html();
		$.DJconfirm("确定将司机拉黑？", function(){
			$.getJSON("bc_DriverBlackList_add?driverId="+driverId, function(data){
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
		var driverId = $('#showDriverId').html();
			
		$.DJconfirm("确定解禁司机？", function(){
			$.getJSON("bc_DriverBlackList_remove?driverId="+driverId, function(data){
				if(data.status==1){
					location.reload();
					//$(".blackbg,.dialongbox").hide();
				}else{
					alert(data.info);
				}
			});
		});
	});
	
	//查看单张大图
	$('.view_p').click(function(){
		var img_src = $(this).children('span').html();
		showImg(img_src, 'dialongbox');
	});
	
	function showImg(img_src, back){
		$('.id_singleP').children('img').attr('src', img_src);
		$('.cancel_photo').attr('back', back);
		$('.singleP').show();
		$('.' + back).hide();
	}
	
	//查看两张大图
	$('.view_ps').click(function(){
		try{
			var img_src1 = $(this).children('.hc_show').eq(0).children('span').html();
			var img_src2 = $(this).children('.hc_show').eq(1).children('span').html();
			showImgs(img_src1, img_src2, 'dialongbox');
		}catch(e){
			alert(e);
		}
		
	});
	function showImgs(img_src1, img_src2, back){
		$('.id_doubleP').children('img').eq(0).attr('src', img_src1);
		$('.id_doubleP').children('img').eq(1).attr('src', img_src2);
		$('.cancel_photo').attr('back', back);
		$('.doubleP').show();
		$('.' + back).hide();
	}
	
	//删除
	$('.button_s').click(function(){
		var driverId = $('#showDriverId').html();
		
		$.DJconfirm("确定删除司机信息？", function(){
			$.getJSON("bc_Driver_deleteDriver?driverId=" + driverId, function(data){
				if(data.status==1){
					$(".blackbg,.dialongbox").hide();
					location.reload();
				}else{
					alert(data.info);
				}
			});
		});
	});
	
	//重置密码
	$('.button_cz').click(function(){
		var driverId = $('#showDriverId').html();
		
		$.DJconfirm("确定重置司机的登录密码？", function(){
			$.getJSON("bc_Driver_resetPassword?driverId=" + driverId, function(data){
				alert(data.info);
			});
		});
	});
</script>
		
		
