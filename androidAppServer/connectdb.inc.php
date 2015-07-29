<?php
$mysql_host = 'localhost';
$mysql_user = 'root';
$mysql_password = '';
$app_db = 'dbForApp';

if(!mysql_connect($mysql_host, $mysql_user, $mysql_password) || !mysql_select_db($app_db)){
	die(mysql_error());
}
?>