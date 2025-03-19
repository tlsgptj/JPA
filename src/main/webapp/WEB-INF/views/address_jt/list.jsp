<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Title(with JDBC)</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 5px;
        }
        a { text-decoration:none }
    </style>
</head>

<body style="text-align:center">
<center>
    <h1>
        Address List with SpringBoot
    </h1>
    <a href='write.do'>입력</a><br/>
    <table border='1' cellpadding='7' cellspacing='2' width='50%'>
        <tr>
            <th>번호</th>
            <th>이름</th>
            <th>주소</th>
            <th>날짜</th>
            <th>삭제</th>
        </tr>
        <c:if test="${empty list}">
            <tr>
                <td align="center" colspan="5">데이터가 없음</td>
            </tr>
        </c:if>
        <c:forEach items="${list}" var="address">
            <tr>
                <td align='center'>${address.seq}</td>
                <td>${address.name}</td>
                <td>${address.addr}</td>
                <td>
                    <fmt:formatDate value="${address.rdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                <td align='center'><a href='del.do?seq=${address.seq}'>삭제</a></td>
            </tr>
        </c:forEach>

        <tr>
            <td align='center'>76</td>
            <td>123</td>
            <td>456</td>
            <td>2018-11-08</td>
            <td align='center'><a href='del?seq=76'>삭제</a></td>
        </tr>
    </table>
</center>
</body>
</html>