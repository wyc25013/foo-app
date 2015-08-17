<?php
if(isset($_POST["sid"]) && !empty($_POST["sid"])){
	session_id($_POST["sid"]);
	session_start();
	session_destroy();
}
?>