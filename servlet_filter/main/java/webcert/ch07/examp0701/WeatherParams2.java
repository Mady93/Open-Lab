package webcert.ch07.examp0701;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WeatherParams2 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
    	 
    	 String user = request.getParameter("user");
    	 String password = request.getParameter("password");
    	 String hidden = request.getParameter("hiddeInfo");
    	 String[] paramValues = request.getParameterValues("weathertype");
    	 
    	 List<String> weatherType = new ArrayList<>();
    	 for(String p: paramValues) {
    		 weatherType.add(p);
    	 }
    	 
    	 ReportBean reportBean = new ReportBean();
    	 reportBean.setUser(user);
    	 reportBean.setPassword(password);
    	 reportBean.setHidden(hidden);
    	 reportBean.setWeatherType(weatherType);
    	 
    	 request.setAttribute("report", reportBean);
    	 
    	 /*HttpSession session = request.getSession();
    	 session.setAttribute("report", reportBean);*/
    	 
    	 
    	 RequestDispatcher rd = request.getRequestDispatcher("/report.jsp");
    	 rd.forward(request, response);
    }
}
