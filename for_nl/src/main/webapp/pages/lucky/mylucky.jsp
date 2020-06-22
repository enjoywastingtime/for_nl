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
<title>我的奖品</title>
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
.used {
	background-color: #E0E0E0;
}
</style>
<body style="background-color:#E8F2FE; ">
	<div style="width:100%;float: left;border: 1px solid rgba(255, 255, 255, 0.8);">
		<button type="button" class="btn btn-info" style="float: left;" onclick="javascript:history.back(-1);">
			<span class="glyphicon glyphicon-arrow-left"></span> 返回上一级
		</button>

		<button type="button" class="btn btn-default" style="float: left;" onclick="getList()">
			<span class="glyphicon glyphicon-refresh"></span> 刷新数据
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
	var list = [];
	function getList() {
		$.ajax({
			type : "GET",
			url : "luckything/getMyList",
			success : function(r) {
				var htmls = [];
				list = r;
				htmls.push("<tr class='danger'><th>" + "抽中内容" + "</th><th>" + "备注(点击修改)" + "</th><th>" + "抽奖时间" + "</th><th>是否已用</th></tr>");
				for (i = 0; i < r.length; i++) {
					var time1 = new Date(r[i].createDate).format("yyyy-MM-dd");
					htmls.push("<tr  id='" + r[i].id + "' name='" + r[i].name + "' " + (r[i].used == 1 ? " class='used' " : "")
							+ "><td style='font-weight:bold;'>" + r[i].name + "</td><td onclick='editRemark(\"" + r[i].id + "\")'>"
							+ (r[i].remark == null ? '无备注' : r[i].remark) + "</td><td>" + time1 + "</td><td>" + (r[i].used == 1 ? "已使用" : "未使用")
							+ "&nbsp;&nbsp;<button " + (r[i].used == 1 ? 'disabled' : '') + " onclick='change(\"" + r[i].id
							+ "\")' class='btn btn-info btn-xs'><span class='glyphicon glyphicon-sort'></span></button></td></tr>");
				}
				var html = htmls.join("");
				$("#things").html(html);
				//给元素绑定数据
				for (i = 0; i < r.length; i++) {
					$("#" + r[i].id).data(r[i]);
				}
			}
		});
	}
	getList();

	function indexOf(id) {
		var j;
		for (i = 0; i < list.length; i++) {
			if (list[i].id == id) {
				j = i;
				break;
			}
		}
		return j;
	}

	function removeFromList(id) {
		list.splice(indexOf(id), 1);
	}

	function change(id) {
		var msg = confirm("设为已使用后不可更改，请确定此中所有奖品已领取完毕！");
		if (!msg) {
			return;
		}
		var rowData = $("#" + id).data();
		$("#" + id).attr("disabled", true);
		$.ajax({
			type : "POST",
			url : "luckything/setUsed",
			data : {
				id : id
			},
			success : function(r) {
				var index = indexOf(id);
				list[index].used = 1;
				var time1 = new Date(list[index].createDate).format("yyyy-MM-dd");
				rowData.used = 1;
				$("#" + id).data(rowData);
				$("#" + id).replaceWith(
						"<tr  id='" + list[index].id + "' name='" + list[index].name + "' " + (list[index].used == 1 ? " class='used' " : "")
								+ "><td style='font-weight:bold;'>" + list[index].name + "</td><td onclick='editRemark(\"" + r[i].id + "\")'>"
								+ (list[index].remark == null ? '无备注' : list[index].remark) + "</td><td>" + time1 + "</td><td>"
								+ (list[index].used == 1 ? "已使用" : "未使用") + "&nbsp;&nbsp;<button disabled onclick='change(\"" + list[index].id
								+ "\")' class='btn btn-info btn-xs'><span class='glyphicon glyphicon-sort'></span></button></td></tr>");
			}
		});
	}

	function editRemark(id) {
		var rowData = $("#" + id).data();
		if (rowData.used == 1) {
			alert("已经使用过的记录不能修改备注！");
			return;
		}
		var msg = prompt("修改备注为：");
		if (msg == null) {
			return;
		}

		$("#" + id).attr("disabled", true);
		$.ajax({
			type : "POST",
			url : "luckything/setRemark",
			data : {
				id : id,
				remark : msg
			},
			success : function(r) {
				var index = indexOf(id);
				list[index].remark = msg;
				var time1 = new Date(list[index].createDate).format("yyyy-MM-dd");
				rowData.remark = msg;
				$("#" + id).data(rowData);
				$("#" + id).replaceWith(
						"<tr  id='" + list[index].id + "' name='" + list[index].name + "' " + (list[index].used == 1 ? " class='used' " : "")
								+ "><td style='font-weight:bold;'>" + list[index].name + "</td><td onclick='editRemark(\"" + r[i].id + "\")'>"
								+ (list[index].remark == null ? '无备注' : list[index].remark) + "</td><td>" + time1 + "</td><td>"
								+ (list[index].used == 1 ? "已使用" : "未使用") + "&nbsp;&nbsp;<button " + (list[index].used == 1 ? 'disabled' : '')
								+ " onclick='change(\"" + list[index].id
								+ "\")' class='btn btn-info btn-xs'><span class='glyphicon glyphicon-sort'></span></button></td></tr>");
			}
		});
	}
</script>
</html>
