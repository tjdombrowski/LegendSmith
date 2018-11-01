<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="sections/head.jsp"%>

<body>
<!-- Menu -->
<%@include file="sections/menu.jsp"%>
<div class="container">

    <!-- Legendary -->
    <div class="row">
        <div class="col-md-1">Picture</div>
        <div class="row">
            <div class="col-md-12">${legendaryData.name}</div>
            <div class="col-md-12">Progress Bar</div>
        </div>
    </div>

    <!-- Items -->
    <div class="row">
        <div class="col-md-5">
            <h3>${primaryItem1.name}</h3>
            <div>

            </div>
        </div>
        <div class="col-md-5">
            <h3>${primaryItem2.name}</h3>
            <div>Tasks</div>
        </div>
        <div class="col-md-5">
            <h3>${primaryItem3.name}</h3>
            <div>Tasks</div>
        </div>
        <div class="col-md-5">
            <h3>${primaryItem4.name}</h3>
            <div>Tasks</div>
        </div>
    </div>




</div>
</body>


</html>
