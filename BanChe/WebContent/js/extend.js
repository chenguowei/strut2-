
$(document).ready(function(){
	/**
	 * 将数据绑定到弹窗
	 */
	$('.view_detail').click(function(){		
		var auth = $(this).attr('auth');
		var obj = $(this);
		$.getJSON(auth, function(data){
			try{
				if(data.status==1){
					var data = obj.children('.hc').html();
					var arr = eval("("+data+")");
							
					bindDataToBox(arr);
					
					//显示司机等级
					showLevel();
					
					$(".blackbg,.dialongbox,.dialongbox3").show();
				}else{
					alert(data.info);
				}
			}catch(e){
				alert('没有权限');
			}						
		});				
	});
	
	
	
});

	function bindDataToBox(arr) {
		//alert(JSON.stringify(arr));
		
		$('.hc_show').children('span').each(function(){
			var flag = $(this).attr('flag');
			var o = $(this);			
			$(arr).each(function(){
				if(this.flag == flag){
					o.html(this.value);
				}			
			});
		});
		
		$('.hc_edit').children('input').each(function(){
			var flag = $(this).attr('flag');
			var o = $(this);
			$(arr).each(function(){
				if(this.flag == flag){
					if(o.attr('type')=="radio"){
						if(o.val()==this.value){
							o.prop('checked', true);
						}						
					}else{
						o.attr('value', this.value);
					}
				}				
			});
		});
		
		$('.hc_edit').children('textarea').each(function(){
			var flag = $(this).attr('flag');
			var o = $(this);
			$(arr).each(function(){
				if(this.flag == flag){
					o.html(this.value);
				}				
			});
		});
	}
	
	function showLevel(){
		
		$('.level_star').each(function(){
			var obj = $(this);
			var level = obj.children('input').val();
			if(!obj.hasClass('xing' + level)){
				obj.removeClass('xing0');
				obj.removeClass('xing1');
				obj.removeClass('xing2');
				obj.removeClass('xing3');
				obj.removeClass('xing4');
				obj.removeClass('xing5');
				obj.addClass('xing' + level);
			}
		});				
	}	
	
	$.DJconfirm = function(info, callback){
		
		$('.buttonshow').children('h1').html(info);
		$(".mode_b2 input").show();
		$(".buttonshow").show();
		$(".blackbg").show();
		$(".hide").hide();
		
		
		$('.button_true').unbind();
		$('.button_true').click(callback);
	};
