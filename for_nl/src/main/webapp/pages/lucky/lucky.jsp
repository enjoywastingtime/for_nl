<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>幸运抽奖</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<script type="text/javascript" src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>

<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<style>
.tt {
	width: 33.33%;
	height: 0;
	padding-top: 33.33%;
	position: relative;
	float: left;
}

.hh {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(231, 234, 237, 0.5);
	border: 1px solid rgba(255, 255, 255, 0.8);
}

.sele {
	background-color: rgba(222, 109, 100, 0.5);
}
</style>
<body style="background-color:#E8F2FE; ">
	<div style="width:100%;float: left;border: 1px solid rgba(255, 255, 255, 0.8);">
		<button type="button" class="btn btn-info" style="float: left;" onclick="javascript:history.back(-1);">
			<span class="glyphicon glyphicon-arrow-left"></span> 返回上一级
		</button>
	</div>
	<div style="width: 100%;height: 900px;">
		<div class="tt">
			<div class="hh" id="b0"></div>
		</div>
		<div class="tt">
			<div class="hh" id="b1"></div>
		</div>
		<div class="tt">
			<div class="hh" id="b2"></div>
		</div>
		<div class="tt">
			<div class="hh" id="b3"></div>
		</div>
		<div class="tt">
			<div class="hh" id="b4"></div>
		</div>
		<div class="tt">
			<div class="hh" id="b5"></div>
		</div>
		<div class="tt">
			<div class="hh" id="b6"></div>
		</div>
		<div class="tt">
			<div class="hh" id="b7"></div>
		</div>
		<div class="tt">
			<div class="hh" id="b8"></div>
		</div>
		<div style="width: 100%;text-align: center;margin-top: 20px;">
			<button type="button" class="btn btn-primary btn-lg" onclick="getLucky()">开始抽奖</button>
		</div>
	</div>
</body>
<script type="text/javascript">
	//获取0-8随机数
	function getRandom() {
		return Math.floor(Math.random() * 9);
	}
	function addClass(id) {
		$("#" + id).addClass("sele");
	}
	function removeClass(id) {
		$("#" + id).removeClass("sele");
	}
	var before;
	var times = 0;
	function getLucky() {
		times = 0;
		var intv = setInterval(function() {
			removeClass("b" + before);
			var ra = getRandom();
			addClass("b" + ra);
			before = ra;
			times++;
			if (times >= 30) {
				clearInterval(intv);
				var intv1 = setInterval(function() {
					removeClass("b" + before);
					ra = getRandom();
					addClass("b" + ra);
					before = ra;
					times++;
					if (times >= 36) {
						clearInterval(intv1);
						var intv2 = setInterval(function() {
							removeClass("b" + before);
							ra = getRandom();
							addClass("b" + ra);
							before = ra;
							times++;
							if (times >= 40) {
								clearInterval(intv2);
								setTimeout(function() {
									alert(before);
								}, 800);
							}
						}, 1200);
					}
				}, 500);
			}
		}, 100);
	}
</script>
</html>
