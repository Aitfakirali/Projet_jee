<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*,com.demo.beans.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Document</title>
   	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
  	<div class="container-fluid">
  		<%@ include file="/Static/navbar.jsp" %>
  		 <div class="row">
	  		 <div class="col-12 m-auto d-flex justify-content-center">
	  		 	<form class="row" action="Home" method="get" class="d-flex justify-content-center">
					  <div class="col-6 mt-4 p-3 ">
					    <label for="inputState" class="form-label">Categorie</label>
					    <% 
					    	String category = (String)request.getAttribute("category");
					    	String auteur = (String)request.getParameter("auteur");	
					    %>
					    <select onchange="this.form.submit()" id="inputState" name="category" class="form-select" autofocus>
					    <option value="all" <%=category.equals("all")?"selected":""%>>Tous les livres</option>
					      <%
						  	List<Category> categories = (List<Category>)request.getAttribute("categories");					      	
						  	for(Category cat : categories){
					  		%>
					      		<option value="<%=cat.getCategory_id()%>" <%=category.equals(String.valueOf(cat.getCategory_id()))?"selected":""%>><%=cat.getCategory_nom()%></option>
					      	<%} %>
					    </select>
					  </div>
						<div class="col-4 mt-4 p-4">
							<label for="floatingInput"></label>
						  <input type="text" name="auteur" value="<%=auteur!=null?auteur.toLowerCase():"" %>" class="form-control" id="floatingInput" placeholder="Nom Auteur">
						  
						</div>
					  <div class="col-2 mt-4 p-5">
					    <button type="submit" class="btn btn-dark d-flex align-items-center">
						    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="d-inline bi bi-search" viewBox="0 0 16 16">
							  <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
							</svg>
						    <div class="ms-2">chercher</div>
					   	</button>
					  </div> 
				</form>
	  		 </div>
  		 	
            <div class="col-12">
            <div>
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
            </div>
            <%
	            List<Livre> livres = (ArrayList<Livre>)request.getAttribute("livres");
			  	if(livres.size() > 0){
            %>
            	
            	<table class="table caption-top table-borderless table-sm user-select-none">
				  <caption class="display-6 m-4">Liste des livres disponible</caption>
				  <thead>
				    <tr class="table-dark">
				      <th scope="col">Num</th>
				      <th scope="col">Auteur</th>
				      <th scope="col">Titre</th>
				      <th scope="col">Date</th>
				      <th scope="col">category</th>
				      <th scope="col">Emprunte</th>
				    </tr>
				  </thead>
				  <tbody>
				  <%
				  	int i =1;
				  	for(Livre livre : livres){
				  %>
				    <tr class="user-select-none" onclick="show(<%=livre.getLivre_id()%>)">
				      <th scope="row"><%=i %></th>
				      <td><%=livre.getLivre_auteur() %></td>
				      <td><%=livre.getLivre_titre() %></td>
				      <td><%=livre.getDate_creation() %></td>
				      <td><%=livre.getCategory() != null ? livre.getCategory().getCategory_nom() : "Aucune" %></td>
				      <td scope="col">
				      	<div class="row">
						    <div class="col col-xl-5">
							    <form method="post" action="Home" >
								    <button type="submit" class="btn btn-sm btn-dark">Reserver</button>
								    <input type="text" class="visually-hidden" name="reserve_id" value="<%=livre.getLivre_id()%>"/>
								</form>
							</div>
						</div>
				      </td>
				    </tr>
				  <%i++;} %>
				  </tbody>
				</table>
				<%}else{ %>
					<div class="h3 m-3">
					  Aucun livres disponible pour l'instant
					  <small class="text-muted">pouvez-vous essayer plus tard</small>
					</div>
				<%} %>
			</div>
			<div class="col-5">
				
			</div>
			<script> 
				show = (id) => {
					location.href = "<%=request.getContextPath()%>/Show?livre_id="+id
				}
			</script>
			<style>
				*{
					margin:0;
					padding:0;	
				}
				tr:hover{
		    		animation: anim2 1s normal;
		    		background-color:var(--bs-success);
		    		color:var(--bs-light) !important;
		    		border-radius:20px;
		    	}
		    	
		    	@keyframes anim2 {
				  from {
				  	background-color:var(--bs-light);
				  }
				  to {
				  	background-color:var(--bs-success);
				  }
				}
				
				tr{
					cursor:pointer;
				}
				
			</style>
        </div>
    </div>
</body>
</html>