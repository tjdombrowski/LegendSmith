<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="sections/head.jsp"%>

<body>
<!-- Menu -->
<%@include file="sections/menu.jsp"%>
<div class="container">

    <!-- Legendary -->
    <div class="row legendary">
        <div class="col-md-6">
            <div class="col-md-12">
                <div class="weapon-img"><img src="${legendaryData.pictureReference}" alt="${legendaryData.name}"></div>
                <h3 class="legendary-name">${legendaryData.name}</h3>
            </div>
            <div class="col-md-12">
                <div id="progressBar">
                    Progress:
                    <c:choose>
                        <c:when test="${userLegendaryData != null}">${userLegendaryData.progress * 100}</c:when>
                        <c:when test="${userLegendaryData == null}">0</c:when>
                    </c:choose>
                    %
                </div>
            </div>
        </div>
    </div>

    <!-- Items -->
    <div class="row">
        <div id="itemTabs" class="col-md-12">
            <!-- set the tab titles -->
            <ul>
            <c:forEach var="legendaryPrimaryItem" items="${legendaryData.getPrimaryItems()}" varStatus="primaryItemStatus"><c:forEach var="userPrimaryItem" items="${userData.getUserPrimaryItems()}">
                <c:if test="${userPrimaryItem.legendaryPrimaryItem.id == legendaryPrimaryItem.id}">
                    <c:set var="userLegendaryPrimaryItem" value="${userPrimaryItem}" scope="page" />
                </c:if>
            </c:forEach>
                <li><a href="#tab${primaryItemStatus.index}">
                        ${legendaryPrimaryItem.primaryItem.name}
                        &emsp;
                        ${userLegendaryPrimaryItem.progress * 100}%
                </a></li>
            </c:forEach>
            </ul>

            <!-- set the tab contents -->
            <c:forEach var="legendaryPrimaryItem" items="${legendaryData.getPrimaryItems()}" varStatus="primaryItemStatus">
                <c:forEach var="userPrimaryItem" items="${userData.getUserPrimaryItems()}">
                    <c:if test="${userPrimaryItem.legendaryPrimaryItem.id == legendaryPrimaryItem.id}">
                        <c:set var="userLegendaryPrimaryItem" value="${userPrimaryItem}" scope="page" />
                    </c:if>
                </c:forEach>
                <div id="tab${primaryItemStatus.index}">
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
                                <td>
                                    <c:if test="${task.taskCost != null}">
                                        ${task.taskCost.goldPrice} Gold, ${task.taskCost.silverPrice} Silver, ${task.taskCost.copperPrice} Copper
                                    </c:if>
                                </td>
                                <td>
                                    <c:set var="userTask" value="${userLegendaryPrimaryItem.getUserTasks().get(taskStatus.index)}" />
                                    <a href="/legendsmith/legendarytask?userTaskId=${userTask.id}&legendaryId=${legendaryData.id}&taskId=${task.id}">
                                    <c:choose>
                                        <c:when test="${userTask == null || userTask.completion == 0}">
                                            <button class="btn btn-sm btn-dark">Done</button>
                                        </c:when>
                                        <c:when test="${userTask.completion == 1}">
                                            <button class="btn btn-sm btn-dark">Undo</button>
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
        $( "#itemTabs" ).tabs();
    } );

  /*  $( "#progressBar" ).progressbar({
        value: false
    });
    */
</script>


</html>
