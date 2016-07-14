<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

			<div class="table_l">
					<h1 type="bc_Driver_uncheckedList" <s:if test='#uri.startsWith("/BanChe/bc_Driver_uncheckedList")'>class="buttonbg"</s:if> >待审核<div class="news unpassed" style="display: <s:if test="#hint.unpassed>0">block</s:if><s:else>none</s:else>;">${hint.unpassed }</div></h1>
					<h1 type="bc_Driver_driverList" <s:if test='#uri.startsWith("/BanChe/bc_Driver_driverList")'>class="buttonbg"</s:if> >司机信息</h1>
					<h1 type="bc_Driver_blackList" <s:if test='#uri.startsWith("/BanChe/bc_Driver_blackList")'>class="buttonbg"</s:if> >黑名单</h1>
			</div>
			
<script type="text/javascript">
	$('.table_l h1').click(function(){
		var url = $(this).attr('type');
		window.location.href = url;
	});
</script>