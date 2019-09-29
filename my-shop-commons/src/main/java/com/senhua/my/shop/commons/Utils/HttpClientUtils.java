package com.senhua.my.shop.commons.Utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by Administrator on 2019/5/26.
 */
public class HttpClientUtils {

    public static final String GET = "get";
    public static final String POST = "post";
    public static final String REQUEST_HEADER_CONNECTION = "keep-alive";
    public static final String REQUEST_HEADER_USER_AGENT ="Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";

    /**
     * Get请求方式
     * @param url
     * @return
     */
    public static String doGet(String url){
        return createRequest(url,GET,null);
    }

    /**
     * Get请求
     * @param url
     * @param cookie
     * @return
     */
    public static String doGet(String url,String cookie){
        return createRequest(url,GET,cookie);
    }

    /**
     * Post请求
     * @param url
     * @param params 可选参数
     * @return
     */
    public static String doPost(String url,BasicNameValuePair ... params){
        return createRequest(url,POST,null,params);
    }

    /**
     * Post请求
     * @param url
     * @param cookie
     * @param params
     * @return
     */
    public static String doPost(String url,String cookie,BasicNameValuePair ... params){
        return createRequest(url,POST,cookie,params);
    }

    private static String createRequest(String url, String requestMethod, String cookie, BasicNameValuePair ... params){

        CloseableHttpClient httpClient = HttpClients.createDefault();

        String result = null;
        try {
            HttpGet httpGet = null;
            HttpPost httpPost = null;
            CloseableHttpResponse httpResponse = null;
            result = null;

            if(GET.equals(requestMethod)){
                httpGet = new HttpGet(url);
                httpGet.setHeader("Connection",REQUEST_HEADER_CONNECTION);
                httpGet.setHeader("User-Agent",REQUEST_HEADER_USER_AGENT);
                httpGet.setHeader("Cookie", cookie);
                httpResponse = httpClient.execute(httpGet);

            }else if(POST.equals(requestMethod)){
                httpPost = new HttpPost(url);
                httpPost.setHeader("Connection",REQUEST_HEADER_CONNECTION);
                httpPost.setHeader("User-Agent", REQUEST_HEADER_USER_AGENT);
                httpPost.setHeader("Cookie", cookie);

                if(params != null && params.length>0){
                    httpPost.setEntity(new UrlEncodedFormEntity(Arrays.asList(params), "UTF-8"));
                }
                httpResponse = httpClient.execute(httpPost);
            }
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
               if(httpClient !=null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

}
