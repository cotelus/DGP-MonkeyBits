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
  $res = mysqli_query($conexion, "SELECT count(*) from route");
  $numRoutes = mysqli_fetch_array($res);
  $numeroRoutes = $numRoutes[0];
  $numPaginas = $numeroRoutes/10 +1;
  $numUltimaPagina = $numeroRoutes % 10;
  $Routes= NULL;
  if ($numRoutes[0]>0) {
    $Routes = mysqli_query($conexion,"SELECT * FROM route");
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
  <div class="py-2">
    <div class="row">
      <div class="col-md-12">
        <a href="Home.php" class="btn btn-light margenes icon-home">&nbsp;Volver a administración</a>
      </div>
    </div>
  </div>
  <div class="py-2" style="">
    <div class="container">
      <div class="row">
        <div class="col-md-12" style="">
          <div class="list-group">
            <a href="#" class="list-group-item list-group-item-action active list-group-item-info icon-map">&nbsp;Rutas </a>
            <?php
              if (isset($_GET['id'])) {
                
                  $id = $_GET['id'];
                  
                  mysqli_query($conexion, "DELETE from route where IdRoute='".$id."'");
                  
                  header("Location: viewRoutes.php");
              }
              if ($numeroRoutes < 10)
                $maxLista = 10;
              else 
                $maxLista = $numeroRoutes;
              for ($k = 0; $k < $maxLista; $k++) {
                $rutas = NULL;
                if ($rutas == NULL) {
                  if ($Routes != NULL)
                    $rutas = mysqli_fetch_array($Routes);
                  if ($rutas == NULL) {
                    echo '<a href="#" class="list-group-item list-group-item-action">(Espacio vacío)</a>';
                  }
                  else {
                    $id = $rutas["IdRoute"];
                    $nombre = $rutas["Name"];

                    echo '<p class="list-group-item list-group-item-action miembro-lista">'.$nombre;  
                      
                    echo '<a href="viewRoutes.php?id='.$id.'"><img class="icono" alt="eliminar" src="./img/cruz.svg" /></a></p>';
                    //echo '<a href="editRoutes.php?id='.$id.'"><img class="icono3" alt="editar" src="./img/editar.png" /></a></p>';   
                  }
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