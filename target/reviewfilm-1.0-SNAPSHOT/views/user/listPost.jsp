<%--
  Created by IntelliJ IDEA.
  User: VuKhanh
  Date: 10/12/2020
  Time: 2:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List Posts - Review Film</title>
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
                                <div class="dropdown-menu" style="right: 0;left: inherit;"
                                     aria-labelledby="navbarDropdownMenuLink">
                                    <c:url var="profile" value="/profile">
                                        <c:param name="id" value="${sessionScope.LOGIN.id}"/>
                                    </c:url>
                                    <c:if test="${sessionScope.LOGIN.role.id == 1}">
                                        <a class="dropdown-item" href="<c:url value="/admin-manage-post"/>">Admin Manage
                                            Post</a>
                                    </c:if>
                                    <c:if test="${sessionScope.LOGIN.role.id == 1}">
                                        <a class="dropdown-item" href="<c:url value="/admin-manage-user"/>">Admin Manage
                                            User</a>
                                    </c:if>
                                    <a class="dropdown-item" href="${profile}">Profile</a>
                                    <a class="dropdown-item" href="<c:url value="/list-posts"/>">Your Posts</a>
                                    <a class="dropdown-item" href="<c:url value="/list-interacted"/>">Your Interacted</a>
                                    <a class="dropdown-item" href="<c:url value="/change-password"/>">Change
                                        Password</a>
                                    <a class="dropdown-item" href="<c:url value="/logout"/>">Log Out</a>
                                </div>
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <c:if test="${not empty sessionScope.LOGIN.avatar}">
                                        <img src="<c:out value="${sessionScope.LOGIN.avatar}"/>" width="40" height="40"
                                             class="rounded-circle">
                                    </c:if>
                                    <c:if test="${empty sessionScope.LOGIN.avatar}">
                                        <img src="<c:url value="/images/NoProfile.png"/>" width="40" height="40"
                                             class="rounded-circle">
                                    </c:if>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-list-4"
                            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
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
    <c:if test="${not empty message}">
        <c:if test="${message == 'success'}">
            <div class="alert alert-success alert-custom">
                SUCCESS
            </div>
        </c:if>
        <c:if test="${message == 'fail'}">
            <div class="alert alert-danger alert-custom">
                Fail
            </div>
        </c:if>
    </c:if>
    <div class="row mb-5">
        <a href="<c:url value="/home?page=1&limit=5"/>" class="cbutton cbutton--blue cbutton--big">List Posts</a>
    </div>
    <p class="heading-primary">Your Activity</p>
    <div class="information">
        <div class="row">
            <div class="col-12">
                <div class="tabs">
                    <input id="tab-1" type="radio" name="radio-set" class="tab-selector-1" checked="checked"/>
                    <label for="tab-1" class="tab-label-1">Your Posts</label>
                    <div class="clear-shadow"></div>
                    <div class="content" style="height: 60rem">
                        <div class="content-1">
                            <a id="btnDelete" style="float:right; margin:3rem 0" class="cbutton cbutton--red cbutton--small">DELETE</a>
                            <table class="table table-bordered u-center-text">
                                <thead>
                                <tr class="d-flex table-secondary">
                                    <th class="col-1"><input type="checkbox" id="checkAll"></th>
                                    <th class="col-1" scope="col">ID Post</th>
                                    <th class="col-6" scope="col">Title</th>
                                    <th class="col-2" scope="col">Status</th>
                                    <th class="col-2" scope="col">Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="item" items="${yourposts}">
                                    <tr class="d-flex">
                                        <td class="col-1"><input type="checkbox" name="cb" id="checkbox_${item.id}"
                                                   value="${item.id}"></td>
                                        <th class="col-1">${item.id}</th>
                                        <td class="col-6">${item.title}</td>
                                        <c:if test="${item.status == 1}">
                                            <td class="col-2 u-color-green u-bold">Approved</td>
                                        </c:if>
                                        <c:if test="${item.status == 0}">
                                            <td class="col-2 u-color-yellow u-bold">Pending</td>
                                        </c:if>
                                        <td class="col-2">
                                            <c:url var="post" value="/post">
                                                <c:param name="id" value="${item.id}"/>
                                            </c:url>
                                            <a href="${post}" class="cbutton cbutton--blue cbutton--small">EDIT</a>
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
<script src="<c:url value="/js/checkbox.js"/>"></script>
<script type="text/javascript">


    // Pagination your post
    var totalPage = ${totalPage};
    var currentPage = ${currentPage};

    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPage,
            visiblePages: 5,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage != page) {
                    window.location = '<c:url value="/list-posts"/>' + "?page=" + page;
                }
            }
        });
    });

    $("#btnDelete").click(function() {
        var data = {};
        var ids = $('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['ids'] = ids;
        deletePost(data);
    });

    function deletePost(data) {
        if (data['ids'] !=""){
            $.ajax({
                url: '<c:url value="/api-post"/>',
                type: 'DELETE',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function (result) {
                    window.location.href = "<c:url value="/list-posts"/>" + "?message=success";
                },
                error: function (error) {
                    window.location.href = "<c:url value="/list-posts"/>" + "?message=fail";
                }
            });
        }
    }
</script>
<script src="<c:url value="/js/pagination.js"/>"></script>
</body>
</html>
