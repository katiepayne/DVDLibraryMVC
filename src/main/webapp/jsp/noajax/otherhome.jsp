<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title class="rainbow">DVD Library</title>
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
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displayDvdLibraryNoAjax">NoAjaxHome</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/newDvdFormNoAjax">Add New DVD</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/home">AjaxHome</a></li>
                </ul>    
            </div>

            <c:forEach var="dvd" items="${dvdLibrary}">
                <div>
                    <!-- We are going to build a custom URL for deleting a dvd-->    
                    <!-- so the place (or url) it's going to go to is deleteDVD NoAjax
                            and giving that url a variable name of deleteDVD_url -->
                    <s:url value="deleteDvdNoAjax" var="deleteDvd_url">
                        <!-- we want that link to also transmit a parameter - like any param
                            in a form, it has a name, and it has a value. In this case, to delete
                            a url, we want to send over a parameter of a dvdId and have it
                            match the dvd id of the contact we are interating over...! -->
                        <s:param name="dvdId" value="${dvd.dvdId}" />
                    </s:url>

                    <!-- do it again, for an edit link! -->
                    <s:url value="bob" var ="editDvd_url">
                        <%--<s:url value="displayEditDvdFormNoAjax" var ="editDvd_url">--%>
                        <s:param name="dvdId" value="${dvd.dvdId}" />
                    </s:url>

                    <b>Title:</b> ${dvd.title}<br/>
                    <b>Release Date:</b> ${dvd.releaseDate} <br />
                    <b>MPAA Rating:</b> ${dvd.mpaaRating} <br />
                    <b>Director:</b> ${dvd.director} <br />
                    <b>Studio:</b> ${dvd.studio} <br />
                    <!-- -->
                    <a href="${deleteDvd_url}">Delete</a> | <a href="${editDvd_url}">Edit</a>
                </div>

                <hr/>


            </c:forEach>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
