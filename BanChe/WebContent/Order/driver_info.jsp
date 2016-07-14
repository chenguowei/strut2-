<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

			<!--查看司机详情弹窗部分-->
			<div class="dialongbox2 buttonshow win2" id="win2">
				<div class="cancel_dialong2"><img src="img/5_2.png"></div>
				<!--行-->
				<div class="text1 overflowH">
					<h1>ID：</h1>
					<p class="width hc_show"><span flag="id">010356789</span></p>
					<h1>物流名称：</h1>
					<P class="width2 hc_show"><span flag="logistics">王宝宝</span></P>
					<!--星级选择部分-->
					<div class="img_x level_star xing2 hc_edit"><input type="hidden" flag="level" name="xing" value="2">
					</div>
 					<!--end星级选择部分-->
				</div>
				<!--end行-->	
				<div class="cancel"><img src="img/5.png"></div>	
				<div class="line"></div>
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle3">联&nbsp;&nbsp;系&nbsp;&nbsp;人：</div>
						<div class="textstyle4 hc_show"><span flag="name">王宝宝</span></div>
				</div>
				<!--end行-->	
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle3">身份证号：</div>
						<div class="textstyle4 hc_show"><span flag="identity">110123456789012345</span></div>
						<div class="textstyle3">身份证照片：</div>
						<div class="textstyle6 view_ps hc_show showImg">查看大图
							<div class="hc_show"><span flag="identityFront" style="display: none;">img/default.png</span></div>
							<div class="hc_show"><span flag="identityBack" style="display: none;">img/default.png</span></div>
						</div>
				</div>
				<!--end行-->	
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle3">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</div>
						<div class="textstyle4 hc_show"><span flag="gender" class="gender_show"></span></div>
						<div class="sex gender_edit hc_edit">
							<input flag="gender" type="radio" name="driverForm.gender" value="男" />男
							<input flag="gender" type="radio" name="driverForm.gender" value="女" checked/>女
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
							<div class="hc_show"><span flag="drivingLicense" style="display: none;">img/default.png</span></div>
							<div class="hc_show"><span flag="drivingLicenseBack" style="display: none;">img/default.png</span></div>
						</div>
				</div>
				<!--end行-->	
				<!--行-->
				<div class="text2 overflowH">
						<div class="textstyle3">收款账号：</div>
						<div class="textstyle4 hc_show"><span flag="paymentAccount">123456789023454576</span></div>
						<div class="mode_b hc_edit"><input flag="paymentAccount" name="driverForm.paymentAccount" type="text" value="123456789023454576"/></div>
						<div class="textstyle3">行驶证照片：</div>
						<div class="textstyle8 view_ps showImg">查看大图
							<div class="hc_show"><span flag="vehicleLicense" style="display: none;">img/default.png</span></div>
							<div class="hc_show"><span flag="vehicleLicenseBack" style="display: none;">img/default.png</span></div>
						</div>
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
				</div>
				<!--end行-->	
			</div>
			<!--end查看司机详情弹窗部分-->
			<!--end查看大图部分-->
			<div class="photo doubleP win4">
				<div class="bg"></div>
				<div class="idphoto photo_r id_doubleP">
					<img src="" onerror='this.src="img/default.png"' onload="AutoResizeImage500(this)"></div>
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

		<script type="text/javascript">
			//查看单张大图
			$('.view_p').click(function(){
				var img_src = $(this).children('span').html();
				showImg(img_src, 'dialongbox2');
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
					showImgs(img_src1, img_src2, 'dialongbox2');
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
		</script>
		