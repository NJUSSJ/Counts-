<%--
  Created by IntelliJ IDEA.
  User: Fortune
  Date: 2018/5/28
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
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
    <script src="js/echarts.min.js"></script>
    <script src="js/echarts-wordcloud.min.js"></script>
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

    <style type="text/css">
        #functionBar{
            margin-right: 5px;
            padding-top: 0px;
        }
        #userManagement{

        }
        #admin{
            font-size: 1.5em;
            font-weight: bold;
        }
        .userSearch{
            margin-top: 0px;
            height: 40px;
            width: 250px;
            margin-right: 5px;
            line-height: 40px;
        }
        #userSearchBt{
            border: solid 1px;
            color: inherit;
            cursor: pointer;
            display: inline-block;
            font-size: 0.8em;
            font-weight: 900;
            letter-spacing: 2px;
            height: 40px;
            width: 100px;
            line-height: 40px;
            padding: 0 0.75em 0.5em 0.75em;
            text-align: -webkit-center;
            text-decoration: none;
        }
        #missionSearchBt{
            border: solid 1px;
            color: inherit;
            cursor: pointer;
            display: inline-block;
            font-size: 0.8em;
            font-weight: 900;
            letter-spacing: 2px;
            height: 40px;
            width: 100px;
            line-height: 40px;
            padding: 0 0.75em 0.5em 0.75em;
            text-align: -webkit-center;
            text-decoration: none;
        }
        #userSearchBt:hover {
            background: rgba(188, 202, 206, 0.15);
            border-color: inherit;
        }
        input{
            float: left;
        }
    </style>
