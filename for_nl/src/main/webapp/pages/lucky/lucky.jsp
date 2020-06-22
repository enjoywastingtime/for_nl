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
	text-align: center;
}

.sele {
	background-color: rgba(222, 109, 100, 0.5);
}

.middle {
	position: relative;
	top: 50%;
	transform: translateY(-50%);
	font-size: 18px;
	font-family: 华文行楷;
	color: mediumseagreen;
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
		<div style="width: 100%;text-align: center;">
			<button type="button" class="btn btn-primary btn-lg" onclick="getLucky()" style="margin-top: 20px;">开始抽奖</button>
		</div>
		<div style="width: 100%;text-align: center;margin-top: 16px;">
			<span>我的奖品：</span> <span id="myLucky"></span>
		</div>
		<div style="width: 100%;text-align: center;margin-top: 16px;">
			<button type="button" class="btn btn-danger btn-lg" onclick="confirmLucky()">奖品确认</button>
		</div>
	</div>
</body>
<script type="text/javascript">
	var glb = [];
	//获取抽奖池列表
	$.ajax({
		method : "GET",
		url : "luckything/getPoolThings",
		success : function(r) {
			//r的长度肯定是小于等于9的
			var t = r.length;
			for (i = t; i < 9; i++) {
				r.push({
					name : "下次努力"
				});
			}
			r.sort(function() {
				return Math.random() > 0.5 ? -1 : 1;
			});
			glb = r;
			for (i = 0; i < 9; i++) {
				$("#b" + i)[0].innerHTML = "<div class='middle'>" + r[i].name + "</div>";
			}
		}
	});
	//获取0-8随机数
	function getRandom() {
		var rdm = Math.floor(Math.random() * 9);
		if (rdm != before) {
			return rdm;//本次获取的随机数不能和上次的相同，否则会造成方块没移动位置的错觉
		} else {
			return getRandom();
		}
	}
	function addClass(id) {
		$("#" + id).addClass("sele");
	}
	function removeClass(id) {
		$("#" + id).removeClass("sele");
	}
	var before;
	var times = 0;
	var myLucky = "";
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
					if (times >= 33) {
						clearInterval(intv1);
						var intv2 = setInterval(function() {
							removeClass("b" + before);
							ra = getRandom();
							addClass("b" + ra);
							before = ra;
							times++;
							if (times >= 34) {
								clearInterval(intv2);
								var intv3 = setInterval(function() {
									removeClass("b" + before);
									ra = getRandom();
									addClass("b" + ra);
									before = ra;
									times++;
									if (times >= 35) {
										clearInterval(intv3);
										setTimeout(function() {
											goods = glb[before].name;
											if (goods == "下次努力") {
												aleret("很遗憾未抽中奖品，请下次努力！");
											} else {
												alert("恭喜抽中：" + goods);
												myLucky += glb[before].name
														+ (glb[before].remark != "" && glb[before].remark != undefined ? ("(" + glb[before].remark + ")") : "")
														+ "；";
												$("#myLucky").html(myLucky);
											}
										}, 800);
									}
								}, 1200);
							}
						}, 900);
					}
				}, 400);
			}
		}, 100);
	}
	function confirmLucky() {
		if (myLucky.length <= 0) {
			alert("还未抽中奖品！");
			return;
		}
		var code = prompt("请输入领奖码：");
		if (code != null && code != '' && code != undefined) {
			$.ajax({
				//请求方式
				type : "POST",
				data : {
					name : myLucky,
					code : code
				},
				//请求地址
				url : "luckything/confirmLucky",
				//请求成功
				success : function(result) {
					if (result == 'done') {
						alert("奖品领取成功，请到我的奖品中查看！");
						window.location.href = "pages/index.jsp";
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
</html>
