<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>게시판 목록</title>
    <meta charset="utf-8">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <div class="page-header">
        <h1>지옥자바
            <small>게시글 작성</small>
        </h1>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <form action="/bbs/write" method="POST">
                        <div class="form-group">
                            <label>작성자</label>
                            <p>${sessionScope.get("account")}</p>
                        </div>
                        <div class="form-group">
                            <label>제목</label>
                            <input type="text" class="form-control input-lg" name="title">
                        </div>
                        <div class="form-group">
                            <label>내용</label>
                            <textarea class="form-control input-lg" name="content"></textarea>
                        </div>
                        <button type="submit" class="btn btn-default btn-lg pull-right">완료</button>
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
