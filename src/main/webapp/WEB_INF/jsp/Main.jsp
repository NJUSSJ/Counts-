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
    </style>
</head>
<body class="no-sidebar" onload="loadMain(${requestScope.phoneNumber},${requestScope.userCategory})">
<div id="page-wrapper">

    <!-- Header -->
    <header id="header">
        <h1 id="logo"><a href="http://localhost:3141/">COUNTS <span>by Social Engineers</span></a></h1>
        <nav id="nav">
            <ul>
                <li class="current"><a>${requestScope.userName}</a></li>
                <li class="submenu">
                    <a href="#">Settings</a>
                    <ul>
                        <li><a href="<c:url value="personal.html"/>?userName=${requestScope.userName}&phoneNumber=${requestScope.phoneNumber}">Personal Info</a></li>
                        <li><a href="right-sidebar.html">Right Sidebar</a></li>
                        <li><a href="no-sidebar.html">No Sidebar</a></li>
                        <li><a href="contact.html">Contact</a></li>
                        <li class="submenu">
                            <a href="#">Submenu</a>
                            <ul>
                                <li><a href="#">Dolore Sed</a></li>
                                <li><a href="#">Consequat</a></li>
                                <li><a href="#">Lorem Magna</a></li>
                                <li><a href="#">Sed Magna</a></li>
                                <li><a href="#">Ipsum Nisl</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li><a href="http://localhost:3141/" class="button special">Sign Out</a></li>
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
            <h2>在线众包标注系统<strong>主界面</strong></h2>
            <p>登陆后请点击下方任意一个图片集开始进行标注</p>
        </header>

        <!-- One -->
        <section class="wrapper style4 container">

            <!-- Content -->
            <div class="content">
                <section>
                    <a href="#" class="image featured "><img src="http://api.dujin.org/bing/1366.php" alt="" /></a>
                    <header>
                        <h3>以下为一段说明文字</h3>
                    </header>
                    <p>众包标注众包标注众包标注众包标注众包标注众包标注众包标注众包标注众包标注众包标注众包标注众包标注众包标注众包标注众包标注众包标注众包标注众包标注</p>
                </section>
            </div>

        </section>

        <!-- Two -->
        <section class="wrapper style1 container special" id="collections">

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
            <li>&copy; Social Engineers</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
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