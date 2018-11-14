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
  $res = mysqli_query($conexion, "SELECT count(*) from user");
  $numUsers = mysqli_fetch_array($res);
  $numPaginas = $numUsers[0]/10 +1;
  $numUltimaPagina = $numUsers % 10;
  $resUsuarios = NULL;
  if ($numUsers[0]>0) {
    $resUsuarios = mysqli_query($conexion,"SELECT * FROM user");
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
  
</head>

<body class="bg-primario">
  <div class="py-2">
    <div class="row">
      <div class="col-md-12">
        <a href="Home.php" class="btn btn-light margenes">Volver a home</a>
      </div>
    </div>
  </div>
  <div class="py-3" style="">
    <div class="container">
      <div class="row">
        <div class="col-md-12" style="">
          <div class="list-group">
            <a href="#" class="list-group-item list-group-item-action active list-group-item-info">Usuarios </a>
            <?php
              if (isset($_GET['email'])) {
                $email = $_GET['email'];
                $banned = $_GET['banned'];
                if ($banned == 0) {
                  mysqli_query($conexion, "Update user set Banned='1' where Email='".$email."'");
                }
                else {
                  mysqli_query($conexion, "Update user set Banned='0' where Email='".$email."'");
                }
                header("Location: Users.php");
              }
              if ($numUsers < 10)
                $maxLista = 10;
              else 
                $maxLista = $numUsers;
              for ($k = 0; $k < $maxLista; $k++) {
                $usuarios = NULL;
                if ($resUsuarios != NULL)
                  $usuarios = mysqli_fetch_array($resUsuarios);
                if ($usuarios == NULL) {
                  echo '<a href="#" class="list-group-item list-group-item-action">(Espacio vacío)</a>';
                }
                else {
                  $email = $usuarios["Email"];
                  $banned = $usuarios["Banned"];
                  echo '<p class="list-group-item list-group-item-action">'.$usuarios["Email"];
                  if ($banned == 0) {
                    echo '<a href="Users.php?email='.$email.'&banned='.$banned.'"><img class="icono" src="./img/bloquear.png" /></a></p>';
                  }
                  else {
                    echo '<a href="Users.php?email='.$email.'&banned='.$banned.'"><img class="icono2" src="./img/desbloquear.png"/></a></p>';
                  }
                }
              }
            ?>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous" style=""></script>
  
</body>
<?php
  mysqli_close($conexion);
?>

</html>