<?php

$MESSAGE = "";

session_start();

//Consultamos los datos de la obra

$conexion = mysqli_connect("localhost", "root", "");
$BD = mysqli_select_db($conexion, "bdr");

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
$search = '';
if ($_POST['search']) {
    $search = $_POST['search'];
}

if(isset($_POST["aniadir"])){
 
    if(!empty($_POST['nombre']) && !empty($_POST['descripcion']) && !empty($_POST['lugares']) && !empty($_POST['imagen'])){
         
         $nombre=$_POST['nombre'];
         $descripcion=$_POST['descripcion'];
         $imagen=$_POST['imagen'];
         $email = $_SESSION['EMAIL'];

        if(!($QUERY = mysqli_query($conexion, "INSERT INTO `route`(`IdRoute`, `Email`, `MadeBy`, `Name`, `Description`, `Image`) VALUES (null, '$email', null, '$nombre', '$descripcion', '$imagen')"))){

            echo "Fallo del query de rutas";
            exit();
        }
        
        if(!($ruta = mysqli_query($conexion, "SELECT * FROM `route` WHERE `Name`='".$nombre."'"))){

            echo "Fallo del query de obtención de id de ruta";
            exit();
        }
        
        $route = mysqli_fetch_assoc($ruta);
        $id = $route["IdRoute"];
        $sequence=1;
        $lugares = $_POST['lugares'];

        foreach ($lugares as $lugar=>$value) {

            if(!($QUERY = mysqli_query($conexion, "INSERT INTO `appearverified`(`IdPlace`, `IdRoute`, `Sequence`) VALUES ('$value', '$id', '$sequence')"))){

                echo "Fallo del query de adición de lugares";
                exit();
            }
            
            $sequence+=1;
        }

        $MESSAGE = "RUTA AÑADIDA";
    
    } else {
      
        $MESSAGE = "ERROR AL AÑADIR LA RUTA";
     }
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

<body class="bg-primario">
    <div class="py-5">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="display-3"><b>Routability: Granada</b></h1>
                </div>
            </div>

            <?php if (!empty($MESSAGE)) {echo "<p class=\"ERROR\">" . "MENSAJE: ". $MESSAGE . "</p>";}
          ?>

            <fieldset>

                <h1>Añadir una ruta</h1>

                <form action="addRoutes.php" method="post">
                    <div class="row">

                        <div class="col-md-6">
                            <div class="form-group">
                                <h4><b>Nombre de la ruta:</b></h4><input type="nombre" class="form-control" required value="" name="nombre" placeholder="Introducir el nombre" onBlur="if(this.value=='')this.value='nombre'" onFocus="if(this.value=='nombre')this.value='' ">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <h4><b>Imagen de la ruta:</b></h4><input type="imagen" class="form-control" required value="" name="imagen" placeholder="Introducir la imagen" onBlur="if(this.value=='')this.value='imagen'" onFocus="if(this.value=='imagen')this.value='' ">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <h4><b>Descripción de la ruta:</b></h4><br /><textarea required value="" name="descripcion" placeholder="Escribe la descripción de la ruta..." maxlength="10000" rows="10" cols="56" onFocus="if(this.value=='descripcion')this.value='' "></textarea>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <h4><b>Lugares:</b></h4>
                                <div class="py-2" style="">
                                  <div class="container py-3 px-3">
                                    <div class="row">
                                      <form action="" method="post" name="search_form" id="search_form" class="col-md-12">
                                        <div class="container">
                                          <div class="row">
                                            <div class="col-md-9">
                                              <div class="form">
                                                  <input type="text" placeholder="Buscar lugar..." name="search" id="search">
                                              </div>
                                            </div>
                                            <div class="col-md-3">
                                              <div class="form" style="background-color: white; padding:5px; height:50px; padding-top:12px;">
                                                  <input type="submit" id="aplicar-cambios" name="aplicar-cambios" value="Buscar" style="margin-right: 4%; float:right;">
                                              </div>
                                            </div>
                                          </div>
                                        </form>
                                      </div>
                                    </div>
                                  </div>
                                <div class="scroll" style="border-radius:5px; background-color:white;">
                                    <?php

                                    $resultado_lugares = mysqli_query($conexion, "SELECT * FROM `place` where IdPlace like '%".$search."%' or Name like '%".$search."%' or Localitation like '%".$search."%'");
      
                                    if ($resultado_lugares->num_rows > 0) {
                                        while($array_resultado =  mysqli_fetch_assoc($resultado_lugares)) {
                                            echo"<p>&nbsp&nbsp<input name='lugares[]' type='checkbox' id='".$array_resultado['IdPlace']."' value=".$array_resultado['IdPlace']."<br/>&nbsp&nbsp<b>".$array_resultado['Name']."</b></p>";
                                        }
                                    }
                                    ?>
                                </div>
                                <hr>
                                <div>
                                    <input class="btn btn btn-primary btn-light icon-home" type="submit" name="aniadir" value="Añadir">
                                    &nbsp<a class="btn btn btn-primary btn-light icon-home" href="Home.php">&nbsp;Volver a administración</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </fieldset>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous" style=""></script>
</body>

</html>
