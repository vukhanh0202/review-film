<%--
  Created by IntelliJ IDEA.
  User: VuKhanh
  Date: 10/9/2020
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="APIRegister" value="/api-user"/>
<c:url var="APIAdminPost" value="/admin-api-post"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Management - Review Film</title>
    <link href="<c:url value='/css/style.css'/>" rel="stylesheet">
    <link rel="shortcut icon" href="<c:url value="/images/logo.ico"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous">
    </script>
</head>
<body>
<!-- CSS heading wrap ở file home.scss -->
<div class="heading-wrap">
    <div class="row">
        <div class="col-8">
            <a href="<c:url value="/home?page=1&limit=5"/>">
                <img class="logo" src="<c:url value="/images/logo.png"/>" alt="">
            </a>
        </div>
        <div class="col-4">
            <c:if test="${sessionScope.LOGIN != null}">
                <nav class="navbar navbar-expand-sm profile-nav">
                    <div class="collapse navbar-collapse" id="navbar-list-4">
                        <ul class="navbar-nav">
                            <li class="nav-item dropdown">
                                <div class="dropdown-menu" style="right: 0;left: inherit;" aria-labelledby="navbarDropdownMenuLink">
                                    <c:url var="profile" value="/profile">
                                        <c:param name="id" value="${sessionScope.LOGIN.id}"/>
                                    </c:url>
                                    <c:if test="${sessionScope.LOGIN.role.id == 1}">
                                        <a class="dropdown-item" href="<c:url value="/admin-manage-post"/>">Admin Manage Post</a>
                                    </c:if>
                                    <c:if test="${sessionScope.LOGIN.role.id == 1}">
                                        <a class="dropdown-item" href="<c:url value="/admin-manage-user"/>">Admin Manage User</a>
                                    </c:if>
                                    <a class="dropdown-item" href="${profile}">Profile</a>
                                    <a class="dropdown-item" href="<c:url value="/list-posts"/>">Your Posts</a>
                                    <a class="dropdown-item" href="<c:url value="/list-interacted"/>">Your Interacted</a>
                                    <a class="dropdown-item" href="<c:url value="/change-password"/>">Change Password</a>
                                    <a class="dropdown-item" href="<c:url value="/logout"/>">Log Out</a>
                                </div>
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <c:if test="${not empty sessionScope.LOGIN.avatar}">
                                        <img src="<c:out value="${sessionScope.LOGIN.avatar}"/>" width="40" height="40" class="rounded-circle">
                                    </c:if>
                                    <c:if test="${empty sessionScope.LOGIN.avatar}">
                                        <img src="<c:url value="/images/NoProfile.png"/>" width="40" height="40" class="rounded-circle">
                                    </c:if>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-list-4" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                </nav>
            </c:if>
            <c:if test="${sessionScope.LOGIN == null}">
                <div class="navigation">
                    <a href="<c:url value='/login'/>" class="cbutton cbutton--blue cbutton--medium">Login</a>
                    <a href="<c:url value='/register'/>" class="cbutton cbutton--blue cbutton--small">Register</a>
                </div>
            </c:if>
        </div>
    </div>
</div>
<div class="listpost-wrap">
    <div class="row mb-5">
        <a href="<c:url value='/home?page=1&limit=5'/>" class="cbutton cbutton--blue cbutton--big">List Posts</a>
    </div>
    <p class="heading-primary">Admin management</p>
    <div class="information">
        <div class="row">
            <div class="col-12">
                <div class="tabs">
                    <input id="tab-1" type="radio" name="radio-set" class="tab-selector-1" checked="checked"/>
                    <label for="tab-1" class="tab-label-1">Post Management</label>
                    <div class="clear-shadow"></div>
                    <div class="content">
                        <div class="content-1">
                            <table class="table table-bordered u-center-text">
                                <thead>
                                <tr class="d-flex table-secondary">
                                    <th class="col-1" scope="col">ID</th>
                                    <th class="col-6" scope="col">Title</th>
                                    <th class="col-2" scope="col">Status</th>
                                    <th class="col-3" scope="col">Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="item" items="${posts}">
                                    <tr class="d-flex">
                                        <th class="col-1">${item.id}</th>
                                        <td class="col-6">${item.title}</td>
                                        <c:if test="${item.status == 1}">
                                            <td class="col-2 u-color-green u-bold">Approved</td>
                                        </c:if>
                                        <c:if test="${item.status == 0}">
                                            <td class="col-2">
                                                <a onclick="approvePost(${item.id}, 1)"
                                                   class="cbutton cbutton--yellow cbutton--small">Pending</a>
                                            </td>
                                        </c:if>
                                        <td class="col-3">
                                            <c:url var="detailPost" value="/detail-post">
                                                <c:param name="id" value="${item.id}"/>
                                            </c:url>
                                            <a href="${detailPost}"
                                               class="cbutton cbutton--blue cbutton--small">VIEW</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <div class="pagination-wrap">
                                <ul class="pagination" id="pagination"></ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/template/paging/jquery.twbsPagination.js"/>"></script>
<script type="text/javascript">

    // Post pending -> Approve
    function approvePost(id, status) {
        $.ajax({
            url: '${APIAdminPost}',
            type: 'POST',
            data: {"id": id, "status": status},
            success: function (result) {
                location.reload()
            },
            error: function (error) {
                location.reload()
            }
        });
    }

    // Pagination user management
    var totalPage = ${totalPage};
    var currentPage = ${currentPage};

    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPage,
            visiblePages: 5,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage != page) {
                    window.location = '<c:url value="/admin-manage-post"/>' + "?page=" + page;
                }
            }
        });
    });
</script>
<script src="<c:url value="/js/pagination.js"/>"></script>
</body>
</html>
