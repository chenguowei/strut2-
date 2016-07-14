<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script src="http://ajax.useso.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/2_1css.css" />
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/jquery.jqplot.min.css" />
<script src="js/jquery.js"></script>
<script src="js/common.js"></script>
<script src="js/win.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery.jqplot.min.js"></script>
<script type="text/javascript"
	src="js/plugins/jqplot.categoryAxisRenderer.min.js"></script>
<script type="text/javascript"
	src="js/plugins/jqplot.dateAxisRenderer.min.js"></script>
<script type="text/javascript"
	src="js/plugins/jqplot.highlighter.min.js"></script>
<script type="text/javascript"
	src="js/plugins/jqplot.donutRenderer.min.js"></script>
<title>总交易量统计</title>
</head>
<body>
	<div class="blackbg"></div>
	<!--导航条-->
	<%@ include file="/header.jsp"%>
	<!--end导航条-->
	<!--第二导航条-->
	<%@ include file="statistics_header.jsp"%>
	<!--end第二导航条-->
	<!--中间部分-->
	<div class="mianbox">
		<div class="main overflowH">
			<!--中间左侧-->
			<%@ include file="statistics_left.jsp"%>
			<!--end中间左侧-->
			<!--中间右侧-->
			<div class="table_2">
			<iframe src="http://www.umeng.com" style="width: 100%; height: 450px;">
				
			</iframe>
			</div>
			<!--end中间右侧-->
			<!--中间底部-->
			<!--end中间底部-->
		</div>
	</div>
	<!--end中间部分-->
	<!--页脚部分-->
	<%@ include file="/footer.jsp"%>
	<!--end页脚部分-->
</body>

<script type="text/javascript">
	$("#timeType").change(function(){
		$("#submitForm").submit();
	});
	$(function() {
		line = ${data.data};
		xticks = ${data.xticks};
		//详细配置   
		plot = $.jqplot('chart',[line], {
			title : '${data.title}', //图表表头标题   
			axes : {
				yaxis : {
					min : 0,
					//max : 20,
					numberTicks : 5
				}, //准确控制y轴最大值及最小值,间隔个数   
				xaxis : {
					//renderer : $.jqplot.CategoryAxisRenderer,
					renderer:$.jqplot.DateAxisRenderer,
					ticks: xticks,
					tickOptions:{
						formatString:'%y年%m月%d日',
						showMark: false, 
						fontSize:'8px',
					}, 
					//min:'2016-03-21', 
					//tickInterval:'1 day'
				}
			},
			seriesDefaults : {
				renderer : $.jqplot.PieRenderer,
				rendererOptions : {
					showDataLabels : true,
				}
			},
			//series: [{ color: '#5FAB78'}],                    //定义趋势线颜色      
			highlighter : {
				show:true,
				lineWidthAdjust : 50, //当鼠标移动到放大的数据点上时，设置增大的数据点的宽度,目前仅适用于非实心数据点    
				sizeAdjust : 10, // 当鼠标移动到数据点上时，数据点扩大的增量增量    
				showTooltip : true, // 是否显示提示信息栏    
				tooltipLocation : 'nw', // 提示信息显示位置（英文方向的首写字母）: n, ne, e, se, s, sw, w, nw.    
				tooltipAxes : 'xy', // 提示信息框显示数据点那个坐标轴上的值，目前有横/纵/横纵三种方式,值分别为 x, y or xy.    
				tooltipSeparator : ':' // 提示信息栏不同值之间的间隔符号    
			}
		});
	});
</script>
<script type="text/javascript">
	function AutoResizeImage(maxWidth, maxHeight, objImg) {
		var img = new Image();
		img.src = objImg.src;
		var hRatio;
		var wRatio;
		var Ratio = 1;
		var w = img.width;
		var h = img.height;
		wRatio = maxWidth / w;
		hRatio = maxHeight / h;
		if (maxWidth == 0 && maxHeight == 0) {
			Ratio = 1;
		} else if (maxWidth == 0) {//
			if (hRatio < 1)
				Ratio = hRatio;
		} else if (maxHeight == 0) {
			if (wRatio < 1)
				Ratio = wRatio;
		} else if (wRatio < 1 || hRatio < 1) {
			Ratio = (wRatio <= hRatio ? wRatio : hRatio);
		}
		if (Ratio < 1) {
			w = w * Ratio;
			h = h * Ratio;
		}
		objImg.height = h;
		objImg.width = w;
	}
</script>
</html>


