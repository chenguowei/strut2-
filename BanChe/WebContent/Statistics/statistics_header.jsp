<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

		<div class="header_s">
			<div class="search overflowH">
				<div class="header_s_btn1">搜索</div>
 				<div class="search_i">
					<form id="tableForm" action="${url }" method="post">
						<input type="text" name="searchForm.search" value="${searchForm.search }" />
						<input type="hidden" name="searchForm.page" value="${searchForm.page }" />
					</form>		
				</div> 
			</div>
		</div>
