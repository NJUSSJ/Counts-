<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>

<html>
<!-- <style type="text/css2">
   .wrapper style4 special container {
       opacity: 45;
   }
</style> -->
<head>
    <title>LOG IN PAGE --COUNTS by Social Engineers</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="css/main.css" />
</head>
<body class="contact" style="background-image:url(http://api.dujin.org/bing/1366.php);">
<div id="page-wrapper">

    <!-- Header -->
    <header id="header">
        <h1 id="logo"><img src="images/logo.png" width="25" height="25"> <a href="http://localhost:3141/">COUNTS <span>by Social Engineers</span></a></h1>
        <nav id="nav">
            <ul>
                <li class="current"><a href="http://localhost:3141/">Welcome</a></li>
                <li class="submenu">
                    <a href="#">Layouts</a>
                    <ul>
                        <li><a href="left-sidebar.html">Profile</a></li>
                        <li><a href="right-sidebar.html">Profile Settings</a></li>
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
                <li><a onclick="showSignUp()" class="button special">Sign Up</a>
                <script>
                    function showSignUp() {
                        var sign = document.getElementById("signUp");
                        var login = document.getElementById("login");
                        login.style.display = "none";
                        sign.style.display = "block";
                    }
                </script>
                </li>
            </ul>
        </nav>
    </header>

    <!-- Main -->
    <article id="main">

       <!-- <header class="special container">
            <span class="icon fa-envelope"></span>
            <h2>LOG IN PAGE</h2>
            <p>Filling the blanks below to sign up and start tagging.</p>
        </header> -->

        <!-- One -->
        <section class="wrapper style4 special container 50%" id="login">
            <script>
                var tmp = document.getElementById("login");
                tmp.style.opacity = 0.8;
            </script>

            <!-- Content -->

            <div class="content">
                <h2>LOG IN</h2>
                <form action="<c:url value="loginCheck.html?"/>" method="post">
                    <div class="row">
                        <div class="12u">
                            <input type="text" name="userName" placeholder="Username" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="12u">
                            <input type="password" name="password" placeholder="Password" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="12u">
                            <ul class="buttons">
                                <li><input type="submit" class="special" value="LOG IN" /></li>
                            </ul>
                        </div>
                    </div>
                </form>
            </div>

        </section>

        <!-- signUp -->
        <section class="wrapper style4 special container 50%" id="signUp">
           <style>
                #signUp{
                    display: none;
                }
            </style>
            <div class="content">
                <h2>SIGN UP</h2>
                <form action="<c:url value="signUpCheck.html?"/>" method="post">
                    <div class="row">
                        <div class="12u">
                            <input type="text" name="userName" placeholder="Username" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="12u">
                            <input type="password" name="password" placeholder="Password" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="12u">
                            <ul class="buttons">
                                <li><input type="submit" class="special" href="javascript:close();" value="LOG IN" /></li>
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
            <li><a href="#" class="icon circle fa-dribbble"><span class="label">Dribble</span></a></li>
        </ul>

        <ul class="copyright">
            <li>&copy; Social Engineers</li></li>
        </ul>

    </footer>

</div>

<!-- Scripts -->
<script src="js/signUp.js"></script>
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