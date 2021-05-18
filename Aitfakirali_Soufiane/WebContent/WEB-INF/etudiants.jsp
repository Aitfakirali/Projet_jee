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
</head>
<body>
  	<div class="container-fluid">
  		<%@ include file="/Static/navbar.jsp" %>
  		 <div class="row m-3 p-3">  		 	
            <div class="col-12">
            <%
	            ArrayList<Etudiant> etudiants = (ArrayList<Etudiant>)request.getAttribute("etudiants");
			  	if(etudiants.size() > 0){
            %>
            	<table class="table caption-top table-borderless table-sm user-select-none">
				  <caption><div class="display-6 m-4">Liste des etudiants </div></caption>
				  <thead>
				    <tr class="table-dark">
				      <th scope="col">Num</th>
				      <th scope="col">Nom</th>
				      <th scope="col">prenom</th>
				      <th scope="col">email</th>
				      <th scope="col">addresse</th>
				      <th scope="col">filiere</th>
				      <th scope="col">telephone</th>
				      <th scope="col" colspan="2">modifier role</th>
				    </tr>
				  </thead>
				  <tbody>
				  <%
				  	int i =1;
				  	for(Etudiant etudiant : etudiants){
				  	if(et.getEtudiant_id() != etudiant.getEtudiant_id()){
				  %>
				  
				    <tr>
				      <th scope="row"><%=i %></th>
				      <td><%=etudiant.getEtudiant_nom()%></td>
				      <td><%=etudiant.getEtudiant_prenom()%></td>
				      <td><%=etudiant.getEtudiant_email() %></td>
				      <td><%=etudiant.getEtudiant_adresse()%></td>
				      <td><%=etudiant.getEtudiant_filiere()%></td>
				      <td><%=etudiant.getEtudiant_telephone()%></td>
				      <td scope="col">
				      	<div class="row">
						    <div class="col col-lg-12 d-flex align-items-center">
						    	<form class="ms-2 justify-content-center" method="post" action="etudiants">
									<div>
								    	<input type="text" value="<%=etudiant.getEtudiant_id()%>" name="delete_id" hidden/>
									    <button type="submit" class="btn btn-sm btn-outline-danger d-flex align-items-center">
								      	<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
										  <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
										  <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
										</svg>
								      	Delete</button>
								    </div>
								</form>
							    <form class="ms-2 justify-content-center" method="post" action="etudiants"  >
							    	<div class="">
									    <input type="text" value="<%=etudiant.getEtudiant_id()%>" name="etudiant_id" hidden/>
									    <select onchange="this.form.submit()" id="inputState" name="role" class="form-select">
					    					<option value="1" <%=etudiant.getEtudiant_role().equals("ADMIN")?"selected":""%>>ADMIN</option>
					    					<option value="2" <%=etudiant.getEtudiant_role().equals("USER")?"selected":""%>>USER</option>
					    				</select>
								    </div>
								</form>
								
							</div>
						</div>
				      </td>
				    </tr>
				  <%i++;}} %>
				  </tbody>
				</table>
				<%}else{ %>
					<div class="h3 m-3">
					  Aucun Etudiant disponible pour l'instant
					  <small class="text-muted">pouvez-vous essayer plus tard</small>
					</div>
				<%} %>
			</div>
			<div class="col-5">
				
			</div>
			
			<style>
				*{
					margin:0;
					padding:0;	
				}
				
				tr{
					cursor:pointer;
				}
				
				tr:hover{
					background-color:lightgrey;
				}
			</style>
        </div>
    </div>
</body>
</html>