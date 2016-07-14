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
<link rel="stylesheet" type="text/css" href="css/jquery.jqplot.min.css"  />
<script src="js/jquery.js"></script>
<script src="js/common.js"></script>
<script src="js/win.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery.jqplot.min.js"></script>
<script src="js/plugins/jqplot.pieRenderer.min.js"></script>
<script src="js/plugins/jqplot.donutRenderer.min.js"></script>
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
			<%@ include file="statistics_left.jsp" %>
			<!--end中间左侧-->
			<!--中间右侧-->
			<div class="table_2">
				<div id="chart"></div>
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
$(function () {

    var data = ${data.data};
    var total = ${data.total};
    var arrLabels = $.makeArray($(data).map(function(){return this[1] + " (" + Math.round(this[1]/total * 100) + "%)";}));
    $.jqplot('chart', [data], {
    	title:"${data.title} (" + Math.floor(total) + ")",
        seriesDefaults: {
            renderer: $.jqplot.PieRenderer,
            rendererOptions: {
            	dataLabels:arrLabels,
                showDataLabels: true,
                dataLabelsFormatString:'%s',
            }
        },
        legend: {
            show: true,
            location: "e"
        }
    });
});

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


