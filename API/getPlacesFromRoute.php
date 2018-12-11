<?PHP
$hostname="localhost";
$database="routability";
$username="luis";
$password="12345";
$json=array();
$json2 = array();

	if(isset($_GET["IdRoute"])){
		$IdRoute = $_GET['IdRoute'];

		$connection=mysqli_connect($hostname,$username,$password,$database);
		
		$sql="SELECT * FROM place LEFT JOIN appearverified ON place.IdPlace = appearverified.IdPlace WHERE appearverified.IdRoute = '{$IdRoute}'";
		$result=mysqli_query($connection,$sql);

		if($sql){
			$x = 0;
			while($reg = mysqli_fetch_array($result)){
				$json['data'][$x]=$reg;
				$x++;

			}
			$json['OPERATION']="GET_PLACES_FROM_ROUTE";
			mysqli_close($connection);
			echo json_encode($json);
		}
	}
?>