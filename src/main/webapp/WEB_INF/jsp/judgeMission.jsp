<%--
  Created by IntelliJ IDEA.
  User: Fortune
  Date: 2018/6/12
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>COUNTS</title>

    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <script src="js/tagTool.js"></script>
    <link rel="stylesheet" href="css/main.css" />
</head>
<body class="left-sidebar" onload="loadPic(${requestScope.url},${requestScope.userPhone}); loadTagType(${requestScope.tagType}, ${requestScope.missionLabel});">
<div id="page-wrapper">
    <!-- Header -->
    <header id="header">
        <h1 id="logo"><img src="images/logo.png" width="25" height="25"> COUNTS <span>by Social Engineers</span></a></h1>
        <nav id="nav" onload="loadPhoneNumber(${requestScope.phoneNumber})">
            <ul>
                <li class="submenu">
                    <a herf="#">Tag</a>
                    <ul>
                        <li><a id="save"><img src="images/save.png" width="20" height="20" align="center">SAVE</a></li>
                        <li><a id="back"><img src="images/back.png" width="20" height="20" align="center">BACK</a></li>
                        <li><a id="more"><img src="images/more.png" width="20" height="20" align="center">NEXT</a></li>
                    </ul>
                </li>
                <li class="current"><a href="<c:url value="personal.html?userName=${requestScope.userName}&phoneNumber=${requestScope.userPhone}&userCategory=${requestScope.userCategory}"/>">${requestScope.userName}</a></li>
                <li><a onclick="submitTagInfo()" class="button special">Submit</a></li>
                <li><a href="/" class="button special">Sign Out</a></li>
            </ul>
        </nav>
    </header>
</div>


<!-- Main -->
<article id="main">

    <header class="special container">
        <span class="icon fa-laptop"></span>
        <h2><strong>Tag Page</strong></h2>
        <p>Where the picture is taged.</p>
    </header>

    <!-- One -->
    <section class="wrapper style5 container">

        <div class="row 150%" id="edit_area">
            <div class="3u 12u(narrower)" align="center">
                <!-- Tag Info-->
                <div class="tag_info" id="textareaPlace" style="overflow: auto">
                    <!-- <textarea placeholder="请输入标注信息..." id="tagArea"></textarea> -->
                    <!-- <a href="#" class="button special">保存</a> -->
                </div>
            </div>
            <div class="9u 12u(narrower)">

                <!-- Content -->
                <div class="content" align="center">
                    <canvas id="canvasForSample" width="500" height="500">

                    </canvas>
                    <p id="info">Tag info.</p>
                </div>

            </div>
            <div class="10u 12u(narrower)" align="center" style="padding-top: 0px">
                <div class="scale" id="bar">
                    <div></div>
                    <span id="btn"></span>
                </div>
                <span id="title" style="font-weight: bold">分数：0</span>
            </div>
            <div class="2u 12u(narrower)" style="padding-top: 0px;"><a href="#" onclick="good()" class="button small2 " style="height: 50px;font-size: 0.7em;text-decoration: none;font-weight: bold" >确认</a></div>
        </div>
    </section>

    <!-- Two -->
    <section class="wrapper style1 container special">
        <div class="row">
            <div class="4u 12u(narrower)">

                <section>
                    <header>
                        <h3>This is Something</h3>
                    </header>
                    <p>tag tag tag</p>
                    <footer>
                        <ul class="buttons">
                            <li><a href="#" class="button small">Learn More</a></li>
                        </ul>
                    </footer>
                </section>

            </div>
            <div class="4u 12u(narrower)">

                <section>
                    <header>
                        <h3>Also Something</h3>
                    </header>
                    <p>tag tag tag</p>
                    <footer>
                        <ul class="buttons">
                            <li><a href="#" class="button small">Learn More</a></li>
                        </ul>
                    </footer>
                </section>

            </div>
            <div class="4u 12u(narrower)">

                <section>
                    <header>
                        <h3>Probably Something</h3>
                    </header>
                    <p>tag tag tag</p>
                    <footer>
                        <ul class="buttons">
                            <li><a href="#" class="button small">Learn More</a></li>
                        </ul>
                    </footer>
                </section>

            </div>
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
        <li>&copy; Social Engineers</li>
    </ul>

</footer>
<!-- Scripts -->
<script src="js/missionDetails.js"></script>
<script src="js/jquery.min.js"></script>
<script src="js/jquery.dropotron.min.js"></script>
<script src="js/jquery.scrolly.min.js"></script>
<script src="js/jquery.scrollgress.min.js"></script>
<script src="js/skel.min.js"></script>
<script src="js/util.js"></script>

<!--[if lte IE 8]><script src="js2/ie/respond.min.js2"></script><![endif]-->
<script src="js/main.js"></script>
</body>
</html>
