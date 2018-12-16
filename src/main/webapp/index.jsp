<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="sections/head.jsp"%>

<body>
<%@include file="sections/menu.jsp"%>
<div class="container container2">


    <!--Intro -->
    <div class="row">
        <div class="col-md-8 section">
            <h3>Welcome</h3>
            Ready to start (or finish) your legendary weapon? You can search through and find any legendary here.
            All you need to do to start working on a legendary is start marking off tasks and your progress will be
            tracked.
        </div>
    </div>

    <div class="row legendary-grid">
        <c:forEach var="legendary" items="${legendaries}">
            <div class="col-sm-2 legendary-grid-item">
                <div>
                    <a href="/legendsmith/legendary?legendaryId=${legendary.id}">
                        <img src="${legendary.pictureReference}" alt="${legendary.name} tooltip="${legendary.name}" >
                    </a>
                </div>
                <div>
                    <a href="/legendsmith/legendary?legendaryId=${legendary.id}">
                            ${legendary.name}
                    </a>
                </div>
            </div>
        </c:forEach>
    </div>

</div>
</body>

</html>
