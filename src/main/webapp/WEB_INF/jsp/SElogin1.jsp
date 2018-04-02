<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>众包标注系统登录</title>
		<link rel="stylesheet" type="text/css" href="temp.css" />
	</head>
	<body>
		<section>
			<h1>众包标注系统</h1>
			<form id="jack" method="post">
				<input type="text" name="username"/>
			</form>
			<form>
				<input type="submit" value="登录" onclick="test()" />
				<script type="text/javascript" >
                    function test () {
                        alert("发送中")
                        var param = {username : "yitop"};

                        $.ajax({
                            timeout : 20000,
                            type : "POST",
                            dataType : "JSON",
                            url : "write",
                            data : param,
							contentType:"application/json",
                            success : function(){
                                alert("success");
                            }
                            //注意：这里不能加下面这行，否则数据会传不到后台
                            //contentType:'application/json;charset=UTF-8',
                        });
                    }
				</script>
			</form>

		</section>
	</body>
</html>
