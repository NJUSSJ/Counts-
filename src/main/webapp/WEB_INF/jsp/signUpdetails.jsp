<%--
  Created by IntelliJ IDEA.
  User: Fortune
  Date: 2018/4/18
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<!--
Twenty by HTML5 UP
html5up.net | @ajlkn
Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
    <title>Counts by SOCIAL ENGINEER</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <!--[if lte IE 8]><script src="js/ie/html5shiv.js"></script><![endif]-->
    <link rel="stylesheet" href="css/main.css" />
    <!--[if lte IE 8]><link rel="stylesheet" href="css/ie8.css" /><![endif]-->
    <!--[if lte IE 9]><link rel="stylesheet" href="css/ie9.css" /><![endif]-->



</head>
<body class="contact" onload="load(${requestScope.number})">
<script>
    function load(Number) {
        document.getElementById("phoneNumber").value=Number;
    }
</script>
<div id="page-wrapper">

    <!-- Header -->
    <header id="header">
        <h1 id="logo"><img src="images/logo.png" width="25" height="25"> <a href="http://localhost:3141/">COUNTS <span>by Social Engineers</span></a></h1>
        <nav id="nav">
            <ul>
                <li class="current"><a href="#">Welcome</a></li>

                <li><a href="<c:url value="/"/>" class="button special">登录</a></li>
            </ul>
        </nav>
    </header>

    <!-- Main -->
    <article id="main">

        <header class="special container">
            <span class="icon fa-envelope"></span>
            <h2>用户信息</h2>
            <p>请正确填写用户信息以完成注册</p>
        </header>

        <!-- One -->
        <section class="wrapper style4 special container 50%">

            <!-- Content -->
            <div class="content">
                <form action="/setUser" method="post">
                    <div class="row">
                        <div class="12u">
                            <input  id="phoneNumber" type="text" name="phoneNumber"/>
                        </div>
                    </div>

                    <div class="row">
                        <div class="12u" align="left">
                            <input  id="userName" type="text" name="userName" placeholder="用户名" required="required"/>
                        </div>
                    </div>

                    <div class="row">
                        <div class="12u" align="left">
                            <input  id="passWord" type="password" name="passWord" placeholder="密码" required="required"/>
                        </div>
                    </div>

                    <div class="row">
                        <div class="12u" align="left">
                            <span class="datePicker">用户类型： </span>
                            <select name="category">
                                <option value="1">众包发起者</option>
                                <option value="2">众包工人</option>
                                <option value="3">系统管理员</option>
                            </select>
                        </div>
                    </div>


                    <div class="row">
                        <div class="12u">
                            <ul class="buttons">
                                <li><input type="submit" class="special" value="完成注册" id="submit"/></li>
                            </ul>
                        </div>
                    </div>
                </form>
            </div>

        </section>

    </article>

    <!-- Footer -->
    <footer id="footer">

        <ul class="icons">
            <li><a href="#" class="icon circle fa-twitter"><span class="label">Twitter</span></a></li>
            <li><a href="#" class="icon circle fa-facebook"><span class="label">Facebook</span></a></li>
            <li><a href="#" class="icon circle fa-google-plus"><span class="label">Google+</span></a></li>
            <li><a href="#" class="icon circle fa-github"><span class="label">Github</span></a></li>
            <li><a href="#" class="icon circle fa-dribbble"><span class="label">Dribbble</span></a></li>
        </ul>

        <ul class="copyright">
            <li>&copy; Social Engineer</li></li>
        </ul>

    </footer>

</div>

<!-- Scripts -->
<script src="js/jquery.min.js"></script>
<script src="js/jquery.dropotron.min.js"></script>
<script src="js/jquery.scrolly.min.js"></script>
<script src="js/jquery.scrollgress.min.js"></script>
<script src="js/skel.min.js"></script>
<script src="js/util.js"></script>
<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
<script src="js/main.js"></script>




</body>
</html>