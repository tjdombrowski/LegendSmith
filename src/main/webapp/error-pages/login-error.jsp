<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="../sections/head.jsp"%>

<body>
<!-- Menu here -->
<%@include file="../sections/menu-logged-out.jsp"%>
<div class="container container2">
    <!--Intro -->
    <%@include file="../sections/login-intro.jsp"%>
    <div class="row">
        <!-- display error information -->
        <div class="col-md-6 error">Please enter your information correctly.</div>
    </div>


    <div class="row">
        <!-- login form -->
        <%@include file="../sections/login-form.jsp"%>
    </div>

</div>
</body>


</html>
