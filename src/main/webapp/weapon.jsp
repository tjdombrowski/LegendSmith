<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="sections/head.jsp"%>
<style>
    .progressLabel {
        position: absolute;
        left: 50%;
        top: 4px;
        font-weight: bold;
    }
</style>
<body>
<%@include file="sections/menu.jsp"%>
<div class="container container2">
    <!-- Legendary -->
    <div class="row">
        <div class="legendary">
            <h3><img src="${legendaryData.pictureReference}" alt="${legendaryData.name}">&nbsp&nbsp${legendaryData.name}</h3>
            <div class="col-md-12">
                <div id="progressBar">
                    <div class="progressLabel"></div>
                </div>
            </div>
        </div>
    </div>

    <!-- Items -->
    <div class="row legendary-tabs">
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
                                    <a href="/legendsmith/legendarytask?userTaskId=${userTask.id}&legendaryId=${legendaryData.id}&taskId=${task.id}&tab=${primaryItemStatus.index}">
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

<c:choose>
    <c:when test="${tabNumber != null}">
        <c:set var="tabStatus" value="${tabNumber}" scope="page" />
    </c:when>
    <c:otherwise>
        <c:set var="tabStatus" value="0" scope="page" />
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${userLegendaryData != null}">
        <c:set var="progressStatus" value="${userLegendaryData.progress * 100}" scope="page" />
    </c:when>
    <c:otherwise>
        <c:set var="progressStatus" value="0" scope="page" />
    </c:otherwise>
</c:choose>
</div>
</body>

<!-- JQuery UI Script -->
<script>

    var tabStatus = ${tabStatus};
    var progressStatus = Math.round(${progressStatus});

    $( function() {
        $( "#itemTabs" ).tabs({
            active: tabStatus
            }

        );
    } );

    $( function() {
        var progressBar = $( "#progressBar" ),
            progressLabel = $( ".progressLabel" );


        progressBar.progressbar({
            value: progressStatus

        });

        progressLabel.text(progressBar.progressbar("value") + "%");
    } );
</script>


</html>
