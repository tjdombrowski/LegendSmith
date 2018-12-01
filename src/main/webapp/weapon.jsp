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
            <div class="col-md-12">
                <div id="progressBar">

                </div>
            </div>
        </div>
    </div>

    <!-- Items -->
    <div class="row">
        <div id="itemTabs" class="col-md-12">
            <!-- set the tab titles -->
            <ul>
            <c:forEach var="legendaryPrimaryItem" items="${legendaryData.getPrimaryItems()}" varStatus="primaryItemStatus">
                <li><a href="#tab${primaryItemStatus}">${legendaryPrimaryItem.primaryItem.name}</a></li>
            </c:forEach>
            </ul>

            <!-- set the tab contents -->
            <c:set var="counter2" value="0" scope="page" />
            <c:forEach var="legendaryPrimaryItem" items="${legendaryData.getPrimaryItems()}" varStatus="primaryItemStatus">
                <c:forEach var="userPrimaryItem" items="${userData.getUserPrimaryItems()}">
                    <c:if test="${userPrimaryItem.legendaryPrimaryItem.id == legendaryPrimaryItem.id}">
                        <c:set var="userLegendaryPrimaryItem" value="${userPrimaryItem}" scope="page" />
                    </c:if>
                </c:forEach>
                <c:set var="counter2" value="${counter2 + 1}" scope="page" />
                <div id="tab${counter2}">
                    <h2>${userPrimaryItem.getPrimaryItem.name}</h2>
                    <table class="table table-dark">
                        <thead>
                            <tr>
                                <td>Name</td>
                                <td>Quantity</td>
                                <td>Description</td>
                                <td>Estimated Cost</td>
                                <td><!-- Consider putting a complete all button here --></td>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="task" items="${legendaryPrimaryItem.getTasks()}" varStatus="taskStatus">
                            <tr>
                                <td>${task.name}</td>
                                <td>${task.quantity}</td>
                                <td>${task.description}</td>
                                <td>Cost!!!OH NOOO</td>
                                <td>
                                    <c:set var="userTask" value="${userLegendaryPrimaryItem.getUserTasks().get(taskStatus.index)}" />
                                    <a href="/legendsmith/taskMarkOff?userTaskId=${userTask.id}&legendaryId=${legendaryData.id}&taskId=${task.id}">
                                    <c:choose>
                                        <c:when test="${userTask == null || userTask.completion == 0}">
                                            <button class="btn btn-sm btn-dark">Done</button>
                                        </c:when>
                                        <c:when test="${userTask.completion == 1}">
                                            <button class="btn btn-sm btn-dark">Reset</button>
                                        </c:when>
                                        <c:otherwise>
                                            <button class="btn btn-sm btn-dark">Done</button>
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>


                    </table>
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

    $( "#progressBar" ).progressbar({
        value: false
    });

</script>


</html>
