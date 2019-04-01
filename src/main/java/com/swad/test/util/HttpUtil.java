package com.swad.test.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

/**
 * Http请求工具
 */
public class HttpUtil {

	/**
	 * 重写验证方法，取消检测ssl
	 */
	private static TrustManager trustAll = new X509TrustManager() {

		public void checkClientTrusted(X509Certificate[] chain, String authType) {

		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) {

		}

		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

	};

	/**
	 * 获取https的HttpClient：忽略证书，此处是最新版本HttpClient的SSL避免方式
	 */
	private static CloseableHttpClient getSupportHttpsIgnoreCert() throws Exception {
		RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory>create();
		ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
		registryBuilder.register("http", plainSF);

		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(null, new TrustManager[] { trustAll }, null);
		LayeredConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(sslContext,
				NoopHostnameVerifier.INSTANCE);

		registryBuilder.register("https", sslSF);

		Registry<ConnectionSocketFactory> registry = registryBuilder.build();

		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);

		return HttpClientBuilder.create().setConnectionManager(connManager).build();
	}

	/**
	 * 关闭连接
	 * 
	 */
	public static void close(CloseableHttpClient httpClient, CloseableHttpResponse response) {
		try {
			if (response != null) {
				response.close();
			}
			if (httpClient != null) {
				httpClient.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * http-https-get请求
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static CloseableHttpResponse doGet(String url, List<BasicNameValuePair> params,
			List<BasicNameValuePair> headers) throws ClientProtocolException, IOException {

		if (StringUtils.isEmpty(url)) {
			return null;
		}

		CloseableHttpClient httpClient = null;
		try {
			httpClient = getSupportHttpsIgnoreCert();
			Registry<CookieSpecProvider> cookieSpecProviderRegistry = RegistryBuilder.<CookieSpecProvider>create()
					.register("myCookieSpec", context -> new MyCookieSpec()).build();// 注册自定义CookieSpec
			HttpClientContext context = HttpClientContext.create();
			context.setCookieSpecRegistry(cookieSpecProviderRegistry);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (httpClient == null) {
			return null;
		}

		RequestBuilder reqBuilder = RequestBuilder.get();
		reqBuilder.setUri(url);

		// 添加请求头
		if (headers != null) {
			for (BasicNameValuePair nvp : headers) {
				reqBuilder.addHeader(nvp.getName(), nvp.getValue());
			}
		}

		// 添加请求参数
		if (params != null) {
			for (BasicNameValuePair nvp : params) {
				reqBuilder.addParameter(nvp);
			}
		}

		// 构建请求参数
		RequestConfig config = RequestConfig.custom().setConnectTimeout(10 * 1000) // 创建连接的最长时间
				.setConnectionRequestTimeout(10 * 1000) // 从连接池中获取到连接的最长时间
				.setSocketTimeout(30 * 1000) // 数据传输的最长时间
				.build();
		// 设置请求配置信息
		reqBuilder.setConfig(config);

		HttpUriRequest req = reqBuilder.build();
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(req);

		return closeableHttpResponse;
	}

	/**
	 * http-https-get请求-使用代理
	 */
	public static String doGetWithProxy(String url, List<BasicNameValuePair> params, List<BasicNameValuePair> headers,
			String ip, int port) {

		if (StringUtils.isEmpty(url)) {
			return null;
		}

		CloseableHttpClient httpClient = null;
		try {
			httpClient = getSupportHttpsIgnoreCert();
			Registry<CookieSpecProvider> cookieSpecProviderRegistry = RegistryBuilder.<CookieSpecProvider>create()
					.register("myCookieSpec", context -> new MyCookieSpec()).build();// 注册自定义CookieSpec
			HttpClientContext context = HttpClientContext.create();
			context.setCookieSpecRegistry(cookieSpecProviderRegistry);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (httpClient == null) {
			return null;
		}

		RequestBuilder reqBuilder = RequestBuilder.get();
		reqBuilder.setUri(url);

		// 添加请求头
		if (headers != null) {
			for (BasicNameValuePair nvp : headers) {
				reqBuilder.addHeader(nvp.getName(), nvp.getValue());
			}
		}

		// 添加请求参数
		if (params != null) {
			for (BasicNameValuePair nvp : params) {
				reqBuilder.addParameter(nvp);
			}
		}

		HttpHost proxy = new HttpHost(ip, port);// 设置代理
		// 构建请求参数
		RequestConfig config = RequestConfig.custom().setConnectTimeout(10 * 1000) // 创建连接的最长时间
				.setConnectionRequestTimeout(10 * 1000) // 从连接池中获取到连接的最长时间
				.setSocketTimeout(30 * 1000) // 数据传输的最长时间
				.setProxy(proxy)// 设置代理
				.build();
		// 设置请求配置信息
		reqBuilder.setConfig(config);

		CloseableHttpResponse response = null;
		String result = null;

		HttpUriRequest req = reqBuilder.build();
		try {
			response = httpClient.execute(req);
			// 获取响应数据（包含错误响应）
			if (response.getEntity() != null) {
				result = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close(httpClient, response);
		}
		return result;
	}

	/**
	 * http-https-post请求-json
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static CloseableHttpResponse doPostWithJson(String url, List<BasicNameValuePair> params,
			List<BasicNameValuePair> headers, String json) throws ClientProtocolException, IOException {

		if (StringUtils.isEmpty(url)) {
			return null;
		}

		CloseableHttpClient httpClient = null;
		try {
			httpClient = getSupportHttpsIgnoreCert();
			Registry<CookieSpecProvider> cookieSpecProviderRegistry = RegistryBuilder.<CookieSpecProvider>create()
					.register("myCookieSpec", context -> new MyCookieSpec()).build();// 注册自定义CookieSpec
			HttpClientContext context = HttpClientContext.create();
			context.setCookieSpecRegistry(cookieSpecProviderRegistry);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (httpClient == null) {
			return null;
		}

		RequestBuilder reqBuilder = RequestBuilder.post();
		reqBuilder.setUri(url);

		// 添加请求头
		if (headers != null) {
			for (BasicNameValuePair nvp : headers) {
				reqBuilder.addHeader(nvp.getName(), nvp.getValue());
			}
		}
		// 添加请求参数
		if (params != null) {
			for (BasicNameValuePair nvp : params) {
				reqBuilder.addParameter(nvp);
			}
		}
		// 添加请求体
		if (!StringUtils.isEmpty(json)) {
			StringEntity entity = new StringEntity(json, "UTF-8");
			entity.setContentType("application/json");
			reqBuilder.setEntity(entity);
		}

		// 构建请求参数
		RequestConfig config = RequestConfig.custom().setConnectTimeout(10 * 1000) // 创建连接的最长时间
				.setConnectionRequestTimeout(10 * 1000) // 从连接池中获取到连接的最长时间
				.setSocketTimeout(30 * 1000) // 数据传输的最长时间
				.build();
		// 设置请求配置信息
		reqBuilder.setConfig(config);

		HttpUriRequest req = reqBuilder.build();
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(req);
		return closeableHttpResponse;
	}

	/**
	 * http-https-post请求-form
	 */
	public static String doPostWithForm(String url, List<BasicNameValuePair> params, List<BasicNameValuePair> headers,
			List<BasicNameValuePair> datas) {

		if (StringUtils.isEmpty(url)) {
			return null;
		}

		CloseableHttpClient httpClient = null;
		try {
			httpClient = getSupportHttpsIgnoreCert();
			Registry<CookieSpecProvider> cookieSpecProviderRegistry = RegistryBuilder.<CookieSpecProvider>create()
					.register("myCookieSpec", context -> new MyCookieSpec()).build();// 注册自定义CookieSpec
			HttpClientContext context = HttpClientContext.create();
			context.setCookieSpecRegistry(cookieSpecProviderRegistry);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (httpClient == null) {
			return null;
		}

		CloseableHttpResponse response = null;
		String result = null;
		try {
			RequestBuilder reqBuilder = RequestBuilder.post();
			reqBuilder.setUri(url);

			// 添加请求头
			if (headers != null) {
				for (BasicNameValuePair nvp : headers) {
					reqBuilder.addHeader(nvp.getName(), nvp.getValue());
				}
			}
			// 添加请求参数
			if (params != null) {
				for (BasicNameValuePair nvp : params) {
					reqBuilder.addParameter(nvp);
				}
			}
			// 添加表单数据
			if (datas != null) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(datas, "utf-8");
				entity.setContentType("application/x-www-form-urlencoded");
				reqBuilder.setEntity(entity);
			}

			// 构建请求参数
			RequestConfig config = RequestConfig.custom().setConnectTimeout(10 * 1000) // 创建连接的最长时间
					.setConnectionRequestTimeout(10 * 1000) // 从连接池中获取到连接的最长时间
					.setSocketTimeout(30 * 1000) // 数据传输的最长时间
					.build();
			// 设置请求配置信息
			reqBuilder.setConfig(config);

			HttpUriRequest req = reqBuilder.build();

			response = httpClient.execute(req);
			// 获取响应数据（包含错误响应）
			if (response.getEntity() != null) {
				result = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close(httpClient, response);
		}
		return result;
	}

	/**
	 * 下载文件
	 */
	public static String downLoadFile(String url, List<BasicNameValuePair> params, List<BasicNameValuePair> headers,
			String filePath, String fileName) {

		if (StringUtils.isEmpty(url)) {
			return null;
		}

		CloseableHttpClient httpClient = null;
		try {
			httpClient = getSupportHttpsIgnoreCert();
			Registry<CookieSpecProvider> cookieSpecProviderRegistry = RegistryBuilder.<CookieSpecProvider>create()
					.register("myCookieSpec", context -> new MyCookieSpec()).build();// 注册自定义CookieSpec
			HttpClientContext context = HttpClientContext.create();
			context.setCookieSpecRegistry(cookieSpecProviderRegistry);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (httpClient == null) {
			return null;
		}

		CloseableHttpResponse response = null;
		String result = null;
		try {
			RequestBuilder reqBuilder = RequestBuilder.get();
			reqBuilder.setUri(url);

			// 添加请求头
			if (headers != null) {
				for (BasicNameValuePair nvp : headers) {
					reqBuilder.addHeader(nvp.getName(), nvp.getValue());
				}
			}
			// 添加请求参数
			if (params != null) {
				for (BasicNameValuePair nvp : params) {
					reqBuilder.addParameter(nvp);
				}
			}

			// 构建请求参数
			RequestConfig config = RequestConfig.custom().setConnectTimeout(10 * 1000) // 创建连接的最长时间
					.setConnectionRequestTimeout(10 * 1000) // 从连接池中获取到连接的最长时间
					.setSocketTimeout(10 * 60 * 1000) // 数据传输的最长时间
					.build();
			// 设置请求配置信息
			reqBuilder.setConfig(config);

			HttpUriRequest req = reqBuilder.build();

			response = httpClient.execute(req);

			// 获取响应数据（包含错误响应）
			InputStream inputStream = null;
			FileOutputStream outputStream = null;
			try {
				if (response.getEntity() != null) {
					HttpEntity entity = response.getEntity();
					inputStream = entity.getContent();
					String saveFilePath = filePath + File.separator + fileName;
					outputStream = new FileOutputStream(saveFilePath);
					int bytesRead = -1;
					byte[] buffer = new byte[1024];
					while ((bytesRead = inputStream.read(buffer)) != -1) {
						outputStream.write(buffer, 0, bytesRead);
						outputStream.flush();
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				if (outputStream != null) {
					outputStream.close();
					outputStream = null;
				}
				if (inputStream != null) {
					inputStream.close();
					inputStream = null;
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close(httpClient, response);
		}
		return result;
	}
}