</head>
<body class="no-sidebar">
<div id="page-wrapper">

    <!-- Header -->
    <header id="header">
        <h1 id="logo"><img src="images/logo.png" width="25" height="25"> COUNTS <span>by Social Engineers</span></h1>
        <nav id="nav">
            <ul>
                <li class="current"><a href="<c:url value="personal.html?userName=${requestScope.userName}&phoneNumber=${requestScope.phoneNumber}&userCategory=${requestScope.userCategory}"/>">${requestScope.userName}</a></li>
                <li><a href="/" class="button special">登出</a></li>
            </ul>
        </nav>
    </header>

    <!-- Banner -->
    <section id="banner" style="height: 10px">
        <span id="admin">COUNTS 系统管理员</span>
    </section>


    <!-- Main -->
    <article id="main">
        <div class="row 150%" id="fullScreen">
            <div class="2u 12u(narrow)" align="center" id="functionBar">
                <ul>
                    <li><a href="#" class="button special2" id="userManagementButton" onclick="userManagement()">用户管理</a></li>
                    <li><a href="#" class="button special2" id="missionManagementButton" onclick="missionManagement()">任务管理</a></li>
                    <li><a href="#" class="button special2" id="recentButton" onclick="recent()">系统近况</a></li>
                </ul>
            </div>
            <div class="9u 12u(narrow)" align="left" id="userManagement" style="display: none;padding-top: 0px;padding-left: 90px">
                <form>
                    <input type="search" placeholder="用户名/手机号码" class="userSearch" id="userInfo">
                    <a id="userSearchBt" onclick="searchUser()">搜索</a>
                </form>

                <table id="userTable" class="default" style="margin-top: 20px">
                    <tr>
                        <td>手机号</td>
                        <td>用户名</td>
                        <td>类别</td>
                        <td>等级</td>
                        <td>状态</td>
                    </tr>
                </table>
            </div>

            <div class="9u 12u(narrow)" align="left" id="missionManagement" style="display: none;padding-top: 0px;padding-left: 90px">
                <form>
                    <input type="search" placeholder="任务名" class="userSearch" id="missionName">
                    <a id="missionSearchBt" onclick="searchMission()">搜索</a>
                </form>

                <table id="missionTable" class="default" style="margin-top: 20px">
                    <tr>
                        <td>任务名</td>
                        <td>任务类型</td>
                        <td>发起者</td>
                        <td>状态</td>
                    </tr>
                </table>
            </div>

            <div class="9u 12u(narrow)" align="left" id="recent" style="display: none;padding-top: 0px;padding-left: 90px">
                <header>近一周系统收支情况</header>
                <div id="revenue" style="width: 900px;height: 400px;margin-bottom: 30px"></div>

                <div id="adminChart2" style="width: 800px;height: 400px;margin-bottom: 30px"></div>

                <div id="adminChart3" style="width: 1000px;height: 400px;margin-bottom: 30px"></div>

                <div id="adminChart4" style="width: 1000px;height: 400px;margin-bottom: 30px"></div>
                
                <script>
                    var dom = document.getElementById("revenue");
                    var myChart = echarts.init(dom);
                    var app = {};
                    option = null;
                    var posList = [
                        'left', 'right', 'top', 'bottom',
                        'inside',
                        'insideTop', 'insideLeft', 'insideRight', 'insideBottom',
                        'insideTopLeft', 'insideTopRight', 'insideBottomLeft', 'insideBottomRight'
                    ];

                    app.configParameters = {
                        rotate: {
                            min: -90,
                            max: 90
                        },
                        align: {
                            options: {
                                left: 'left',
                                center: 'center',
                                right: 'right'
                            }
                        },
                        verticalAlign: {
                            options: {
                                top: 'top',
                                middle: 'middle',
                                bottom: 'bottom'
                            }
                        },
                        position: {
                            options: echarts.util.reduce(posList, function (map, pos) {
                                map[pos] = pos;
                                return map;
                            }, {})
                        },
                        distance: {
                            min: 0,
                            max: 100
                        }
                    };

                    app.config = {
                        rotate: 90,
                        align: 'left',
                        verticalAlign: 'middle',
                        position: 'insideBottom',
                        distance: 15,
                        onChange: function () {
                            var labelOption = {
                                normal: {
                                    rotate: app.config.rotate,
                                    align: app.config.align,
                                    verticalAlign: app.config.verticalAlign,
                                    position: app.config.position,
                                    distance: app.config.distance
                                }
                            };
                            myChart.setOption({
                                series: [{
                                    label: labelOption
                                }, {
                                    label: labelOption
                                }, {
                                    label: labelOption
                                }, {
                                    label: labelOption
                                }]
                            });
                        }
                    };


                    var labelOption = {
                        normal: {
                            show: true,
                            position: app.config.position,
                            distance: app.config.distance,
                            align: app.config.align,
                            verticalAlign: app.config.verticalAlign,
                            rotate: app.config.rotate,
                            formatter: '{c}  {name|{a}}',
                            fontSize: 16,
                            rich: {
                                name: {
                                    textBorderColor: '#fff'
                                }
                            }
                        }
                    };

                    option = {
                        color: ['#4cabce', '#e5323e'],
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {
                                type: 'shadow'
                            }
                        },
                        legend: {
                            data: ['收入', '支出']
                        },
                        toolbox: {
                            show: true,
                            orient: 'vertical',
                            left: 'right',
                            top: 'center',
                            feature: {
                                mark: {show: true},
                                dataView: {show: true, readOnly: false},
                                magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                                restore: {show: true},
                                saveAsImage: {show: true}
                            }
                        },
                        calculable: true,
                        xAxis: [
                            {
                                type: 'category',
                                axisTick: {show: false},
                                data: ['6.14', "6.15",'6.16', '6.17', '6.18', '6.19', '6.20']
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value'
                            }
                        ],
                        series: [
                            {
                                name: '收入',
                                type: 'bar',
                                label: labelOption,
                                data: [0, 0, 150, 232, 201, 154, 190]
                            },
                            {
                                name: '支出',
                                type: 'bar',
                                label: labelOption,
                                data: [0, 0, 98, 77, 101, 99, 40]
                            }
                        ]
                    };;
                    if (option && typeof option === "object") {
                        myChart.setOption(option, true);
                    }
                </script>
            </div>
        </div>
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
<script src="js/jquery.min.js"></script>
<script src="js/jquery.dropotron.min.js"></script>
<script src="js/jquery.scrolly.min.js"></script>
<script src="js/jquery.scrollgress.min.js"></script>
<script src="js/skel.min.js"></script>
<script src="js/util.js"></script>
<!--[if lte IE 8]><script src="js2/ie/respond.min.js2"></script><![endif]-->
<script src="js/main.js"></script>
<script src="js/admin.js"></script>
<script src="js/DrawAdminChart.js"></script>
</body>
</html>