<!--/**
 *  Copyright (c) 2005 by David Bridgewater
 *  All rights reserved.
 *  
 *  You may study, use, modify, and distribute this
 *  software for any purpose provided that this
 *  copyright notice appears in all copies.
 *  
 *  This software is provided without warranty
 *  either expressed or implied.
 */-->
<%-- Set up a dog with some default characteristics --%>
<jsp:useBean id="currentAnimal" scope="session" class="webcert.ch07.examp0701.Dog">
  <jsp:setProperty name="currentAnimal" property="name" value="Fido" />
  <jsp:setProperty name="currentAnimal" property="weight" value="6.5" />
  <jsp:setProperty name="currentAnimal" property="sex" value="F" />
  <jsp:setProperty name="currentAnimal" property="insured" value="false" />
  <jsp:setProperty name="currentAnimal" property="barkVolume" value="Loud" />
</jsp:useBean>
<%
   RequestDispatcher rd = application.getRequestDispatcher("/animalInput2.jsp");
   rd.forward(request, response);
%>
