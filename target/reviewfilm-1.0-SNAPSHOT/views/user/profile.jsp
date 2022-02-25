<%--
  Created by IntelliJ IDEA.
  User: VuKhanh
  Date: 10/13/2020
  Time: 10:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIRegister" value="/api-user"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile - Review Film</title>
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
                                    <a class="dropdown-item" href="<c:url value="/change-password"/>">Change Password</a>
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
<div class="information-wrap">
    <c:if test="${message == 'fail_update'}">
        <div class="alert alert-danger alert-custom">
            FAIL TO UPDATE PROFILE
        </div>
    </c:if>
    <c:if test="${message == 'success'}">
        <div class="alert alert-success alert-custom">
            UPDATE PROFILE SUCCESS
        </div>
    </c:if>
    <c:if test="${message == 'not_permission'}">
        <div class="alert alert-danger alert-custom">
            You are not permission for this action
        </div>
    </c:if>
    <div class="row mb-5">
        <a href="<c:url value="/home?page=1&limit=5"/>" class="cbutton cbutton--blue cbutton--big">List Posts</a>
    </div>
    <p class="heading-primary">Your Profile</p>
    <div class="information">
        <div class="row">
            <div class="col-4">
                <div class="information__avatar image-preview" id="imagePreview">
                    <c:if test="${empty profileuser.avatar}">
                        <img src="/images/NoProfile.png" alt="Image Preview"
                             class="image-preview-image">
                        <span class="image-preview-default-text"
                              style="display: flex;align-items: center; justify-content: center;font-weight: bold; color: #CCCCCC">Image Preview</span>
                    </c:if>
                    <c:if test="${not empty profileuser.avatar}">
                        <img src="<c:out value="${profileuser.avatar}"/>" alt="Image Preview"
                             class="image-preview-image" style="display: block;width: 100%">
                        <span class="image-preview-default-text"
                              style="display: none;align-items: center; justify-content: center;font-weight: bold; color: #CCCCCC">Image Preview</span>
                    </c:if>

                    <input type="file" id="InputImages" class="input"/>
                    <%--<img src="<c:url value="/images/bgLogin.jpg"/>" alt="">
                    <a href="index.html" class="cbutton cbutton--blue cbutton--medium">Change Avatar</a>--%>
                </div>
            </div>
            <div class="col-8">
                <form id="informationForm" class="information__info validate-form">
                    <label for="name">Full Name</label>
                    <div class="validate-input"
                         data-validate="Fullname is required">
                        <input class="input input--big" type="text" id="name" name="fullname" autocomplete="off"
                               value="${profileuser.fullname}">
                    </div>

                    <label for="email">Email</label>
                    <div class="validate-input" data-validate="Valid email is required: abc@xyz.com">
                        <input class="input input--big" type="text" id="email" name="email"
                               value="${profileuser.email}" autocomplete="off">
                    </div>

                    <label for="dob">Date of Birth</label>
                    <div class="validate-input" data-validate="Date of Birth is required">
                        <input class="input input--big" type="date" id="dob" name="dateOfBirth"
                               value="${profileuser.dateOfBirth}">
                    </div>


                    <label for="phone">Phone</label>
                    <div class="validate-input" data-validate="Valid phone is required: example +84123456789 or 0123456789">
                        <input class="input input--big" type="text" id="phone" name="phone" value="${profileuser.phone}"
                        autocomplete="off">
                    </div>

                    <label for="phone" class="mt-5">Status: </label>
                    <c:if test="${profileuser.status == 1}">
                        <label for="phone" class="u-color-green u-bold">ACTIVE</label>
                    </c:if>
                    <c:if test="${profileuser.status == 0}">
                        <label for="phone" class="u-color-yellow u-bold">BLOCK</label>
                    </c:if>
                    <c:if test="${profileuser.status == -1}">
                        <label for="phone" class="u-color-red u-bold">BAN</label>
                    </c:if>
                    <div class="information__btn">
                        <c:url var="profile_user" value="/profile">
                            <c:param name="id" value="${profileuser.id}"/>
                        </c:url>
                        <a href="${profile_user}" class="cbutton cbutton--blue cbutton--small">Reset</a>
                        <a id="saveProfile" class="cbutton cbutton--green cbutton--small">Save</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/js/validate.js"/>"></script>
<script type="text/javascript">

    const inpFile = document.getElementById("InputImages");
    const previewContainer = document.getElementById("imagePreview");
    const previewImage = previewContainer.querySelector(".image-preview-image");
    const previewDefaultText = previewContainer.querySelector(".image-preview-default-text");

    inpFile.addEventListener("change", function () {
        const file = this.files[0];

        if (file) {
            const reader = new FileReader();

            previewDefaultText.style.display = "none";
            previewImage.style.display = "block";

            reader.addEventListener("load", function () {
                previewImage.setAttribute("src", this.result);
            });

            reader.readAsDataURL(file);
        } else {
            previewDefaultText.style.display = null;
            previewImage.style.display = null;
            previewImage.setAttribute("src", "");
        }
    });


    $('#saveProfile').click(function (e) {
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
            var formData = $('#informationForm').serializeArray();
            data["id"] = '${profileuser.id}';

            $.each(formData, function (i, v) {
                data["" + v.name + ""] = v.value;
            });

            var files = $('#InputImages')[0].files[0];
            if (files !== undefined) {
                var reader = new FileReader();

                reader.onload = function (ev) {
                    data["avatar"] = ev.target.result;
                    updateProfile(data);
                };
                reader.readAsDataURL(files);
            } else {
                updateProfile(data);
            }
        }


    });

    function updateProfile(data) {
        $.ajax({
            url: '${APIRegister}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',

            success: function (result) {
                if (result == "{}"){
                    window.location.href = '<c:url value="/profile"/>' + "?id=" + ${profileuser.id} + "&message=not_permission";
                }else{
                    window.location.href = '<c:url value="/profile"/>' + "?id=" + result.id + "&message=success";
                }
            },
            error: function (error) {
                window.location.href = '<c:url value="/profile"/>' + "?id=" + error.id + "&message=fail_update";
            }
        });
    }


</script>
</body>
</html>
