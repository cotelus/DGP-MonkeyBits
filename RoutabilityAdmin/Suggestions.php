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
  if (isset($_POST['search'])) {
    $search = $_POST['search'];
  }

  $resRutas = mysqli_query($conexion, "SELECT * from suggestedroute where IdRoute like '%".$search."%' or Name like '%".$search."%' order by IdRoute");
  $resLugares = mysqli_query($conexion, "SELECT * from suggestedplace where IdPlace like '%".$search."%' or Name like '%".$search."%' order by IdPlace");
  $total = mysqli_num_rows($resRutas) + mysqli_num_rows($resLugares);
  $fila = NULL;
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
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        $("#search_form").submit(function(e){
          e.preventDefault();
        })
        $("#search").keyup(function() {
          var envio = $("#search").val();
        })
        $('#')
    </script>

</head>

<body class="bg-primario">
    <div class="py-2" style="">
      <div class="container py-3 px-3">
        <div class="row">
          <div class="col-md-6">
            <div class="form-center">
              <form action="" method="post" name="search_form" id="search_form">
                <input type="text" placeholder="Buscar sugerencia..." name="search" id="search">
              </form>
            </div>
          </div>
          <div class="col-md-6">
            <div class="filtros">
              
            </div>
          </div>
        </div>
      </div>
        <div class="container">
            <div class="row">
                <div class="col-md-12" style="">
                    <div class="list-group" id="resultados">
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

              if ($total > 0) {
                while($fila = mysqli_fetch_assoc($resRutas)){
                  $id = $fila['IdRoute'];
                  $name = $fila['Name'];
                  $madeby = $fila['MadeBy'];
                  $description = $fila['Description'];
                  $image = $fila['Image'];
                   echo '<p class="list-group-item list-group-item-action"><b>Ruta';
                    echo '('.$id.'):</b>'.$name;
                    echo '<a href="Suggestions.php?id='.$id.'&tipo=1" alt="añadir"><img title="Borrar sugerencia" alt="Borrar sugerencia" class="icono" src="./img/cruz.svg" /></a>';
                    echo '<a href="Suggestions.php?id='.$id.'&madeby='.$madeby.'&description='.$description.'&name='.$name.'&image='.$image.'&tipo=3" alt="eliminar"><img title="Aceptar sugerencia" alt="Aceptar sugerencia" class="icono" src="./img/incluir.png" /></a></p>';

                } 
                while($fila = mysqli_fetch_assoc($resLugares)){
                  $id = $fila['IdPlace'];
                  $name = $fila['Name'];
                  $madeby = $fila['MadeBy'];
                  $description = $fila['Description'];
                  $image = $fila['Image'];
                  $localitation = $fila['Localitation'];

                   echo '<p class="list-group-item list-group-item-action"><b>Lugar';
                    echo '('.$id.'):</b>'.$name;
                    echo '<a href="Suggestions.php?id='.$id.'&tipo=0" alt="añadir"><img title="Borrar sugerencia" alt="Borrar sugerencia" class="icono" src="./img/cruz.svg" /></a>';
                    echo '<a href="Suggestions.php?id='.$id.'&madeby='.$madeby.'&description='.$description.'&localitation='.$localitation.'&name='.$name.'&image='.$image.'&tipo=2" alt="eliminar"><img title="Aceptar sugerencia" alt="Aceptar sugerencia" class="icono" src="./img/incluir.png" /></a></p>';

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
