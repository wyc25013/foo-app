<?php
require 'connectdb.inc.php';

$account = $_POST['postAcct'];
$password = $_POST['postPwd'];
$firstname = $_POST['postFn'];
$lastname = $_POST['postLn'];

$query = "INSERT INTO dentInfo VALUES ('$account', '$password', '$firstname', '$lastname');";
if($query_run = mysql_query($query)){
	echo "sign up success! Welcome ";
}

?>