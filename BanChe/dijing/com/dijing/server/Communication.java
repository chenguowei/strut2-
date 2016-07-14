package com.dijing.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;


public class Communication {
	
	static Logger logger = LogManager.getLogger(Communication.class.getName());
	
	public static final String CHARSET = "UTF-8";
	
	public boolean send(Socket socket,String content,String username){
		//String str = null;
		try {
			socket.setSoTimeout(30000);//30s
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter
			          (socket.getOutputStream())), true);
			//BufferedReader in = new BufferedReader(new InputStreamReader(
				//	                               socket.getInputStream()));
			out.println(content);
			//out.close();//1113注释
			//logger.info("["+username+"] Server has sended ["+content+"] to "+username);
			//TODO
			//发送数据的验证机制。
			/*str = in.readLine();
			if(content.equals(str)){
				return true;
			}else{
				return false;
			}*/
		} catch(SocketException e){
			e.printStackTrace();
			System.out.println("readline timeout!");
			logger.error("SocketException-40", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("IOException-44", e);
		}
		return true;
	}
	/**
	 * 发送一维数组
	 * @param socket
	 * @param array
	 */
	public void sendArray(Socket socket,String[] array){
		try {
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(array);
			//out.close();//1113注释
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("IOException-57", e);
		}
	}
	/**
	 * 发送二维数组
	 * @param socket
	 * @param array
	 */
	public void sendArray2(Socket socket, String[][] array){
		try {
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(array);
			//out.close();//1113注释
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("IOException-57", e);
		}
	}
	
	public boolean sendByte(Socket socket, byte[] b){
		try {
			OutputStream out = socket.getOutputStream();
			out.write(b);
			//out.close();//1113注释
		} catch (IOException e) {
			
			logger.error("IOException-sendByte", e);
			return false;
		}
		return true;
	}
	
	public void send(Socket socket,String content){
		//String str = null;
		try {
			socket.setSoTimeout(30000);//30s
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter
			          (socket.getOutputStream())), true);
			out.println(content);
			out = null;//130502添加
			//out.close();//1113注释
		} catch(SocketException e){
			e.printStackTrace();
			logger.error("SocketException", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("IOException", e);
		}
		
	}
	
	public void sendJsonFeedback(Socket socket, int status, String info){
		JSONObject json = new JSONObject();
		json.put("status", status);
		json.put("info", info);
		json.put("result", "");
		send(socket, json);
	}
	
	public void sendJsonFeedback(Socket socket, int status, String info, String result){
		JSONObject json = new JSONObject();
		json.put("status", status);
		json.put("info", info);
		json.put("result", result);
		send(socket, json);
	}
	
	public void sendJsonFeedback(Socket socket, int status, String info, JSONObject result){
		JSONObject json = new JSONObject();
		json.put("status", status);
		json.put("info", info);
		json.put("result", result.toString());
		send(socket, json);
	}
	
	public void send(Socket socket,JSONObject content){
		//String str = null;
		try {
			socket.setSoTimeout(12000);//12s
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter
			          (socket.getOutputStream(), CHARSET)), true);
			
//				System.out.println("server sended: "+content.toString());
				logger.debug("server sended: "+content.toString());
			
			out.println(content.toString());
			out = null;//130502添加
			//out.close();//1113注释
		} catch(SocketException e){
			e.printStackTrace();
			logger.error("SocketException", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("IOException", e);
		}
		
	}
	
	public String readLine(Socket socket){
		try {
			socket.setSoTimeout(20*1000);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream(), CHARSET));
			return in.readLine();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String readLine(Socket socket, boolean log){
		try {
			socket.setSoTimeout(20*1000);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream(), CHARSET));
			return in.readLine();
		} catch (Exception e) {
			if(log)
				e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 接收超时时间为10s。
	 * @param socket
	 * @return null, when error occur;
	 */
	public byte[] readByte(Socket socket){
		
		byte[] buf = new byte[1024];
		byte[] data = null;
		
		try {
			socket.setSoTimeout(10000);//10s
			//BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//InputStream is = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			InputStream is = new DataInputStream(socket.getInputStream());
			int length = 0;
			int start_position = -1;
			while(true){
				length = is.read(buf, 0, 1024);
				//System.out.println(length);
				if(length<0){
					logger.info("length: "+length);
					return null;
				}
				for(int i=0;i<length;i++){
					if((buf[i]&0xff)!=0xff){ //0xff是主机定时给服务器发送的字节数据
						start_position = i;
						break;
					}
				}
				String s = "";
				for(int i=0;i<length;i++){
					//s += Integer.toHexString(buf[i]&0xff);
					s += Integer.toHexString(buf[i]&0xff);
					s += " ";
				}		
				logger.info("start_position: "+start_position+"length: "+length+
						" / readByte: "+s);
				if(start_position!=-1){
					break;
				}
			}
			data = new byte[length-start_position];
			for(int i=start_position;i<length;i++){
			//	data[i] = buf[i];
				data[i - start_position] = buf[i];
			}
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("SocketException", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("IOException", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception", e);
		}
		return data;
	}
	/**
	 * 
	 * @param socket
	 * @param Timeout the specified timeout, in milliseconds.
	 * @return
	 */
	public byte[] readByte(Socket socket,int Timeout){
		
		byte[] buf = new byte[1024];
		byte[] data = null;
		
		try {
			socket.setSoTimeout(Timeout);
			//BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//InputStream is = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			InputStream is = new DataInputStream(socket.getInputStream());
			int length = 0;
			int start_position = -1;
			while(true){
				length = is.read(buf, 0, 1024);
				//System.out.println(length);
				if(length<0){
					logger.info("length: "+length);
					return null;
				}
				for(int i=0;i<length;i++){
					if((buf[i]&0xff)!=0xff){ //0xff是主机定时给服务器发送的字节数据
						start_position = i;
						break;
					}
				}
				String s = "";
				for(int i=0;i<length;i++){
					//s += Integer.toHexString(buf[i]&0xff);
					s += Integer.toHexString(buf[i]&0xff);
					s += " ";
				}		
				logger.info("start_position: "+start_position+"length: "+length+
						" / readByte: "+s);
				if(start_position!=-1){
					break;
				}
			}
			data = new byte[length-start_position];
			for(int i=start_position;i<length;i++){
			//	data[i] = buf[i];
				data[i - start_position] = buf[i];
			}
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("SocketException", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("IOException", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception", e);
		}
		return data;
	}

}
