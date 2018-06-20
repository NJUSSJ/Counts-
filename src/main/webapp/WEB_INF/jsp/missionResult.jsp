<%--
  Created by IntelliJ IDEA.
  User: Fortune
  Date: 2018/6/16
  Time: 23:32
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

    .scale{background: #ddd; width: 600px; height: 12px; position: relative;margin: 20px;}

    .scale span{background:#aaa;width:8px;height:24px;position:absolute;left:-2px;top:-6px;cursor:pointer; border-radius: 3px;}

    .scale div{background: #123244; position: absolute; height: 12px; width: 0; left: 0; bottom: 0; display: inline}


</style>
<body onload="load(${requestScope.quality}, ${requestScope.credit}, ${requestScope.rank}, ${requestScope.total})">

<!-- Wrapper-->
<div id="wrapper">

    <!-- Nav -->
    <nav id="nav">
        <a href="#me" class="icon fa-home active" onclick="addOne()"><span>任务主页</span></a>
        <a href="#work" class="icon fa-eye" onclick="addOne()"><span>任务结果</span></a>
        <a onclick="back()" class="icon fa-refresh"><span>返回</span></a>
    </nav>

    <!-- Main -->
    <div id="main">

        <!-- Me -->
        <article id="me" class="panel">
            <header>
                <h1 id="Name">任务结果</h1>
                <p>Croudsourcing Worker</p>
            </header>
            <a href="#work" class="jumplink pic">
                <span class="arrow icon fa-chevron-right"><span>See my work</span></span>
                <img src="images/logo.jpg" alt="" />
            </a>
        </article>

        <!-- Work -->
        <article id="work" class="panel">
            <header>
                <h2>任务结果</h2>
            </header>
            <section>
                <div class="row">
                    <div class="12u 12u$(mobile)" id="blank">
                        <span>任务得分：</span><span id="missionQuality"></span><br>
                        <span>任务排名：</span><span id="missionRank"></span><br>
                        <span>任务获得积分：<span id="missionCredit"></span></span><br>
                        <span>在该任务中您超越了</span><span id="percent"></span><span>的用户</span>
                    </div>
                </div>

                <div class="row" id="picSection">

                </div>
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
<script>
    function load(quality, credit, rank, total) {
        var percent = (total-rank)/total*100;
        document.getElementById("missionQuality").innerHTML = quality;
        document.getElementById("missionCredit").innerHTML = credit;
        document.getElementById("missionRank").innerHTML = rank;
        document.getElementById("percent").innerHTML = "%"+percent;
    }
</script>


</body>

</html>

