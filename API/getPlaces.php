<?PHP

$hostname="localhost";
$database="routability";
$username="luis";
$password="12345";
$json=array();
$json2 = array();

	if(isset($_GET["Start"])){
		$Start = $_GET['Start'];

		$connection=mysqli_connect($hostname,$username,$password,$database);
		
		$sql="SELECT * FROM place LIMIT $Start,10";
		$result=mysqli_query($connection,$sql);

		if($sql){
			$x = 0;
			while($reg = mysqli_fetch_array($result)){
				$json['data'][$x]=$reg;
				$x++;

			}
			$json['OPERATION']="GET_PLACES";
			mysqli_close($connection);
			echo json_encode($json);
		}
	}
?>