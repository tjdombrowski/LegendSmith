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
        <div id="itemTabs" class="col-md-12">
            <!-- set the tab titles -->
            <ul>
            <c:set var="counter1" value="0" scope="page" />
            <c:forEach var="legendaryPrimaryItem" items="${legendaryData.getPrimaryItems()}">
                <c:set var="counter1" value="${counter1 + 1}" scope="page" />
                <li><a href="#tab${counter1}">${legendaryPrimaryItem.primaryItem.name}</a></li>
            </c:forEach>
            </ul>

            <!-- set the tab contents -->
            <c:set var="counter2" value="0" scope="page" />
            <c:forEach var="legendaryPrimaryItem" items="${legendaryData.getPrimaryItems()}">
                <c:set var="counter2" value="${counter2 + 1}" scope="page" />
                <div id="tab${counter2}">
                    <div class="accordion">
                        <c:forEach var="task" items="${legendaryPrimaryItem.getTasks()}" >
                            <h5>${task.name}</h5>
                            <div>${task.description}</div>
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>
        </div>
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

    $( function() {
        $( "#itemTabs" ).tabs();
    } );


</script>


</html>
