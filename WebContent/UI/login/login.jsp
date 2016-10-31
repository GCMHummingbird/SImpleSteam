<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登录</title>
        <style>
        	#biaodan{border: none; medium; background: black; margin:5% auto; width: 600px; height: 600px;
        	border-radius: 20px; opacity: 0.8;}
        	.Input{width: 400px; height: 60px; border-radius: 10px; font-size: 26px; padding-left: 15px; font: "bookshelf symbol 7"; 
        	border: none; outline: none; color: lightslategray;}
        	#top{width: 600px; height: 50px; background: orangered; font: "微软雅黑"; color: white; font-size: 36px; text-align: left; padding-left: 15px;
        	margin:0; opacity: 0.8; line-height: 50px;}
        	.tishi{position: relative; color: yellow; font-size: 10px; width: 100px; height: 25px; margin-top: 7px;}
        	body{background-image: url(img/bg.jpg); background-repeat: no-repeat; background-attachment:fixed}
			#div1{ position:fixed; top:0; left:0; bottom:0; right:0; z-index:-1;} 
			#div1 > img { height:100%; width:100%; border:0; } 
        </style>
    </head>
    <body>
    <div id="div1">
   				<img src="images/background.jpg" class="blur" />
    </div>
    	<form action="" method="post">
    	<div id="top">登录SimpleSteam</div>
    		<div id="biaodan" align="center">
    			
    			<input id="end" type="text" name="user" class="Input" placeholder="用户名" style="margin-top: 12%;"/>
    			<div id="001" class="tishi"></div>
    			<input id="Password" type="password" name="password" class="Input" placeholder="密码" style="margin-top: 7px;"/>
    			<div id="002" class="tishi"></div>
    			<div style="margin-top: 7px;">
    			<table>
    				<tr>
	    				<td><input id="yzm" type="text" name="yanzheng" placeholder="验证码" class="Input" style="width: 150px;"/></td>
	    				<td><img src="" style="width: 200px; height: 60px; margin-left: 50px;border: 1px initial;"/></td>
    				</tr>
    			</table>
    			<div id="003" class="tishi"></div>
    			</div>
    			<input id="dl" type="submit" value="登    录" class="Input" style="font-size: 36px; margin-top: 60px; background: orangered; color: white;"/>
    			<div style="margin-top: 15px; width: 400px;"><a href="" style="text-align: left;">忘记密码?</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="../regist/regist.jsp">注册</a></div>
    		</div>
    	</form>
    	<script>
    		var end  =document.getElementById("end");
    		var Password = document.getElementById("Password");
    		var yzm = document.getElementById("yzm");
    		var dl = document.getElementById("dl");
    		
    		var div1 = document.getElementById("001");
    		var div2 = document.getElementById("002");
    		var div3 = document.getElementById("003");
    	
    		dl.onclick = function (){
    			if(end.value.length<6){
    			
    				div1.innerHTML = "用户名不能为空或少于6位";
    					
    					return false;
    				
    			}
    			if(Password.value.equals(<% %>)){
    					div2.innerHTML = "密码错误";
    					
    					return false;
    			}
    			if(yzm.value.equals(<%     %>)){
    					div3.innerHTML = "验证码错误";
    					
    					return false;
    			}	
    		};
    	</script>
 	</body>
</html>