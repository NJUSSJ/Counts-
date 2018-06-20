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
    <link rel="stylesheet" href="css/style.css" />
    <script src="js/Sign.js"></script>
    <script src="js/Message.js"></script>

    <style type="text/css">
        #collections{
            -moz-box-shadow: 3px 3px 4px #000;
            -webkit-box-shadow: 3px 3px 4px #000;
            box-shadow: 3px 3px 4px #000;
            /* For IE 8 */
            -ms-filter: "progid:DXImageTransform.Microsoft.Shadow(Strength=4, Direction=135, Color='#000000')";
            /* For IE 5.5 - 7 */
            filter: progid:DXImageTransform.Microsoft.Shadow(Strength=4, Direction=135, Color='#000000');
        }
        .collection{
            width: 400px;
            height: 247.2px;
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
                <li class="submenu">
                    <a>任务</a>
                    <ul>
                        <li><a onclick="sign(${phoneNumber})">签到</a></li>
                        <li><a href="<c:url value="personal.html?userName=${requestScope.userName}&phoneNumber=${requestScope.phoneNumber}&userCategory=${requestScope.userCategory}#message" />" >消息</a></li>
                    </ul>
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
                    <li><a href="#search" class="button fit scrolly">Tell Me More</a></li>
                </ul>
            </footer>

        </div>

    </section>


    <!-- Main -->
    <article id="main">
        <!-- Two -->
        <section class="wrapper style4 container special" style="padding-top: 0px; margin-top: 3px" >
            <section style="margin-bottom: 3px;">
                <div class="search d1" id="search" style="background-color: #ffffff;">
                    <a onclick="refreshMission(${requestScope.phoneNumber}, ${requestScope.userCategory})" style="padding-right: 3px; float: right"><img src="images/refresh.png" width="30" height="30"></a>
                    <form style="margin-left: 80px">
                                <select id="searchType" style="border-radius: 0.3em 0.3em 0.3em 0.3em">
                                    <option value="all">全部</option>
                                    <option value="notEnded">未截止</option>
                                    <option value="ended">已截止</option>
                                    <option value="missionName">任务名</option>
                                    <option value="requestor">发布者</option>
                                </select>
                                <input type="text" placeholder="搜索从这里开始..." id="searchContent">
                                <button type="button" id="searchBtn"></button>
                    </form>
                </div>
            </section>
            <div id="collections">

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

</div>

<!-- Scripts -->
<script src="js/Search.js"></script>
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