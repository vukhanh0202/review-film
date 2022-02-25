<%--
  Created by IntelliJ IDEA.
  User: VuKhanh
  Date: 10/16/2020
  Time: 10:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Change Password - Review Film</title>
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
<div class="wrapper">
    <c:if test="${not empty message}">
        <c:if test="${message == 'success'}">
            <div class="alert alert-success alert-custom">
                Success
            </div>
        </c:if>
        <c:if test="${message == 'fail'}">
            <div class="alert alert-danger alert-custom">
                Change password fail. Please try again
            </div>
        </c:if>
    </c:if>
    <div id="formContent"  >
        <!-- Logo -->
        <div class="logo-wrap">
            <a href="<c:url value="/home?page=1&limit=5"/>">
                <img class="logo" src="<c:url value="/images/logo.png"/>" alt="">
            </a>
        </div>
        <!-- Tabs Titles -->
        <!-- Icon -->
        <div class="title">
            <h2 class="h3-heading-primary my-5">Change password</h2>
        </div>

        <!-- Login Form -->
        <form id="changePassword" class="validate-form">
            <div class="validate-input"
                 data-validate="Old Password is required">
                <input class="input" type="password" id="oldpassword" class="m-2" name="oldpassword"
                       placeholder="Old Password">
            </div>
            <div class="validate-input"
                 data-validate="New Password is required: At least 8 character length, 1 UPPERCASE character, 1 SPECIAL character, 1 NUMERALS (0-9)">
                <input class="input" type="password" id="password" class="m-2" name="password"
                       placeholder="New Password">
            </div>
            <div class="validate-input"
                 data-validate="New Password again not match">
                <input class="input" type="password" id="password-again" class="m-2" name="passwordAgain"
                       placeholder="New Password Again">
            </div>
            <div id="formFooter">
            </div>
            <a id="change" class="cbutton cbutton--blue cbutton--big">Change</a>
            <a href="<c:url value="/change-password"/>" class="cbutton cbutton--blue cbutton--medium">Reset</a>
        </form>
    </div>
</div>
<script src="<c:url value="/js/validate.js"/>"></script>
<script type="text/javascript">


    $('#change').click(function (e) {

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
            var formData = $('#changePassword').serializeArray();

            $.each(formData, function (i, v) {
                data["" + v.name + ""] = v.value;
            });

            changePassword(data);
        }
    });

    function changePassword(data) {
        $.ajax({
            url: '<c:url value="/api-user"/>',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                if (result == "{}"){
                    window.location.href = '<c:url value="/change-password"/>' + "?message=fail";
                }else{
                    window.location.href = '<c:url value="/change-password"/>' + "?message=success";
                }
            },
            error: function (error) {
                window.location.href = '<c:url value="/change-password"/>' + "?message=fail";
            }
        });
    }

</script>
</body>
</html>
