
$(document).ready(function(){
	
		
	$('input[id="different"]').change(function(){
		var different = $(this).val();
		var price = $('input[name="price"]').val();
		if(different==""){
			alert("请输入数字");
			return;
		}
		if(isNaN(different)){
			alert("请输入数字");
			return;
		}
		$.DJconfirm("是否将" + different + "设置为默认值？", function(){
			$.getJSON("bc_Order_editDifferent?different=" + different, function(data){
				if(data.status==1){
					if(!isNaN(price) && !isNaN(different)){
						var driver = price - different;
						if(driver<=0){
						//	alert("差钱金额必须小于总费用");
						}else{
							$('span[flag="driverShowPrice"]').html(driver);
							$('input[name="driverShowPrice"]').val(driver);
						}			
					}
				}else{
					alert(data.info);
				}
			});
		}, "设为默认", "仅单次", function(){
			if(!isNaN(price) && !isNaN(different)){
				var driver = price - different;
				if(driver<=0){
				//	alert("差钱金额必须小于总费用");
				}else{
					$('span[flag="driverShowPrice"]').html(driver);
					$('input[name="driverShowPrice"]').val(driver);
				}			
			}
		});
//		if(!isNaN(price) && !isNaN(different)){
//			var driver = price - different;
//			if(driver<=0){
//			//	alert("差钱金额必须小于总费用");
//			}else{
//				$('span[flag="driverShowPrice"]').html(driver);
//				$('input[name="driverShowPrice"]').val(driver);
//				$('show').html(driver);
//			}			
//		}
		
	});

	$('input[name="price"]').bind("input propertychange", function(){
		var rate = $('input[name="rate"]').val();
		var price = $(this).val();
		
		var different = $('input[id="different"]').val();
		if(!isNaN(price) && !isNaN(different)){
			var driver = price - different;
			if(driver<=0){
//				alert("差钱金额必须小于总费用");
				return;
			}else{
				$('span[flag="driverShowPrice"]').html(driver);
				$('input[name="driverShowPrice"]').val(driver);
			}			
		}
		
		if(!isNaN(price)){
			var earnest = price * rate / 100;
			$('span[flag="earnest"]').html(earnest);
		}
		
	});
	
	$('input[name=rate]').change(function(){
		var rate = $(this).val();
		if(rate==""){
			alert("请输入数字");
			return;
		}
		if(isNaN(rate)){
			alert("请输入数字");
			return;
		}
		$.DJconfirm("是否将" + rate + "%设置为默认值？", function(){
			$.getJSON("bc_Order_editRate?rate=" + rate, function(data){
				if(data.status==1){
					var price = $('input[name="price"]').val();
					var earnest = price * rate / 100;
					$('span[flag="earnest"]').html(earnest);
				}else{
					alert(data.info);
				}
			});
		}, "设为默认", "仅单次", function(){
			var price = $('input[name="price"]').val();
			var earnest = price * rate / 100;
			$('span[flag="earnest"]').html(earnest);
		});
	});
		
});

