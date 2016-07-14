package com.dijing.server.checkcoder;
/*
 * Created on 2005-10-15
 * Author stephen
 * Email zhoujianqiang AT gamil DOT com
 * CopyRight(C)2005-2008 , All rights reserved.
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 
 * @author stephen
 * @version 1.0.0
 */
public class CheckCoderTool extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 生成登录验证码.
     * 验证码的数据从客户的session中的属性c中获取.<br>
     * 生成的验证码以JPEG图片方式输出.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/jpeg");
        String c = (String) request.getSession().getAttribute("c");
        if (c == null)c = "";
        int width = c.length() * 8 + 10;
        int height = 16;
        int startX = 5;
        int startY = 12;
        BufferedImage bi = new BufferedImage(width, height,BufferedImage.TYPE_INT_BGR);
        Graphics2D g = bi.createGraphics();
        g.setColor(Color.GREEN);
        g.setBackground(Color.black);
        g.clearRect(0, 0, width, height);
        g.drawString(c, startX, startY);
        JPEGImageEncoder encoder = null;
        JPEGEncodeParam param = null;

        try {
            encoder = JPEGCodec.createJPEGEncoder(response.getOutputStream());
            param = encoder.getDefaultJPEGEncodeParam(bi);
            param.setQuality(0.9f, false);
            encoder.encode(bi);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bi = null;
            g = null;
            c = null;
            encoder = null;
            param = null;
        }

    }
}

