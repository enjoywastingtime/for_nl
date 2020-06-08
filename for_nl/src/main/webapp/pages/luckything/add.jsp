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
<title>添加奖品</title>
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
</style>
<body style="background-color:#E8F2FE; ">
	<div style="width:100%;float: left;border: 1px solid rgba(255, 255, 255, 0.8);">
		<button type="button" class="btn btn-info" style="float: left;" onclick="javascript:history.back(-1);">
			<span class="glyphicon glyphicon-arrow-left"></span> 返回上一级
		</button>
	</div>
	<div style="width: 100%;height: 900px;">
		<div>
			<div class="form-group">
				<label for="thingName">奖品名称：<span style="color: red;">*</span></label> <input class="form-control" id="thingName" placeholder="请输入名称">
			</div>
			<div class="form-group">
				<label for="thingRemark">奖品描述</label> <input class="form-control" id="thingRemark" placeholder="请输入额外描述">
			</div>
			<div style="width: 100%;margin-top: 40px;text-align: center;">
				<button type="button" class="btn btn-primary" onclick="save()">添加</button>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function save() {
		var name = $("#thingName").val();
		var remark = $("#thingRemark").val();
		if (name == "undefined" || name.trim() == '') {
			alert("名称为必填项!");
			return;
		}
		$.ajax({
			type : "POST",
			data : {
				name : name,
				remark : remark
			},
			url : "luckything/add",
			success : function(r) {
				if (r == "done") {
					alert("保存成功");
					window.location.href = "pages/luckything/manage.jsp";
				} else {
					alert("保存失败");
				}
			}
		});
	}
</script>
</html>
