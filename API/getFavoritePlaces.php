<?PHP
mb_internal_encoding('UTF-8');
mb_http_output('UTF-8');
$hostname="localhost";
$database="bdr";
$username="luis";
$password="12345";
$json=array();
$jsonTuple;
	if(isset($_GET["Email"])){
		$Email = $_GET['Email'];

		$connection=mysqli_connect($hostname,$username,$password,$database);
		
		$sql="SELECT IdPlace FROM favoriteplaces WHERE Email= '{$Email}'";
		$result=mysqli_query($connection,$sql);

		if($sql){
			$x = 0;
			$jsonTuple;
			$json['OPERATIONS'][0]="GET_FAVORITE_PLACES";
			while($reg = mysqli_fetch_array($result)){
				$jsonTuple['IdPlace']=$reg['IdPlace'];
				$json['GET_FAVORITE_PLACES'][$x] = $jsonTuple;
				$x++;
			}
			mysqli_close($connection);
			echo json_encode($json);

		}
	}
?>

