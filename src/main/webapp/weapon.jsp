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
            <h3>${legendaryData.primaryItems}</h3>
            <div>Tasks</div>
        </div>
        <div class="col-md-5">
            <h3>Item Name</h3>
            <div>Tasks</div>
        </div>
        <div class="col-md-5">
            <h3>Item Name</h3>
            <div>Tasks</div>
        </div>
        <div class="col-md-5">
            <h3>Item Name</h3>
            <div>Tasks</div>
        </div>
    </div>




</div>
</body>


</html>
