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
		<script src="js/extend.js"></script>
		<script src="js/common.js"></script>
		<script src="js/win.js"></script>		
		<title>成员管理</title>
	</head>
	<body>
		<div class="blackbg">
		</div>
		<!--导航条-->
		<%@ include file="/header.jsp" %>
		<!--end导航条-->
		<!--第二导航条-->
		<%@ include file="user_header.jsp" %>
		<!--end第二导航条-->
		<!--中间部分-->
		<div class="mianbox">
		<div class="main overflowH">
				<div class="topcolumn">
					项目成员（${list.size() }）
					<div class="button_gl" onClick="mode()">管理</div>
					<div class="button_qx" onClick="mode()">取消</div>
				</div>
				<div class="copecolumn">
				
				<div class="peoplecolumn">
					<s:iterator id="item" value="#list">
						<ul class="showbutton">
							<li class="imgbox"><img src="img/13.png"></li>
							<li class="namebox">
								<h1>${item.account }</h1>
								<p class="<s:if test="#item.roleId==@com.banche.enums.RoleEnums@SUPER_ADMIN.getCode()">namecolor1</s:if><s:elseif test="#item.roleId==@com.banche.enums.RoleEnums@ADMIN.getCode()">namecolor2</s:elseif><s:else>namecolor</s:else>">${item.roleVO }</p>
							</li>
							<li class="origin">
								<s:if test="#item.roleId==@com.banche.enums.RoleEnums@SUPER_ADMIN.getCode()">
									<img src="img/red.png">
								</s:if>
								<s:elseif test="#item.roleId==@com.banche.enums.RoleEnums@ADMIN.getCode()">
									<img src="img/yellow.png">
								</s:elseif>
								<s:else>
									<img src="img/green.png">
								</s:else>
							</li>
							<li class="power editBtn" type="${item.id }">进入权限管理</li>
							<li class="delete deleteBtn" type="${item.id }" account="${item.account }">删除该成员</li>
						</ul>
					</s:iterator>
					
				</div>
				</div>
		</div>
		</div>
		<!--end中间部分-->
		<!--页脚部分-->
		<div class="footerbox">
			<div class="foonter">©2016 咔咔汽车 All rights reserved版权所有</div>
		</div>
		<!--end页脚部分-->
		<!--[if IE 7]><style>
		.power{margin-left: -165px;}
		.delete{margin-left: -165px;}
		.button_qx{top:0;}
		</style><![endif]-->
	</body>
	
	<script type="text/javascript">
		$('.editBtn').click(function(){
			var userId = $(this).attr('type');
			$.getJSON("bc_User_checkEditAuth?userId="+userId, function(data){
				if(data.status==1){
					window.location.href = "bc_User_editUserPage?userId=" + userId;
				}else{
					alert(data.info);
				}
			});		
		});
		
		$('.deleteBtn').click(function(){
			var userId = $(this).attr('type');
			var account = $(this).attr('account');
			
			try{
				$.DJconfirm("是否要删除账号："+account, function(){
					$.getJSON("bc_User_deleteUser?userId=" + userId, function(data){
						if(data.status==1){
							location.reload();
						}else{
							alert(data.info);
						}
					});
				});
			}catch(e){
				alert(e);
			}
			
		});
		
		$(".showbutton").mouseenter(function(){
			if(sum==1){
				$(this).children('.power').show();
			}else{
				$(this).children('.delete').show();
			}
						
		});
		$(".button_gl").click(function(){
			$(this).hide();
			$(".button_qx").show();
		});
		$(".button_qx").click(function(){
			$(this).hide();
			$(".button_gl").show();
		});
		$(".delete").mouseleave(function(){
			$(this).hide();
		});
		$(".power").mouseleave(function(){
			$(this).hide();
		});
		var sum=1;
		function mode()
		{
			if(sum==1)
			{
				sum = 0;
			
			}
			else
			{
				sum = 1;
			}
		}
	</script>
	
</html>
