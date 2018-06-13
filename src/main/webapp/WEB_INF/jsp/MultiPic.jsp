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

<body class="index" onload="load(${requestScope.picNum},${requestScope.url},${requestScope.collection},${requestScope.userCategory},${requestScope.Tagable},${requestScope.userPhone},${requestScope.picNum})">
<div id="page-wrapper">
    <header id="header">
        <h1 id="logo"><img src="images/logo.png" width="25" height="25"> COUNTS <span>by Social Engineers</span></h1>
        <nav id="nav">
            <ul>
                <li class="current"><a href="/personal.html?userName=${requestScope.userName}&phoneNumber=${requestScope.userPhone}&userCategory=${requestScope.userCategory}">${requestScope.userName}</a></li>
                <li class="submenu" id="submenu">
                    <a id="addMissionBtn" href="/personal.html?userName=${requestScope.userName}&phoneNumber=${requestScope.userPhone}&userCategory=${requestScope.userCategory}" onclick="addMissionToUser(${requestScope.collection},${requestScope.userPhone})">标注该任务集</a>
                </li>
                <li class="submenu">
                    <a id="judgeMissionBtn" href="/personal.html?userName=${requestScope.userName}&phoneNumber=${requestScope.userPhone}&userCategory=${requestScope.userCategory}" onclick="addJudgeMissionToUser(${requestScope.collection},${requestScope.userPhone}) )">评估该任务集</a>
                </li>
                <li><a href="/" class="button special">登出</a></li>
            </ul>
        </nav>
    </header>
</div>


<!-- Main -->
<article id="main">

    <header class="special container">
        <span class="icon fa-bar-chart-o"></span>
        <h2>当前任务集--<strong id="strong">${requestScope.collection}</strong></h2>
            <br/>
    </header>




    <!-- Details -->
    <section class="wrapper style4 container">
        <div class="row">
            <div class="6u 12u(narrower)">

            </div>

            <div class="6u 12u(narrower)">
                <span style="display: block;margin-top: 8px"><span>任务积分：</span><span id="missionCredit">${requestScope.credit}</span></span>

                <span style="display: block;margin-top: 8px"><span>期待标注人数：</span><span id="expectedNum">${requestScope.expectedNum}</span></span>

                <span style="display: block;margin-top: 8px"><span>起始时间：</span><span id="startTime">${requestScope.startTime}</span></span>

                <span style="display: block;margin-top: 8px"><span>截止时间：</span><span id="endTime">${requestScope.endTime}</span></span>

                <span style="display: block;margin-top: 8px"><span>工人最低等级：</span><span id="level">${requestScope.Level}</span></span>

                <span style="display: block;margin-top: 8px"><span>任务描述：</span><span id="missionDescription">${requestScope.description}</span></span>

                <span style="display: block;margin-top: 8px"><span>任务难度：</span><span id="difficulty">${requestScope.difficulty}</span></span>

                <span style="display: block;margin-top: 8px"><span>图片类型：</span><span id="picType">${requestScope.picType}</span></span>

                <span style="display: block;margin-top: 8px"><span>标注类型：</span><span id="tagType">${requestScope.tagType}</span></span>

                <span style="display: block;margin-top: 8px"><span>标签：</span><span id="missionLabel">${requestScope.missionLabel}</span></span>
            </div>

        </div>
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
<script src="js/multipic1.js"></script>
<script src="js/jquery.min.js"></script>
<script src="js/jquery.dropotron.min.js"></script>
<script src="js/jquery.scrolly.min.js"></script>
<script src="js/jquery.scrollgress.min.js"></script>
<script src="js/skel.min.js"></script>
<script src="js/util.js"></script>
<script src="js/main.js"></script>
</body>
</html>