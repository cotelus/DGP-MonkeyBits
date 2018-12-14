<?PHP
mb_internal_encoding('UTF-8');
mb_http_output('UTF-8');
$hostname="localhost";
$database="bdr";
$username="luis";
$password="12345";

$json=array();
	if(isset($_GET["IdPlace"])){
		$IdPlace=$_GET['IdPlace'];
		$connection=mysqli_connect($hostname,$username,$password,$database);
		
		$sql1="SELECT * FROM place WHERE IdPlace = '{$IdPlace}'";
		$result=mysqli_query($connection,$sql1);

		if($sql1){
			$jsonTuple;
			$json['OPERATIONS'][0]="GET_PLACE";
			if($reg=mysqli_fetch_array($result)){
				$jsonTuple1['IdPlace'] = $reg['IdPlace'];
				$jsonTuple1['Email'] = $reg['Email'];
				$jsonTuple1['MadeBy'] = $reg['MadeBy'];
				$jsonTuple1['Name'] = $reg['Name'];
				$jsonTuple1['Description'] = $reg['Description'];
				$jsonTuple1['Localitation'] = $reg['Localitation'];
				$jsonTuple1['Image'] = $reg['Image'];
				$jsonTuple1['RedMovility'] = $reg['RedMovility'];
				$jsonTuple1['RedVision'] = $reg['RedVision'];
				$jsonTuple1['ColourBlind'] = $reg['ColourBlind'];
				$jsonTuple1['Deaf'] = $reg['Deaf'];
				$jsonTuple1['Foreigner'] = $reg['Foreigner'];

				$json['GET_PLACE'] = $jsonTuple1;
			}
		}

		$sql2 = "SELECT * FROM placecomments WHERE IdPlace = '{$IdPlace}' and Reported = 0";
		$result=mysqli_query($connection,$sql2);

		if($sql2){
			$jsonTuple2;
			$x = 0;
			$json['OPERATIONS'][1]="GET_COMMENTS_FROM_PLACE";
			while($reg = mysqli_fetch_array($result)){
				$jsonTuple2['IdPlace'] = $reg['IdPlace'];
				$jsonTuple2['Email'] = $reg['Email'];
				$jsonTuple2['Content'] = $reg['Content'];
				$jsonTuple2['Date'] = $reg['Date'];
				$jsonTuple2['Time'] = $reg['Time'];

				$json['GET_COMMENTS_FROM_PLACE'][$x]=$jsonTuple2;
				$x++;
			}
		}

		$sql3 = "SELECT AVG(Rating) FROM visit WHERE IdPlace IN ('".$IdPlace."')";
		$result=mysqli_query($connection,$sql3);

		if($sql3){
			$jsonTuple3;
			$json['OPERATIONS'][2]="GET_AVERAGE_SCORE_PLACE";
			if($reg=mysqli_fetch_array($result)){
				$jsonTuple3['score']=$reg[0];
				$json['GET_AVERAGE_SCORE_PLACE']=$jsonTuple3['score'];
			}
		}

		if ($sql1 && $sql2 && $sql3) {
			mysqli_close($connection);
			echo json_encode($json);
		}
	}
?>