<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        a{text-decoration: none}
    </style>
</head>
<body style="text-align: center">
    <h2>main.jsp ( ${data} )</h2><br/>

    <h3>뷰(View)</h3>
    <a href="hello.html">스테이틱(static)</a><br/>
    <a href="main.do">제이에스피(jsp)</a><br/><br/>

    <h3>컨트롤러(Controller): 리턴방식</h3>
    <a href="template.do?na=진달래">탬플릿(template)</a><br/>
    <a href="string.do?na=사과">문자열(String)</a><br/>
    <a href="json.do?title=스프링&price=50000">제이슨(JSON)</a><br/>

    <h3>컨트롤러(Controller): Give&Take</h3>
    <a href="test">m00</a>
    <a href="test/base1">m01</a>
    <a href="test/base2">m02</a>
    <a href="test/base3">m03</a>
    <a href="test/param1?name=길동&age=33">m04</a>
    <a href="test/param2?na=순신&a=40">m05</a>
    <a href="test/param3?names=강감찬&names=이순신&names=유관순">m06</a>
    <a href="test/param4?ns=강감찬&ns=이순신&ns=유관순">m07</a>
    <a href="test/param5?names=강감찬&names=이순신&names=유관순">m08</a>
    <!--
    <a href="test/param6?list[0].name=홍길동&list[0].age=27&list[1].name=이순신&list[1].age=28">m09</a>
    [ -> %5B
    ] -> %5D
    -->
    <a href="test/param6?list%5B0%5D.name=홍길동&list%5B0%5D.age=27&list%5B1%5D.name=이순신&list%5B1%5D.age=28">m09</a>
    <a href="test/param7?name=이순신&page=10&age=30">m10</a>
    <a href="test/param8?subject=데이트&cdate=2025/03/17 15:28:30">m11</a>
    <a href="test/json1">m12</a>
    <a href="test/json2">m13</a>
</body>
</html>