package top.qlbxxxz.tools;

import java.awt.image.BufferedImage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

import cn.itcast.vcode.utils.VerifyCode;

/**
 * @作者		Hummingbird
 * @功能		产生随机的一次性验证码
 * @使用方法	在a标签中调用此类即可, 要获取验证码的值则使用相应jsp请求中的
 * 			getSession().getAttriubute("realVerifyCode")方法获取String值
 *
 */
public class SimpleVerifyCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException ,java.io.IOException {
		VerifyCode vc = new VerifyCode();										// 创建验证码对象
		BufferedImage image = vc.getImage();									// 获取验证码
		
		VerifyCode.output(image, response.getOutputStream());					// 利用VerifyCode将验证码发送到客户端
		request.getSession().setAttribute("realVerifyCode", vc.getText());		// 获取验证码保存为String对象到Session中, 方便校验
	};
}
