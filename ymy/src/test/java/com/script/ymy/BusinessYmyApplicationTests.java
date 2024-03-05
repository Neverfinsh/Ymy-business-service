package com.script.ymy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BusinessYmyApplicationTests {

	@Test
	void contextLoads() {
//// 创建自定义 TrustManager
//		TrustManager[] trustManagers = new TrustManager[]{new TrustAllCertManager()};
//
//		// 获取默认的 SSLContext
//		SSLContext sslContext = null;
//		try {
//			sslContext = SSLContext.getInstance("SSL");
//			sslContext.init(null, trustManagers, new java.security.SecureRandom());
//		} catch (NoSuchAlgorithmException | KeyManagementException e) {
//			e.printStackTrace();
//		}
//		// 应用自定义的 SSLContext
//		HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
//		try {
//			Connection.Response response = Jsoup.connect("https://chat18.aichatos.xyz/")
//					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36 Edg/111.0.1661.54")
//					.ignoreContentType(true)
//					.execute();
//
//			String body = response.body();
//			System.out.println("---body----"+body);
//		//	JSONObject jsonObject = JSONObject.parseObject(body);
//		//	System.out.println("》》》》》》》》jsonObject 》》》》》》》"+jsonObject);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}

}
