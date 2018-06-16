<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>COUNTS</title>

    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="css/main.css" />
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
    </style>
</head>
<body class="left-sidebar" onload='loadMissionLabel1(${requestScope.missionLabel});'>

<div onload="loadPic(${requestScope.url},${requestScope.userPhone}); loadTagType(${requestScope.tagType});">
    <!-- Header -->
    <header id="header">
        <h1 id="logo"><img src="images/logo.png" width="25" height="25" /> COUNTS <span>by Social Engineers</span></h1>
        <nav id="nav" onload="loadPhoneNumber(${requestScope.userPhone})">
            <ul>
                <li class="submenu">
                    <a herf="#">Tag</a>
                    <ul>
                        <li><a id="save"><img src="images/save.png" width="20" height="20" align="center">SAVE</a></li>
                        <li><a id="back"><img src="images/back.png" width="20" height="20" align="center">BACK</a></li>
                        <li><a id="more"><img src="images/more.png" width="20" height="20" align="center">NEXT</a></li>
                    </ul>
                </li>
                <li class="current"><a href="<c:url value="personal.html?userName=${requestScope.userName}&phoneNumber=${requestScope.userPhone}&userCategory=${requestScope.userCategory}"/>">${requestScope.userName}</a></li>
                <li><a onclick="submitTagInfo()" class="button special">Submit</a></li>
                <li><a href="/" class="button special">Sign Out</a></li>
            </ul>
        </nav>
    </header>
</div>


<!-- Main -->
<article id="main">

    <header class="special container">
        <span class="icon fa-laptop"></span>
        <h2><strong>Tag Page</strong></h2>
        <p>Where the picture is taged.</p>
    </header>

    <!-- One -->
    <section class="wrapper style5 container">

        <div class="row 150%" id="edit_area">
            <div class="2u 12u(narrower)" align="center">

                <!-- Tool bar -->
                <div class="sidebar" align="left">
                    <ul class="picturetools" id="picTools">
                        <li><img src="images/graphics_editor.svg" align="center" id="RectTool"></li>
                        <li><img src="images/pen.svg" align="center" id="CurlTool"></li>
                        <li><img src="images/note.svg" align="center" id="OverallTag"></li>
                    </ul>
                </div>

            </div>

            <div class="4u 12u(narrower)" align="center">

                <!-- Tag Info-->
                <div class="tag_info" id="textareaPlace" style="overflow: auto">
                    <ul class="buttons" id="missionLabel">

                    </ul>
                        <!-- <textarea placeholder="请输入标注信息..." id="tagArea"></textarea> -->
                        <!-- <a href="#" class="button special">保存</a> -->
                </div>
            </div>
            <div class="6u 12u(narrower)">

                <!-- Content -->
                <div class="content" align="center">
                    <canvas id="canvas" width="500" height="500">

                    </canvas>
                    <p id="info">Tag info.</p>

                </div>

            </div>
        </div>
    </section>

    <!-- Two -->
    <section class="wrapper style1 container special">
        <div class="row">
            <div class="4u 12u(narrower)">

                <section>
                    <header>
                        <h3>This is Something</h3>
                    </header>
                    <p>tag tag tag</p>
                        <footer>
                        <ul class="buttons">
                            <li><a href="#" class="button small">Learn More</a></li>
                        </ul>
                    </footer>
                </section>

            </div>
            <div class="4u 12u(narrower)">

                <section>
                    <header>
                        <h3>Also Something</h3>
                    </header>
                    <p>tag tag tag</p>
                    <footer>
                        <ul class="buttons">
                            <li><a href="#" class="button small">Learn More</a></li>
                        </ul>
                    </footer>
                </section>

            </div>
            <div class="4u 12u(narrower)">

                <section>
                    <header>
                        <h3>Probably Something</h3>
                    </header>
                    <p>tag tag tag</p>
                    <footer>
                        <ul class="buttons">
                            <li><a href="#" class="button small">Learn More</a></li>
                        </ul>
                    </footer>
                </section>

            </div>
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
<!-- Scripts -->
<script src="js/TagType.js"></script>
<script src="js/singleEdit.js"></script>
<script src="js/tagTool.js"></script>
<script src="js/jquery.min.js"></script>
<script src="js/jquery.dropotron.min.js"></script>
<script src="js/jquery.scrolly.min.js"></script>
<script src="js/jquery.scrollgress.min.js"></script>
<script src="js/skel.min.js"></script>
<script src="js/util.js"></script>

<!--[if lte IE 8]><script src="js2/ie/respond.min.js2"></script><![endif]-->
<script src="js/main.js"></script>
</body>
</html>