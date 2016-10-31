<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<link rel="stylesheet" type="text/css" href="css/regist.css" />
</head>
<body>	
	<div id="mainDiv">
		<div id="titleDiv">
			<span id="titleSpan">SimpleSteam &nbsp;&nbsp;&nbsp;&nbsp;
			<a style="color:#555;" href="../login/login.jsp">登录</a>&nbsp;&nbsp;
			<a style="color:#555;" href="../homePage/homePage.jsp">主页</a></span>
		</div>
		<div id="bodyDiv">
			<form action="/SimpleSteam/RegistServlet?method=regist" method="post" id="registForm">
				<table id="bodyTable">
					<!-- 用户名 -->
					<tr class="contents">
						<td class="inputTd"><input type="text" class="inputs" id="userNameInput" name="userName" placeholder="用户名" /></td>
						<td class="informationTd"><span id="userNameSpan"></span></td>
					</tr>
					<!-- 用户密码 -->
					<tr class="contents">
						<td class="inputTd"><input type="password" class="inputs" id="passwordInput" name="password" placeholder="密码" /></td>
						<td class="informationTd"><span id="passwordSpan"></span></td>
					</tr>
					<!-- 重复密码 -->
					<tr class="contents">
						<td class="inputTd"><input type="password" class="inputs" id="repeatPasswordInput" name="repeatPassword" placeholder="重复密码" /></td>
						<td class="informationTd"><span id="repeatPasswordSpan"></span></td>
					</tr>
					<!-- 邮箱 -->
					<tr class="contents">
						<td class="inputTd"><input type="text" class="inputs" id="emailInput" name="email" placeholder="邮箱" /></td>
						<td class="informationTd"><span id="emailSpan"></span></td>
					</tr>
					<!-- 验证码框 -->
					<tr class="contents">
						<td class="inputTd">
							<div style="width:80px;height:43px;position:relative;top:0px;left:0px;">
								<input type="text" class="inputs" id="verifyCodeInput" name="verifyCode" placeholder="验证码" />
							</div>
							<div style="width:80px;height:43px;position:relative;top:-43px;left:400px;">
								<a id="repeatVerifyCode"><img src="/SimpleSteam/SimpleVerifyCode" id="verifyCodeImage"></a>
							</div>
						</td>
						<td class="informationTd"><span id="verifyCodeSpan"></span></td>
					</tr>
					<!-- 提交按钮 -->
					<tr class="contents">
						<td><input type="submit" id="submitBtn" value="注册" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<!-- 专门用于控制内容位置的脚本 -->
	<script type="text/javascript">
		// 获取页面主体方便改变大小
		var mainDivElement = document.getElementById("mainDiv");
		// 最初进入页面的时候改变大小
		var width = (document.documentElement.clientWidth - 680) / 2;
		var height = (document.documentElement.clientHeight - 400) / 2;
		mainDivElement.style.marginLeft = width + "px";
		mainDivElement.style.marginTop = height + "px";
		// 当浏览器窗口大小变动的时候可以随时调整位置
		window.onresize = function() {
			var width = (document.documentElement.clientWidth - 680) / 2;
			var height = (document.documentElement.clientHeight - 400) / 2;
			mainDivElement.style.marginLeft = width + "px";
			mainDivElement.style.marginTop = height + "px";
		};
		
		
	</script>
	<script type="text/javascript" src="../../jQuery/jquery-3.1.1.js"></script>
	<script type="text/javascript" src="js/regist.js"></script>
</body>
</html>
