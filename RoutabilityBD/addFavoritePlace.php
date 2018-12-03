<?PHP
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
		//$consulta="SELECT IdRoute, Email, MadeBy, Name, Description FROM route WHERE Name= '{$Name}'";
		$resultado=mysqli_query($connection,$sql);

		if($sql){
		
		/*
			if($reg=mysqli_fetch_array($resultado)){
				$json['datos'][]=$reg;
			}
			mysqli_close($conexion);
			echo json_encode($json);
			*/
			mysqli_close($connection);
		}



		else{
			$results["IdRoute"]='';
			$results["Email"]='';
			$results["MadeBy"]='';
			$results["Name"]='';
			$results["Description"]='';
			$json['datos'][]=$results;
			echo json_encode($json);
		}
		
	}
	else{
			$results["IdRoute"]='';
			$results["Email"]='';
			$results["MadeBy"]='';
			$results["Name"]='';
			$results["Description"]='';
			$json['datos'][]=$results;
			echo json_encode($json);
		}
?>