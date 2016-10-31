<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 激活信息 </title>
<style type="text/css">
	body {
		font-size: 10pt;
		color: #404040;
		font-family: SimSun;
	}
	.titleDiv {
		text-align:left;
		width: 900px;
		height: 25px;
		line-height: 25px;
		background-color: #efeae5;
		border: 5px solid #efeae5;
	}
	.titleSpan {
		margin-top: 10px;
		margin-left:10px;
		height:25px;
		font-weight: 900;
		font-size:20px;
		line-height:25px;
		font-family:微软雅黑;
	}
	.contentDiv {
		width: 900px;
		height: 230px;
		border: 5px solid #efeae5;
		margin-right:20px; 
		margin-bottom:20px;
	}
	a {text-decoration: none;}
	a:visited {color: #018BD3;}
	a:hover {color:#FF6600; text-decoration: underline;}
}
</style>
</head>
<body>
	<!-- 设置标题内容 -->
	<c:choose>
		<c:when test="${code eq 'success' }"><!-- 设置成功信息 -->
			<c:set var="informationImage" value="UI/regist/images/success.jpg" />
			<c:set var="informationText" value="激活成功!请进行登录!" />
		</c:when>
		<c:when test="${code eq 'fail' }"><!-- 设置失败信息 -->
			<c:set var="informationImage" value="UI/regist/images/fail.png" />
			<c:set var="informationText" value="激活失败!" />
		</c:when>
	</c:choose>
	
	<div id="bodyDiv">
		<div class="titleDiv">
			<span class="titleSpan">${informationText }</span>
		</div>
		
		<div class="contentDiv">
			<div style="margin:20px;">
				<img style="float:left; margin-right:30px;" src="${informationImage }" width="150" />
				<span style="font-size:30px; color:#c30; font-weight:900;">${msg }</span>
				
				<br />
				<br />
				<br />
				<br />
				
				<span style="margin-left:50px; font-size:25px; font-family:微软雅黑;"><a target="_top" href="/SimpleSteam/index.jsp">返回主页</a></span>
			</div>
		</div>
	</div>
	
	<!-- 专门用于控制内容位置的脚本 -->
	<script type="text/javascript">
		// 获取页面主体方便改变大小
		var bodyDivElement = document.getElementById("bodyDiv");
		// 最初进入页面的时候改变大小
		var width = (document.documentElement.clientWidth - 900) / 2;
		var height = (document.documentElement.clientHeight - 400) / 2;
		bodyDivElement.style.marginLeft = width + "px";
		bodyDivElement.style.marginTop = height + "px";
		// 当浏览器窗口大小变动的时候可以随时调整位置
		window.onresize = function() {
			var width = (document.documentElement.clientWidth - 900) / 2;
			var height = (document.documentElement.clientHeight - 400) / 2;
			bodyDivElement.style.marginLeft = width + "px";
			bodyDivElement.style.marginTop = height + "px";
		};
	</script>
</body>
</html>