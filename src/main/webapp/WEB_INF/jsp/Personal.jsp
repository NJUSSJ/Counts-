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
    <noscript><link rel="stylesheet" href="css2/noscript.css" /></noscript>
</head>
<body>

<!-- Wrapper-->
<div id="wrapper">

    <!-- Nav -->
    <nav id="nav">
        <a href="#me" class="icon fa-home active"><span>Home</span></a>
        <a href="#work" class="icon fa-folder"><span>Work</span></a>
        <a href="#info" class="icon fa-envelope"><span>Personal</span></a>
        <a href="<c:url value="backMain.html?"/>" class="icon fa-twitter"><span>Back</span></a>
    </nav>

    <!-- Main -->
    <div id="main">

        <!-- Me -->
        <article id="me" class="panel">
            <header>
                <h1>User Example</h1>
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
                <h2>Work</h2>
            </header>
            <p>
                标注过的图集列表
            </p>
            <section>
                <div class="row">
                    <div class="4u 12u$(mobile)">
                        <a href="#" class="image fit"><img src="images/pic01.jpg" alt=""></a>
                    </div>
                    <div class="4u 12u$(mobile)">
                        <a href="#" class="image fit"><img src="images/pic02.jpg" alt=""></a>
                    </div>
                    <div class="4u$ 12u$(mobile)">
                        <a href="#" class="image fit"><img src="images/pic03.jpg" alt=""></a>
                    </div>
                    <div class="4u 12u$(mobile)">
                        <a href="#" class="image fit"><img src="images/pic04.jpg" alt=""></a>
                    </div>
                    <div class="4u 12u$(mobile)">
                        <a href="#" class="image fit"><img src="images/pic05.jpg" alt=""></a>
                    </div>
                    <div class="4u$ 12u$(mobile)">
                        <a href="#" class="image fit"><img src="images/pic06.jpg" alt=""></a>
                    </div>
                    <div class="4u 12u$(mobile)">
                        <a href="#" class="image fit"><img src="images/pic07.jpg" alt=""></a>
                    </div>
                    <div class="4u 12u$(mobile)">
                        <a href="#" class="image fit"><img src="images/pic08.jpg" alt=""></a>
                    </div>
                    <div class="4u$ 12u$(mobile)">
                        <a href="#" class="image fit"><img src="images/pic09.jpg" alt=""></a>
                    </div>
                    <div class="4u 12u$(mobile)">
                        <a href="#" class="image fit"><img src="images/pic10.jpg" alt=""></a>
                    </div>
                    <div class="4u 12u$(mobile)">
                        <a href="#" class="image fit"><img src="images/pic11.jpg" alt=""></a>
                    </div>
                    <div class="4u$ 12u$(mobile)">
                        <a href="#" class="image fit"><img src="images/pic12.jpg" alt=""></a>
                    </div>
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
                        <div class="6u 12u$(mobile)">
                            <input type="text" name="name" placeholder="Name" />
                        </div>
                        <div class="6u$ 12u$(mobile)">
                            <input type="text" name="phoneNumber" placeholder="phoneNumber" />
                        </div>
                        <div class="12u$">
                            <input type="text" name="subject" placeholder="Subject" />
                        </div>
                        <div class="12u$">
                            <textarea name="message" placeholder="Message" rows="8"></textarea>
                        </div>
                        <!-- <div class="12u$">
                            <input type="submit" value="Send Message" />
                        </div> -->
                    </div>
                </div>
            </form>
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

</body>
</html>
