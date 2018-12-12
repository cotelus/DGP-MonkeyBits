<?PHP
$hostname="localhost";
$database="routability";
$username="luis";
$password="12345";
$json=array();
	if(isset($_GET["MadeBy"]) && isset($_GET["Description"]) && isset($_GET["Localitation"]) && isset($_GET["Name"]) && isset($_GET["Image"]) && isset($_GET["RedMovility"]) && isset($_GET["RedVision"]) && isset($_GET["ColourBlind"]) && isset($_GET["Deaf"]) && isset($_GET["Foreigner"])){

		$MadeBy = $_GET['MadeBy'];
		$Description = $_GET['Description'];
		$Localitation = $_GET['Localitation'];
		$Name = $_GET['Name'];
		$Image = $_GET['Image'];
		$RedMovility = $_GET['RedMovility'];
		$RedVision = $_GET['RedVision'];
		$ColourBlind = $_GET['ColourBlind'];
		$Deaf = $_GET['Deaf'];
		$Foreigner = $_GET['Foreigner'];


		$connection=mysqli_connect($hostname,$username,$password,$database);
		
		$sql = "INSERT INTO suggestedplace (IdPlace, MadeBy, Description, Localitation, Name, Image, RedMovility, RedVision, ColourBlind, Deaf, Foreigner) VALUES (NULL, '{$MadeBy}', '{$Description}', '{$Localitation}', '{$Name}', '{$Image}', '{$RedMovility}', '{$RedVision}', '{$ColourBlind}', '{$Deaf}', '{$Foreigner}')";
		$result=mysqli_query($connection,$sql);

		if($sql){
			echo mysqli_error($connection);
			$json["OPERATION"] = "SUGGESTPLACE";
			mysqli_close($connection);
			echo json_encode($json);
		}	
	}
?>