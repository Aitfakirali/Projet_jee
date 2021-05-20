<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="com.demo.beans.*" %>		
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Compte</title>
   	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
			<div class="container-fluid">
			<%@ include file="/Static/navbar.jsp" %>
				   <%
			      	Etudiant u=(Etudiant)session.getAttribute("Etudiant");
			       %>	
						<div class="col col-xl-8 m-auto  shadow p-3 mt-5 bg-body rounded">
						<% String error = (String) request.getAttribute("error");
				  		if(error!= null){
					  	%>
					  		<div class="alert alert-danger alert-dismissible fade show" role="alert">
							  <strong>Un probleme!!!!</strong> 
								<%= error%>
							  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
							</div>
				  		<% } %>
			            <% String message = (String) request.getAttribute("message");
					  		if(message!= null){
					  	%>
					  		<div class="alert alert-success alert-dismissible fade show" role="alert">
							  <strong> C'est Bonne... </strong> 
								<%= message%>
							  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
							</div>
				  		<% } %>
						 <h2 class="display-6 m-4">Modifiez votre compte</h2>
			 				<form class="row g-3 test-validation" action="Update" method="post" novalidate>
			 					<div class="col col-sm-6 col-md-6">
								    <label for="inputEmail4" class="form-label">Nom</label>
								    <input type="text" name="nom" value="<%=u != null?u.getEtudiant_nom():""%>" class="form-control" id="inputNom4" required>
								    <div class="invalid-feedback">
								      le nom ne doit pas être vide
								    </div>
								  </div>
								  <div class="col col-sm-6 col-md-6">
								    <label for="inputPassword4" class="form-label">Prenom</label>
								    <input type="text" name="prenom" value="<%=u != null?u.getEtudiant_prenom():""%>"class="form-control" id="inputPrenom4" required>
								    <div class="invalid-feedback">
								      le prenom ne doit pas être vide
								    </div>
								  </div>
								  <div class="col col-sm-5 col-md-5">
								    <label for="inputEmail4" class="form-label">Addresse Email</label>
								    <input type="email" name="email" value="<%=u != null?u.getEtudiant_email():""%>" placeholder="example@gmail.com" class="form-control" id="inputEmail4" required>
								    <div class="invalid-feedback">
								      l'email ne doit pas être vide et doit être formé comme e-mail
								    </div>
								  </div>
								  <div class="col col-sm-4  col-md-4">
								    <label for="inputCity" class="form-label">Filiere</label>
								    <input type="text" name="filiere" value="<%=u != null?u.getEtudiant_filiere():""%>" class="form-control" id="inputFiliere" required>
								    <div class="invalid-feedback">
								      la filiere ne doit pas être vide 
								    </div>
								  </div>
								  <div class="col col-sm-3  col-md-3">
								    <label for="inputZip" class="form-label">Age</label>
								    <input type="Number" name="age" value="<%=u != null?u.getEtudiant_age():""%>" min=0 max=50 class="form-control" id="inputAge">
								  </div>
								  <div class="col col-sm-7 col-12">
								    <label for="inputAddress" class="form-label">Addresse</label>
								    <input type="text" name="addresse" value="<%=u != null?u.getEtudiant_adresse():""%>" class="form-control" id="inputAddresse" placeholder="Rue 4 N° 35" required>
								    <div class="invalid-feedback">
								      l'addresse ne doit pas être vide 
								    </div>
								  </div>
								  <div class="col col-sm-4  col-md-5">
								    <label for="inputCity" class="form-label">Telephone</label>
								    <input type="text" name="telephone" value="<%=u != null?u.getEtudiant_telephone():""%>" class="form-control" id="inputTelephone" min=10 required>
								    <div class="invalid-feedback">
								      le telephone doit comporter plus de 10 caractères
								    </div>
								  </div>
								  
								  <div class="col col-sm-12 col-12 ps-3 m-5">
								    <button type="submit" class="btn btn-dark">Validez-vous</button>
								  </div>
								</form>           
			            </div>
			           </div>
			           <script type="text/javascript">
			        (function () {
						  'use strict'
						  var forms = document.querySelectorAll('.test-validation')
						  Array.prototype.slice.call(forms)
						    .forEach( (form) => {
						      form.addEventListener('submit', function (event) {
						        if (!form.checkValidity()) {
						          event.preventDefault()
						          event.stopPropagation()
						        }
						        form.classList.add('was-validated')
						      }, false)
						    })
						})();
			        </script>
</body>
</html>