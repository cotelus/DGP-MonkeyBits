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
  $res = mysqli_query($conexion, "SELECT count(*) from place");
  $numPlaces = mysqli_fetch_array($res);
  $numeroPlaces = $numPlaces[0];
  $numPaginas = $numeroPlaces/10 +1;
  $numUltimaPagina = $numeroPlaces % 10;
  $Places= NULL;
  if ($numPlaces[0]>0) {
    $Places = mysqli_query($conexion,"SELECT * FROM place");
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
              <h3><a href="#" class="list-group-item list-group-item-action active list-group-item-info icon-library">&nbsp;Lista de Lugares</a></h3>
            <?php
              if (isset($_GET['id'])) {
                
                  $id = $_GET['id'];
                  
                  mysqli_query($conexion, "DELETE from place where IdPlace='".$id."'");
                  
                  header("Location: viewPlaces.php");
              }
              if ($numeroPlaces < 10)
                $maxLista = 10;
              else 
                $maxLista = $numeroPlaces;
              for ($k = 0; $k < $maxLista; $k++) {
                $lugares = NULL;
                if ($lugares == NULL) {
                  if ($Places != NULL)
                    $lugares = mysqli_fetch_array($Places);
                  
                    if ($lugares != NULL) {

                    $id = $lugares["IdPlace"];
                    $nombre = $lugares["Name"];

                    echo '<p class="list-group-item list-group-item-action miembro-lista">'.$nombre;  
                      
                    echo '<a href="viewPlaces.php?id='.$id.'"><img class="icono" title="Eliminar Lugar" alt="Eliminar Lugar" src="./img/cruz.svg" /></a>';
                    echo '<a href="editPlaces.php?id='.$id.'"><img class="icono3" title="Editar Lugar" alt="Editar Lugar" src="./img/editar.png" /></a></p>';   
                  }
                }
              }
              
            ?>
          </div>
        </div>
      </div>
    </div>
  </div>
    <div class="py-3 bg-secundario" style="position: absolute; bottom: 0; width: 100%;">
    <div class="container">
      <div class="row">
        <div class="col-md-12 text-center">
          <p class="mb-0 text-white"><b>© 2018 MonkeyBits. Todos los derechos reservados.</b></p>
        </div>
      </div>
    </div>
  </div>
</body>
<?php
  mysqli_close($conexion);
?>
</html>