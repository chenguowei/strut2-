<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

			<div class="table_l">
					<h1 type="bc_Agency_agencyList" <s:if test='#uri.startsWith("/BanChe/bc_Agency_agencyList")'>class="buttonbg"</s:if> >经销商信息</h1>
					<h1 type="bc_Agency_blackList" <s:if test='#uri.startsWith("/BanChe/bc_Agency_blackList")'>class="buttonbg"</s:if> >黑名单</h1>
			</div>
			
<script type="text/javascript">
	$('.table_l h1').click(function(){
		var url = $(this).attr('type');
		window.location.href = url;
	});
</script>