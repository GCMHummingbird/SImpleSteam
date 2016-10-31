package top.qlbxxxz.tools;

import java.awt.image.BufferedImage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

import cn.itcast.vcode.utils.VerifyCode;

/**
 * @����		Hummingbird
 * @����		���������һ������֤��
 * @ʹ�÷���	��a��ǩ�е��ô��༴��, Ҫ��ȡ��֤���ֵ��ʹ����Ӧjsp�����е�
 * 			getSession().getAttriubute("realVerifyCode")������ȡStringֵ
 *
 */
public class SimpleVerifyCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException ,java.io.IOException {
		VerifyCode vc = new VerifyCode();										// ������֤�����
		BufferedImage image = vc.getImage();									// ��ȡ��֤��
		
		VerifyCode.output(image, response.getOutputStream());					// ����VerifyCode����֤�뷢�͵��ͻ���
		request.getSession().setAttribute("realVerifyCode", vc.getText());		// ��ȡ��֤�뱣��ΪString����Session��, ����У��
	};
}
