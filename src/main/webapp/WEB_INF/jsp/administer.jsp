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
                    <li><a href="#" class="button special2">消息系统</a></li>
                    <li><a href="#" class="button special2">系统近况</a></li>
                </ul>
            </div>
            <div class="9u 12u(narrow)" align="left" id="userManagement" style="display: none;padding-top: 0px;padding-left: 90px">
                <form>
                    <input type="search" placeholder="用户名/手机号码" class="userSearch">
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
                    <input type="search" placeholder="任务名" class="userSearch">
                    <a id="missionSearchBt">搜索</a>
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
</body>
</html>