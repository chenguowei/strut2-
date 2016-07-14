<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
		
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		
		<link rel="stylesheet" type="text/css" href="css/2_1css.css"/>
		<link rel="stylesheet" type="text/css" href="css/common.css"/>
		<link rel="stylesheet" type="text/css" href="css/lunbo.css"/>
		<script src="js/jquery.js"></script>
		<script src="js/common.js"></script>
		<script src="js/win.js"></script>
		<script type="text/javascript" src="js/jquery.form.js"></script>
		<title>轮播图</title>
	</head>
	<body>
		<div class="blackbg"></div>
		<!--导航条-->
		<%@ include file="/header.jsp" %>
		<!--end导航条-->
		<!--第二导航条-->
		<%@ include file="banner_header.jsp" %>
		<!--end第二导航条-->
		<!--中间部分-->
		<div class="mianbox">
		<div class="main overflowH">
			<!--中间左侧-->
			<%@ include file="banner_left.jsp" %>
			<!--end中间左侧-->
			<!--中间右侧-->
			<div class="table_2">
				<!--表头-->
				<div class="line_H overflowH listbg">
					<div class="list_1">ID</div>
					<div class="list_2">
						<div class="list_01">图片</div>
						<div class="list_01 pad">图片标题</div>
						<div class="list_01 pad1">排序</div>
						<div class="list_01 pad2">添加时间</div>
						<div class="list_01 pad3">状态</div>
					</div>
					<div class="list_03">操作</div>
				</div>
				<!--end表头-->
				<s:iterator id="banner" value="#pageVO.getData()">
					<!--行-->
					<div class="line_H overflowH">
						<div class="plist_1 textstyle">${banner.id }</div>
						<div class="plist_2 item_${banner.id }">
							<div class="p01"><img id="bannerImage_${banner.id }" src="${banner.image }" onload="AutoResizeImage(207,94,this)"></div>
							<div class="p02">${banner.title }</div>
							<div class="p03">${banner.showOrder }</div>
							<div class="p04">${banner.timeVO }</div>
							<s:if test="#banner.statusVO==@com.banche.enums.BannerStatus@RELEASED.getName()">
								<div class="p06 status">${banner.statusVO }</div>
							</s:if>
							<s:else>
								<div class="p05 status">${banner.statusVO }</div>
							</s:else>
						</div>
						<div class="plist_3">
							<div class="btn_bj" type="${banner.id }">编辑</div>
							<div class="btn_sc" type="${banner.id }">删除</div>
						</div>
					</div>
					<!--end行-->
					<!--编辑状态-->
					<div class="line_H overflowH editbox_${banner.id }" style="display: none;">
					<form action="bc_Banner_edit">
						<input type="hidden" name="bannerForm.id" value="${banner.id }">
						<input type="hidden" name="bannerForm.bannerType" value="${banner.bannerType }">
						<div class="edit_l">
							<h1 class="textstyle">上传轮播图：</h1>
							<div class="preview"><img id="bannerImage_edit_${banner.id }" alt="" src="${banner.image }" onload="AutoResizeImage(273,124,this)"></div>
							<div class="btn_f upload" flag="${banner.id }">上传图片<input /></div>
							<input type="hidden" name="bannerForm.image" value="${banner.image }">
							<div class="link">
								<h1 class="textstyle">轮播链接：</h1><input name="bannerForm.url" value="${banner.url }" />
							</div>
							<div class="link">
								<h1 class="textstyle">图片标题：</h1><input name="bannerForm.title" value="${banner.title }" />
							</div>
						</div>
						<div class="edit_r">
							<div class="textstyle pad4">轮播顺序：<input name="bannerForm.showOrder" value="${banner.showOrder }">
								<div class="text">注：数值越大轮播图显示越靠前</div>
							</div>
							<div class="textstyle pad5">发布情况：
								<s:iterator id="bs" value="@com.banche.enums.BannerStatus@values()">
									<input type="radio" name="bannerForm.status" value="${bs.code }" vo="${bs.name }" <s:if test="#bs.getCode()==#banner.status">checked</s:if> >${bs.name }</input>
								</s:iterator>
							</div>
							<div class="textstyle pad6">轮播图说明：<input name="bannerForm.intro" value="${banner.intro }"></div>
							<div class="textstyle pad7">说明备注：<input name="bannerForm.note" value="${banner.note }"></div>
						</div>
					</form>
					</div>
					<!--编辑状态end-->
				</s:iterator>		
				
			</div>
			<!--end中间右侧-->
			<!--中间底部-->
			<%@ include file="/page.jsp" %>
			<!--end中间底部-->
		</div>
		</div>
		<!--end中间部分-->
		
				<form style="display:none" id="import_form" action="bc_File_uploadFile" enctype="multipart/form-data">
					<input id="importFile" type="file" name="upfile" />
					<input id="importFileFlag" type="hidden" name="flag" />
				</form>
		
		<!--页脚部分-->
		<%@ include file="/footer.jsp" %>
		<!--end页脚部分-->
	</body>
	
	<script type="text/javascript">
		$('.btn_sc').click(function(){
			var id = $(this).attr('type');
			$.ajax({
			    type: 'POST',
			    url: 'bc_Banner_checkDelete' ,
			    dataType: 'json',
			    success: function(data){
			    	if(data.status==1){
						$.DJconfirm("确定删除轮播图？", function(){
							$.getJSON('bc_Banner_delete?bannerId=' + id, function(data){
								if(data.status==1){
									location.reload();
								}else{
									alert(data.info);
								}
							});
						});		
			    	}
			    	else{
			    		alert(data.info);
			    	}
			    }
			});
		});
		
		/*轮播图片编辑*/
		$(".btn_bj").click(function(){
			var id = $(this).attr('type');
			var className = ".editbox_" + id;
			if($(className).is(':hidden')){
				$.ajax({
				    type: 'POST',
				    url: 'bc_Banner_checkEdit' ,
				    dataType: 'json',
				    success: function(data){
				    	if(data.status==0){
				    		alert(data.info);
				    	}
				    	else{
				    		$(className).slideToggle(500);
				    	}
				    }
				});
			}else{
				$.DJconfirm("确定保存轮播图？", function(){
					$(className).children('form').ajaxSubmit({
						type:'post',
						dataType: 'json',
				        success: function (data) {
				        	if(data.status==1){
				        		var temp = $(className).children('form').find('input[name="bannerForm.status"]:checked').attr('vo');
				        		var o = $('.item_' + id).find('.status');
				        		o.html(temp);
				        		if(temp=='<s:property value="@com.banche.enums.BannerStatus@RELEASED.getName()" />'){
				        			o.removeClass('p05');
				        			o.addClass('p06');
				        		}else{
				        			o.removeClass('p06');
				        			o.addClass('p05');
				        		}
				        		temp = $(className).children('form').find('input[name="bannerForm.showOrder"]').val();
				        		$('.item_' + id).find('.p03').html(temp);
				        		
				        		temp = $(className).children('form').find('input[name="bannerForm.title"]').val();
				        		$('.item_' + id).find('.p02').html(temp);
				        		
				        		$('#bannerImage_' + id).attr('src', $('#bannerImage_edit_' + id).attr('src'));
				        		
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
				}, "确定", "取消", function(){
					location.reload();
				});			
				$(className).slideToggle(500);
			}
			
		})
		
		$('.upload').click(function(){
			var id = $(this).attr('flag');
			$('input[name="flag"]').val(id);
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
		        		//$('#bannerImage_' + flag).attr('src', path);
		        		$('#bannerImage_edit_' + flag).attr('src', path);
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


