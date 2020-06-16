package tech.chending.wechat.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * @author ChenDing
 */
public class HttpReqUtil {

    private HttpReqUtil(){}

    public static String getRespMsg(String url ,String method,String args) {

        try {
            //创建url对象
            URL u = new URL(url);
            //获取url连接
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod(method.toUpperCase());
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.9 Safari/537.36");

            //判断是否是GET请求
            if ("GET".equalsIgnoreCase(method)) {
                conn.connect(); //连接
            }

            //post 请求必须设置的
            if ("POST".equalsIgnoreCase(method)) {
                // 设置是否向connection输出，因为这个是post请求，参数要放在
                // http正文内，因此需要设为true
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setUseCaches(false);
            }
            // 当有数据需要提交时
            if (null != args) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(args.getBytes(StandardCharsets.UTF_8));
            }
            InputStream in = conn.getInputStream();
            BufferedInputStream inputStream = new BufferedInputStream(in);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int len;
            byte[] bytes = new byte[8192];
            while ((len = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
            return new String(out.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String getTextByHttpClient(String url) throws IOException {
        //获取连接对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建get请求对象
        HttpGet httpGet = new HttpGet(url);
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
        httpGet.setConfig(requestConfig);

        //发送请求
        CloseableHttpResponse response = client.execute(httpGet);

        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity, "utf-8");

    }

    public static String getPublicNetIp() throws IOException {
        String url = "http://ip.chinaz.com/";
        String textByHttpClient = HttpReqUtil.getTextByHttpClient(url);

        Pattern p = compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
        Matcher m = p.matcher(textByHttpClient);
        if (m.find()) {
            return m.group(1);
        }

        return null;
    }



}
