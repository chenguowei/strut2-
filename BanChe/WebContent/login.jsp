<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
		<link rel="stylesheet" type="text/css" href="css/1.0css.css"/>
		<link rel="stylesheet" type="text/css" href="css/common.css"/>
		<script src="js/jquery.js"></script>
		<title>登录页面</title>
	</head>
	<body>	
		<div class="mainbox1 overflowH">
			<div class="topbox">
				<div class="topboxbg"></div>
				<div class="column">
				<div class="title"><img src="img/d1.png"></div>
				<form id="login" action="userLogin" method="post">
				<div class="number">用户名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img id="ae" style="display: none;" src="img/7.png">
					<input name="loginForm.account" value="${loginForm.account }" placeholder="请输入用户名"/>
				</div>
				<div class="number">密码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img id="pe" style="display: none;" src="img/7.png">
					<input type="password" name="loginForm.password" value="" placeholder="请输入密码"/>
				</div>
				<div class="point" style="display: none;">您输入的密码有误</div>
				<!-- <a>忘记登录密码</a> -->
				<div class="button_landing">登录</div>
				</form>
				<div style="display: none;"><s:fielderror fieldName="error"/></div>
			</div>
			</div>
			<div class="bottombox">
				©2016 咔咔汽车 All rights reserved版权所有
		</div>
		</div>
	</body>
	<script>
		$(document).ready(function(){
			var width = document.documentElement.clientWidth;
			var height = document.documentElement.clientHeight;
			$('.mainbox1').css('width', width);
			$('.mainbox1').css('height', height);
		});
		
		$('.button_landing').click(function(){
			if($('input[name="loginForm.account"]').val()==""){
				$('#ae').show();
				return;
			}
			if($('input[name="loginForm.password"]').val()==""){
				$('#pe').show();
				return;
			}
			$('#login').submit();
		});
		
		$('input[name="loginForm.account"]').change(function(){
			$('#ae').hide();
		});
		$('input[name="loginForm.password"]').change(function(){
			$('.point').hide();
			$('#pe').hide();
		});
		
		$('.errorMessage').children('li').children('span').each(function(){
			if(Number($(this).html())==1){
				$('.point').show();
			}
		});
	</script>
</html>
