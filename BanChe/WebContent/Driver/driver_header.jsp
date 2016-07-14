<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

		<div class="header_s">
			<div class="search overflowH">
				<div class="header_s_btn2">
					<img src="img/9.png" />
					添加司机
				</div>
				<div class="header_s_btn1">搜索</div>
				<!--<img src="img/2_s.png"/>-->
				<div class="search_i">
				<form id="tableForm" action="${url }" method="post">
					<input type="text" name="searchForm.search" value="${searchForm.search }" />
					<input type="hidden" name="searchForm.page" value="${searchForm.page }" />
				</form>	
				</div>
			</div>
		</div>

		<script type="text/javascript">
			$('.header_s_btn2').click(function(){
				
				$.getJSON("bc_Driver_checkAdd", function(data){
					if(data.status==1){
						
						$(".dialongbox2,.blackbg,.dialongbox5").show();
						$(".cancel2,.button_w2,.button_w").show();
						
						$.getJSON("bc_Driver_generateId", function(result){
							if(result.status==1){
								$('#driverId').html(result.info);
								$('#did').val(result.info);
							}else{
								alert(result.info);
							}
						});
						
						$.getJSON('bc_Common_todayDate', function(result){
							if(result.status==1){
								$('#registerTime').html(result.info);
							}else{
								alert(result.info);
							}
						});
					}else{
						alert(data.info);
					}
				});				
			});
			
			$('.header_s_btn1').click(function(){
				$('#tableForm').submit();
			});
		</script>