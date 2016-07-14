<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
			
			<div class="table_l">
				<s:iterator id="item" value="#order">
					<h1 type="${item.url }" <s:if test='#actionName.equals(#item.url)'>class="buttonbg"</s:if> >${item.name }<div class="news <s:if test='#item.url.equals("bc_Order_pricingList")'>pricing</s:if>" style="display: <s:if test="#item.hint>0">block</s:if><s:else>none</s:else>;">${item.hint }</div></h1>
				</s:iterator>
			</div>
			
<script type="text/javascript">
	$('.table_l h1').click(function(){
		var url = $(this).attr('type');
		window.location.href = url;
	});
</script>