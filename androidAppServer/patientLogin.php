<?php
//session_start();
require 'connectdb.inc.php';

$account = $_POST['postAcct'];
$password = $_POST['postPwd'];

$query = "SELECT email, password FROM userInfo WHERE BINARY email = '$account' AND BINARY password = '$password';";
if($query_run = mysql_query($query)){
	$query_num_rows = mysql_num_rows($query_run);
	if($query_num_rows == 1){
		
	//	include 'session.inc.php';
		
	//	$_SESSION["id"] = 'p';
	//	$_SESSION["acct"] = $account;
	//	$_SESSION["pswd"] = $password;
		session_start();
		$sid = session_id();	
		echo $sid;
	}else{
		echo "not working";
	}
}
?>