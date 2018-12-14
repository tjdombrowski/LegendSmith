<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="sections/head.jsp"%>

<body>
<!-- Menu here -->
<%@include file="sections/menu.jsp"%>
<div class="container">
    <!--Intro -->
    <div class="row">
        <div class="col-md-12">Welcome!</div>
    </div>


    <div class="row">
        <!-- signup form -->
        <div class="col-md-8">
            ${errorMsg}
        </div>
        <div class="col-md-5">
            <form action="/legendsmith/signup" method="get">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" />
                </div>
                <div class="form-group">
                    <label for="pass1">Password</label>
                    <input type="password" id="pass1" name="password1" />
                </div>
                <div class="form-group">
                    <label for="pass2">Retype Password</label>
                    <input type="password" id="pass2" name="password2" />
                </div>
                <input type="submit" class="btn btn-dark" name="submit" value="Sign Up" />
            </form>
        </div>
    </div>

</div>
</body>


</html>
