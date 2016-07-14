<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

		<div class="header_s">
			<div class="search overflowH">
				<div class="header_s_btn1 margin">搜索</div>
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
			$(document).ready(function(){
				$('.view_detail').click(function(){
					try{
						if($('textarea[name="note"]').val()==""){
							$('.button_add').html('添加备注');
						}else{
							$('.button_add').html('修改备注');
						}
					}catch(e){
						alert(e);
					}
					
				});
				
				$('.button_add').click(function(){
					$('#editNote_form').ajaxSubmit({
						type:'post',
						dataType: 'json',
						success: function (data) {
							if(data.status==1){
								location.reload();
							}else{
								alert(data.info);
							}
						},
						error: function (XMLResponse) {
							//alert(XMLResponse.responseText);
			                var ss =JSON.stringify(XMLResponse);
			                alert('操作失败！'+ss);
						}
					});
				});
				
				$('.button_list').click(function(){
					$.DJconfirm("确定取消订单？", function(){
						var id = $('input[name="orderId"]').val();
						var note = $('textarea[name="note"]').val();
						$.getJSON('bc_Order_cancelOrder?orderId='+id+'&note='+note, function(data){
							alert(data.info);
							if(data.status==1){
								location.reload();
							}else{
								
							}
						});
					});
					
				});
				
				$('.header_s_btn1').click(function(){
					$('#tableForm').submit();
				});
			});
			
		</script>