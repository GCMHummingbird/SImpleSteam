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
	// ע�����
	private RegistService registService = new RegistService();

	public String regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ������ҳ�ύ������userNameΪname
		String userName = request.getParameter("userName");
		// ������ҳ�ύ������passwordΪpassword
		String password = request.getParameter("password");
		// ������ҳ�ύ�����Ĺ�����emailΪemail
		String email = request.getParameter("email");
		// ������ҳ�ύ��������֤��
		String verifyCode = request.getParameter("verifyCode");
		// ������ʵ����֤��
		String realVerifyCode = (String)request.getSession().getAttribute("realVerifyCode");

		// �Ƚ���Servlet�������У��, ��Ȼ��У���û���, �����Ƿ����
		if (submitCheck(userName, password, email, verifyCode, realVerifyCode)
			&& registService.isExistenceName(userName)
			&& registService.isExistenceEmail(email)) {

			User newUser = new User();// ��Ҫ��ӵ��û�����
			// ��ȫ�û�����
			newUser.setLoginName(userName);
			newUser.setLoginPWD(password);
			newUser.setEmail(email);
			// ����û�
			registService.addUser(newUser);
			// �����ʾ��Ϣ
			request.setAttribute("msg", "ע��ɹ�!��ǰ��" + email + "����!");
			request.setAttribute("code", "success");
		} else {
			// �����ʾ��Ϣ
			request.setAttribute("msg", "�Ƿ�����!");
			request.setAttribute("code", "fail");
		}

		return "f:/UI/regist/RegistMessage.jsp";
	}

	// Servlet��У��
	public boolean submitCheck(String name, String password, String email,
			String verifyCode, String realVerifyCode){
		// ��ֱֹ���ύ������
		if (name == null) name = "";
		if (password == null) password = "";
		if (email == null) email = "";
		if (verifyCode == null) verifyCode = "";
		if (realVerifyCode == null) realVerifyCode = "";

		// ����У����ͨ����
		boolean isSuccess = true;
		// �ж��û�����ʽ�Ƿ���ȷ
		isSuccess = name.matches("[\\S]{3,14}") && isSuccess;
		// �ж������ʽ�Ƿ���ȷ
		isSuccess = password.matches("[\\S]{6,18}") && isSuccess;
		// �ж������ʽ�Ƿ���ȷ
		isSuccess = email.matches("[\\w\\.]{3,19}@[0-9a-zA-Z]{2,7}(\\.[a-zA-Z]{2,7})+") && isSuccess;
		// �ж���֤���ʽ�Ƿ���ȷ
		isSuccess = verifyCode.matches("[0-9a-zA-Z]{4}")
				&& verifyCode.equalsIgnoreCase(realVerifyCode) && isSuccess;
		// ����У���Ƿ�ͨ��
		return isSuccess;
	}

	// AJAX��У�����ݿ����Ƿ����Name
	public void AJAXExistenceName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String inputName = request.getParameter("userName");				// ��ȡ���û���
		PrintWriter out = response.getWriter();							// ��ȡ��Ӧ��

		out.print(registService.isExistenceName(inputName));				// ��Ӧ�û����Ƿ����
	}

	// AJAX��У�����ݿ����Ƿ����Email
	public void AJAXExistenceEmail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");					// ��ȡ������
		PrintWriter out = response.getWriter();							// ��ȡ��Ӧ��

		out.print(registService.isExistenceEmail(email));				// ��Ӧ�����Ƿ����
	}

	// AJAX��У����֤��
	public void AJAXCheckVerifyCode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String verifyCode = request.getParameter("verifyCode");			// ��ȡ����֤��
		String realVerifyCode = (String)request.getSession()
				.getAttribute("realVerifyCode");							// ��ȡ��Ӧ��
		PrintWriter out = response.getWriter();							// ��Ӧ��֤������

		out.print(verifyCode.equalsIgnoreCase(realVerifyCode));
	}
}
