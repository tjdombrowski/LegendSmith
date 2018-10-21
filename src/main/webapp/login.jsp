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
        <!-- login form -->
        <div class="col-md-5">
            <form action="login" type="post">
                <input type="text" name="username" id="username">
                <input type="text" name="password" id="password">
                <input type="submit" value="Log In" name="login">
            </form>
        </div>
    </div>

</div>
</body>


</html>
