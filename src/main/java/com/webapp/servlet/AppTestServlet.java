package com.webapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/test")
public class AppTestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5890886380874594767L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		try (PrintWriter pw = resp.getWriter()) {
			pw.write(" do get called ");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
