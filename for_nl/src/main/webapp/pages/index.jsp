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
<title>欢迎</title>
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

.menu-button {
	width: 100%;
	height: 100%;
	border-radius: 0px;
}

.btn-info {
	background-color: #ECC7CF;
	border-color: rgb(230, 183, 191);
}

.btn-primary {
	background-color: #E3A4AD;
	border-color: rgb(230, 183, 191);
}
</style>
<body style="background-color:#E8F2FE; ">
	<div style="text-align:center; border: 1px solid rgba(255, 255, 255, 0.8); width: 100%;height:50px;background: linear-gradient(to right, #148396 , #148396,#148396);">
		<span style="font-weight: bold;font-size: 30px;color: white;">欢迎</span><!--  <div id="username"></div><a href="login/logout" style="color:white;float:right;">退出</a> -->
	</div>
	<div style="width: 100%;height: 900px;">
		<!-- 菜单1 -->
		<div class="tt">
			<div class="hh">
				<button type="button" class="btn btn-primary btn-lg menu-button" onclick="window.location.href='pages/lucky/lucky.jsp'">
					<span class="glyphicon glyphicon-cutlery" aria-hidden="true"></span> <br />幸运抽奖
				</button>
			</div>
		</div>
		<!-- 菜单2 -->
		<div class="tt">
			<div class="hh">
				<button type="button" class="btn btn-primary btn-lg menu-button" onclick="window.location.href='pages/luckything/manage.jsp'">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> <br />奖品管理
				</button>
			</div>
		</div>
		<!-- 菜单3 -->
		<div class="tt">
			<div class="hh">
				<button type="button" class="btn btn-info btn-lg menu-button">
					<span class="glyphicon glyphicon-lock" aria-hidden="true"></span> <br />暂未开放
				</button>
			</div>
		</div>
		<!-- 菜单4 -->
		<div class="tt">
			<div class="hh">
				<button type="button" class="btn btn-info btn-lg menu-button">
					<span class="glyphicon glyphicon-lock" aria-hidden="true"></span> <br />暂未开放
				</button>
			</div>
		</div>
		<!-- 菜单5 -->
		<div class="tt">
			<div class="hh">
				<button type="button" class="btn btn-info btn-lg menu-button">
					<span class="glyphicon glyphicon-lock" aria-hidden="true"></span> <br />暂未开放
				</button>
			</div>
		</div>
		<!-- 菜单6 -->
		<div class="tt">
			<div class="hh">
				<button type="button" class="btn btn-info btn-lg menu-button">
					<span class="glyphicon glyphicon-lock" aria-hidden="true"></span> <br />暂未开放
				</button>
			</div>
		</div>
		<!-- 菜单7 -->
		<div class="tt">
			<div class="hh">
				<button type="button" class="btn btn-info btn-lg menu-button">
					<span class="glyphicon glyphicon-lock" aria-hidden="true"></span> <br />暂未开放
				</button>
			</div>
		</div>
		<!-- 菜单8 -->
		<div class="tt">
			<div class="hh">
				<button type="button" class="btn btn-info btn-lg menu-button">
					<span class="glyphicon glyphicon-lock" aria-hidden="true"></span> <br />暂未开放
				</button>
			</div>
		</div>
		<!-- 菜单9 -->
		<div class="tt">
			<div class="hh">
				<button type="button" class="btn btn-info btn-lg menu-button">
					<span class="glyphicon glyphicon-lock" aria-hidden="true"></span> <br />暂未开放
				</button>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$.ajax({
		method : "GET",
		url : "login/getUser",
		success : function(r) {
			document.getElementById("username").innerHTML=r.name;
		}
	});
</script>
</html>
