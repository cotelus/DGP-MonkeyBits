<?php

  $MESSAGE = "";

  session_start();

  //Consultamos los datos de la obra

  $conexion = mysqli_connect("localhost", "root", "");
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
  $res = mysqli_query($conexion, "SELECT count(*) from routecomments");
  $numComRutas = mysqli_fetch_array($res);
  $res = mysqli_query($conexion, "SELECT count(*) from placecomments");
  $numComPlaces = mysqli_fetch_array($res);
  $numeroComentarios = $numComRutas[0] + $numComPlaces[0];
  $numPaginas = $numeroComentarios/10 +1;
  $numUltimaPagina = $numeroComentarios % 10;
  $resComPlaces= NULL;
  $resComRutas = NULL;
  if ($numComPlaces[0]>0) {
    $resComPlaces = mysqli_query($conexion,"SELECT * FROM placecomments");
  }
  if ($numComRutas[0]>0) {
    $resComRutas = mysqli_query($conexion,"SELECT * FROM routecomments");
  }
?>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.1.3.css">
  <link rel="stylesheet" href="theme.css">
  
</head>
<body class="bg-primary">
  <div class="py-2">
    <div class="row">
      <div class="col-md-12">
        <a href="Home.php" class="btn btn-light margenes">Volver a home</a>
      </div>
    </div>
  </div>
  <div class="py-2" style="">
    <div class="container">
      <div class="row">
        <div class="col-md-12" style="">
          <div class="list-group">
            <a href="#" class="list-group-item list-group-item-action active list-group-item-info">Comentarios </a>
            <?php
              if (isset($_GET['id'])) {
                $id = $_GET['id'];
                $email = $_GET['email'];
                $date = $_GET['date'];
                $time = $_GET['time'];
                if ($_GET['tipo'] == 0) {
                  mysqli_query($conexion, "DELETE from placecomments where IdPlace='".$id."' and Email='".$email."' and Date='".$date."' and Time='".$time."'");
                }
                else {
                  mysqli_query($conexion, "DELETE from routecomments where IdRoute='".$id."' and Email='".$email."' and Date='".$date."' and Time='".$time."'");
                }
                header("Location: Comments.php");
              }
              for ($k = 0; $k < 10; $k++) {
                $comentarios = NULL;
                if ($resComRutas != NULL)
                  $comentarios = mysqli_fetch_array($resComRutas);
                if ($comentarios == NULL) {
                  if ($resComPlaces != NULL)
                    $comentarios = mysqli_fetch_array($resComPlaces);
                  if ($comentarios == NULL) {
                    echo '<a href="#" class="list-group-item list-group-item-action">(Espacio vacío)</a>';
                  }
                  else {
                    $email = $comentarios["Email"];
                    $id = $comentarios["IdPlace"];
                    $date = $comentarios["Date"];
                    $time = $comentarios["Time"];

                    echo '<p class="list-group-item list-group-item-action miembro-lista">'.$comentarios["Email"].': '. $comentarios["Content"];
                    echo '<a href="Comments.php?id='.$id.'&email='.$email.'&date='.$date.'&time='.$time.'&tipo=0"><img class="icono" src="./img/cruz.svg" /></a></p>';
                  }
                }
                else {
                  $email = $comentarios["Email"];
                  $id = $comentarios["IdRoute"];
                  $date = $comentarios["Date"];
                  $time = $comentarios["Time"];

                  echo '<p class="list-group-item list-group-item-action miembro-lista">'.$comentarios["Email"].': '. $comentarios["Content"];
                  echo '<a href="Comments.php?id='.$id.'&email='.$email.'&date='.$date.'&time='.$time.'&tipo=1"><img name="eliminar" class="icono" src="./img/cruz.svg" /></a></p>';
                }
              }
              
            ?>
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