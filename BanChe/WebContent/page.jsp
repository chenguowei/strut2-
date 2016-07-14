<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		
			<div class="buttonbox">
				<span class="jump" page="1">首页</span>
				<s:if test="#pageVO.hasForward()">
				<div class="up jump" page="${pageVO.currentPage - 1 }">上一页</div>
				</s:if>
				<ul>
					<s:if test="#pageVO.hasMoreForback">
						<li>...</li>
					</s:if>	
					<s:iterator id="item" value="#pageVO.getPageList()">
						<li class="jump" page="${item }">${item }</li>
					</s:iterator>
					<s:if test="#pageVO.hasMoreForward">
						<li>...</li>
					</s:if>	
				</ul>	
				<s:if test="#pageVO.hasNext()">
				<div class="down jump" page="${pageVO.currentPage + 1 }">下一页</div>	
				</s:if>
				<span class="jump" page="${pageVO.totalPage }">末页</span>
			</div>	

<script type="text/javascript">
	$('.jump').click(function(){
		var page = $(this).attr('page');
		$('input[name="searchForm.page"]').val(page);
		$('#tableForm').submit();
	});
</script>		

