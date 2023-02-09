<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP, Servlet et Hibernate-compte</title>

<link rel="stylesheet"
 href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
 integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
 crossorigin="anonymous">
</head>

</head>
<body>
 <div class="container">
 <div class="row text-center" style="color: tomato;">
  <h2>Formationkilo JSP, Servlet et Hibernate</h2>
 </div>
 <hr>
  <div class="row col-md-10 col-md-offset-3"> 
   
   <div class="card card-body">
   
    <h2>Création de compte utilisateur</h2>
    <div class="col-md-8 col-md-offset-3">

     <form action="<%=request.getContextPath()%>/account" method="post">

      <div class="form-group">
       <label for="uname">Prénom:</label> <input type="text"
        class="form-control" id="uname" placeholder="Veuillez saisir votre prénom"
        name="firstName" required>
      </div>

      <div class="form-group">
       <label for="uname">Nom:</label> <input type="text"
        class="form-control" id="uname" placeholder="Veuillez saisir votre nom"
        name="lastName" required>
      </div>

      <div class="form-group">
       <label for="uname">Identifiant:</label> <input type="text"
        class="form-control" id="username" placeholder="Veuillez entrer un identifiant"
        name="username" required>
      </div>

      <div class="form-group">
       <label for="uname">Mot de passe:</label> <input type="password"
        class="form-control" id="password" placeholder="Veuillez entrer un mot de passe"
        name="password" required>
      </div>

      <button type="submit" class="btn btn-primary">Créer mon compte</button>

     </form>
    </div>
   </div>
  </div>
 </div>
</body>
</html>
