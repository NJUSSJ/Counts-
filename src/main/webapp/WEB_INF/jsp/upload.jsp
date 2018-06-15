<%--
  Created by IntelliJ IDEA.
  User: Fortune
  Date: 2018/4/11
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<!--
Twenty by HTML5 UP
html5up.net | @ajlkn
Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
    <title>Counts by SOCIAL ENGINEER</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <!--[if lte IE 8]><script src="js/ie/html5shiv.js"></script><![endif]-->
    <link rel="stylesheet" href="css/main.css" />
    <!--[if lte IE 8]><link rel="stylesheet" href="css/ie8.css" /><![endif]-->
    <!--[if lte IE 9]><link rel="stylesheet" href="css/ie9.css" /><![endif]-->
    <link rel="stylesheet" type="text/css" href="dropZoneCSS/dropzone.css">
    <link rel="stylesheet" type="text/css" href="dropZoneCSS/basic.css">
    <link rel="stylesheet" type="text/css" href="css/jquery.tag-editor.css">
    <script src="js/jquery.min.js"></script>
    <script src="dropZoneJS/dropzone.js"></script>
    <style type="text/css">
        /*  修改日历控件类型 */
        ::-webkit-datetime-edit { border-color: #cccccc; }  /*控制编辑区域的*/
        ::-webkit-datetime-edit-fields-wrapper { border-color: #cccccc; }    /*控制年月日这个区域的*/
        ::-webkit-datetime-edit-text { color: #cccccc; }  /*这是控制年月日之间的斜线或短横线的*/
        ::-webkit-datetime-edit-year-field { color: #cccccc; }
        ::-webkit-datetime-edit-month-field { color: #cccccc; }
        ::-webkit-datetime-edit-day-field { color: #cccccc; }
        ::-webkit-inner-spin-button { visibility: hidden; }    /*这是控制上下小箭头的*/
        ::-webkit-calendar-picker-indicator {      /*这是控制下拉小箭头的*/
            border: 1px solid #ccc;
            border-radius: 2px;
            box-shadow: inset 0 1px #fff, 0 1px #eee;
            background-color: #eee;
            background-image: -webkit-linear-gradient(top, #f0f0f0, #e6e6e6);
            color: #666;
        }
        ::-webkit-clear-button {    /*控制清除按钮*/

        }

        .datePicker{
            color: #cccccc;
        }
    </style>
</head>
<body class="contact" onload="load(${requestScope.requestorNum}); loadPhone(${requestScope.requestorNum});">

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
                <li><a href="/" class="button special">Log out</a></li>
            </ul>
        </nav>
    </header>

    <!-- Main -->
    <article id="main">

        <header class="special container">
            <span class="icon fa-envelope"></span>
            <h2>任务发布</h2>
            <p>请正确填写任务信息</p>
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
                        <div class="6u 12u(mobile)" align="left">
                            <span>任务名称：<input  id="name" type="text" name="name" />
                            </span>
                        </div>
                    </div>

                    <div class="row 50%">
                        <div class="6u 12u(mobile)" align="left">
                            <span>图片类型： </span>
                            <select id="picType" >
                                <option value="人物">人物</option>
                                <option value="动物">动物</option>
                                <option value="风景">风景</option>
                                <option value="卡通动画">卡通动画</option>
                                <option value="交通工具">交通工具</option>
                                <option value="生活用品">生活用品</option>
                                <option value="其他">其他</option>
                            </select>
                        </div>
                    </div>

                    <div class="row 50%">
                        <div class="6u 12u(mobile)" align="left">
                            <span>任务难度： </span>
                            <select id="difficulty" >
                                <option value="1">Lev1</option>
                                <option value="2">Lev2</option>
                                <option value="3">Lev3</option>
                            </select>
                        </div>
                    </div>

                    <div class="row 50%">
                        <div class="6u 12u(mobile)" align="left">
                            <span>任务类型： </span>
                            <select id="tagType">
                                <option value="1">标签式</option>
                                <option value="2">非标签式</option>
                            </select>
                        </div>
                    </div>

                    <link rel="stylesheet" type="text/css" href="css/jquery.tag-editor.css">
                    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
                    <script src="http://www.jq22.com/jquery/jquery-ui-1.10.2.js"></script>
                    <script src="js/jquery.tag-editor.min.js"></script>

                    <div class="row 50%"id="missionLabel">
                        <div class="12u" align="left" >
                            <textarea id="tag_editor" style="display: none"></textarea>
                            <script>
                                $("#tag_editor").tagEditor({ placeholder: 'Enter tags ...' });
                            </script>
                        </div>
                    </div>

                    <div class="row 50%">
                        <div class="6u 12u(mobile)" align="left">
                            <span>限制工人最低等级：
                            <select id="workerLevel">
                                <option value="1">Lev1</option>
                                <option value="2">Lev2</option>
                                <option value="3">Lev3</option>
                                <option value="4">Lev4</option>
                            </select></span>
                        </div>
                    </div>

                    <div class="row 50%">
                        <div class="6u 12u(mobile)" align="left">
                            <span>期望标注人数：<input  id="maxWorkerNum" type="text" name="maxNum" /></span>
                        </div>
                    </div>

                    <div class="row 50%">
                        <div class="6u 12u(mobile)" align="left">
                            <span class="datePicker">截至时间： <input id="endTime" type="date" name="endTime"  /></span>
                        </div>
                    </div>

                    <div class="row 50%">
                        <div class="12u">
                            <textarea  id="description" name="despription" placeholder="任务描述" rows="4"></textarea>
                        </div>
                    </div>

                    <div class="row">
                        <div class="12u">
                            <ul class="buttons">
                                <li><input type="button" class="special" value="确认信息" id="ensure"/></li>
                            </ul>
                        </div>
                    </div>

                    <div class="row 50%">
                        <div class="6u 12u(mobile)" id="rewardBlank" align="left">
                            <!-- <span>任务奖励：</span>
                            <input  id="reward" type="text" name="reward" value= "0" disabled="disabled"/> -->
                        </div>
                    </div>

                    <div class="row 50%">
                        <div class="12u">
                            <div class="dropzone" id="myDropzone">
                                <div class="am-text-success dz-message" id="files">
                                    将文件拖拽到此处<br>或点此打开文件管理器选择文件
                                </div>
                            </div>


                            <script type="text/javascript">

                            </script>
                        </div>
                    </div>/*图片数量不能少于15*/

                    <div class="row">
                        <div class="12u">
                            <ul class="buttons">
                                <li><input type="button" class="special" value="发布任务" id="submitButton"/></li>
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
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="http://www.jq22.com/jquery/jquery-ui-1.10.2.js"></script>
<script src="js/jquery.tag-editor.min.js"></script>
<script src="js/CalReward.js"></script>
<script src="js/jquery.dropotron.min.js"></script>
<script src="js/jquery.scrolly.min.js"></script>
<script src="js/jquery.scrollgress.min.js"></script>
<script src="js/skel.min.js"></script>
<script src="js/util.js"></script>
<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
<script src="js/main.js"></script>
<script src="js/upload1.js"></script>
</body>
</html>
