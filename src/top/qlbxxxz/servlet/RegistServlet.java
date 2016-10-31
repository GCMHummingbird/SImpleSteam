package top.qlbxxxz.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.qlbxxxz.object.User;
import top.qlbxxxz.service.RegistService;
import cn.itcast.servlet.BaseServlet;

public class RegistServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	// 注册服务
	private RegistService registService = new RegistService();

	public String regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 定义网页提交过来的userName为name
		String userName = request.getParameter("userName");
		// 定义网页提交过来的password为password
		String password = request.getParameter("password");
		// 定义网页提交过来的过来的email为email
		String email = request.getParameter("email");
		// 定义网页提交过来的验证码
		String verifyCode = request.getParameter("verifyCode");
		// 定义真实的验证码
		String realVerifyCode = (String)request.getSession().getAttribute("realVerifyCode");

		// 先进行Servlet层的正则校验, 再然后校验用户名, 邮箱是否存在
		if (submitCheck(userName, password, email, verifyCode, realVerifyCode)
			&& registService.isExistenceName(userName)
			&& registService.isExistenceEmail(email)) {

			User newUser = new User();// 需要添加的用户对象
			// 补全用户数据
			newUser.setLoginName(userName);
			newUser.setLoginPWD(password);
			newUser.setEmail(email);
			// 添加用户
			registService.addUser(newUser);
			// 添加提示信息
			request.setAttribute("msg", "注册成功!请前往" + email + "激活!");
			request.setAttribute("code", "success");
		} else {
			// 添加提示信息
			request.setAttribute("msg", "非法访问!");
			request.setAttribute("code", "fail");
		}

		return "f:/UI/regist/RegistMessage.jsp";
	}

	// Servlet层校验
	public boolean submitCheck(String name, String password, String email,
			String verifyCode, String realVerifyCode){
		// 防止直接提交空数据
		if (name == null) name = "";
		if (password == null) password = "";
		if (email == null) email = "";
		if (verifyCode == null) verifyCode = "";
		if (realVerifyCode == null) realVerifyCode = "";

		// 定义校验是通过的
		boolean isSuccess = true;
		// 判断用户名格式是否正确
		isSuccess = name.matches("[\\S]{3,14}") && isSuccess;
		// 判断密码格式是否正确
		isSuccess = password.matches("[\\S]{6,18}") && isSuccess;
		// 判断邮箱格式是否正确
		isSuccess = email.matches("[\\w\\.]{3,19}@[0-9a-zA-Z]{2,7}(\\.[a-zA-Z]{2,7})+") && isSuccess;
		// 判断验证码格式是否正确
		isSuccess = verifyCode.matches("[0-9a-zA-Z]{4}")
				&& verifyCode.equalsIgnoreCase(realVerifyCode) && isSuccess;
		// 返回校验是否通过
		return isSuccess;
	}

	// AJAX用校验数据库中是否存在Name
	public void AJAXExistenceName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String inputName = request.getParameter("userName");				// 获取表单用户名
		PrintWriter out = response.getWriter();							// 获取响应流

		out.print(registService.isExistenceName(inputName));				// 响应用户名是否存在
	}

	// AJAX用校验数据库中是否存在Email
	public void AJAXExistenceEmail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");					// 获取表单邮箱
		PrintWriter out = response.getWriter();							// 获取响应流

		out.print(registService.isExistenceEmail(email));				// 响应邮箱是否存在
	}

	// AJAX用校验验证码
	public void AJAXCheckVerifyCode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String verifyCode = request.getParameter("verifyCode");			// 获取表单验证码
		String realVerifyCode = (String)request.getSession()
				.getAttribute("realVerifyCode");							// 获取响应流
		PrintWriter out = response.getWriter();							// 响应验证码正误

		out.print(verifyCode.equalsIgnoreCase(realVerifyCode));
	}
}
