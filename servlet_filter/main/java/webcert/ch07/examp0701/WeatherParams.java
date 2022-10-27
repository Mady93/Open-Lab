/*
 * Copyright (c) 2005 by David Bridgewater
 * All rights reserved.
 *
 * You may study, use, modify, and distribute this
 * software for any purpose provided that this
 * copyright notice appears in all copies.
 *
 * This software is provided without warranty
 * either expressed or implied.
 *
 */
package webcert.ch07.examp0701;


import java.io.*;
import java.util.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author David Bridgewater
 * This servlet exists to display all the parameters passed to it.
 */
public class WeatherParams extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {
        // Set appropriate content type for response, and obtain
        // the HTML output stream.
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Set up start of HTML page
        out.write("<HTML>\n<HEAD>\n<TITLE>Display Weather Parameters</TITLE>\n</HEAD>\n<BODY>");
        Enumeration paramNames = request.getParameterNames();
        
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            out.write("<h4>" + paramName + "</h4>");
            String[] paramValues = request.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                out.write("<br />" + paramValues[i]);
            }
        }
        
        // Finish off the HTML page and close cleanly
        out.write("\n</BODY>\n</HTML>");
        out.close();
    }
}
