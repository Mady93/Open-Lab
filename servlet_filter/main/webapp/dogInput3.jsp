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
<html>
    <head>
       <title>Specific Dog Information</title>
    </head>
    <body>
        <h2>Fill in specific dog information here...</h2>
        <jsp:useBean id="currentAnimal" scope="session" class="webcert.ch07.examp0701.Dog" />
        <jsp:setProperty name="currentAnimal" property="*" />
        <p>Overtype the defaults in the form below...</p>
        <form action="dogInput4.jsp" method="post">
            <br />Bark Volume: <input type="text" name="barkVolume"
            value="<jsp:getProperty name="currentAnimal" property="barkVolume" />" />
            <br /><input type="submit" value="Continue..." />
        </form>
    </body>
</html>