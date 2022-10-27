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
        <title>General Animal Information</title>
    </head>
    <body>
        <h2>Fill in general animal information here, regardless of what sort of animal...</h2>
        <jsp:useBean id="currentAnimal" scope="session" type="webcert.ch07.examp0701.Animal" />
        <p>Overtype the defaults in the form below...</p>
        <form action="dogInput3.jsp">
            <br />Name: <input type="text" name="name" value="<jsp:getProperty name="currentAnimal" property="name" />" />
            <br />Weight: <input type="text" name="weight" value="<jsp:getProperty name="currentAnimal" property="weight" />" />
            <br />Sex: <input type="text" name="sex" value="<jsp:getProperty name="currentAnimal" property="sex" />" />
            <br />Insured: <input type="text" name="insured" value="<jsp:getProperty name="currentAnimal" property="insured" />" />
            <br /><input type="submit" value="Continue..." />
        </form>
    </body>
</html>