<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OurSearch</title>
</head>
<style>
body{background-image:url('http://insightreport.debeersgroup.com/_images/backgrounds/global-consumer-demand-bg.jpg');background-repeat:no-repeat;}


input[type="text"]{position:relative;left:5px;top:30px;font-size:13px;
font-style:italic;width:500px;height:20px;z-index:2;border:2px solid#FFFFFF;border-radius: 15px;box-shadow:5px 3px 5px 0px rgba(42,42,42,.75);}
input[type="text"]:hover{box-shadow:5px 3px 5px 0px rgba(23,87,214,.75);}
input[type="submit"]{position:relative;left:5px;top:30px;border-radius:10px;color:#000000;background-color:#FFFFFF;box-shadow:5px 3px 5px 0px rgba(42,42,42,.75);}
input[type="submit"]:hover{color:#0099FF;box-shadow:5px 3px 5px 0px rgba(23,87,214,.75)}
</style>
<body>


<form action='${requestUri}' method='get'>
<input  type='text' name='keyword' placeholder = 'keyword'  />
<input  type='submit' value='search' />
</form>
</body>
</html>

