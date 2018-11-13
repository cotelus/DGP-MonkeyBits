<?php

$id = $_GET["id"];
$MESSAGE = "";

session_start();

//Consultamos los datos de la obra

$conexion = mysqli_connect("localhost", "ramon", "ramon");
$BD = mysqli_select_db($conexion, "routability");

//Comprueba conexion
if(mysqli_connect_errno()){
    
    echo "Fallo de conexión: ".mysqli_connect_error();
    exit();
}

/* cambiar el conjunto de caracteres a utf8 */
if (!$conexion->set_charset("utf8")) {
            
    printf("Error cargando el conjunto de caracteres utf8: %s\n", $conexion->error);
    exit();
}

if(isset($_POST["editar"])){
         
         $id=1;
         $nombre=$_POST['nombre'];
         $descripcion=$_POST['descripcion'];
         $imagen=$_POST['imagen'];

        //EDITAMOS TABLA RUTA
        if(!($QUERY = mysqli_query($conexion, "UPDATE `route` SET `Name`='".$nombre."',`Description`='".$descripcion."',`Image`='".$imagen."' WHERE `IdRoute` =".$id))){

            $MESSAGE = "ERROR AL EDITAR LA RUTA";
            echo "Fallo del query de rutas";
            exit();
        }
    
        //EDITAMOS TABLA APPEAR
        if(!($text_result2 = mysqli_query($conexion, "SELECT Sequence FROM appear WHERE IdRoute=".$id." ORDER BY Sequence ASC"))){
    
            echo "Fallo del query de selección de puntos de ruta";
            exit();

        }
    
        $texto2 = mysqli_fetch_assoc($text_result2);
    
        $sequence=$texto2['Sequence'];
        $lugares = $_POST['lugares'];   //ESTE PUEDE SER EL PROBLEMA, QUE RECOJA SOLO LOS NUEVOS LUGARES

        foreach ($lugares as $lugar=>$value) {

            if(!($QUERY2 = mysqli_query($conexion, "UPDATE `appear` SET `IdPlace`='".$value."', `Sequence`='".$sequence."' WHERE `IdRoute` =".$id))){

                $MESSAGE = "ERROR AL EDITAR LOS PUNTOS DE RUTA";
                echo "Fallo del query de edición de lugares";
                exit();
            }
            
            $sequence+=1;
        }
    
            $nomb=$nombre;
            $desc=$descripcion;
            $img=$imagen;
            
            $MESSAGE = "RUTA EDITADA";

} else {
        
        //OBTENEMOS LOS DATOS QUE YA TENIAMOS DE LA BBDD PARA MOSTRARLOS Y SER EDITABLES
        if(!($text_result = mysqli_query($conexion, "SELECT * FROM route WHERE IdRoute=".$id))){
    
            echo "Fallo del query de selección de ruta";
            exit();

        }
    
        $texto = mysqli_fetch_assoc($text_result);
        $nomb=$texto['Name'];
        $desc=$texto['Description'];
        $img=$texto['Image'];    

}
       
?>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>RoutabilityAdmin</title>
    <link rel="shortcut icon" href="./img/monkeybits2.png" type="image/png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="./theme.css" type="text/css">
    <link rel="stylesheet" href="./fonts.css" type="text/css">
</head>

<body class="bg-primary">
    <div class="py-5">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="display-3"><b>Routability: Granada</b></h1><a class="btn btn-secondary" href="admin.php" style="	transform:  translateX(900px)  translateY(-60px) ;">Volver a administración</a>
                </div>
            </div>

            <?php if (!empty($MESSAGE)) {echo "<p class=\"ERROR\">" . "MENSAJE: ". $MESSAGE . "</p>";}
          ?>

            <fieldset>

                <h1>Información de la ruta</h1>
                
                <?php echo"<form action='editRoutes.php?id='".$id."' method='post'>"; ?>
                    <div class="row">

                        <div class="col-md-6">
                            <?php echo"<div class='form-group'> <label><b>Nombre de la ruta:</b></label><input type='nombre' class='form-control' required value='".$nomb."' name='nombre' placeholder='Introducir el nombre' onBlur='if(this.value=='')this.value='nombre'' onFocus='if(this.value=='nombre')this.value='' '></div>"; ?>
                        </div>
                        <div class="col-md-6">
                            <?php echo"<div class='form-group'> <label><b>Imagen de la ruta:</b></label><input type='imagen' class='form-control' required value='".$img."' name='imagen' placeholder='Introducir la imagen' onBlur='if(this.value=='')this.value='imagen'' onFocus='if(this.value=='imagen')this.value='' '></div>"; ?>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <?php echo "<div class='form-group'> <label>Descripción de la ruta</label><br/><textarea name='descripcion' placeholder='Escribe la descripción de la ruta...' maxlength='10000' rows='10' cols='50' onFocus='if(this.value=='descripcion')this.value='' '>".$desc."</textarea></div>";?>
                        </div>
                        <!-- AQUI ES DONDE PUEDE ESTAR EL PROBLEMA -->
                        <div class="col-md-6">
                            <div class="form-group"> <label><b>Lugares:</b></label><br />
                                <div class="scroll" style="border-radius:5px; background-color:white;">
                                    <?php
                                    //SE OBTIENEN DE LA BBDD TODOS LUGARES
                                    $resultado_lugares = mysqli_query($conexion, "SELECT * FROM `place`");
                                    
                                    //SE OBTIENEN DE LA BBDD LOS LUGARES DE LA RUTA
                                    $resultado_lugares_appear = mysqli_query($conexion, "SELECT * FROM `appear` WHERE IdRoute=".$id);

                                    if ($resultado_lugares->num_rows > 0) {
                                    
                                        //BUCLE DE LUGARES DE LA RUTA
                                        while($array_resultado2 =  mysqli_fetch_assoc($resultado_lugares_appear)){
                                        
                                            //BUCLE DE TODOS LOS LUGARES
                                            while($array_resultado =  mysqli_fetch_assoc($resultado_lugares)) {
                                                
                                                //COMPARAMOS AMBOS LUGARES, SI ESTA EN LA RUTA, APARECERA MARCADO
                                                if($array_resultado['IdPlace'] == $array_resultado2['IdPlace']){
                                                    //EL PROBLEMA PUEDE SER AL COGER LOS LUGARES lugares[]
                                                    echo"<p>&nbsp&nbsp<input name='lugares[]' type='checkbox' id='".$array_resultado['IdPlace']."' value=".$array_resultado['IdPlace']." checked <br/>&nbsp&nbsp<b>".$array_resultado['Name']."</b></p>";

                                                }
                                                //SI NO ESTA EN LA RUTA, APARECERA DESMARCADO
                                                else{
                                                    //EL PROBLEMA PUEDE SER AL COGER LOS LUGARES lugares[]
                                                    echo"<p>&nbsp&nbsp<input name='lugares[]' type='checkbox' id='".$array_resultado['IdPlace']."' value=".$array_resultado['IdPlace']."<br/>&nbsp&nbsp<b>".$array_resultado['Name']."</b></p>";
                                                }
                                            }
                                        }
                                    }
                                    ?>
                                    </div>
                                    <div>
                                        <input class="bg-secondary" type="submit" name="editar" value="Editar">
                                    </div>
                            </div>
                        </div>
                    </div>
                <?php echo"</form>";?>
            </fieldset>
        </div>
    </div>
    <div class="py-3 bg-secondary" style="">
        <div class="container">
            <div class="row">
                <div class="col-md-12 text-center">
                    <p class="mb-0"><b>© 2018 MonkeyBits. All rights reserved</b></p>
                </div>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous" style=""></script>
</body>

</html>