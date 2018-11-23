<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="sections/head.jsp"%>

<body>
<!-- Menu -->
<%@include file="sections/menu.jsp"%>
<div class="container">

    <!-- Legendary -->
    <div class="row">
        <div class="col-md-1">image</div>
        <div class="row">
            <div class="col-md-12"><h3>${legendaryData.name}</h3></div>
            <div class="col-md-12">Progress: ${userLegendaryData.progress}%</div>
        </div>
    </div>

    <!-- Items -->
    <div class="row">
        <c:forEach var="legendaryPrimaryItem" items="${legendaryData.getPrimaryItems()}">
            <div class="col-md-5">
                <h3>${legendaryPrimaryItem.primaryItem.name}</h3>
                <div class="accordion">
                        <c:forEach var="task" items="${legendaryPrimaryItem.getTasks()}">
                            <h5>${task.name}<a href="/taskMarkOff?userId=${user.id}"><button class="btn btn-sm">Done</button></a></h5>
                            <div>${task.description}</div>
                        </c:forEach>
                </div>
            </div>
        </c:forEach>
    </div>




</div>
</body>

<!-- JQuery UI Script -->
<script>
    $( function() {
        $( ".accordion" ).accordion({
            collapsible: true
        });
    } );
</script>


</html>
