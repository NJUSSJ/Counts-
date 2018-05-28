<%--
  Created by IntelliJ IDEA.
  User: Fortune
  Date: 2018/5/27
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<body class="contact" onload="load(${requestScope.requestorNum})">

<div id="page-wrapper">

    <!-- Header -->
    <header id="header">
        <h1 id="logo"><img src="images/logo.png" width="25" height="25"> <a href="/">COUNTS <span>by Social Engineers</span></a></h1>
        <nav id="nav">
            <ul>
                <li class="current"><a href="/">Welcome</a></li>
                <li class="submenu">
                    <a href="#">Layouts</a>
                    <ul>
                        <li><a href="left-sidebar.html">Left Sidebar</a></li>
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
                <li><a href="/" class="button special">登出</a></li>
            </ul>
        </nav>
    </header>

    <!-- Main -->
    <article id="main">

        <header class="special container">
            <span class="icon fa-envelope"></span>
            <h2>任务结果投诉</h2>
            <p>请正确填写投诉信息</p>
        </header>

        <!-- One -->
        <section class="wrapper style4 special container 75%">

            <!-- Content -->
            <div class="content">
                <form action="/uploadFinish.html" method="post" id="formID">
                    <div class="row 50%" id="phoneNumber">
                        <div class="6u 12u(mobile)" >
                            <input type="text" name="phoneNumber" id="actualNumber" />
                        </div>
                    </div>

                    <style>
                        #phoneNumber{
                            display: none;
                        }
                    </style>
                    <div class="row 50%">
                        <div class="6u 12u(mobile)">
                            <input  id="name" type="text" name="name" placeholder="任务名称"/>
                        </div>
                    </div>

                    <div class="row 50%">
                        <div class="12u">
                            <textarea  id="description" name="despription" placeholder="投诉描述" rows="4"></textarea>
                        </div>
                    </div>
                    <div class="row">
                        <div class="12u">
                            <ul class="buttons">
                                <li><input type="button" class="special" value="发送投诉" id="submitButton"/></li>
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
            <li>&copy; Social Engineer</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
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
