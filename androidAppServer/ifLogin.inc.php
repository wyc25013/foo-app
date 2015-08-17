<?php
session_start();
function ifLoggedIn(){
	if(isset($_SESSION["id"]) 
		//&& isset($_SESSION["acct"]) && isset($_SESSION["pswd"])
		){
		return true;
	}else{
		return false;
	}
}
?>