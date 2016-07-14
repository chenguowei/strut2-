<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
			<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
		<link rel="stylesheet" type="text/css" href="css/2_1css.css"/>
		<link rel="stylesheet" type="text/css" href="css/common.css"/>
		<link rel="stylesheet" type="text/css" href="css/inputstyle.css"/>
		<script src="js/jquery.js"></script>
		<script src="js/common.js"></script>
		<script src="js/input.js"></script>
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
					<div class="add1 line_m overflowH"><span>登录名称</span><input name="userForm.account" value="${editUser.account}"><span>登录密码</span><input type="password" value="${editUser.password }" name="userForm.password"><span>电话</span><input name="userForm.tel" value="${editUser.tel }"></div>
					<div class="add2 line_m overflowH">
						<span>角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色</span>&nbsp;&nbsp;&nbsp;&nbsp;
						<s:iterator id="role" value="@com.banche.enums.RoleEnums@values()" >
							<s:if test="#editUser.roleId==#role.code">
								<div class="radio on margin2" style="margin-left: 40px;"><input <s:if test="#disable">disabled="disabled"</s:if> type="radio" name="userForm.roleId" value="${role.code }" checked="checked"></div><p>${role.name }</p>
							</s:if>
							<s:else>
								<div class="radio margin2"><input <s:if test="#disable">disabled="disabled"</s:if> type="radio" name="userForm.roleId" value="${role.code }"></div><p>${role.name }</p>
							</s:else>							
						</s:iterator>
						<s:if test="#disable"><input type="hidden" name="userForm.roleId" value="${editUser.roleId }"></s:if>					
					</div>
					<div class="add3 overflowH">
						<span>成员权限</span>
						<div style="float: left;margin-left: 40px;margin-right: 14px;">司机管理：</div>
						<s:iterator id="item" value="#driver.funcList">
							<s:if test="#item.has">								
								<div class="<s:if test="#disable">check2</s:if><s:else>check1 on</s:else> margin1"><input checked <s:if test="#disable">disabled="disabled"</s:if> type="checkbox" name="userForm.authIds" value="${item.id }"></div><p>${item.name }</p>
							</s:if>
							<s:else>
								<div class="check1 margin1"><input <s:if test="#disable">disabled="disabled"</s:if> type="checkbox" name="userForm.authIds" value="${item.id }"></div><p>${item.name }</p>
							</s:else>							
						</s:iterator>
					</div>
					<div class="addline"></div>
				<div class="add3 overflowH">
						<div style="float: left; margin-left: 104px;">经销商管理：</div>
						<s:iterator id="item" value="#agency.funcList">
							<s:if test="#item.has">
								<div class="<s:if test="#disable">check2</s:if><s:else>check1 on</s:else> margin1"><input <s:if test="#disable">disabled="disabled"</s:if> checked type="checkbox" name="userForm.authIds" value="${item.id }"></div><p>${item.name }</p>
							</s:if>
							<s:else>
								<div class="check1 margin1"><input <s:if test="#disable">disabled="disabled"</s:if> type="checkbox" name="userForm.authIds" value="${item.id }"></div><p>${item.name }</p>
							</s:else>							
						</s:iterator>						
				</div>
					<div class="addline"></div>
					<div class="add3 overflowH">
						<div style="float: left; margin-left: 104px;">轮播图管理：</div>
						<s:iterator id="item" value="#banner.funcList">
							<s:if test="#item.has">
								<div class="<s:if test="#disable">check2</s:if><s:else>check1 on</s:else> margin1"><input <s:if test="#disable">disabled="disabled"</s:if> checked type="checkbox" name="userForm.authIds" value="${item.id }"></div><p>${item.name }</p>
							</s:if>
							<s:else>
								<div class="check1 margin1"><input <s:if test="#disable">disabled="disabled"</s:if> type="checkbox" name="userForm.authIds" value="${item.id }"></div><p>${item.name }</p>
							</s:else>							
						</s:iterator>						
				</div>
					<div class="addline"></div>
					<div class="add4 overflowH">
						<div class="blocktitle">货单管理：</div>
						<s:iterator id="item" value="#order.sonModuleList" status="s">
							<s:if test="#s.odd">
								<div class="block">
								<p>${item.name }</p>
								<s:if test="#item.has">
									<div class="check on margin3"><input <s:if test="#disable">disabled="disabled"</s:if> checked type="checkbox" name="userForm.authIds" value="${item.id }"></div><br />
								</s:if>
								<s:else>
									<div class="check margin3"><input <s:if test="#disable">disabled="disabled"</s:if> type="checkbox" name="userForm.authIds" value="${item.id }"></div><br />
								</s:else>
									<div class="margin4">							
									<s:iterator id="func" value="#item.funcList">
										<s:if test="#func.has">
											<div class="<s:if test="#disable">check2</s:if><s:else>check1 on</s:else> margin5"><input <s:if test="#disable">disabled="disabled"</s:if> checked type="checkbox" name="userForm.authIds" value="${func.id }"></div><p>${func.name }</p>
										</s:if>
										<s:else>
											<div class="check1 margin5"><input <s:if test="#disable">disabled="disabled"</s:if> type="checkbox" name="userForm.authIds" value="${func.id }"></div><p>${func.name }</p>
										</s:else>
									</s:iterator>
									</div>
								</div>
							</s:if>
							<s:else>
								<div class="block1">
								<p>${item.name }</p>
								<s:if test="#item.has">
									<div class="check on margin3"><input <s:if test="#disable">disabled="disabled"</s:if> checked type="checkbox" name="userForm.authIds" value="${item.id }"></div><br />
								</s:if>
								<s:else>
									<div class="check margin3"><input <s:if test="#disable">disabled="disabled"</s:if> type="checkbox" name="userForm.authIds" value="${item.id }"></div><br />
								</s:else>	
									<div class="margin4">							
									<s:iterator id="func" value="#item.funcList">
										<s:if test="#func.has">
											<div class="<s:if test="#disable">check2</s:if><s:else>check1 on</s:else> margin5"><input <s:if test="#disable">disabled="disabled"</s:if> checked type="checkbox" name="userForm.authIds" value="${func.id }"></div><p>${func.name }</p>
										</s:if>
										<s:else>
											<div class="check1 margin5"><input <s:if test="#disable">disabled="disabled"</s:if> type="checkbox" name="userForm.authIds" value="${func.id }"></div><p>${func.name }</p>
										</s:else>
									</s:iterator>
									</div>
								</div>
							</s:else>							
							<s:if test="#s.index%2==1">
									<div class="addline2"></div>
								</s:if>
						</s:iterator>
											
					</div>
					<div class="button_sc" isAdd="${isAdd }" isSelf=${isSelf }><s:if test="#isAdd">生成</s:if><s:else>保存</s:else></div>
					<div class="add5">
						<span>备注</span>
						<textarea <s:if test="#disable">disabled="disabled"</s:if> name="userForm.note">${editUser.note }</textarea>						
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
			var isSelf = $(this).attr('isSelf');
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
		        			if(isSelf=='true'){
		        				location.reload();
		        			}else{
		        				//window.location.href = document.referrer;
			        			window.location.href = "bc_User_userList";
		        			}		        			
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

