<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

			<div class="table_l">
					<h1 type="bc_Statistics_orderCount" <s:if test='#uri.startsWith("/BanChe/bc_Statistics_orderCount")'>class="buttonbg"</s:if> >总交易量统计</h1>
					<h1 type="bc_Statistics_orderNew" <s:if test='#uri.startsWith("/BanChe/bc_Statistics_orderNew")'>class="buttonbg"</s:if> >新订单统计</h1>
					<h1 type="bc_Statistics_user" <s:if test='#uri.startsWith("/BanChe/bc_Statistics_user")'>class="buttonbg"</s:if> >用户相关</h1>
					<h1 type="bc_Statistics_feedback" <s:if test='#uri.startsWith("/BanChe/bc_Statistics_feedback")'>class="buttonbg"</s:if> >意见反馈</h1>
			</div>
			
<script type="text/javascript">
	$('.table_l h1').click(function(){
		var url = $(this).attr('type');
		window.location.href = url;
	});
</script>