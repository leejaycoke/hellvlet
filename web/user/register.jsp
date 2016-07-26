<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>회원가입</title>
    <meta charset="utf-8">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <div class="page-header">
        <h1>지옥자바
            <small>회원가입</small>
        </h1>
    </div>
    <c:choose>
        <c:when test="true == true">
            ...1
        </c:when>
        <c:when test="false == true">
            ...2
        </c:when>
        <c:otherwise>
            ...3
        </c:otherwise>
    </c:choose>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <form action="/user/register" method="POST">
                        <div class="form-group">
                            <label>아이디</label>
                            <input type="text" class="form-control input-lg" name="account">
                        </div>
                        <div class="form-group">
                            <label>비밀번호</label>
                            <input type="password" class="form-control input-lg" name="password">
                        </div>
                        <div class="form-group">
                            <label>휴대폰 번호</label>
                            <input type="text" class="form-control input-lg" name="phone">
                        </div>
                        <button type="submit" class="btn btn-success btn-lg btn-block">회원가입</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
