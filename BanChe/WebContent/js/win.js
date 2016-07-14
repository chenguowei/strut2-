/*查看详情窗口拖动*/
	$(document).ready(function(){
		(function(o,s,x,y,d){
			if(o){
				s = o.style;
				d = document;
				o.onselectstart = function(){ return false; };//阻止选择
				o.onmousedown = function(e){
					e = e||event;
					x = e.clientX-o.offsetLeft;
					y = e.clientY-o.offsetTop;
					d.onmousemove = function(e){
						e = e||event;
						s.left = e.clientX - x - 280 + "px";
						s.top = e.clientY - y +70 + "px";
					}
	  			d.onmouseup = function(){ d.onmouseup = d.onmousemove = ""; }
	 			}
			}			
		})
		(document.getElementById("win"));
		
		(function(o,s,x,y,d){
			if(o){
				s = o.style;
				d = document;
				o.onselectstart = function(){ return false; };//阻止选择
				o.onmousedown = function(e){
					e = e||event;
					x = e.clientX-o.offsetLeft;
					y = e.clientY-o.offsetTop;
					d.onmousemove = function(e){
						e = e||event;
						s.left = e.clientX - x - 280 + "px";
						s.top = e.clientY - y + 20 + "px";
					}
	  			d.onmouseup = function(){ d.onmouseup = d.onmousemove = ""; }
	 			}
			}			
		})
		(document.getElementById("win2"));
		
		(function(o,s,x,y,d){
			if(o){
				s = o.style;
				d = document;
				o.onselectstart = function(){ return false; };//阻止选择
				o.onmousedown = function(e){
					e = e||event;
					x = e.clientX-o.offsetLeft;
					y = e.clientY-o.offsetTop;
					d.onmousemove = function(e){
						e = e||event;
						s.left = e.clientX - x - 400 + "px";
						s.top = e.clientY - y +10 + "px";
					}
	  			d.onmouseup = function(){ d.onmouseup = d.onmousemove = ""; }
	 			}
			}			
		})
		(document.getElementById("win3"));
	});
	