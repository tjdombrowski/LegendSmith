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
            <div class="col-md-12"><h3>${legendaryData.name}</h3></div>
            <div class="col-md-12">Progress Bar</div>
        </div>
    </div>

    <!-- Items -->
    <div class="row">
        <c:forEach var="legendaryPrimaryItem" items="${legendaryData.getPrimaryItems()}">
            <div class="col-md-5">
                <h3>${legendaryPrimaryItem.getPrimaryItem().name}</h3>
                <div class="accordion">
                        <c:forEach var="task" items="${legendaryPrimaryItem.getTasks()}">
                            <h5>${task.name}</h5>
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
