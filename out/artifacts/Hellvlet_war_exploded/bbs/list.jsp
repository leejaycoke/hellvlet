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
            <small>게시판 목록</small>
        </h1>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <a href="/user/logout"><h4>로그아웃</h4></a>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <table class="table table-bordered">
                <thead>
                <th class="col-lg-1">번호</th>
                <th class="col-lg-2">사용자</th>
                <th class="col-lg-6">제목</th>
                <th class="col-lg-3">작성일</th>
                </thead>
                <tbody>
                <c:forEach var="post" items="${posts}">
                    <tr>
                        <td>${post.id}</td>
                        <td>${post.user.account}</td>
                        <td>${post.title}</td>
                        <td>${post.regDateStr}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <form action="/bbs/list" method="GET">
            <div class="col-lg-1">
                <select class="form-control" name="filter">
                    <option value="title">제목</option>
                    <option value="author">작성자</option>
                </select>
            </div>
            <div class="col-lg-3">
                <input type="text" class="form-control" name="q">
            </div>

            <div class="col-lg-1">
                <button type="submit" class="btn btn-default btn-block">검색</button>
            </div>
        </form>
        <div class="col-lg-7">
            <a href="/bbs/write">
                <button type="button" class="btn btn-primary pull-right">글쓰기</button>
            </a>
        </div>
    </div>
</div>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
