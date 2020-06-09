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
.inpool {
	background-color: darkseagreen;
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
	var list = [];
	function getList() {
		$
				.ajax({
					type : "GET",
					url : "luckything/getAvailable",
					success : function(r) {
						var htmls = [];
						list = r;
						htmls.push("<tr class='danger'><th>" + "奖品名称" + "</th><th>" + "备注" + "</th><th>" + "创建时间" + "</th><th>抽奖池</th></tr>");
						for (i = 0; i < r.length; i++) {
							var time1 = new Date(r[i].createDate).format("yyyy-MM-dd");
							htmls
									.push("<tr  id='"
											+ r[i].id
											+ "' name='"
											+ r[i].name
											+ "' "
											+ (r[i].inPool == 1 ? " class='inpool' " : "")
											+ "><td style='font-weight:bold;'><button type='button' class='btn btn-danger btn-xs' style='float: left;' onclick='dele(this)'><span class='glyphicon glyphicon-trash'></span> </button>&nbsp;&nbsp;"
											+ r[i].name + "</td><td>" + (r[i].remark == null ? '无备注' : r[i].remark) + "</td><td>" + time1 + "</td><td>"
											+ (r[i].inPool == 1 ? "是" : "否") + "&nbsp;&nbsp;<button title='移入或移出抽奖池' onclick='change(\"" + r[i].id
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

	function dele(b) {
		var thingName = $(b).parents('tr').attr('name');
		var thingId = $(b).parents('tr').attr('id');
		var msg = confirm("是否确定删除奖品：" + thingName);
		if (msg) {
			$.ajax({
				type : "POST",
				url : "luckything/dele",
				data : {
					id : thingId
				},
				success : function(r) {
					if (r == 'done') {
						alert("删除成功！");
						$("#" + thingId).remove();
						removeFromList(thingId);
					} else {
						if (r == 'used') {
							alert("数据已被使用！");
						} else {
							alert("删除失败");
						}

					}
				}
			});
		} else {
			//do nothing
		}
	}

	function change(id) {
		var rowData = $("#" + id).data();
		$("#" + id).attr("disabled", true);
		$
				.ajax({
					type : "POST",
					url : "luckything/movePool",
					data : {
						id : id,
						inOrOut : rowData.inPool == 1 ? "out" : "in"
					},
					success : function(r) {
						var index = indexOf(id);
						list[index].inPool = rowData.inPool == 1 ? 0 : 1;
						var time1 = new Date(list[index].createDate).format("yyyy-MM-dd");
						rowData.inPool = rowData.inPool == 1 ? 0 : 1;
						$("#" + id).data(rowData);
						$("#" + id)
								.replaceWith(
										"<tr  id='"
												+ list[index].id
												+ "' name='"
												+ list[index].name
												+ "' "
												+ (list[index].inPool == 1 ? " class='inpool' " : "")
												+ "><td style='font-weight:bold;'><button type='button' class='btn btn-danger btn-xs' style='float: left;' onclick='dele(this)'><span class='glyphicon glyphicon-trash'></span> </button>&nbsp;&nbsp;"
												+ list[index].name + "</td><td>" + (list[index].remark == null ? '无备注' : list[index].remark) + "</td><td>"
												+ time1 + "</td><td>" + (list[index].inPool == 1 ? "是" : "否")
												+ "&nbsp;&nbsp;<button title='移入或移出抽奖池' onclick='change(\"" + list[index].id
												+ "\")' class='btn btn-info btn-xs'><span class='glyphicon glyphicon-sort'></span></button></td></tr>");
						var t = 0;
						for (i = 0; i < list.length; i++) {
							if (list[i].inPool == 1) {
								t++;
							}
							if (t > 9) {
								alert("抽奖池最多能用9个奖品，多出的将不会使用！")
							}
						}
					}
				});
	}
</script>
</html>
