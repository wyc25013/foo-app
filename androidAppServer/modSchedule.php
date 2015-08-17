<?php
include 'ifLogin.inc.php';
if(ifLoggedIn()){
	if($_SESSION["id"] == 'd'){

	}else{	// only dentists can modify the schedule

	}
}else{
	echo "please log in!";
}

?>