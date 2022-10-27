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
        <title>Your Completed Dog</title>
    </head>
    <body>
        <h2>The animal database has been updated with these DOG details:</h2>
        <jsp:useBean id="currentAnimal" scope="session" type="webcert.ch07.examp0701.Dog" />
        <jsp:setProperty name="currentAnimal" property="*" />
        <br />Name: <jsp:getProperty name="currentAnimal" property="name" />
        <br />Weight: <jsp:getProperty name="currentAnimal" property="weight" />
        <br />Sex: <jsp:getProperty name="currentAnimal" property="sex" />
        <br />Insured: <jsp:getProperty name="currentAnimal" property="insured" />
        <br />Bark Volume: <jsp:getProperty name="currentAnimal" property="barkVolume" />
    </body>
</html>