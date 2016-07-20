<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>DVD Library</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/rainbow.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">
            <h1 class="rainbow">DVD Library</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayDvdLibraryNoAjax">NoAjaxHome</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/newDvdFormNoAjax">Add New DVD</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/home">AjaxHome</a></li>
                </ul>    
            </div>

            <div class="container">
                <h1 class="rainbow">Edit DVD Form:</h1>
                <c:if test="${dvd == null}">
                    <h1>I'm sorry. That DVD #${param.dvdId} does not exist.</h1>
                </c:if>

                <c:if test="${dvd != null}">
                    <sf:form action="editDvdNoAjax" method="POST" modelAttribute="dvd">
                        <sf:input class="form-control" type="text"  id="editTitle" path="title" placeholder="Title"/><br>
                        <sf:errors path="title"></sf:errors>
                        <sf:input class="form-control" type="text"  id="editReleaseDate"  path="releaseDate"  placeholder="Release Date"/><br>
                        <sf:errors path="releaseDate"></sf:errors>
                        <sf:input class="form-control" type="text"  id="editMpaaRating"   path="mpaaRating"   placeholder="MPAA Rating"/><br>
                        <sf:errors path="mpaaRating"></sf:errors>
                        <sf:input class="form-control" type="text" id="editDirector"     path="director"     placeholder="Director"/><br>
                        <sf:errors path="director"></sf:errors>
                        <sf:input class="form-control" type="text"   id="editStudio"     path="studio"     placeholder="Studio"/><br>
                        <sf:errors path="studio"></sf:errors>
                        <sf:hidden path="dvdId"/>
                        <button class="form-control"  type="submit" id="addDvd">Add New DVD</button>
                    </sf:form>
                </c:if>


            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
