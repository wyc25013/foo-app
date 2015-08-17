<?php
session_start();
$httpreferer = $_SERVER['HTTP_REFERER'];
if($httpreferer == 'http://localhost/androidAppServer/patientLogin.php'){
	$_SESSION["id"] = 'p';
}else{
	$_SESSION["id"] = 'd';
}
?>