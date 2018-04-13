<%--
  Created by IntelliJ IDEA.
  User: Fortune
  Date: 2018/4/11
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<body class="contact">
<div id="page-wrapper">

    <!-- Header -->
    <header id="header">
        <h1 id="logo"><a href="index.html">Counts <span> by Social Engineers</span></a></h1>
        <nav id="nav">
            <ul>
                <li class="current"><a href="index.html">Welcome</a></li>
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
                <li><a href="#" class="button special">Sign Up</a></li>
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
                <form>
                    <div class="row 50%">
                        <div class="6u 12u(mobile)">
                            <input  id="name" type="text" name="name" placeholder="任务名称"/>
                        </div>
                    </div>

                    <div class="row 50%">
                        <div class="6u 12u(mobile)" align="left">
                            <span class="datePicker">起始时间： </span><input  id="startTime" type="date" name="startTime"  />
                        </div>
                    </div>

                    <div class="row 50%">
                        <div class="6u 12u(mobile)" align="left">
                            <span class="datePicker">起始时间： </span><input id="endTime" type="date" name="endTime"  />
                        </div>
                    </div>

                    <div class="row 50%">
                        <div class="12u">
                            <textarea  id="description" name="despription" placeholder="任务描述" rows="4"></textarea>
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
                                Dropzone.autoDiscover = false;
                                var myDropzone = new Dropzone("#myDropzone", {
                                    url: "uploadPics",
                                    addRemoveLinks: true,
                                    autoProcessQueue: false,
                                    method: 'post',
                                    filesizeBase: 1024,
                                    parallelUploads: 10,
                                    acceptedFiles: ".jpg,.gif,.png,.jpeg", //上传的类型

                                    sending: function(file, xhr, formData) {

                                        },
                                    success: function (file, response, e) {
                                        alert("任务发布成功！");
                                    },

                                    dictDefaultMessage:'拖动文件至此或者点击上传',
                                    dictMaxFilesExceeded: "您最多只能上传1个文件！",
                                    dictResponseError: '文件上传失败!',
                                    dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
                                    dictFallbackMessage:"浏览器不受支持",
                                    dictFileTooBig:"文件过大上传文件最大支持.",
                                    dictRemoveLinks: "删除",
                                    dictCancelUpload: "取消"
                                });
                            </script>
                        </div>
                    </div>
                    <div class="row">
                        <div class="12u">
                            <ul class="buttons">
                                <li><input type="button" class="special" value="Send Message" id="submit"/></li>
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
<script src="js/jquery.min.js"></script>
<script src="js/jquery.dropotron.min.js"></script>
<script src="js/jquery.scrolly.min.js"></script>
<script src="js/jquery.scrollgress.min.js"></script>
<script src="js/skel.min.js"></script>
<script src="js/util.js"></script>
<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
<script src="js/main.js"></script>
<script src="js/upload.js"></script>



</body>
</html>
