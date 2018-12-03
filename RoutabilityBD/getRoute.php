<?PHP
$hostname="localhost";
$database="routability";
$username="luis";
$password="12345";
$json=array();
	if(isset($_GET["Name"])){
		$Name=$_GET['Name'];

		$conexion=mysqli_connect($hostname,$username,$password,$database);
		
		$consulta="SELECT IdRoute, Email, MadeBy, Name, Description FROM route WHERE Name= '{$Name}'";
		$resultado=mysqli_query($conexion,$consulta);

		if($consulta){
		
			if($reg=mysqli_fetch_array($resultado)){
				$json['datos'][]=$reg;
			}
			mysqli_close($conexion);
			echo json_encode($json);
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