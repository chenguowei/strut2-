<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
			<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
		<link rel="stylesheet" type="text/css" href="css/2_1css.css"/>
		<link rel="stylesheet" type="text/css" href="css/common.css"/>
		<script src="js/jquery.js"></script>
		<script src="js/common.js"></script>
		<script src="js/win.js"></script>
		<script type="text/javascript" src="js/jquery.form.js"></script>
		<title>添加成员</title>
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
				<div class="addbox">
				<form id="userForm" action="<s:if test="#isAdd">bc_User_addUser</s:if><s:else>bc_User_editUser</s:else>">
					<input type="hidden" value="${editUser.id }" name="userForm.id" >
					<div class="add1 line_m"><span>登录名称</span><input name="userForm.account" value="${editUser.account}"><span>登录密码</span><input type="password" value="${editUser.password }" name="userForm.password"><span>电话</span><input name="userForm.tel" value="${editUser.tel }"></div>
					<div class="add2 line_m"><span>角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色</span>&nbsp;&nbsp;&nbsp;&nbsp;
						<s:iterator id="role" value="@com.banche.enums.RoleEnums@values()" >
							<input type="radio" name="userForm.roleId" value="${role.code }" <s:if test="#editUser.roleId==#role.code">checked</s:if> >${role.name }
						</s:iterator>
					</div>
					<div class="add3"><span>成员权限</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;司机管理：&nbsp;&nbsp;&nbsp;
						<s:iterator id="item" value="#driver.funcList">
							<input type="checkbox" name="userForm.authIds" value="${item.id }" <s:if test="#item.has">checked</s:if> >${item.name }
						</s:iterator>
					</div>
					<div class="addline"></div>
					<div class="add3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;经销商管理：
						<s:iterator id="item" value="#agency.funcList">
							<input type="checkbox" name="userForm.authIds" value="${item.id }" <s:if test="#item.has">checked</s:if> >${item.name }
						</s:iterator>
					</div>
					<div class="addline"></div>
					<div class="add4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;货单管理：
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						[<s:iterator id="item" value="#order.sonModuleList">
							<input type="checkbox" name="userForm.authIds" value="${item.id }" <s:if test="#item.has">checked</s:if> >${item.name }
							(
								<s:iterator id="func" value="#item.funcList">
									<input type="checkbox" name="userForm.authIds" value="${func.id }" <s:if test="#func.has">checked</s:if> >${func.name }
								</s:iterator>
							)<br>
						</s:iterator>]<br>
					</div>
					
					<div class="addline2"></div>
					<div class="button_sc" isAdd="${isAdd }"><s:if test="#isAdd">生成</s:if><s:else>保存</s:else></div>
					<div class="add5">
						<span>备注</span>
						<textarea name="userForm.note">${editUser.note }</textarea>
					</div>
					<input type="reset" id="reset" style="display: none;">
				</form>
				</div>
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
		$('.button_sc').click(function(){
			var isAdd = $(this).attr('isAdd');
			$('#userForm').ajaxSubmit({
				type:'post',
				dataType: 'json',
		        success: function (data) {
		        	if(data.status==1){
		        		alert(data.info);
		        		if(isAdd=='true'){		        			
		        			$('#reset').trigger('click');
		        			location.reload();
		        		}else{
		        			//window.location.href = document.referrer;
		        			window.location.href = "bc_User_userList";
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
	</script>
</html>
