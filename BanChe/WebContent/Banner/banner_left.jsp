<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

			<div class="table_l">
					<h1 <s:if test='#actionName.equals("bc_Banner_bannerList")'>class="buttonbg"</s:if> type="bc_Banner_bannerList">经销商轮播图</h1>
					<h1 <s:if test='#actionName.equals("bc_Banner_driverBannerList")'>class="buttonbg"</s:if> type="bc_Banner_driverBannerList">司机轮播图</h1>
			</div>
			
			<script type="text/javascript">
				$('.table_l h1').click(function(){
					var url = $(this).attr('type');
					window.location.href = url;
				});
			</script>