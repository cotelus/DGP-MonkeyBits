<?php session_start();?>

<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>RoutabilityAdmin</title>
  <link rel="shortcut icon" href="./img/monkeybits2.png" type="image/png">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="theme.css" type="text/css">
  <link rel="stylesheet" href="fonts.css" type="text/css">

  <script src="https://www.gstatic.com/firebasejs/5.5.8/firebase.js"></script>
  <script>
    // Initialize Firebase
    var config = {
      apiKey: "AIzaSyALMvr5CqpG1Et8qP8BNQ0uFt6P_H0kvhI",
      authDomain: "adminroutability.firebaseapp.com",
      databaseURL: "https://adminroutability.firebaseio.com",
      projectId: "adminroutability",
      storageBucket: "adminroutability.appspot.com",
      messagingSenderId: "253774242478"
    };
    firebase.initializeApp(config);
  </script>
  <script>
    function cerrarSesion() {
      firebase.auth().signOut().then(function() {
        $_SESSION['EMAIL'] = null;
        alert("Usted ha cerrado sesión");
      }).cath(function(error) {
        alert("Error cerrando sesión");
      })
    }
  </script>

</head>

<body class="bg-primario">
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-3"><img class="img-fluid d-block float-left p-2" src="./img/monkeybits2.png" width="200" height="10"></div>
        <div class="col-md-7">
          <h1 class="display-3">Routability:Granada</h1>
        </div>
        <div class="col-md-2"><a class="btn btn-light w-100 p-2 align-items-center icon-enter" href="index.php" onClick="cerrarSesion()">&nbsp;Cerrar Sesión</a></div>
      </div>
    </div>
  </div>
  <div class="py-5" style="">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="mx-auto col-md-12 col-10 bg-white p-5 border border-dark rounded-0" style=" transform:  translateY(-120px) ;">
              <div class="py-1">
                <div class="container">
                  <div class="row">
                    <div class="col-md-3">
                      <h1 class="">Rutas:</h1>
                    </div>
                    <div class="col-md-9">
                      <div class="btn-group"> <a href="addRoutes.php" class="btn btn-primary bg-primario icon-plus">&nbsp;Añadir ruta</a> <a href="viewRoutes.php" class="btn btn-primary bg-primario icon-map" style="">&nbsp;Ver ruta&nbsp; &nbsp;</a> </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="py-1 ancho">
                <div class="container">
                  <div class="row">
                    <div class="col-md-3">
                      <h1 class="">Lugares:</h1>
                    </div>
                    <div class="col-md-9">
                      <div class="btn-group ancho"> <a href="addPlaces.php" class="btn btn-primary bg-primario icon-plus">&nbsp;Añadir lugar</a> <a href="viewPlaces.php" class="btn btn-primary bg-primario icon-library">&nbsp;Ver lugar</a> </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="py-1">
                <div class="container">
                  <div class="row">
                    <div class="col-md-3">
                      <h1 class="">Feedback:</h1>
                    </div>
                    <div class="col-md-9">
                      <div class="btn-group"> <a href="Comments.php" class="btn btn-primary bg-primario ancho icon-bubble">&nbsp;Comentarios</a> <a href="Suggestions.php" class="btn btn-primary bg-primario ancho icon-drawer">&nbsp;Sugerencias</a> <a href="Users.php" class="btn btn-primary bg-primario ancho icon-users">&nbsp;Usuarios</a> </div>
                    </div>
                  </div>
                </div>
                </div>
              </div>
              </div>
          </div>
        </div>
      </div>
    <div class="py-3 bg-secundario" style="">
    <div class="container">
      <div class="row">
        <div class="col-md-12 text-center">
          <p class="mb-0 text-white"><b>© 2018 MonkeyBits. Todos los derechos reservados.</b></p>
        </div>
      </div>
    </div>
  </div>
  
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  </body>

</html>