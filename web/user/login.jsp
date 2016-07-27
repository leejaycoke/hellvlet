<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>로그인</title>
    <meta charset="utf-8">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

</head>
<body>

<div class="container">
    <div class="page-header">
        <h1>지옥자바
            <small>로그인</small>
        </h1>
    </div>
    <c:if test="${error != null}">
        <div class="row">
            <div class="col-lg-12">
                <div class="alert alert-danger">${error}</div>
            </div>
        </div>
    </c:if>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <form action="/user/login" method="POST">
                        <div class="form-group">
                            <label>아이디</label>
                            <input type="text" class="form-control input-lg" name="account"
                                   <c:if test="${account != null}">value="${account}"</c:if>>
                        </div>
                        <div class="form-group">
                            <label>비밀번호</label>
                            <input type="password" class="form-control input-lg" name="password">
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="is_remember" <c:if test="${account != null}">checked</c:if>> 아이디 저장
                            </label>
                        </div>

                        <button type="submit" class="btn btn-success btn-lg btn-block">로그인</button>
                    </form>
                </div>
            </div>
            <div class="pull-right"><a href="/user/register"><h4>회원가입</h4></a></div>
        </div>
    </div>
</div>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
