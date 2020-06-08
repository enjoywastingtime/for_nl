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
<title>奖品管理</title>
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

		<button type="button" class="btn btn-default" style="float: left;" onclick="getList()">
			<span class="glyphicon glyphicon-refresh"></span> 刷新数据
		</button>

		<button type="button" class="btn btn-success" style="float: left;" onclick="window.location.href='pages/luckything/add.jsp'">
			<span class="glyphicon glyphicon-plus"></span> 添加奖品
		</button>
	</div>
	<div style="width: 100%;height: 900px;">

		<table class="table" id="things">
		</table>
	</div>
</body>
<script type="text/javascript">
	Date.prototype.format = function(fmt) {
		var o = {
			"M+" : this.getMonth() + 1, //月份
			"d+" : this.getDate(), //日
			"h+" : this.getHours(), //小时
			"m+" : this.getMinutes(), //分
			"s+" : this.getSeconds(), //秒
			"q+" : Math.floor((this.getMonth() + 3) / 3), //季度
			"S" : this.getMilliseconds()
		//毫秒
		};
		if (/(y+)/.test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		}
		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(fmt)) {
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
			}
		}
		return fmt;
	}
	function getList() {
		$
				.ajax({
					type : "GET",
					url : "luckything/getAvailable",
					success : function(r) {
						var htmls = [];
						htmls.push("<tr class='danger'><th>" + "奖品名称" + "</th><th>" + "备注" + "</th><th>" + "创建时间" + "</th></tr>");
						for (i = 0; i < r.length; i++) {
							var time1 = new Date(r[i].createDate).format("yyyy-MM-dd hh:mm:ss");
							htmls
									.push("<tr id='"
											+ r[i].id
											+ "'"
											+ (i % 2 != 0 ? " class='success' " : "")
											+ "><td style='font-weight:bold;'><button type='button' class='btn btn-danger btn-xs' style='float: left;' onclick='dele(this)'><span class='glyphicon glyphicon-trash'></span> </button>&nbsp;&nbsp;"
											+ r[i].name + "</td><td>" + (r[i].remark == null ? '无备注' : r[i].remark) + "</td><td>" + time1 + "</td></tr>");
						}
						var html = htmls.join("");
						$("#things").html(html);
					}
				});
	}
	getList();

	function dele(b) {
		alert("删除" + b);
	}
</script>
</html>
