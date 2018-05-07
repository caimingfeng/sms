package util;

import java.text.NumberFormat;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
* @author 蔡名锋:
* @version 创建时间：2018年3月29日 下午7:26:05
* <pre>
* 类说明:
* </pre>
*/
public class ShaTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String passwd = new SimpleHash("SHA-1", "test", "123456").toString();
		System.out.println(passwd);
		
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(2);
		String avgGpa = nf.format(1.8999999999999997);
		System.out.println(avgGpa);
	}

}
