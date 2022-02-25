<%--
  Created by IntelliJ IDEA.
  User: VuKhanh
  Date: 10/7/2020
  Time: 2:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Review Film</title>
    <link href="<c:url value='/css/style.css'/>" rel="stylesheet">
    <link rel="shortcut icon" href="<c:url value="/images/logo.ico"/>"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous">
    </script>

</head>
<body>
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
                                        <a class="dropdown-item" href="<c:url value="/admin-manage-post"/>">Admin Manage Post</a>
                                    </c:if>
                                    <c:if test="${sessionScope.LOGIN.role.id == 1}">
                                        <a class="dropdown-item" href="<c:url value="/admin-manage-user"/>">Admin Manage User</a>
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
<div class="container-fluid">
    <c:if test="${message == 'account-block'}">
        <div class="alert alert-danger alert-custom">
            Account was blocked. Not access this page
        </div>
    </c:if>
    <div class="row">
        <a href="<c:url value="/post"/>" class="cbutton cbutton--blue cbutton--medium">Create New Post</a>
    </div>
    <div class="row">
        <div class="col-8 content-wrap">
            <!-- Toolbar -->
            <!-- Search-->
            <div class="content-wrap--left">
                <input class="input" onkeypress="if(event.key === 'Enter') search()" type="text" id="search-input"
                       class="m-2" name="search" placeholder="Search" value="${search}">
            </div>
            <!-- Filter -->
            <div class="content-wrap--right">
                <div class="select">
                    <select id="standard-select" onchange="filterHome(this)">
                        <option value="date-asc" <c:if test="${sort == 'date-asc'}">selected="selected"</c:if>>Sort by
                            Date increase <span class="u-color-green u-bold">&uarr;</span>
                        </option>
                        <option value="date-desc" <c:if test="${sort == 'date-desc'}">selected="selected"</c:if>>Sort by
                            Date decrease <span class="u-color-green u-bold">&darr;</span>
                        </option>
                        <option value="like-asc" <c:if test="${sort == 'like-asc'}">selected="selected"</c:if>>Sort by
                            Like increase <span class="u-color-green u-bold">&uarr;</span>
                        </option>
                        <option value="like-desc" <c:if test="${sort == 'like-desc'}">selected="selected"</c:if>>Sort by
                            Like decrease <span class="u-color-green u-bold">&darr;</span>
                        </option>
                        <option value="dislike-asc" <c:if test="${sort == 'dislike-asc'}">selected="selected"</c:if>>
                            Sort by Dislike increase <span
                                class="u-color-green u-bold">&uarr;</span></option>
                        <option value="dislike-desc" <c:if test="${sort == 'dislike-desc'}">selected="selected"</c:if>>
                            Sort by Dislike decrease <span
                                class="u-color-green u-bold">&darr;</span></option>
                    </select>
                    <span class="focus"></span>
                </div>
            </div>
            <div class="content-collapse">
                <c:forEach var="item" items="${post}">
                    <c:url var="detailPost" value="/detail-post">
                        <c:param name="id" value="${item.id}"/>
                    </c:url>
                    <a href="${detailPost}">
                        <div class="content-collapse__item">
                            <div class=" content-collapse__item--left">
                                <div class="content-collapse__item--avatar">
                                    <c:if test="${not empty item.user.avatar}">
                                        <img src="<c:out value="${item.user.avatar}"/>" alt="avatar">
                                    </c:if>
                                    <c:if test="${empty item.user.avatar}">
                                        <img src="<c:url value="/images/NoProfile.png"/>" alt="">
                                    </c:if>
                                </div>
                                <div class="content-collapse__item--textavatar">
                                        ${item.user.fullname}
                                </div>
                            </div>
                            <div class="content-collapse__item--right">
                                <div class="content-collapse__item--title">
                                    <span>#${item.id} </span><span>${item.title}</span>
                                </div>
                                <div class="content-collapse__item--subtitle">
                                    <p><span class="u-bold">Date Modified:</span> <span>${item.dateModified}</span></p>
                                    <p><span class="u-bold">Like:</span> <span
                                            class="u-color-green">${item.upvote}</span>
                                    </p>
                                    <p><span class="u-bold">Dislike:</span> <span
                                            class="u-color-red">${item.downvote}</span></p>
                                </div>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            </div>
            <div class="pagination-wrap">
                <ul class="pagination" id="pagination"></ul>
                <%-- <input type="hidden" value="" id="page" name="page"/>
                 <input type="hidden" value="" id="limit" name="limit"/>--%>
            </div>
        </div>
        <div class="col-4 rank-wrap">
            <div class="row">
                <div class="rank-wrap__title">
                    Top Reviewers
                </div>
                <div class="rank-reviewers">
                    <c:forEach var="item" items="${topReviewers}">
                        <div class="rank-reviewers__item">
                            <div class="rank-reviewers__item--left">
                                <div class="rank-reviewers__item--avatar">
                                    <c:if test="${not empty item.avatar}">
                                        <img src="<c:out value="${item.avatar}"/>" alt="">
                                    </c:if>
                                    <c:if test="${empty item.avatar}">
                                        <img src="<c:url value="/images/NoProfile.png"/>" alt="">
                                    </c:if>
                                </div>
                            </div>
                            <div class="rank-reviewers__item--right">
                                <div class="rank-reviewers__item--title">
                                        ${item.fullname}
                                </div>
                                <div class="rank-reviewers__item--subtitle">
                                    <p><span class="u-bold">Total Posts:</span> <span
                                            class="u-color-green">${item.quantityPost}</span>
                                    </p>
                                    <p><span class="u-bold">Total likes:</span> <span
                                            class="u-color-blue">${item.quantityUpvote}</span>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="row">
                <div class="rank-wrap__title">
                    Top Posts
                </div>
                <div class="rank-posts">
                    <c:forEach var="item" items="${topPosts}">
                        <c:url var="detailPostRank" value="/detail-post">
                            <c:param name="id" value="${item.id}"/>
                        </c:url>
                        <a href="${detailPostRank}" style="text-decoration: none; color: inherit;">
                            <div class="rank-posts__item">
                                <div class="rank-posts__item--title">
                                    <span>#${item.id}: </span><span>${item.title}</span>
                                </div>
                                <div class="rank-posts__item--subtitle">
                                    <p><span class="u-bold">Total Likes:</span> <span
                                            class="u-color-green">${item.upvote}</span></p>
                                </div>
                            </div>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/template/paging/jquery.twbsPagination.js"/>"></script>
