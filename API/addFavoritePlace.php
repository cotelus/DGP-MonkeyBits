<?PHP
mb_internal_encoding('UTF-8');
mb_http_output('UTF-8');
$hostname="localhost";
$database="routability";
$username="luis";
$password="12345";
$json=array();
	if(isset($_GET["IdPlace"]) && isset($_GET["Email"])){
		$IdPlace =$_GET['IdPlace'];
		$Email = $_GET['Email'];

		$connection=mysqli_connect($hostname,$username,$password,$database);
		
		$sql = "INSERT INTO favoriteplaces (IdPlace, Email) VALUES ('{$IdPlace}', '{$Email}')";
		$result=mysqli_query($connection,$sql);

		if($sql){
			$json["OPERATION"] = "ADD_FAVORITE_PLACE";
			mysqli_close($connection);
			echo json_encode($json);
		}
?>