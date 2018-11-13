<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.1.3.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script>
    $(document).ready(function() {
      $("#BotonComentarios").click(function() {
        $("#EspacioBlanco").load("Comments.php");
      });
    });
  </script>
</head>
<body class="bg-primary">
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-3" style="">
          <div class="row">
            <div class="col-md-12"><img class="img-fluid d-block float-left m-1 mx-auto p-1 px-1 py-1" src="./img/monkeybits2.png" width="120" height="120"></div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <h1 class="w-25 text-left text-dark">MonkeyBits</h1>
            </div>
          </div>
        </div>
        <div class="col-md-9" style="">
          <h1 class="display-1 text-right text-dark">Routability: Granada</h1>
        </div>
      </div>
    </div>
  </div>
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
          <div class="btn-group btn-group-lg"> <button id="BotonComentarios" class="btn btn-light" >Comentarios</button> <a href="#" class="btn btn-light" id="BotonSugerencias">Sugerencias</a> <a href="#" class="btn btn-light" id="BotonUsuarios">Usuarios</a> </div>
        </div>
        <div class="col-md-4"></div>
      </div>
    </div>
  </div>
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div id="EspacioBlanco" class="col-md-12"><p>Pulse un botón</p></div>
      </div>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>

</html>