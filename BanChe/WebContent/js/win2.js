/*查看详情窗口拖动*/
	$(document).ready(function(){
		function $id(id) {
				return document.getElementById(id);
				}
		var isIE = (window.navigator.userAgent.indexOf("IE") == -1) ? false : true;
//		var win = $id("win3");
		$('.win3').each(function(){
			var win = this;
			var header = $(this).children('#win_header')[0];
			
			var pos =function(x, y) {
				this.x = x;
				this.y = y;
			};
				
			var originalpos = new pos(20, 20);
			var oldmouse =new pos(0, 0);
			var oldpos = new pos(0, 0);
			var newpos = new pos(0, 0);
			win.style.left = originalpos.x + "px";
			win.style.top = originalpos.y + "px";
			
			var mousedown = false;
			
			bind("mouseover", over);
			bind("mouseenter", over);
			bind("mouseout", out);
			bind("mouseleave", out);
			bind("blur", out);
			bind("mousedown", down);
			bind("mouseup", up);
			bind("mousemove", move);
			
			function bind(ev, func) {
				if(isIE) {
	　　				header.attachEvent("on" + ev, func);
				} else {
					header.addEventListener(ev, func, false);
				}
			}
			function isLeftButton(btn) {
	    		if(isIE) {
	        		if(btn == 1)
	            		return true;
	        		else
	            		return false;
	   			 } else {
	        		if(btn == 0)
	            		return true;
	        		else
	            		return false;
	    		}
			}
			
			function over(e){
	　　			header.style.backgroundColor = "white";
			}
			function out(e) {
				header.style.backgroundColor = "white";
				up(e);
			}
			function down(e) {
			    e = e || event;
			    if(!isLeftButton(e.button))
			        return;
			    mousedown = true;
			    oldmouse.x = e.clientX;
			    oldmouse.y = e.clientY;
			    oldpos.x = parseInt(win.style.left.replace("px", ""));
			    oldpos.y = parseInt(win.style.top.replace("px", ""));
			}
			function up(e) {
			    if(!isLeftButton(e.button))
			        return;
			    mousedown = false;
			}
			function move(e) {
			    if(!isLeftButton(e.button))
			        return;
			        
			    if(mousedown) {
			        e =e || event;
			        
			        newpos.x = e.clientX - oldmouse.x;
			        newpos.y = e.clientY - oldmouse.y
			    
			        win.style.left = (oldpos.x + newpos.x) + "px";
			        win.style.top = (oldpos.y + newpos.y) + "px";
			    }
			}
		});
	
	});
	