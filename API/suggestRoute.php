<?PHP
mb_internal_encoding('UTF-8');
mb_http_output('UTF-8');
$hostname="localhost";
$database="bdr";
$username="luis";
$password="12345";
$json=array();
$places=array();
	if(isset($_GET["MadeBy"]) && isset($_GET["Name"]) && isset($_GET["Description"]) && isset($_GET["Image"]) && isset($_GET["Places"])){

		$MadeBy = $_GET['MadeBy'];
		$Name = $_GET['Name'];
		$Description = $_GET['Description'];
		$Image = $_GET['Image'];
		$places = json_decode($_GET["Places"]);
		$routeId = -1;


		$connection=mysqli_connect($hostname,$username,$password,$database);
		
		$sql = "INSERT INTO suggestedroute (IdRoute, MadeBy, Name, Description, Image) VALUES (NULL, '{$MadeBy}', '{$Name}', '{$Description}', '{$Image}')";
		$result=mysqli_query($connection,$sql);

		$json['OPERATIONS'][0] = "SUGGEST_ROUTE";
	
		if (&sql) {
			$sql2 = "SELECT LAST_INSERT_ID()";
			$result = mysqli_query($connection,$sql2);
			
			if ($sql2) {
				if ($reg=mysqli_fetch_array($result)) {
					$routeId = $reg['LAST_INSERT_ID()'];
					$json['SUGGEST_ROUTE'] = $reg['LAST_INSERT_ID()'];
				}
			}
		}

		if ($routeId != -1) {
			$stringTuples = "";
			$count = 0;
			foreach($places as $place) {
				$idPlace = $place['IdPlace'];
				$sequence = $place['Sequence'];
				$stringTuples += " ('{$routeId}','{$idPlace}','{$sequence}'),"
			}
			$stringTuples = trim($stringTuples, ',');
			
			$sql3 = "INSERT INTO suggestedroute (IdRoute, MadeBy, Name, Description, Image) VALUES " + $stringTuples;
			echo $sql3;
			$result=mysqli_query($connection,$sql3);

			if($sql && sql2 && $sql3){
				echo mysqli_error($connection);
				mysqli_close($connection);
				echo json_encode($json);
			}
		}
	}
?>