package com.devjones.obpc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.devjones.obpc.domain.LowestPriceCash;
import com.devjones.obpc.domain.ParserThread;
import com.devjones.obpc.domain.Product;

@WebServlet("/textparser")
public class TextParserController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;
		LowestPriceCash lpc = new LowestPriceCash();
		String[] query = request.getParameter("data").split("\\n");
		
//		for(int i = 0; i < query.length; i++) {
//			int rt = lpc.parse(query[i]);
//			result += rt;
//		}
		
		Product prod = new Product();
		// 2. 스레드처리
		for(int i = 0; i < query.length; i++) {
			ParserThread pt = new ParserThread(query[i], prod);
			Thread t = new Thread(pt);
			t.start();
//			try {
//				t.join();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			
			result = pt.getProd().getTotal();
		}
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print("최저가: " + result);
	}
}
