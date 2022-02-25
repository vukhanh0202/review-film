<%--
  Created by IntelliJ IDEA.
  User: VuKhanh
  Date: 10/10/2020
  Time: 4:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="createCommentAPI" value="/api-comment"/>
<c:url var="voteAPI" value="/api-vote"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detail Post - Review Film</title>
    <link href="<c:url value='/css/style.css'/>" rel="stylesheet">
    <link rel="shortcut icon" href="<c:url value="/images/logo.ico"/>"/>
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
          integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous">
    </script>
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/material-design-iconic-font/2.2.0/css/material-design-iconic-font.min.css">
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
                                    <a class="dropdown-item" href="<c:url value="/list-interacted"/>">Your
                                        Interacted</a>
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
<div class="detail-post-wrap">
    <div class="row mb-5">
        <a href="<c:url value='/home?page=1&limit=5'/>" class="cbutton cbutton--blue cbutton--big">List Posts</a>
    </div>
    <div class="row">
        <div class="col-3">
            <div class="box-avatar">
                <div class="box-avatar__avatar">
                    <c:if test="${not empty post.user.avatar}">
                        <img src="<c:out value="${post.user.avatar}"/>" alt="">
                    </c:if>
                    <c:if test="${empty post.user.avatar}">
                        <img src="<c:url value="/images/NoProfile.png"/>" alt="">
                    </c:if>
                </div>
                <div class="box-avatar__name">
                    ${post.user.fullname}
                </div>
                <div class="box-avatar__action">
                    <p><span class="u-bold">Total Posts:</span> <span
                            class="u-color-blue">${post.user.quantityPost}</span>
                    </p>
                    <p><span class="u-bold">Total Likes:</span> <span
                            class="u-color-green">${post.user.quantityUpvote}</span>
                    </p>
                </div>
            </div>
            <br>
            <c:if test="${sessionScope.LOGIN != null}">
                <div class="comment-box">
                    <c:if test="${message == 'fail'}">
                        <div class="alert alert-danger alert-custom">
                            Fail to comment
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.LOGIN.status == 0}">
                        <div class="alert alert-danger alert-custom" style="width:100%">
                            Can not comment
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.LOGIN.status != 0}">
                        <div class="comment-box__new validate-form">
                            <div class="validate-input"
                                 data-validate="Comment is required">
                                <input id="typeComment" style="margin-left:2rem" class="input" type="text" id="title"
                                       name="title" autocomplete="off" placeholder="Type to comment">
                            </div>

                            <a id="comment" class="cbutton cbutton--blue cbutton--small">Comment</a>
                        </div>
                    </c:if>
                    <c:forEach var="item" items="${comments}">
                        <div class="comment-box__item">
                            <div class="comment-box__item__logo">
                                <c:if test="${not empty item.user.avatar}">
                                    <img src="<c:out value="${item.user.avatar}"/>" alt="">
                                </c:if>
                                <c:if test="${empty item.user.avatar}">
                                    <img src="<c:url value="/images/NoProfile.png"/>" alt="">
                                </c:if>
                            </div>
                            <div class="comment-box__item__content">
                                <p><span class="u-bold">${item.user.fullname}</span> <span>${item.content}</span>
                                </p>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="pagination-wrap">
                        <ul class="pagination" id="pagination"></ul>
                    </div>
                </div>
            </c:if>
            <c:if test="${sessionScope.LOGIN == null}">
                <div class="comment-box comment-box--disable">
                    <a href="<c:url value="/login"/>" class="cbutton cbutton--blue cbutton--small">LOGIN</a>
                    <p>Sign in to comment</p>
                </div>
            </c:if>
        </div>
        <div class="col-9">
            <div class="box-detail-post">
                <div class="box-detail-post__heading">
                    ${post.title}
                </div>
                <div class="box-detail-post__row">
                    <div class="box-detail-post__row--left box-detail-post__row--bold">
                        Film Name
                    </div>
                    <div class="box-detail-post__row--right box-detail-post__row--thin">
                        ${post.filmName}
                    </div>
                </div>
                <div class="box-detail-post__row">
                    <div class="box-detail-post__row--left box-detail-post__row--bold">
                        Rate
                    </div>
                    <div class="box-detail-post__row--right box-detail-post__row--thin">
                        ${post.postRate}/10
                    </div>
                </div>
                <div class="box-detail-post__row">
                    <div class="box-detail-post__row--left box-detail-post__row--bold">
                        Review
                    </div>
                    <div class="box-detail-post__row--right box-detail-post__row--thin">
                        ${post.postReview}
                    </div>
                </div>
                <div class="box-detail-post__row">
                    <div class="box-detail-post__row--left">
                        <p><span class="u-bold">Like:</span> <span class="u-color-green">${post.upvote}</span>
                        </p>
                        <p><span class="u-bold">Dislike:</span> <span class="u-color-red">${post.downvote}</span>
                        </p>
                    </div>
                    <c:if test="${sessionScope.LOGIN != null}">
                        <div class="box-detail-post__row--right">
                            <div class="box-detail-post__row--icon">
                                <c:if test="${not empty vote}">
                                    <c:if test="${vote.actionVote == 1}">
                                        <i onclick="deleteVote(${post.id})" id="like"
                                           class="fas fa-thumbs-up u-color-green"><span class="badge">+1</span></i>
                                        <i onclick="vote(${post.id}, 0)" id="dislike" class="fas fa-thumbs-down"></i>
                                    </c:if>
                                    <c:if test="${vote.actionVote == 0}">
                                        <i onclick="vote(${post.id}, 1)" id="like" class="fas fa-thumbs-up"></i>
                                        <i onclick="deleteVote(${post.id})" id="dislike"
                                           class="fas fa-thumbs-down u-color-red"><span class="badge">+1</span></i>
                                    </c:if>
                                </c:if>
                                <c:if test="${empty vote}">
                                    <i onclick="vote(${post.id}, 1)" id="like" class="fas fa-thumbs-up"></i>
                                    <i onclick="vote(${post.id}, 0)" id="dislike" class="fas fa-thumbs-down"></i>
                                </c:if>

                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
        <%--<div class="col-3">


        </div>--%>
    </div>
