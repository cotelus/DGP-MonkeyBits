<?PHP
mb_internal_encoding('UTF-8');
mb_http_output('UTF-8');
$hostname="localhost";
$database="bdr";
$username="luis";
$password="12345";
$json=array();
	if(isset($_GET["IdRoute"])){
		$IdRoute = $_GET['IdRoute'];

		$connection=mysqli_connect($hostname,$username,$password,$database);
		
		$sql = "SELECT IdRoute, Email, Content, Date, Time, Reported FROM routecomments WHERE IdRoute = '{$IdRoute}'";
		$result=mysqli_query($connection,$sql);

		if($sql){
			$x = 0;
			while($reg = mysqli_fetch_array($result)){
				$json['data'][$x]=$reg;
				$x++;
			}
			
			$json['OPERATION']="GET_ROUTE_COMMENTS";
			mysqli_close($connection);
			echo json_encode($json);
		}
	}
?>
