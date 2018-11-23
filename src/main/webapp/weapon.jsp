<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="sections/head.jsp"%>

<body>
<!-- Menu -->
<%@include file="sections/menu.jsp"%>
<div class="container">

    <!-- Legendary -->
    <div class="row">
        <div class="col-md-1">${user.username}</div>
        <div class="row">
            <div class="col-md-12"><h3>${legendaryData.legendary.name}</h3></div>
            <div class="col-md-12">Progress: ${legendaryData.progress}%</div>
        </div>
    </div>

    <!-- Items -->
    <div class="row">
        <c:forEach var="userPrimaryItem" items="${primaryItemData}">
            <div class="col-md-5">
                <h3>${userPrimaryItem.primaryItem.name}</h3>
                <div class="accordion">
                        <c:forEach var="task" items="${userPrimaryItem.getTasks()}">
                            <h5>${task.name}<button class="btn btn-sm">Done</button></h5>
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
