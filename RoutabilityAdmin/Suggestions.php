<?php

  $MESSAGE = "";

  session_start();

  //Consultamos los datos de la obra

  $conexion = mysqli_connect("localhost", "root", "");
  $BD = mysqli_select_db($conexion, "routabilityv2");

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
  $res = mysqli_query($conexion, "SELECT count(*) from suggestedroute");
  $numRutas = mysqli_fetch_array($res);
  $res = mysqli_query($conexion, "SELECT count(*) from suggestedplace");
  $numLugares = mysqli_fetch_array($res);
  $numeroSugerencias = $numRutas[0] + $numLugares[0];
  $numPaginas = $numeroSugerencias/10 +1;
  $numUltimaPagina = $numeroSugerencias % 10;
  $resLugares= NULL;
  $resRutas = NULL;
  if ($numLugares[0]>0) {
    $resLugares = mysqli_query($conexion,"SELECT * FROM suggestedplace");
  }
  if ($numRutas[0]>0) {
    $resRutas = mysqli_query($conexion,"SELECT * FROM suggestedroute");
  }
?>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>RoutabilityAdmin</title>
    <link rel="shortcut icon" href="./img/monkeybits2.png" type="image/png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.1.3.css">
    <link rel="stylesheet" href="theme.css">
    <link rel="stylesheet" href="fonts.css" type="text/css">

</head>

<body class="bg-primario">
    <div class="py-2" style="">
        <div class="container">
            <div class="row">
                <div class="col-md-12" style="">
                    <div class="list-group">
                        <h3><a href="#" class="list-group-item list-group-item-action active list-group-item-info icon-drawer">&nbsp;Lista de Sugerencias</a></h3>
                        <?php
              if (isset($_GET['id'])) {
                $id = $_GET['id'];
                if ($_GET['tipo'] == 0) {
                  mysqli_query($conexion, "DELETE from suggestedplace where IdPlace='".$id."'");
                }
                else if ($_GET['tipo']==1){
                  mysqli_query($conexion, "DELETE from suggestedroute where IdRoute='".$id."'");
                }
                else if ($_GET['tipo']==2) {
                  $madeby = $_GET['madeby'];
                  $localitation = $_GET['localitation'];
                  $name = $_GET['name'];
                  $description = $_GET['description'];
                  $image = $_GET['image'];
                  $email = $_SESSION['EMAIL'];    //Aquí hay que usar el nombre del admin que tiene iniciada la sesión
                  mysqli_query($conexion, "Insert into place(IdPlace, Email, MadeBy, Name, Description, Localitation, Image) values('NULL', '$email', '$madeby', '$name', '$description', '$localitation', '$image')");
                  mysqli_query($conexion, "DELETE from suggestedplace where IdPlace='".$id."'");
                }
                else {
                  $madeby = $_GET['madeby'];
                  $name = $_GET['name'];
                  $description = $_GET['description'];
                  $image = $_GET['image'];
                  $email = $_SESSION['EMAIL'];
                  mysqli_query($conexion, "Insert into route(IdRoute, Email, MadeBy, Name, Description, Image) values('NULL', '$email', '$madeby', '$name', '$description', '$image')");
                  mysqli_query($conexion, "DELETE from suggestedroute where IdRoute='".$id."'");      
                }
              header("Location: Suggestions.php");
              }
              if ($numeroSugerencias < 10)
                $maxLista = 10;
              else 
                $maxLista = $numeroSugerencias;
              for ($k = 0; $k < $maxLista; $k++) {
                $sugerencias = NULL;
                if ($resRutas != NULL)
                  $sugerencias = mysqli_fetch_array($resRutas);
                if ($sugerencias == NULL) {
                  if ($resLugares != NULL)
                    $sugerencias = mysqli_fetch_array($resLugares);
                  if ($sugerencias != NULL) {
                      
                    $id = $sugerencias["IdPlace"];
                    $madeby = $sugerencias["MadeBy"];
                    $description = $sugerencias["Description"];
                    $name = $sugerencias["Name"];
                    $localitation = $sugerencias["Localitation"];
                    $image = $sugerencias["Image"];
                    echo '<p class="list-group-item list-group-item-action"><b>Lugar: </b>';
                    echo '('.$sugerencias["IdPlace"].'):'. $sugerencias["Name"];
                    echo '<a href="Suggestions.php?id='.$id.'&tipo=0" alt="añadir"><img title="Borrar sugerencia" alt="Borrar sugerencia" class="icono" src="./img/cruz.svg" /></a>';
                    echo '<a href="Suggestions.php?id='.$id.'&madeby='.$madeby.'&description='.$description.'&name='.$name.'&localitation='.$localitation.'&image='.$image.'&tipo=2" alt="eliminar"><img title="Aceptar sugerencia" alt="Aceptar sugerencia" class="icono" src="./img/incluir.png" /></a></p>';
                    
                  }
                }
                else {
                  $id = $sugerencias["IdRoute"];
                  $madeby = $sugerencias["MadeBy"];
                  $description = $sugerencias["Description"];
                  $name = $sugerencias["Name"];
                  $image = $sugerencias["Image"];
                  echo '<p class="list-group-item list-group-item-action"><b>Ruta: </b>';
                  echo '('.$sugerencias["IdRoute"].'):'. $sugerencias["Name"];
                  echo '<a href="Suggestions.php?id='.$id.'&tipo=1" alt="añadir"><img title="Borrar sugerencia" alt="Borrar sugerencia" class="icono" src="./img/cruz.svg" /></a>';
                  echo '<a href="Suggestions.php?id='.$id.'&madeby='.$madeby.'&description='.$description.'&name='.$name.'&image='.$image.'&tipo=3" alt="eliminar"><img title="Aceptar sugerencia" alt="Aceptar sugerencia" class="icono" src="./img/incluir.png" /></a></p>';
                  
                }
              }
              
            ?>
                    </div>
                    <div class="py-2">
                        <div class="row">
                            <div class="col-md-12">
                                <a href="Home.php" class="btn btn-light margenes icon-home">&nbsp;Volver a administración</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<?php
  mysqli_close($conexion);
?>

</html>
