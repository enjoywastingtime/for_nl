<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>登录页面</title>

<script type="text/javascript" src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>

<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<style>
.font-color-jianbian {
	background: linear-gradient(to bottom, #FAFAFA, #FFD392, #E61717);
	-webkit-background-clip: text;
	color: transparent;
	font-weight: bold;
}
</style>
<body style="background: url('sources/img/loginback.jpg');background-size:cover;;">
	<div class="container">
		<div style="text-align: center;">
			<h2 class="font-color-jianbian">CBA for NL</h2>
			<h4 style="color: #CAD1D5">系统登录</h4>
		</div>
		<div class="form-horizontal">
			<div class="form-group">
				<div class="col-xs-1"></div>
				<label for="username" class="col-xs-3 control-label" style="color:white;margin-top: 5px;"> 用 户：</label>
				<div class="col-xs-7">
					<input type="email" class="form-control" id="username" placeholder="账号">
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-1"></div>
				<label for="password" class="col-xs-3 control-label" style="color:white;margin-top: 5px;"> 密 码：</label>
				<div class="col-xs-7">
					<input type="password" class="form-control" id="password" placeholder="密码">
				</div>
			</div>
			<div class="form-group">
				<div style="text-align: center;">
					<div class="btn-group" role="group" aria-label="...">
						<button type="button" class="btn btn-info" onclick="register()">注册</button>
						<button type="button" class="btn btn-primary" onclick="login()">登录</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		function login() {
			$.ajax({
				//请求方式
				type : "POST",
				data : {
					username : $('#username').val(),
					password : $('#password').val()
				},
				//请求地址
				url : "login/doLogin",
				//请求成功
				success : function(result) {
					if (result == 'success') {
						window.location.href = "pages/index.jsp";
					} else {
						alert("用户名或密码错误！");
					}
				},
				//请求失败，包含具体的错误信息
				error : function(e) {
					alert(e.responseText);
				}
			});
		}

		function register() {
			var name = prompt("请输入邀请码：");
			if (name != null && name != '' && name != undefined) {
				$.ajax({
					//请求方式
					type : "POST",
					data : {
						username : $('#username').val(),
						password : $('#password').val(),
						invitecode:name
					},
					//请求地址
					url : "login/register",
					//请求成功
					success : function(result) {
						if (result == 'success') {
							alert("注册成功，请点击登录！");
						} else {
							alert(result);
						}
					},
					//请求失败，包含具体的错误信息
					error : function(e) {
						alert(e.responseText);
					}
				});
			} else {
				alert("请重新输入！");
			}
		}
	</script>
</body>

</html>