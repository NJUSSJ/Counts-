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
<body class="contact" style="background-image:url(http://api.dujin.org/bing/1366.php);" onload="judge(${requestScope.error})">
<script>
    function judge(returnData) {
        if(returnData!=null){
            alert(returnData);
        }
    }
</script>
<div id="page-wrapper">

    <!-- Header -->
    <header id="header">
        <h1 id="logo"><img src="images/logo.png" width="25" height="25"> <a href="http://localhost:3141/">COUNTS <span>by Social Engineers</span></a></h1>
        <nav id="nav">
            <ul>
                <li class="current"><a href="/getMissionDetails?missionName=社会人的任务">Welcome</a></li>
                <li class="submenu">
                    <a href="#">帮助</a>
                    <ul>
                        <li><a href="left-sidebar.html">网站手册</a></li>
                        <li><a href="right-sidebar.html">找回密码</a></li>
                    </ul>
                </li>
                <li><a onclick="showSignUp()" class="button special">注册</a>
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
                <h2>登录</h2>
                <form action="<c:url value="loginCheck.html?"/>" method="post">
                    <div class="row">
                        <div class="12u">
                            <input type="text" name="userName" placeholder="用户名/手机" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="12u">
                            <input type="password" name="password" placeholder="密码" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="12u">
                            <ul class="buttons">
                                <li><input type="submit" class="special" value="登录" /></li>
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
                    opacity: 0.8;
                }
            </style>
            <div class="content">
                <h2>注册</h2>
                <form action="<c:url value="/signUpDetails"/>" method="post" id="signForm">
                    <div class="row">
                        <div class="12u">
                            <input type="text" name="phoneNumber" placeholder="手机号" required="required" id="phoneNumber" />
                        </div>
                    </div>

                    <div class="row 50%" id="variCodeArea">
                        <div class="6u 12u(mobile)" align="left">
                            <input type="text" name="variCode" placeholder="验证码" id="variCode">
                        </div>
                        <div class="6u 12u(mobile)" align="left">
                            <span id="time">秒后可重新发送</span> <a id="resend" onclick="sendVari()">重新发送验证码</a>

                            <style>
                                #resend:hover{
                                    cursor: pointer;
                                }
                                #resend{
                                    display: none;
                                }
                            </style>
                        </div>

                        <style>
                            #variCodeArea{
                                display: none;
                            }
                        </style>
                    </div>

                    <div class="row">
                        <div class="12u">
                        <ul class="buttons">
                            <li><input type="button" value="发送验证码"  class="button special" onclick="sendVari()" id="button1"/></li>
                            <li><input type="button" value="确认"  class="button special"  id="button2"/></li>

                            <style>
                                #button2{
                                    display: none;
                                }
                            </style>
                            <script>
                                var phoneNumber=document.getElementById("phoneNumber");
                                var variCode;

                                var time=60;
                                var t;
                                var existed=false;
                                function setTime() {
                                    if(time==0){
                                        document.getElementById("resend").style.display="inline";
                                        document.getElementById("time").style.display="none";
                                        return;
                                    }
                                    document.getElementById("time").innerHTML=time+"秒后可重新发送";
                                    time=time-1;
                                    t=setTimeout("setTime()",1000);

                                }
                                function sendVari() {
                                    if(document.getElementById("phoneNumber").value==null||document.getElementById("phoneNumber").value==""||document.getElementById("phoneNumber").value.length!=11){
                                        alert("请正确输入手机号码");
                                        return;
                                    }
                                    $.ajax({
                                        type: "POST",
                                        url: "sendVaricationCode",
                                        contentType: "application/json",
                                        dataType: "json",
                                        data: document.getElementById("phoneNumber").value,
                                        //data: JSON.stringify(a),
                                        success: function (returnData) {
                                            if(returnData=="0"){
                                                alert("该手机号已注册过！");
                                                existed=true;
                                                return;
                                            }
                                            variCode=returnData;
                                            alert(returnData);
                                            document.getElementById("variCodeArea").style.display="block";
                                            document.getElementById("button1").style.display="none";
                                            document.getElementById("button2").style.display="block";
                                            document.getElementById("time").style.display="inline";
                                            document.getElementById("resend").style.display="none";
                                            time=60;
                                            setTime();
                                            document.getElementById("button2").addEventListener("click", function () {
                                                confirmCode();
                                            });

                                        },
                                        error:function () {
                                            alert("发送失败,请检查您的网络链接是否正常");
                                            document.getElementById("button1").value="发送验证码";
                                        }
                                    });
                                }
                                function confirmCode() {
                                    var enterCode=document.getElementById("variCode").value;
                                    if(enterCode==variCode){
                                        var form=document.getElementById("signForm");
                                        form.submit();
                                    }else{
                                        alert("验证码错误！");
                                    }
                                }
                            </script>
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