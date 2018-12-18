<?PHP
mb_internal_encoding('UTF-8');
mb_http_output('UTF-8');
$hostname="localhost";
$database="bdr";
$username="luis";
$password="12345";
$json=array();

	if(isset($_GET["IdRoute"]) && isset($_GET["Email"])){
		$IdRoute =$_GET['IdRoute'];
		$Email = $_GET['Email'];

		$connection=mysqli_connect($hostname,$username,$password,$database);
		
		$sql = "UPDATE `routecomments` SET `Reported`=1 WHERE `IdRoute`= {$IdRoute} AND `Email`= '{$Email}'";
		$result=mysqli_query($connection,$sql);

		if($sql){
			echo mysqli_error($connection);
			$json['OPERATIONS'][0]="REPORT_ROUTE_COMMENT";
			mysqli_close($connection);
			echo json_encode($json);
		}
	}
?>