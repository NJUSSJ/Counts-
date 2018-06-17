<%--
  Created by IntelliJ IDEA.
  User: julia98
  Date: 2018/4/10
  Time: 下午3:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>

<html>
<head>
    <title>Personal</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="css2/main.css" />
    <link rel="stylesheet" href="css/samples-styles.css">
    <script src="js/Personal.js"></script>
    <script src="js/CreateChartArea.js"></script>
    <script src="js/echarts.min.js"></script>
    <script src="js/echarts-wordcloud.min.js"></script>
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.filterTable.js"></script>
    <!-- load 3D stat -->
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>
    <!-- end load 3D stat -->
    <noscript><link rel="stylesheet" href="css2/noscript.css" /></noscript>
    <style type="text/css">
        /* filter table specific styling */
        td.alt { background-color: #ffc; background-color: rgba(255, 255, 0, 0.2); }

        .image.fit:hover{
            top: -0.2em;
        }
    </style>
</head>
<body onload="loadPersonal()">

<!-- Wrapper-->
<div id="wrapper">

    <!-- Nav -->
    <nav id="nav">
        <a href="#me" class="icon fa-home active"><span>Home</span></a>
        <a href="#work" class="icon fa-folder"><span>Work</span></a>
        <a href="#info" class="icon fa-envelope"><span>Personal</span></a>
        <a href="#charts" class="icon fa-pie-chart"><span>Charts</span></a>
        <a href="#message" class="icon fa-info"><span>Message</span></a>
        <a href="<c:url value="loginCheck.html?userName=${requestScope.phoneNumber}&password=${requestScope.password}"/>" class="icon fa-refresh"><span>Back</span></a>
    </nav>

    <!-- Main -->
    <div id="main">

        <!-- Me -->
        <article id="me" class="panel">
            <header>
                <h1 id="_userNameCard"></h1>
                <p>Croudsourcing User</p>
            </header>
            <a href="#work" class="jumplink pic">
                <span class="arrow icon fa-chevron-right"><span>See my work</span></span>
                <img src="images/logo.jpg" alt="" />
            </a>
        </article>

        <!-- Work -->
        <article id="work" class="panel">
            <header>
                <h2>Work</h2>
            </header>
            <p>
                进行中...
            </p>
            <section>
                <div class="row" id="personalCollections">

                </div>
            </section>

            <p>
                已完成！
            </p>
            <section>
                <div class="row" id="personalFinishedCollection">

                </div>
            </section>

        </article>

        <!-- Personal Info -->
        <article id="info" class="panel">
            <header>
                <h2>Personal Info</h2>
            </header>
            <form action="#" method="post">
                <div>
                    <div class="row">
                        <div class="6u 12u$(mobile)" id="blank">
                            <span>UserName</span><input type="text" name="userName" placeholder="UserName" value="" id="_userName"/>
                        </div>
                        <div class="6u$ 12u$(mobile)">
                            <span>PhoneNumber</span><input type="text" name="phoneNumber" placeholder="PhoneNumber" disabled="disabled" value="" id="_phoneNumber"/>
                        </div>
                        <div class="6u 12u$(mobile)">
                            <span>UserType</span><input type="text" name="userType" placeholder="UserType" disabled="disabled" value="" id="_userType"/>
                        </div>
                        <div class="6u$ 12u$(mobile)">
                            <span>Credit</span><input type="text" name="credit" placeholder="Credit" disabled="disabled" value="" id="_credit"/>
                        </div>
                        <div class="6u 12u$(mobile)">
                            <span>Level</span><input type="text" name="level" placeholder="Level" disabled="disabled" value="" id="_level"/>
                        </div>
                        <div class="6u$ 12u$(mobile)">
                            <span>Password</span><input type="text" name="password" placeholder="Password" value="" id="_password"/>
                        </div>
                        <div class="12u$">
                            <span>Description</span><textarea name="description" placeholder="Description" rows="8" id="_description">A very honest and industrious croudsourcing worker.</textarea>
                        </div>
                        <div class="12u$">
                            <input type="button" name="save" value="Save Changes" onclick="savePersonalBlanks()"/>
                        </div>
                    </div>
                </div>
            </form>
        </article>

        <!-- Charts -->
        <article id="charts" class="panel">
            <header>
                <h2>Charts</h2>
                <p>
                    分析图表
                </p>
                <section>
                    <div id="chartsArea"></div>
                </section>
            </header>
        </article>

        <!-- message -->
        <article id="message" class="panel">
            <header>
                <h2>Message</h2>
                <p>
                    消息
                </p>
                <section>
                    <div class="htmleaf-content">
                        <div class="input-group">
                            <div class="input-group-addon">过滤条件</div>
                            <input class="form-control" type="search" id="input-filter" size="15" placeholder="输入过滤条件"/>
                        </div>
                        <br>
                        <table class="" onload="getMessage(${requestScope.phoneNumber});">
                            <thead>
                            <tr>
                                <th scope="col" title="Number">#</th>
                                <th scope="col">Content</th>
                                <th scope="col">Sender</th>
                                <th scope="col">Keyid</th>
                            </tr>
                            </thead>
                            <tbody id="messageArea">
                            <tr><td>1</td><td>George Washington</td><td>two</td><td>1789-1797</td></tr>
                            <tr><td>2</td><td>John Adams</td><td>one</td><td>1797-1801</td></tr>
                            <tr><td>3</td><td>Thomas Jefferson</td><td>two</td><td>1801-1809</td></tr>
                            <tr><td>4</td><td>James Madison</td><td>two</td><td>1809-1817</td></tr>
                            <tr><td>5</td><td>James Monroe</td><td>two</td><td>1817-1825</td></tr>
                            <tr><td>6</td><td>John Quincy Adams</td><td>one</td><td>1825-1829</td></tr>
                            <tr><td>7</td><td>Andrew Jackson</td><td>two</td><td>1829-1837</td></tr>
                            <tr><td>8</td><td>Martin Van Buren</td><td>one</td><td>1837-1841</td></tr>
                            <tr><td>9</td><td>William Henry Harrison</td><td>one-partial</td><td>1841</td></tr>
                            <tr><td>10</td><td>John Tyler</td><td>one-partial</td><td>1841-1845</td></tr>
                            </tbody>
                        </table>
                    </div>
                </section>
            </header>
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
<script src="js/ChartData.js"></script>
<script src="js/GetChartData.js"></script>
<script src="js2/jquery.min.js"></script>
<script src="js2/skel.min.js"></script>
<script src="js2/skel-viewport.min.js"></script>
<script src="js2/util.js"></script>
<script src="js2/main.js"></script>

</body>
</html>
