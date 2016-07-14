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
		<link rel="stylesheet" type="text/css" href="css/lunbo.css"/>
		<script src="js/jquery.js"></script>
		<script src="js/common.js"></script>
		<script src="js/win.js"></script>
		<script type="text/javascript" src="js/jquery.form.js"></script>
		<title>添加轮播图</title>
	</head>
	<body>
		<div class="blackbg">
		</div>
		<!--导航条-->
		<%@ include file="/header.jsp" %>
		<!--end导航条-->
		<!--第二导航条-->
		<%@ include file="banner_header.jsp" %>
		<!--end第二导航条-->
		<!--中间部分-->
		<div class="mianbox">
		<div class="main overflowH">
				<!--编辑状态-->
				<div class="line_H overflowH add_box">
				<form id="addForm" action="bc_Banner_add">
					<input type="hidden" name="bannerForm.bannerType" value="${bannerType }">
					<div class="title">添加${typeName }</div>
					<div class="edit_l">
						<h1 class="textstyle">上传轮播图：</h1>
						<div class="preview"><img id="bannerImage" onload="AutoResizeImage(273,124,this)"></div>
						<div class="btn_f upload">上传图片<input/></div>
						<input type="hidden" name="bannerForm.image">
						<div class="link">
							<h1 class="textstyle">轮播链接：</h1><input name="bannerForm.url" />
						</div>
						<div class="link">
								<h1 class="textstyle">图片标题：</h1><input name="bannerForm.title" />
						</div>
					</div>
					<div class="edit_r">
						<div class="textstyle pad4">轮播顺序：<input name="bannerForm.showOrder">
							<div class="text">注：数值越大轮播图显示越靠前</div>
						</div>
						<div class="textstyle pad5">发布情况：
							<input type="radio" name="bannerForm.status" value="<s:property value="@com.banche.enums.BannerStatus@RELEASED.getCode()"/>" checked >发布</input>
							<input type="radio" name="bannerForm.status" value="<s:property value="@com.banche.enums.BannerStatus@UN_RELEASED.getCode()"/>">未发布</input>
						</div>
						<div class="textstyle pad6">轮播图说明：<input name="bannerForm.intro"></div>
						<div class="textstyle pad7">说明备注：<input name="bannerForm.note"></div>
					</div>
					<div class="button_bc">保存</div>
					<div style="display: none;"><input id="resetBtn" type="reset"></div>
				</form>
				</div>
				<!--编辑状态end-->
				
				<form style="display:none" id="import_form" action="bc_File_uploadFile" enctype="multipart/form-data">
					<input id="importFile" type="file" name="upfile" />
					<input id="importFileFlag" type="hidden" name="flag" />
				</form>
		</div>
		</div>
		<!--end中间部分-->
		<!--页脚部分-->
		<%@ include file="/footer.jsp" %>
		<!--end页脚部分-->
	</body>
	
	<script type="text/javascript">
		$('.button_bc').click(function(){
			$('#addForm').ajaxSubmit({
				type:'post',
				dataType: 'json',
		        success: function (data) {
		        	if(data.status==1){
		        		$('#resetBtn').trigger('click');
		        		$('#bannerImage').attr('src', '');
		        		window.location.href = "${url}";
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
		
		$('.upload').click(function(){
			$('#importFile').trigger('click');
		});
		
		$('#importFile').change(function(){
			$("#import_form").ajaxSubmit({
				type:'post',
				dataType: 'json',
		        success: function (data) {
		        	if(data.status==1){
		        		var flag = data.result.flag;
		        		var path = data.result.path;
		        		$('#bannerImage').attr('src', path);
		        		$('input[name="bannerForm.image"]').val(path);
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
		function AutoResizeImage(maxWidth,maxHeight,objImg){
			var img = new Image();
			img.src = objImg.src;
			var hRatio;
			var wRatio;
			var Ratio = 1;
			var w = img.width;
			var h = img.height;
			wRatio = maxWidth / w;
			hRatio = maxHeight / h;
			if (maxWidth ==0 && maxHeight==0){
				Ratio = 1;
				}
			else if (maxWidth==0){//
				if (hRatio<1) Ratio = hRatio;
				}
			else if (maxHeight==0){
				if (wRatio<1) Ratio = wRatio;
				}
			else if (wRatio<1 || hRatio<1){
				Ratio = (wRatio<=hRatio?wRatio:hRatio);
			}
			if (Ratio<1){
				w = w * Ratio;
				h = h * Ratio;
			}
			objImg.height = h;
			objImg.width = w;
		}
	</script>
</html>


