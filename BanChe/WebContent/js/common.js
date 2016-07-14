
		$(document).ready(function(){
				/*下拉菜单*/
			$(".imgbox3").click(
				function(){
					$(".down_menu").show();
				}
			);
			$(".menu").click(
				function(){
					$(".down_menu").hide();
				}
			);
			$(".down_menu").mouseleave(
				function(){
					$(this).hide()
					}
			);
			/*下拉消息*/
			$(".imgbox1").click(
				function(){
					$(".down_news").show();
				}
			);
			$(".menu2").click(
				function(){
					$(".down_news").hide();
				}
			);
			$(".down_news").mouseleave(
				function(){
					$(this).hide()
					}
			);
			/*查看详情*/
			$(".list_3,.list_width02").click(
				function(){
					//$(".blackbg,.dialongbox").show();
				}
			)
			$(".button_h,.button_s,.cancel_dialong").click(
				function(){
//					$(".blackbg,.dialongbox").hide();
				}
			)
		/*查看大图*/
		$('.imgbutton').click(
			function(){
				var img = $(this).attr('img');
				$(".idphoto img").attr('src', img);
				$(".photo").show();
				$(".dialongbox,.dialongbox2").hide();
			}
		);
		$(".cancel_photo").click(
			function(){
				$(".photo").hide();
				var back = $(this).attr('back');
				$("." + back).show();
//				$(".show_box").show();
			}
		);
		/*编辑*/	
		$(".cancel,.button_w").hide();
		$(".button_b").click(
			function(){
//				$(".button_b,.button_s,.img_x").hide();
//				$(".mode_b input").show();
//				$(".cancel,.button_w,.sex,.ratingbox").show();
			}
		)
		$(".cancel,.cancel_dialong").click(
			function(){
				showMode();
			}
		);
		
		$('.button_a').click(function(){
			$(".mode_b2 input").show();
			$(".buttonshow").show();
			$(".dialongbox").hide();
		});
		$('.cancel_dialong2').click(function(){
			$(".mode_b2 input").hide();
			$(".dialongbox").show();
			$(".buttonshow").hide();
		});
		
		/*添加司机、经销商信息弹窗*/
		$(".header_s_btn2,.header_s_btn3").click(
			function(){
//				$(".dialongbox2,.blackbg,.dialongbox5").show();
//				$(".cancel2,.button_w2,.button_w").show();
			}
		)
		$(".cancel2,.cancel_dialong").click(
			function  () {
				$(".dialongbox2,.blackbg,.dialongbox5,.button_w").hide();
			}
		)
		/*经销商查看详情*/
			$(".list_3").click(
				function(){
//					$(".blackbg,.dialongbox3").show();
				}
			)
			$(".cancel_dialong,.cancel_dialong3").click(
				function(){
					$(".blackbg,.dialongbox3,.dialongbox").hide();
				}
			)
		/*经销商编辑*/
			$(".buttonshow").hide();
			$(".button_bg,.button_a,.delete").click(
				function(){
					$(".mode_b2 input").show();
					$(".buttonshow").show();
					$(".hide").hide();
				}
			)
			$(".cancel3,.cancel_dialong,.cancel_dialong2,.button_cancel").click(
				function(){
					$(".mode_b2 input").hide();
					$(".hide").show();
					$(".buttonshow").hide();
				}
			)
		/*运输途中查看物流*/
		$(".button_car1").click(function(){
			$(this).hide();
			$(".mapbox,.button_car2").show();
		});
		$(".button_car2").click(function(){
			$(".button_car1").show();
			$(".mapbox,.button_car2").hide();
		});
		/*限制字数 */
		$(document).ready(function(){ 
		$(".list_width1").each(
			function(){
				var maxwidth=29; if($(this).text().length>maxwidth)
				{ 
					$(this).text($(this).text().substring(0,maxwidth));
					$(this).html($(this).html()+'...');
				}
			}); 
		});
	
	/*星级选择*/
	function __start(){
			 var initialize_width=0;
			 if(document.getElelmentById){return false};
			 if(document.getElementsByTagName==null){return false;}
			 var startLevelObj=document.getElementById("xingxing")
			 if(startLevelObj==null){return false;}
			 initialize_width=parseInt(startLevelObj.getAttribute("star_width"),10);
			 if(isNaN(initialize_width) || initialize_width==0){return false;}
			 var ul_obj=startLevelObj.getElementsByTagName("ul");
			 if(ul_obj.length<1){return false;}
			 var length=ul_obj.length;
			 var li_length=0;
			 var a_length=0;
			 var li_obj=null;
			 var a_obj=null;
			 var defaultInputObj=null;
			 var defaultValue=null;
			 for(var i=0;i<length;i++){
			  li_obj=ul_obj[i].getElementsByTagName("li");
			  li_length=li_obj.length;
			  if(li_length<0){return false;}
			  defaultInputObj=li_obj[0].getElementsByTagName("input");if(!defaultInputObj){return false;}
			  defaultValue=parseInt(defaultInputObj[0].value,10);
			  if(!isNaN(defaultValue) && defaultValue!=0){
			  }
			  for(var j=0;j<li_length;j++){
			   a_obj=li_obj[j].getElementsByTagName("a");
			   if(a_obj.length<1){continue;}
			   if(a_obj[0].className.indexOf("star")>0){
			    a_obj[0].onclick=function(){
			     return give_value(this);
			    }
			    a_obj[0].onfocus=function(){
			     this.blur();
			    }
			   }
			  }
			 }
			}
			function give_value(obj){
			 var status=true;
			 var parent_obj=obj.parentNode;
			 var i=0;
			 while(status){
			  i++;
			  if(parent_obj.nodeName=="UL"){break;}
			  parent_obj=parent_obj.parentNode;
			  if(i>1000){break;}
			 }
			 var hidden_input=parent_obj.getElementsByTagName("input")[0];
			 if(hidden_input.length<1){}
			 var txt=obj.firstChild.nodeValue;
			 if(isNaN(parseInt(txt,10))){return false;}
			 hidden_input.setAttribute("value",txt.toString());
			
			 var current_li=parent_obj.getElementsByTagName("li");
			 var length=current_li.length;
			 var ok_li_obj=null;
			 for(var i=0;i<length;i++){
			  if(current_li[i].className.indexOf("current_rating")>=0){
			   ok_li_obj=current_li[i];break;
			  }
			 }
			 __current_width=txt*20;
			 ok_li_obj.style.width=__current_width+"px";
			 return false;
			}
		__start();
				

});


	function showMode(){
		$(".cancel,.button_w,.sex,.ratingbox").hide();
		$(".mode_b input").hide();
		$(".button_b,.button_s,.img_x").show();
	}
	
	function AutoResizeImage500(objImg){
		AutoResizeImage(500, 300,objImg);
	}
	function AutoResizeImage(maxWidth,maxHeight,objImg){
		var img = new Image();
		img.src = objImg.src;
		var hRatio;
		var wRatio;
		var Ratio = 1;
		var w = img.width;
		var h = img.height;
		wRatio = maxWidth / w;
		hRatio = maxHeight / h;
		if (maxWidth ==0 && maxHeight==0){
			Ratio = 1;
			}
		else if (maxWidth==0){//
			if (hRatio<1) Ratio = hRatio;
			}
		else if (maxHeight==0){
			if (wRatio<1) Ratio = wRatio;
			}
		else if (wRatio<1 || hRatio<1){
			Ratio = (wRatio<=hRatio?wRatio:hRatio);
		}
		if (Ratio<1){
			w = w * Ratio;
			h = h * Ratio;
		}
		objImg.height = h;
		objImg.width = w;
	}