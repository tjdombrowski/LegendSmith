<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="sections/head.jsp"%>

<body>
<!-- Menu here -->
<%@include file="sections/menu.jsp"%>
<div class="container">
    <!--Intro -->
    <div class="row">
        <div class="col-md-12">
            Welcome to LegendSmith, a Guild Wars 2 fan website for helping you achieve your legendary weapon. You can find out what
            each weapon requires you to do, what the materials you need are, potential gold costs, and how much progress you've already
            achieved. Sound useful? <a href="/legendsmith/signup">Sign up</a> and check it out!
        </div>
    </div>


    <div class="row">
        <!-- login form -->
        <div class="col-md-5">
            <form action="j_security_check" method="post">
                <div class="form-group">
                    <input type="text" name="j_username" />
                    <input type="password" name="j_password" />
                </div>
                <input type="submit" class="btn btn-dark" value="Log In" />
            </form>
        </div>
    </div>

</div>
</body>


</html>
