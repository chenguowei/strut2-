$(document).ready(function(){
	
	$(".radio").click(function(){
		$(this).addClass("on").siblings().removeClass("on");
		$(this).children("input[type='radio']").attr("checked","checked" );
	});
	$(".check,.check1").click(function(){
		 if($(this).hasClass("on")){
		 	$(this).removeClass("on");
		 	$(this).children("input[type='checkbox']").attr("checked","" );
		 }else{
		 	$(this).addClass("on");
		 	$(this).children("input[type='checkbox']").attr("checked","checked" );
		 }
	});
	
	
})
