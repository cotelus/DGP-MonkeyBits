<?PHP
mb_internal_encoding('UTF-8');
mb_http_output('UTF-8');
$hostname="localhost";
$database="bdr";
$username="luis";
$password="12345";
$json=array();
	if(isset($_GET["MadeBy"]) && isset($_GET["Name"]) && isset($_GET["Description"]) && isset($_GET["Image"])){

		$MadeBy = $_GET['MadeBy'];
		$Name = $_GET['Name'];
		$Description = $_GET['Description'];
		$Image = $_GET['Image'];


		$connection=mysqli_connect($hostname,$username,$password,$database);
		
		$sql = "INSERT INTO suggestedroute (IdRoute, MadeBy, Name, Description, Image) VALUES (NULL, '{$MadeBy}', '{$Name}', '{$Description}', '{$Image}')";
		$result=mysqli_query($connection,$sql);

		if($sql){
			echo mysqli_error($connection);
			$json['OPERATIONS'][0] = "SUGGEST_ROUTE";
			mysqli_close($connection);
			echo json_encode($json);
		}	
	}
?>