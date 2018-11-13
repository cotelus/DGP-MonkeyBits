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
        alert("Usted ha cerrado sesión");
      }).cath(function(error) {
        alert("Error cerrando sesión");
      })
    }
  </script>

</head>

<body class="bg-primary">
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <!-- el onclick de cerrar Sesión solo -->
          <h1 class="display-3"><b>Routability: Granada</b></h1><a class="btn btn-secondary" onClick="cerrarSesion()" href="index.php" style="	transform:  translateX(990px)  translateY(-60px) ;">Cerrar Sesión</a>
        </div>
      </div>
    </div>
    <div class="container">
      <div class="row">
        <div class="col-md-3 offset-md-9">
          <ul class="list-group">
              <li class="list-group-item d-flex justify-content-between align-items-center border-dark"><a href="./addPlaces.php"><span class="icon-plus"></span>&nbsp&nbsp<b>Añadir Lugares</b></a></li>
              <li class="list-group-item d-flex justify-content-between align-items-center border-dark"><a href="./addRoutes.php"><span class="icon-plus"></span>&nbsp&nbsp<b>Añadir Rutas</b></a></li>
              <li class="list-group-item d-flex justify-content-between align-items-center border-dark"><a href="./.php"><span class="icon-cross"></span>&nbsp&nbsp<b>Bloquear Usuarios</b></a></li>  
              <li class="list-group-item d-flex justify-content-between align-items-center border-dark"><a href="./.php"><span class="icon-bell"></span>&nbsp&nbsp<b>Sugerencias</b></a></li>  
          </ul>
        </div>
      </div>
    </div>
  </div>
  <div class="py-5 border-dark border">
    <div class="container">
      <div class="row">
        <div class="col-md-12 text-center">
          <h1 class="text-left"><b>Lugares</b></h1>
        </div>
      </div>
      <div class="row justify-content-center">
        <div class="col-lg-4 col-md-6 p-4">
          <div class="row">
            <div class="col-3 p-0 d-flex align-items-center"> <img class="img-fluid d-block" src="https://static.pingendo.com/img-placeholder-1.svg"> </div>
            <div class="col-9">
              <p class="lead mb-1"> <b>#1</b> </p>
              <p class="mb-0">A wonderful serenity has taken possession of my entire soul.</p>
            </div>
          </div>
        </div>
        <div class="col-lg-4 col-md-6 p-4">
          <div class="row">
            <div class="col-3 p-0 d-flex align-items-center"> <img class="img-fluid d-block" src="https://static.pingendo.com/img-placeholder-4.svg"> </div>
            <div class="col-9">
              <p class="lead mb-1"> <b>#2</b> </p>
              <p class="mb-0">I am alone, and feel the charm of existence in this spot.</p>
            </div>
          </div>
        </div>
        <div class="col-lg-4 col-md-6 p-4">
          <div class="row">
            <div class="col-3 p-0 d-flex align-items-center"> <img class="img-fluid d-block" src="https://static.pingendo.com/img-placeholder-2.svg"> </div>
            <div class="col-9">
              <p class="lead mb-1"> <b>#3</b> </p>
              <p class="mb-0">I should be incapable of drawing a single stroke.</p>
            </div>
          </div>
        </div>
        <div class="col-lg-4 col-md-6 p-4">
          <div class="row">
            <div class="col-3 p-0 d-flex align-items-center"> <img class="img-fluid d-block" src="https://static.pingendo.com/img-placeholder-3.svg"> </div>
            <div class="col-9">
              <p class="lead mb-1"> <b>#4</b> </p>
              <p class="mb-0">I throw myself down among the tall grass by the trickling stream.</p>
            </div>
          </div>
        </div>
        <div class="col-lg-4 col-md-6 p-4">
          <div class="row">
            <div class="col-3 p-0 d-flex align-items-center"> <img class="img-fluid d-block" src="https://static.pingendo.com/img-placeholder-1.svg"> </div>
            <div class="col-9">
              <p class="lead mb-1"> <b>#5</b> </p>
              <p class="mb-0">I lie close to the earth, a thousand unknown plants.</p>
            </div>
          </div>
        </div>
        <div class="col-lg-4 col-md-6 p-4">
          <div class="row">
            <div class="col-3 p-0 d-flex align-items-center"> <img class="img-fluid d-block" src="https://static.pingendo.com/img-placeholder-4.svg"> </div>
            <div class="col-9">
              <p class="lead mb-1"> <b>#6</b> </p>
              <p class="mb-0">When I hear the buzz of the little world among the stalks.</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="py-3 border-dark border">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h1 class="text-left">Rutas</h1>
        </div>
      </div>
      <div class="row">
        <div class="col-md-4 col-6 p-3" style=""> <img class="img-fluid d-block" src="https://static.pingendo.com/cover-bubble-dark.svg">
          <h2 class="my-3"><b>One&nbsp;</b> </h2>
          <p>A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart.</p>
        </div>
        <div class="col-md-4 col-6 p-3"> <img class="img-fluid d-block" src="https://static.pingendo.com/cover-moon.svg">
          <h2 class="my-3"> <b>Two</b> </h2>
          <p>I am alone, and feel the charm of existence in this spot, which was created for the bliss of souls like mine. I am so happy, my dear friend.</p>
        </div>
        <div class="col-md-4 col-6 p-3"> <img class="img-fluid d-block" src="https://static.pingendo.com/cover-bubble-light.svg">
          <h2 class="my-3"> <b>Three</b> </h2>
          <p>So absorbed in the exquisite sense of mere tranquil existence, that I neglect my talents. I should be incapable of drawing a single stroke.</p>
        </div>
      </div>
    </div>
  </div>
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-5" style="">
          <div class="card">
            <div class="card-header text-dark bg-secondary border border-dark"><b class="" style=""> Comentarios</b></div>
            <div class="card-body border-dark border">
              <blockquote class="blockquote mb-0">
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</p>
                <footer class="blockquote-footer">Someone famous in <cite title="Source Title">Source Title</cite></footer>
              </blockquote>
            </div>
            <div class="card-body border border-dark">
              <blockquote class="blockquote mb-0">
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</p>
                <footer class="blockquote-footer">Someone famous in <cite title="Source Title">Source Title</cite></footer>
              </blockquote>
            </div>
          </div>
        </div>
      </div>
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