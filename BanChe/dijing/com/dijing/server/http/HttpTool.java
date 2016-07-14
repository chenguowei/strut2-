package com.dijing.server.http;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2015年11月9日下午4:05:25
 */
public class HttpTool {

	private static final String APPLICATION_JSON = "application/json";
    
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

    /**
     * 以json的形式POST表单
     * @param url
     * @param json
     * @return
     * @throws Exception
     */
    public static String httpPost(String url, String json) throws Exception {
        // 将JSON进行UTF-8编码,以便传输中文
        String encoderJson = URLEncoder.encode(json, HTTP.UTF_8);
        
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
        
        StringEntity se = new StringEntity(encoderJson);
        se.setContentType(CONTENT_TYPE_TEXT_JSON);
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
        httpPost.setEntity(se);
        HttpResponse response = httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == 200) {
        	String str = EntityUtils.toString(response.getEntity(), "UTF8");
        	return URLDecoder.decode(str, "UTF8");
		}else{
			return null;
		}
    }
    
    public static JSONObject httpPostWithJSON(String url, JSONObject json) throws Exception{
    	String str = httpPost(url, json.toString());
    	if(str==null){
    		return null;
    	}
    	JSONObject result = new JSONObject(str);
    	return result;
    }
    
    /**
     * 以键值对的形式提交表单
     * @param url
     * @param data
     * @return
     * @throws Exception
     */
    public static JSONObject httpPostWithJSON(String url, Map<String, String> data) throws Exception{
    	String str = httpPost(url, data);
    	if(str==null){
    		return null;
    	}
    	JSONObject result = new JSONObject(str);
    	return result;
    }
    
    /**
     * 以键值对的形式POST表单
     * @param url
     * @param json
     * @return
     * @throws Exception
     */
    public static String httpPost(String url, Map<String, String> data) throws Exception {
                
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
        
        List<NameValuePair> values = new ArrayList<>(data.size());
        for(Map.Entry<String, String> entry : data.entrySet()){
        	values.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        
        httpPost.setEntity(new UrlEncodedFormEntity(values, "UTF-8"));
        HttpResponse response = httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == 200) {
        	String str = EntityUtils.toString(response.getEntity(), "UTF8");
        	return URLDecoder.decode(str, "UTF8");
		}else{
			return null;
		}
    }
    
    public static String httpGet(String url) throws Exception{
    	DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        System.out.println("url: " + httpGet.getRequestLine().toString());
        HttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
        	String str = EntityUtils.toString(response.getEntity(), "UTF8");
        	return URLDecoder.decode(str, "UTF8");
		}else{
			return null;
		}
    }
}
