<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="sections/head.jsp"%>

<body>
<!-- Menu here -->
<%@include file="sections/header.jsp"%>
<div class="container">
    <!--Intro -->
    <div class="row">
        <div class="col-md-12">Welcome to LegendSmith. Please fill out each field to sign up and get started!</div>
    </div>


    <div class="row">
        <!-- signup form -->
        <div class="col-md-8">
            <p class="error">${errorMsg}</p>
        </div>
        <div class="col-md-5">
            <form action="/legendsmith/signup" method="get">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" maxlength="20"/>
                </div>
                <div class="form-group">
                    <label for="pass1">Password</label>
                    <input type="password" id="pass1" name="password1" maxlength="20"/>
                </div>
                <div class="form-group">
                    <label for="pass2">Retype Password</label>
                    <input type="password" id="pass2" name="password2" maxlength="20"/>
                </div>
                <input type="submit" class="btn btn-dark" name="submit" value="Sign Up" />
                <a href="/legendsmith"><button class="btn btn-dark">Return</button></a>
            </form>
        </div>
    </div>

</div>
</body>


</html>
