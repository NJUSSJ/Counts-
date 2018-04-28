<%--
  Created by IntelliJ IDEA.
  User: Fortune
  Date: 2018/4/23
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<!--
Astral by HTML5 UP
html5up.net | @ajlkn
Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
    <title>Astral by HTML5 UP</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="css2/main.css" />
    <script src="js/echarts.min.js"></script>
    <script src="js/echarts-wordcloud.min.js"></script>
    <noscript><link rel="stylesheet" href="css2/noscript.css" /></noscript>

</head>
<style type="text/css">
    div.sidebar{
        width: 90px;
        height: 500px;
        border-top: solid 2px rgba(124, 128, 129, 0.1);
        padding-top: 1.5em;
    }
    div.content{
        height: 500px;
        border-top: solid 2px rgba(124, 128, 129, 0.1);
        padding-top: 1.5em;
    }

    div.tag_info{
        height: 500px;
        border-top: solid 2px rgba(124, 128, 129, 0.1);
        padding-top: 1.5em;
        alignment: right;
    }

    .wrapper.style5{
        background: #fff;
        color: inherit;
        width: 1300px;
        height: 700px;
    }

    ul.picturetools{
        cursor: pointer;
    }

    ul.picturetools li{
        width: 80px;
        height: 80px;
        display: block;
        line-height: 1em;
        padding-left: 0.5em;
        margin-bottom: 1em;
    }

    ul.picturetools li img:hover{
        margin-top: -0.2em;
    }

    #edit_area{
        height: 700px;
    }

    .picture{
        width: 300px;
        height: 186px;
    }


</style>
<body onload="loadDetails(${requestScope.picNum})">

<!-- Wrapper-->
<div id="wrapper">

    <!-- Nav -->
    <nav id="nav">
        <a href="#me" class="icon fa-home active"><span>任务主页</span></a>
        <a href="#work" class="icon fa-eye"><span>任务详情</span></a>
        <a href="#samplePanel" class="icon fa-tag"><span>评估任务</span></a>
        <a href="#charts" class="icon fa-pie-chart" onclick=""><span>任务统计</span></a>
        <a href="JavaScript:history.go(-1)" class="icon fa-refresh"><span>返回</span></a>
    </nav>

    <!-- Main -->
    <div id="main">

        <!-- Me -->
        <article id="me" class="panel">
            <header>
                <h1 id="Name">${requestScope.missionName}</h1>
                <p>Croudsourcing Requestor</p>
            </header>
            <a href="#work" class="jumplink pic">
                <span class="arrow icon fa-chevron-right"><span>See my work</span></span>
                <img src="images/logo.jpg" alt="" />
            </a>
        </article>

        <!-- Work -->
        <article id="work" class="panel">
            <header>
                <h2>任务详情</h2>
            </header>
            <section>
                <div class="row">
                    <div class="12u 12u$(mobile)" id="blank">
                        <span>任务名称：</span><span id="missionName">${requestScope.missionName}</span>
                    </div>
                    <div class="6u$ 12u$(mobile)">
                        <span>任务积分：</span><span id="missionCredit">${requestScope.credit}</span>
                    </div>
                    <div class="6u 12u$(mobile)">
                        <span>期待标注人数：</span><span id="expectedNum">${requestScope.expectedNum}</span>
                    </div>
                    <div class="12u$ 12u$(mobile)">
                        <span>起始时间：</span><span id="startTime">${requestScope.startTime}</span>
                    </div>
                    <div class="12u 12u$(mobile)">
                        <span>截止时间：</span><span id="endTime">${requestScope.endTime}</span>
                    </div>
                    <div class="12u$">
                        <span>工人最低等级：</span><span id="level">${requestScope.Level}</span>
                    </div>
                    <div class="12u$ 12u$(mobile)">
                        <span>任务描述：</span><span id="missionDescription">${requestScope.description}</span>
                    </div>

                </div>

                <div class="row" id="picSection">

                </div>
            </section>
        </article>

        <!-- Contact -->
        <article id="samplePanel" class="panel">
            <header>
                <h2 id="sample">抽样评估</h2>
            </header>
            <div class="row 150%" id="edit_area">
                <div class="2u 12u(narrower)" align="center">

                    <!-- Tool bar -->
                    <div class="sidebar" align="left">
                        <ul class="picturetools">
                            <li><input type="button" name="" value="优" onclick="good()"></li>
                            <li><input type="button" name="" value="良" onclick="fair()"></li>
                            <li><input type="button" name="" value="中" onclick="middle()"></li>
                            <li><input type="button" name="" value="差" onclick="bad()"></li>
                        </ul>
                    </div>

                </div>

                <div class="2u 12u(narrower)" align="center">

                    <!-- Tag Info-->
                    <div class="tag_info" id="textareaPlace" style="overflow: auto">
                        <!-- <textarea placeholder="请输入标注信息..." id="tagArea"></textarea> -->
                        <!-- <a href="#" class="button special">保存</a> -->
                    </div>
                </div>
                <div class="8u 12u(narrower)">

                    <!-- Content -->
                    <div class="content" align="center">
                        <canvas id="canvasForSample" width="500" height="500">

                        </canvas>
                        <p id="info">Tag info.</p>
                    </div>

                </div>
            </div>
        </article>

        <article id="charts" class="panel">
            <header>
                <h2>统计信息</h2>
            </header>
            <section>
                <div class="row">
                    <div id="chart1" style="width: 600px;height:400px;">

                    </div>
                </div>


                <div class="row">
                    <div id="chart2" style="width: 600px;height:400px;">

                    </div>
                </div>
                <script type="text/javascript" src="js/missionDetailsCharts.js"></script>
            </section>
        </article>

    </div>

    <!-- Footer -->
    <div id="footer">
        <ul class="copyright">
            <li>&copy; Social Engineers</li>
        </ul>
    </div>

</div>

<!-- Scripts -->
<script src="js2/jquery.min.js"></script>
<script src="js2/skel.min.js"></script>
<script src="js2/skel-viewport.min.js"></script>
<script src="js2/util.js"></script>
<script src="js2/main.js"></script>
<script src="js/missionDetails.js"></script>

</body>
</html>
