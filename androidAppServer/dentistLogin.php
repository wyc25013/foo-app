<?php
require 'connectdb.inc.php';

$account = $_POST['postAcct'];
$password = $_POST['postPwd'];

$query = "SELECT email, password FROM dentInfo WHERE BINARY email = '$account' AND BINARY password = '$password';";
if($query_run = mysql_query($query)){
	$query_num_rows = mysql_num_rows($query_run);
	if($query_num_rows == 1){
	//	include 'session.inc.php';
		session_start();
		$sid = session_id();
		echo $sid;
	}else{
		echo "not working";
	}
}
?>