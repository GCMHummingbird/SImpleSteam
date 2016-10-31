package top.qlbxxxz.tools;

import java.util.UUID;

/**
 *	生成UUID的类
 */
public class SimpleUUID {
	public static String getUUID() {
		String temp = UUID.randomUUID().toString();									// 生成的随机UUID32位码
		String result = "";															// 最终的返回结果
		String[] args = temp.split("[^a-zA-Z0-9]");									// 根据正则拆开的字符所在的字符串
		
		// 连接已经分开的字符串
		for (int count = 0; count <= args.length - 1; count++) {
			result += args[count];
		}
		
		// 返回之前转为大写
		return result.toUpperCase();
	}
}
