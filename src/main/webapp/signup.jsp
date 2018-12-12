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
        <div class="col-md-5">
            <form action="/signup" method="post">
                <input type="username" name="username" />
                <input type="password1" name="password1" />
                <input type="password2" name="password2" />
                <input type="submit" value="Sign Up" />
            </form>
        </div>
    </div>

</div>
</body>


</html>
