/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;

public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    public static String fetchDataFromURL(String myURL) {

        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null) {
                urlConn.setReadTimeout(60 * 1000);
            }
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(),
                        Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception while calling URL:" + myURL, e);
        }

        return sb.toString();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String op = request.getParameter("op");
        if (op != null && op.equals("search")) {
            String url = request.getParameter("txturl").toString();
            String res = fetchDataFromURL(url);

            String parsedata = Jsoup.parse(res).text();

            request.setAttribute("resultdata", parsedata.toString());
            request.getRequestDispatcher("searchresults.jsp").forward(request, response);
        }
        if (op != null && op.equals("savedata")) {
            String data = request.getParameter("result").toString();
            System.out.println("Length: " + data.length());
            DatabaseConnection db = new DatabaseConnection();
            Connection con = db.dbConnection();
            String username = (String) request.getSession().getAttribute("currentuser");
            if (username == "" && username.equals("")) {
                out.println("<script>alert('Login First');</script>");
                response.sendRedirect("index.jsp");
            }
            if (con != null) {
                try {
                    String sql = "insert into searchrecords(username,record) values(?,?);";
                    PreparedStatement smt = con.prepareStatement(sql);
                    smt.setString(1, username);
                    smt.setString(2, data);
                    if (smt.executeUpdate() != 0) {
                        out.println("<script>alert('URL Content Saved Successfully!!');</script>");
                    } else {
                        out.println("<script>alert('URL Content Can not Saved!!');</script>");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

    }

}
