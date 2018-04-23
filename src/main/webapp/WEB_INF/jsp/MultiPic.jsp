<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>

<html>
<head>
    <title>COUNTS</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="css/main.css" />
</head>

<body class="index" onload="load(${requestScope.picNum},${requestScope.url},${requestScope.collection},${requestScope.userCategory},${requestScope.Tagable})">
<div id="page-wrapper">
    <header id="header">
        <h1 id="logo"><a href="index.html">COUNTS <span>Social Engineers</span></a></h1>
        <nav id="nav">
            <ul>
                <li class="current"><a href="index.html">${requestScope.userName}</a></li>
                <li class="submenu" id="submenu">
                    <a href="/personal.html" onclick="addMissionToUser(${requestScope.collection},${requestScope.userPhone})">加入我的任务集</a>
                </li>
                <li><a href="#" class="button special">登出</a></li>
            </ul>
        </nav>
    </header>
</div>


<!-- Main -->
<article id="main">

    <header class="special container">
        <span class="icon fa-bar-chart-o"></span>
        <h2>当前任务集--<strong id="strong">${requestScope.collection}</strong></h2>
            <br />
    </header>



    <!-- Details -->
    <section class="wrapper style1 container special" id="pictureSection">

    </section>

    <p id="info"></p>

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
        <li>&copy; Social Engineers</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
    </ul>

</footer>

<!-- Scripts -->
<script src="js/multipic.js"></script>
<script src="js/jquery.min.js"></script>
<script src="js/jquery.dropotron.min.js"></script>
<script src="js/jquery.scrolly.min.js"></script>
<script src="js/jquery.scrollgress.min.js"></script>
<script src="js/skel.min.js"></script>
<script src="js/util.js"></script>
<script src="js/main.js"></script>
</body>
</html>