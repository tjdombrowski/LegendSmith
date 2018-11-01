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
        <c:forEach var="legendaryPrimaryItem" items="${legendaryData.getPrimaryItems()}">
            <div class="col-md-5">
                <h3>${legendaryPrimaryItem.getPrimaryItem().name}</h3>
                <div>
                    <ul>
                        <c:forEach var="task" items="${legendaryPrimaryItem.getTasks()}">
                            <li>${task.name}</li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </c:forEach>
    </div>




</div>
</body>


</html>
