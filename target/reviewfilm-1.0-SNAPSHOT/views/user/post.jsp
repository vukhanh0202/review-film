<%--
  Created by IntelliJ IDEA.
  User: VuKhanh
  Date: 10/8/2020
  Time: 3:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="createPostAPI" value="/api-post"/>
<c:url var="listPosts" value="/list-posts"/>
<c:url var="createPost" value="/post"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Post - Review Film</title>
    <link href="<c:url value='/css/style.css'/>" rel="stylesheet">
    <link rel="shortcut icon" href="<c:url value="/images/logo.ico"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
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
<div class="create-post-wrap">
    <c:if test="${not empty message}">
        <c:if test="${message == 'fail_insert'}">
            <div class="alert alert-danger alert-custom">
                FAIL TO CREATE NEW POST
            </div>
        </c:if>
        <c:if test="${message == 'fail_edit'}">
            <div class="alert alert-danger alert-custom">
                FAIL TO EDIT POST
            </div>
        </c:if>
        <c:if test="${message == 'success'}">
            <div class="alert alert-success alert-custom">
                SUCCESS
            </div>
        </c:if>
    </c:if>
    <div class="row mb-5">
        <a href="<c:url value='/home?page=1&limit=5'/>" class="cbutton cbutton--blue cbutton--big">List Posts</a>
    </div>
    <div class="row">
        <div class="col-12">
            <div class="new-post">
                <c:if test="${post.id != null}">
                    <div class="new-post__title">
                        Edit post
                    </div>
                </c:if>
                <c:if test="${post.id == null}">
                    <div class="new-post__title">
                        Create New Post
                    </div>
                </c:if>
                <form id="createPostForm" class="new-post__form validate-form">
                    <label class="new-post__label" for="title">Title</label>
                    <div class="validate-input"
                         data-validate="Title is required">
                        <input class="input" type="text" id="title" name="title" autocomplete="off"
                               value="${post.title}">
                    </div>
                    <label class="new-post__label" for="filmName">Film Name</label>
                    <div class="validate-input"
                         data-validate="Film Name is required">
                        <input class="input" type="text" id="filmName" name="filmName" autocomplete="off"
                               value="${post.filmName}">
                    </div>

                    <label class="new-post__label">Rate</label>
                    <div class="select">
                        <select id="standard-select">
                            <c:forEach begin="0" end="10" varStatus="loop">
                                <option value="${loop.index}"
                                        <c:if test="${loop.index == post.postRate}">selected="selected"</c:if>>
                                        ${loop.index}
                                </option>
                            </c:forEach>
                        </select>
                        <span class="focus"></span>
                    </div>
                    <label class="new-post__label" for="postReview">Review</label>
                    <div class="validate-input"
                         data-validate="Review is required">
                        <textarea class="input" id="postReview" name="postReview">${post.postReview}</textarea>
                    </div>

                </form>
                <div class="new-post__btn">
                    <a href="<c:url value="/post"/>" class="cbutton cbutton--blue cbutton--small">Reset</a>
                    <a id="createPost" class="cbutton cbutton--green cbutton--small">Save</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.ckeditor.com/4.15.0/standard/ckeditor.js"></script>
<script src="<c:url value="/js/validate.js"/>"></script>
<script type="text/javascript">

    var editor = "";
    $(document).ready(function () {
        editor = CKEDITOR.replace('postReview')
    });

    $('#createPost').click(function (e) {
        var input = $('.validate-input .input');

        var check = true;

        for (var i = 0; i < input.length; i++) {
            if ($(input[i]).attr('name') == 'postReview') {
                if (editor.getData() == "") {
                    showValidate(input[i]);
                    check = false;
                }
            } else if (validate(input[i]) == false) {
                showValidate(input[i]);
                check = false;
            }
        }
        if (check === true) {
            e.preventDefault();
            var data = {};
            var formData = $('#createPostForm').serializeArray();

            data["postRate"] = document.getElementById("standard-select").value;

            $.each(formData, function (i, v) {
                data["" + v.name + ""] = v.value;
            });

            data["postReview"] = editor.getData();
            var id = '${post.id}';
            if (id == "") {
                addNewPost(data);
            } else {
                data["id"] = id;
                editPost(data);
            }
        }

    });

    function addNewPost(data) {
        $.ajax({
            url: '${createPostAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${listPosts}?message=success";
            },
            error: function (error) {
                window.location.href = "${createPost}?message=fail_insert";
            }
        });
    }

    function editPost(data) {
        $.ajax({
            url: '${createPostAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${createPost}?id=" + result.id + "&message=success";
            },
            error: function (error) {
                window.location.href = "${createPost}?id=" + error.id + "&message=fail_edit";
            }
        });
    }
</script>
</body>
</html>