</div>
<script src="<c:url value="/template/paging/jquery.twbsPagination.js"/>"></script>
<script src="<c:url value="/js/validate.js"/>"></script>
<script type="text/javascript">
    $('#comment').click(function (e) {
        var input = $('.validate-input .input');

        var check = true;

        for (var i = 0; i < input.length; i++) {
            if (validate(input[i]) == false) {
                showValidate(input[i]);
                check = false;
            }
        }
        if (check === true) {
            e.preventDefault();
            var data = {};
            data["content"] = $('#typeComment').val();
            data["post_id"] = ${post.id};
            data["user_id"] = ${sessionScope.LOGIN.id};
            addComment(data);
        }
    });

    // Comment
    function addComment(data) {
        $.ajax({
            url: '${createCommentAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                if (result == "{}"){
                    window.location.href = '<c:url value="/detail-post"/>' + "?id=${post.id}" + "&message=fail";
                }else{
                    location.reload()
                }
            },
            error: function (error) {
                window.location.href = '<c:url value="/detail-post"/>' + "?id=${post.id}" + "&message=fail";
            }
        });
    }

    // Vote (Like or dislike)
    function vote(idPost, statusVote) {
        $.ajax({
            url: '${voteAPI}',
            type: 'POST',
            data: {"idPost": idPost, "statusVote": statusVote},
            success: function (result) {
                location.reload();
            },
            error: function (error) {
                location.reload();
            }
        });
    }

    // Unvote
    function deleteVote(idPost) {
        $.ajax({
            url: '<c:url value="/api-vote"/>' + "?idpost=" + idPost,
            type: 'DELETE',
            success: function (result) {
                location.reload()
            },
            error: function (error) {
                location.reload()
            }
        });

    }

    // Pagination comment


    var totalPages = ${totalPage};
    var currentPage = ${currentPage};

    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 3,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage != page) {
                    window.location = '<c:url value="/detail-post"/>' + "?id=" + ${post.id} +"&page=" + page;
                }
            }
        });
    });


</script>
<script src="<c:url value="/js/pagination.js"/>"></script>
</body>
</html>