<script>
    var totalPages = ${totalPage};
    var currentPage = ${currentPage};

    var urlParams = new URLSearchParams(window.location.search);
    var pageSort = urlParams.get('page');
    var limitSort = urlParams.get('limit');

    var searchText = '${search}';
    var limit = 5;

    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 5,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage != page) {
                    var sort = '${sort}';

                    if (sort == " ") {
                        if (searchText == "") {
                            window.location = '<c:url value="/home"/>' + "?page=" + page + "&limit=" + limit;
                        } else {
                            window.location = '<c:url value="/home"/>' + "?page=" + page + "&limit=" + limit + "&search=" + searchText;
                        }
                    } else {
                        if (searchText == "") {
                            window.location = '<c:url value="/home"/>' + "?page=" + page + "&limit=" + limit + "&sort=" + sort;
                        } else {
                            window.location = '<c:url value="/home"/>' + "?page=" + page + "&limit=" + limit + "&sort=" + sort + "&search=" + searchText;
                        }
                    }

                }
            }
        });
    });

    function filterHome(obj) {
        var sortValue = obj.value;
        if (pageSort == null) {
            pageSort = 1;
        }
        if (limitSort == null) {
            limitSort = 5;
        }
        if (searchText == null) {
            window.location = '<c:url value="/home"/>' + "?page=" + pageSort + "&limit=" + limitSort + "&sort=" + sortValue;
        } else {
            window.location = '<c:url value="/home"/>' + "?page=" + pageSort + "&limit=" + limitSort + "&sort=" + sortValue + "&search=" + searchText;
        }
    }

    // Search
    function search() {
        var searchText = $('#search-input').val();

        if (searchText == "") {
            window.location = '<c:url value="/home"/>' + "?page=" + 1 + "&limit=" + 5;
        } else {
            window.location = '<c:url value="/home"/>' + "?page=" + 1 + "&limit=" + 5 + "&search=" + searchText;
        }
    }

</script>/
<script src="<c:url value="/js/pagination.js"/>"></script>
</body>
</html>
