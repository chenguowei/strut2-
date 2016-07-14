<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

		<div class="header_s">
			<div class="search overflowH">
				<div class="header_s_btn3">
					<img src="img/9.png" />
					添加轮播图
				</div>
				<div class="header_s_btn1">搜索</div>
				<div class="search_i">
					<form id="tableForm" action="${url }" method="post">
						<input type="text" name="searchForm.search" value="${searchForm.search }" />
						<input type="hidden" name="searchForm.page" value="${searchForm.page }" />
					</form>		
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
			$('.header_s_btn3').click(function(){
				$.ajax({
				    type: 'POST',
				    url: 'bc_Banner_checkAdd' ,
				    dataType: 'json',
				    success: function(data){
				    	if(data.status==1){
				    		window.location.href = "bc_Banner_addPage?bannerType=${bannerType}";
				    	}
				    	else{
				    		alert(data.info);
				    	}
				    }
				});
				
			});	
		
			$('.header_s_btn1').click(function(){
				$('#tableForm').submit();
			});
		</script>