package top.qlbxxxz.tools;

import java.util.UUID;

/**
 *	����UUID����
 */
public class SimpleUUID {
	public static String getUUID() {
		String temp = UUID.randomUUID().toString();									// ���ɵ����UUID32λ��
		String result = "";															// ���յķ��ؽ��
		String[] args = temp.split("[^a-zA-Z0-9]");									// ��������𿪵��ַ����ڵ��ַ���
		
		// �����Ѿ��ֿ����ַ���
		for (int count = 0; count <= args.length - 1; count++) {
			result += args[count];
		}
		
		// ����֮ǰתΪ��д
		return result.toUpperCase();
	}
}
