<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="sections/head.jsp"%>

<body>
    <!-- Menu here -->
    <%@include file="sections/menu.jsp"%>
    <div class="container">
        <h3>Welcome</h3>
        <!--Intro -->
        <div class="row">
            <div class="col-md-12">
                Ready to start (or finish) your legendary weapon? You can search through and find any legendary here.
                All you need to do to start working on a legendary is start marking off tasks and your progress will be
                tracked.
            </div>
        </div>


        <div class="row">
            <!-- An overview of tasks is displayed here. -->
            <div class="col-sm-5">
                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Alias asperiores, autem consequuntur dolore ea illum incidunt magni minima minus nemo nesciunt nihil obcaecati porro reiciendis reprehenderit sed veritatis voluptas voluptatem?
            </div>

            <div class="col-sm-5">
                <table id="dataTable" class="display">
                    <thead>
                        <tr>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="legendary" items="${legendaries}">
                            <tr>
                                <!-- TODO picture should link to the page -->
                                <td>
                                    <a href="/legendsmith/legendary?legendaryId=${legendary.id}">
                                        <img src="${legendary.pictureReference}" alt="${legendary.name} tooltip="${legendary.name}" >
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</body>

<script>
    $(document).ready( function () {
        $('#dataTable').DataTable();
    } );
</script>

</html>
