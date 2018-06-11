<%--
  Created by IntelliJ IDEA.
  User: Fortune
  Date: 2018/5/28
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>

<html>
<head>
    <title>众包标注系统主界面</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="css/main.css" />

    <style type="text/css">
        .collection{
            width: 400px;
            height: 260px;
        }
        .admin{
            font-size: large;
        }
    </style>
</head>
<body class="no-sidebar" onload="loadMain(${requestScope.phoneNumber},${requestScope.userCategory})">
<div id="page-wrapper">

    <!-- Header -->
    <header id="header">
        <h1 id="logo"><img src="images/logo.png" width="25" height="25"> COUNTS <span>by Social Engineers</span></h1>
        <nav id="nav">
            <ul>
                <li class="current"><a href="<c:url value="personal.html?userName=${requestScope.userName}&phoneNumber=${requestScope.phoneNumber}&userCategory=${requestScope.userCategory}"/>">${requestScope.userName}</a></li>
                <li class="submenu" id="extra">
                    <a id="menuTitle"></a>
                </li>
                <style type="text/css">
                    #extra{
                        display: none;
                    }
                </style>
                <li><a href="/" class="button special">登出</a></li>
            </ul>
        </nav>
    </header>

    <!-- Banner -->
    <section id="banner">

        <div class="inner">

            <header>
                <h2>COUNTS</h2>
            </header>
            <p>This is <strong>COUNTS</strong>, a Crowdsourcing
                <br />
                Tagging System
                <br />
                by SOCIAL ENGINEERS.</p>
            <footer>
                <ul class="buttons vertical">
                    <li><a href="#main" class="button fit scrolly">Tell Me More</a></li>
                </ul>
            </footer>

        </div>

    </section>


    <!-- Main -->
    <article id="main">

        <header class="special container">
            <span class="icon fa-user-md"></span>
            <h2>在线众包标注系统<strong>系统管理员界面</strong></h2>
        </header>

        <!-- One -->
        <section class="wrapper style4 container">
            <ul class="icons">
                <li><a href="#" class="button small">任务管理</a></li>
                <li><a href="#" class="button small">用户管理</a></li>
                <li><a href="#" class="button small">消息系统</a></li>
            </ul>
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
            <li>&copy; Social Engineers</li>
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
<!--[if lte IE 8]><script src="js2/ie/respond.min.js2"></script><![endif]-->
<script src="js/main.js"></script>
<script src="js/Maincollection.js"></script>

</body>
</html>