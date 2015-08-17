<?php
//include "ifLogin.inc.php";
//$_SESSION["id"] = "d";
if(isset($_POST["sid"]) && !empty($_POST["sid"])){
	session_id($_POST["sid"]);
	session_start();
	echo $_POST["sid"];
}else{
	echo "please log in!";
}
?>
