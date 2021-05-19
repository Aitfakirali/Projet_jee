<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Document</title>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
  	<div class="container-fluid">
  		<%@ include file="/Static/navbar.jsp" %>
  		 <div class="row ps-4 pt-4">
  		 	<div class="col col-lg-6 p-5 m-auto ">
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
			  	
			  	<div  class="m-3 d-flex justify-content-center">
			  		<img alt="logo FSBM"  src="<%=request.getContextPath()%>/images/logo.png"/>
			  	</div>
				<div class="display-6 ">Bienvenue à la bibliothèque de la faculté de science Ben M'sik</div>
			</div>
            <div class="col col-lg-6 ">
	            <% String autherror = (String) request.getAttribute("autherror");
		  		 	if(autherror!= null){
		  		%>
			  		<div class="alert alert-danger alert-dismissible fade show" role="alert">
					  <strong>Un probleme!!!! </strong> 
						<%= autherror%>
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
					</div>
		  		<% } %>
            	<h2 class="display-6 m-4">Authentification</h2>
                <form class="needs-validation" action="Auth" method="post" novalidate>
                    <div class="row mb-4">
                      <label for="inputEmail3" class="col col-sm-2 col-form-label">Email</label>
                      <div class="col-sm-10">
                        <input type="email" name="email" class="col col-md-12 form-control" id="inputusername" value="${email}" placeholder="exemple@gmail.com" autofocus="true" required>
                      	<div class="invalid-feedback">
					      l'email ne doit pas être vide
					    </div>
                      </div>
                    </div>
                    <div class="row mb-4">
                      <label for="inputPassword3" class="col col-sm-2 col-form-label">Password</label>
                      <div class="col-sm-10">
                        <input type="password" name="password" class=" col col-md-12 form-control" id="inputPass" placeholder="***********" required>
                      	<div class="invalid-feedback">
					      le mot de passe ne doit pas être vide
					    </div>
                      </div>
                    </div>
                    
                    <div class="d-flex">
                    	<button type="submit" class="align-items-start col-4 m-2 btn btn-dark">Connectez-vous</button>
						<button type="button" class="align-items-end col-4 m-2 btn btn-success" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
						  Inscrivez-vous
						</button>
					</div>
                </form>
            </div>
 			<%@ include file="/Static/register_form.jsp" %>
        </div>
        <script type="text/javascript">
        (function () {
			  'use strict'
			  var forms = document.querySelectorAll('.needs-validation')	
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
			})()
        </script>
    </div>
</body>
</html>