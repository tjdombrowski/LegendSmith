<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="sections/head.jsp"%>

<body>
<!-- Menu here -->
<%@include file="sections/menu.jsp"%>
<div class="container">
    <h2>Hello World!</h2>
    <!--Intro -->
    <div class="row">
        <div class="col-md-12">Welcome to your dashboard. Here's how to use it!</div>
    </div>


    <div class="row">
        <!-- Place legendary info here, or a message to start one -->
        <div class="col-md-5">
        </div>
        <!-- Search field for legendaries -->
        <div class="col-md-5">
            <form action="searchLegendary" type="post">
                <input type="text" name="name">
                <input type="submit" value="Search" name="search">
            </form>
        </div>
    </div>

    <div class="row">
        <!-- An overview of tasks is displayed here. -->
        <div class="col-sm-5">
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Alias asperiores, autem consequuntur dolore ea illum incidunt magni minima minus nemo nesciunt nihil obcaecati porro reiciendis reprehenderit sed veritatis voluptas voluptatem?
        </div>
        <!-- Legendaries being worked on OR any being searched by the user are displayed here-->
        <div class="col-sm-5">
            <table class="table">
                <c:forEach var="legendary" items="${legendaries}">
                    <tr>
                        <!-- TODO picture (not name) should link to the page -->
                        <!-- TODO Add some form of checkbox for tracking -->
                        <td><a href="/legendary">${legendary.name}</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

</div>
</body>


</html>
