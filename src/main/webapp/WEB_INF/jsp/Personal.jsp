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
    <script src="js/echarts.min.js"></script>
    <script src="js/echarts-wordcloud.min.js"></script>
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.filterTable.js"></script>
    <script src="js/Message.js"></script>
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

        .button.small2{
            height: 50px;font-size: 0.7em;text-decoration: none;font-weight: bold;
        }

        :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
            color: #7c8081;
        }

        ::-moz-placeholder { /* Mozilla Firefox 19+ */
            color: #7c8081;
        }

        input:-ms-input-placeholder,
        textarea:-ms-input-placeholder {
            color: #7c8081;
        }

        input::-webkit-input-placeholder,
        textarea::-webkit-input-placeholder {
            color: #7c8081;
        }
    </style>
</head>
<body onload="loadPersonal(${requestScope.phoneNumber});">

<!-- Wrapper-->
<div id="wrapper">

    <!-- Nav -->
    <nav id="nav">
        <a href="#me" class="icon fa-home active"><span>Home</span></a>
        <a href="#work" class="icon fa-folder"><span>Work</span></a>
        <a href="#info" class="icon fa-envelope"><span>Personal</span></a>
        <a href="#charts" class="icon fa-pie-chart"><span>Charts</span></a>
        <a href="#message" class="icon fa-info"><span>Message</span></a>
        <a href="#money" class="icon fa-money"><span>Money</span></a>
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
                <div class="row" id="personalFinishedCollections">

                </div>
                <div id="downloadArea">

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
                        <table class="">
                            <thead>
                            <tr>
                                <th scope="col" title="Number">#</th>
                                <th scope="col">Content</th>
                                <th scope="col">Sender</th>
                                <th scope="col">Keyid</th>
                            </tr>
                            </thead>
                            <tbody id="messageArea">

                            </tbody>
                        </table>
                    </div>
                </section>
            </header>
        </article>

        <article id="money" class="panel" style="height: 700px;">
            <div id="worker">
                <header>充值</header><br>
                <div class="row 150%">
                    <div class="2u 12u(narrower)" style="padding-top: 0px;"><a id="20button" onclick="charge(20)" class="button small2" style="height: 50px;font-size: 0.7em;text-decoration: none;font-weight: bold" >20元</a></div>
                    <div class="2u 12u(narrower)" style="padding-top: 0px;"><a id="50button" onclick="charge(50)" class="button small2" style="height: 50px;font-size: 0.7em;text-decoration: none;font-weight: bold" >50元</a></div>
                    <div class="2u 12u(narrower)" style="padding-top: 0px;"><a id="100button" onclick="charge(100)" class="button small2" style="height: 50px;font-size: 0.7em;text-decoration: none;font-weight: bold" >100元</a></div>
                </div>

                <br>
                <div id="20yuan" style="display: none">
                    <img src="images/20.jpg">
                    <div class="2u 12u(narrower)" style="padding-top: 0px;"><a onclick="chargeCredit(20)" class="button small2" style="height: 50px;font-size: 0.7em;text-decoration: none;font-weight: bold" >完成支付</a></div>
                </div>

                <div id="50yuan" style="display: none;">
                    <img src="images/50.jpg">
                    <div class="2u 12u(narrower)" style="padding-top: 0px;"><a onclick="chargeCredit(50)" class="button small2" style="height: 50px;font-size: 0.7em;text-decoration: none;font-weight: bold" >完成支付</a></div>
                </div>

                <div id="100yuan" style="display: none;">
                    <img src="images/100.jpg">
                    <div class="2u 12u(narrower)" style="padding-top: 0px;"><a onclick="chargeCredit(100)" class="button small2" style="height: 50px;font-size: 0.7em;text-decoration: none;font-weight: bold" >完成支付</a></div>
                </div>
            </div>
            <script>
                function para3(uid, delta) {
                    this.uid = uid;
                    this.delta = delta;
                }
                function charge(num) {
                    document.getElementById("20yuan").style.display = "none";
                    document.getElementById("50yuan").style.display = "none";
                    document.getElementById("100yuan").style.display = "none";
                    document.getElementById(num+"yuan").style.display = "block";
                }

                function chargeCredit(num) {
                    var chargePara = new para3(phoneNumber, num);
                    $.ajax({
                        async: true,
                        method: "POST",
                        url: "/ChangeCredit",
                        contentType: "application/json",
                        dataType: "json",
                        data: JSON.stringify(chargePara),
                        success: function (returnData) {
                            if(returnData != 0){
                                alert("充值成功！")
                            }
                        },
                        error: function () {
                            //alert("fail")
                        }
                    });
                }
                function withdraw() {
                    var num = document.getElementById("num").value;
                    alert(num);
                    var chargePara = new para3(phoneNumber, -num);
                    $.ajax({
                        async: true,
                        method: "POST",
                        url: "/ChangeCredit",
                        contentType: "application/json",
                        dataType: "json",
                        data: JSON.stringify(chargePara),
                        success: function (returnData) {
                            if(returnData != 0){
                                alert("提现成功！")
                            }
                        },
                        error: function () {
                            //alert("fail")
                        }
                    });
                }
            </script>
            <div class="row 150%" id="requester">
                <input type="text" id="num" placeholder="提现金额" style="width: 250px; height: 48px;padding-top: 5px;padding-left: 3px;display: block;margin-right: 20px">
                <input type="text" placeholder="支付宝账号" style="width: 250px; height: 48px;padding-top: 5px;padding-left: 3px">
                <div class="2u 12u(narrower)" style="padding-top: 0px;"><a id="withdraw" onclick="withdraw()" class="button small2" style="height: 50px;font-size: 0.7em;text-decoration: none;font-weight: bold" >提现</a></div>
            </div>

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
<script src="js/CreateChartArea.js"></script>
<script src="js2/jquery.min.js"></script>
<script src="js2/skel.min.js"></script>
<script src="js2/skel-viewport.min.js"></script>
<script src="js2/util.js"></script>
<script src="js2/main.js"></script>

</body>
</html>
