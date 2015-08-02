<?php
require 'connectdb.inc.php';

$account = $_POST['postAcct'];
$password = $_POST['postPwd'];

$query = "SELECT email, password FROM dentInfo WHERE email = '$account' AND password = '$password';";
if($query_run = mysql_query($query)){
	$query_num_rows = mysql_num_rows($query_run);
	if($query_num_rows == 1){
		echo "working";
	}else{
		echo "not working";
	}
}
?>