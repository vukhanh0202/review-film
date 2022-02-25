<%--
  Created by IntelliJ IDEA.
  User: VuKhanh
  Date: 10/8/2020
  Time: 5:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIRegisters" value="/api-user"/>
<c:url var="login" value="/login"/>
<c:url var="register" value="/register"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up - Review Film</title>
    <link href="<c:url value='/css/style.css'/>" rel="stylesheet">
    <link rel="shortcut icon" href="<c:url value="/images/logo.ico"/>"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/material-design-iconic-font/2.2.0/css/material-design-iconic-font.min.css">
</head>
<body>
<div class="wrapper">
    <c:if test="${not empty message}">
        <c:if test="${message == 'fail'}">
            <div class="alert alert-danger alert-custom">
                REGISTER FAIL
            </div>
        </c:if>
        <c:if test="${message == 'account_exist'}">
            <div class="alert alert-danger alert-custom">
                Username is exist. Please try again
            </div>
        </c:if>
    </c:if>
    <div id="formContent">
        <!-- Logo -->
        <div class="logo-wrap">
            <a href="<c:url value="/home?page=1&limit=5"/>">
                <img class="logo" src="<c:url value="/images/logo.png"/>" alt="">
            </a>
        </div>
        <!-- Tabs Titles -->
        <!-- Icon -->
        <div class="title">
            <h2 class="h3-heading-primary my-5">Login</h2>
        </div>
        <!-- Login Form -->
        <form id="registerForm" class="validate-form">
            <div class="validate-input"
                 data-validate="Valid username is required: Include NUMERALS or LOWERCASE character">
                <input class="input" type="text" id="username" class="m-2" name="username" placeholder="Username"
                       autocomplete="off">
            </div>
            <div class="validate-input"
                 data-validate="Password is required: At least 8 character length, 1 UPPERCASE character, 1 SPECIAL character, 1 NUMERALS (0-9)">
                <input class="input" type="password" id="password" class="m-2" name="password"
                       placeholder="Password">
            </div>
            <div class="validate-input" data-validate="Password again not match">
                <input class="input" type="password" id="passwordAgain" class="m-2" name="passwordAgain"
                       placeholder="Password Again">
            </div>
            <div class="validate-input" data-validate="Fullname is required">
                <input class="input" type="text" id="name" class="m-2" name="fullname" placeholder="Full Name"
                       autocomplete="off">
            </div>
            <div class="validate-input" data-validate="Valid email is required: abc@xyz.com">
                <input class="input" type="email" id="email" class="m-2" name="email" placeholder="Email"
                       autocomplete="off">
            </div>
            <div id="formFooter">
            </div>
        </form>
        <a id="registerNew" class="mb-5 cbutton cbutton--blue cbutton--big">Register</a>
        <h2 class="h3-heading-secondary ">You have an account ?</h2>
        <a href="<c:url value="/login"/>" class="cbutton cbutton--blue cbutton--big">Login</a>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous">
</script>
<script src="<c:url value="/js/validate.js"/>"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#registerNew').click(function (e) {

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
                var formData = $('#registerForm').serializeArray();
                $.each(formData, function (i, v) {
                    if (v.name !== "passwordAgain") {
                        data["" + v.name + ""] = v.value;
                    }
                });
                registers(data);
            }
        });

        function registers(data) {
            $.ajax({
                url: '${APIRegisters}',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(data),
                dataType: 'json',
                success: function (result) {
                    if (result == "{}"){
                        window.location.href = "${register}?message=account_exist";
                    }else{
                        window.location.href = "${login}?message=success";
                    }
                },
                error: function (error) {
                    window.location.href = "${register}?message=fail";
                }
            });
        }
    });

</script>
</body>
</html>
