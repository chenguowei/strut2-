<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

		<div class="header_s">
			<div class="search overflowH">
				<s:if test="#user.getRoleId()==@com.banche.enums.RoleEnums@SUPER_ADMIN.code">
					<div class="header_s_btn4">
						<img src="img/9.png" />
						添加成员
					</div>
				</s:if>				
				<div class="header_s_btn1">搜索</div>
				<!--<img src="img/2_s.png"/>-->
				<div class="search_i">
				<form id="tableForm" action="bc_User_userList" method="post">
					<input type="text" name="searchForm.search" value="${searchForm.search }" />
					<input type="hidden" name="searchForm.page" />
				</form>	
				</div>
			</div>
		</div>

		<script type="text/javascript">
			$('.header_s_btn4').click(function(){
				$.getJSON("bc_User_checkAddAuth", function(data){
					if(data.status==1){
						window.location.href = "bc_User_addUserPage";
					}else{
						alert(data.info);
					}
				});
			});
			
			$('.header_s_btn1').click(function(){
				$('#tableForm').submit();
			});
		</script>