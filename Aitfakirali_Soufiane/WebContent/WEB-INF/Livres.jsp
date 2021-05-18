<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList,com.demo.beans.Livre"%>
<%@page import="com.demo.beans.Category"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
     <style>
				*{
					margin:0;
					padding:0;	
				}
				
				tr{
					cursor:pointer;
				}
				
				tr:hover > td{
					background-color:#C1BDB3;
					color:black;
				}
	</style>
	<script> 
				show = (id) => {
					window.location.href = 'Show?livre_id='+id
				}
	</script>
</head>
<body>
  	<div class="container-fluid">
  		<%@ include file="/Static/navbar.jsp" %>
  		 <div class="row align-items-start">
  		 	<div class="col-12 col-sm-4 col-md-4 ms-2 mt-5">
  		 		<div class="display-6 m-4">Ajouter livre</div>
				<form class=" row needs-validation" action="Livres" method="post" novalidate>
				  <div class="col-md-6">
				    <label for="inputEmail4" class="form-label">Auteur</label>
				    <input type="text" name="auteur" class="form-control" id="inputEmail4" required>
				    <div class="invalid-feedback">
					      l'Auteur ne doit pas être vide
					</div>
				  </div>
				  <div class="col-6">
				    <label for="inputAddress" class="form-label">Date</label>
				    <input type="date" name="date" class="form-control" id="inputAddress" placeholder="1234 Main St" required>
				    <div class="invalid-feedback">
					      la date ne doit pas être vide
					</div>
				  </div>
				  <div class="col-md-12">
				    <label for="inputPassword4" class="form-label">Titre</label>
				    <input type="text" name="titre" class="form-control" id="inputPassword4" required>
				    <div class="invalid-feedback">
					      le titre ne doit pas être vide
					</div>
				  </div>
				   <div class="col-md-6">
				    <label for="inputState" class="form-label">Category</label>
				    <select id="inputState" name="category" class="form-select" required>
				      <%
					  	ArrayList<Category> categories = (ArrayList<Category>)request.getAttribute("categories");
					  	for(Category cat : categories){
				  		%>
				      		<option value="<%=cat.getCategory_id()%>"><%=cat.getCategory_nom()%></option>
				      	<%} %>
				    </select>
				    <div class="invalid-feedback">
					      la categorie ne doit pas être vide
					</div>
				  </div>
				  <div class="col-6">
				    <label for="inputAddress" class="form-label">Image</label>
				    <input type="file" name="image" class="form-control" id="inputAddress">
				  </div> 
				  <div class="col-6 mt-4 p-3 d-flex justify-content-center">
				    <button type="submit" class="btn btn-dark">Ajouter</button>
				  </div>
				</form>
			</div>
            <div class="col col-xxl-7 ps-5 pt-5">
            	<div class="display-6 m-4">Les livres de la bibliothèque de FSBM</div>
            			<div class="m-3 pb-3  shadow p-3 mb-5 bg-body rounded">
		            		<form action="" onchange="this.submit()">
			            		<div class="form-check">
								  <input class="form-check-input" type="radio" value="all" name="filter" id="flexRadioDefault1" ${filtered?"":"checked" }>
								  <label class="form-check-label" for="flexRadioDefault1">
								    Tous les livres
								  </label>
								</div>
								<div class="form-check">
								  <input class="form-check-input" type="radio" value="filtered" name="filter" id="flexRadioDefault2" ${filtered?"checked":"" }>
								  <label class="form-check-label" for="flexRadioDefault2">
								    Livres emprunter
								  </label>
								</div>
		            		</form>
		            	</div>
            	<table class="table table-borderless table-sm user-select-none">
				  <thead>
				    <tr class="table-dark">
				      <th scope="col">#</th>
				      <th scope="col">Auteur</th>
				      <th scope="col">Titre</th>
				      <th scope="col">Date</th>
				      <th scope="col">category</th>
				      <th scope="col" colspan="2">Action</th>
				    </tr>
				  </thead>
				  <tbody>
				  <%
				  	ArrayList<Livre> livres = (ArrayList<Livre>)request.getAttribute("livres");
				  	int i =1;
				  	for(Livre livre : livres){
				  %>
				    <tr onclick="show(<%=livre.getLivre_id()%>)">
				      <th scope="row"><%=i %></th>
				      <td><%=livre.getLivre_auteur() %></td>
				      <td><%=livre.getLivre_titre() %></td>
				      <td><%=livre.getDate_creation() %></td>
				      <td><%=livre.getCategory() != null ? livre.getCategory().getCategory_nom() : "Aucune" %></td>
				      <td>
				      	<div class="row">
						      	<div class="col col-xl-5">
							      	<form method="get" action="Livres">
								      	<button type="submit" class="btn btn-sm btn-outline-danger d-flex align-items-center">
								      	<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
										  <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
										  <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
										</svg>
								      	Delete</button>
								      	<input type="text" class="visually-hidden" name="delete_id" value="<%=livre.getLivre_id()%>"/>
									</form>
								</div>
							</div>
				      </td>
				    </tr>
				  <%i++;} %>
				  </tbody>
				</table>
			</div>
        </div>
       <script>
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