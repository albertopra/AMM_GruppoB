<%-- 
    Document   : login
    Created on : 2-mag-2016, 17.48.37
    Author     : alber
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" />

<div id="content">
    <h2>Login</h2>

    <c:if test="${datiErrati == true}">
        <p style="color: red">Attenzione! La username o 
            la password sono errati.</p>
    </c:if>

        <form method="post" action="login.html">
        <div class="input-field">
            <!-- Username -->
            <label class="label" for="username">Username:</label>
            <input type="text" name="Username" id="username" />
        </div>

        <div class="input-field">
            <!-- Password -->
            <label class="label" for="pswd">Password:</label>
            <input type="password" name="Password" id="password" />
        </div>

        <div class="input-field">
            <!-- Submit -->
            <input type="submit" name="Submit" value="Login"/>
        </div>
    </form>
</div>

<jsp:include page="footer.jsp" />