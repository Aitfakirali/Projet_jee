<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList,com.demo.beans.Livre"%>
<%@page import="com.demo.beans.Category,java.io.File"%>
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
  		<div class="m-5 display-6 ">Détails du livre </div>
  		<%
  			Livre livre= (Livre)request.getAttribute("livre");
  		%>
  		<div class="card m-5 mb-3" style="max-width: 80%;">
		  <div class="row g-0">
		    <div class="col-md-4">
		      <img src="..." alt="...">
		    </div>
		    <div class="col-md-8">
		      <div class="card-body">
		        <h5 class="card-title mb-5"><%=livre.getLivre_titre()%></h5>
		        <p class="card-text"><%=livre.getDescription()!=null?livre.getDescription():"Il n y a aucune description pour l'instant"%></p>
		        <p class="card-text">Auteur : <strong><%=livre.getLivre_auteur()%></strong></p>
		        <p class="card-text">Emprunte : <small class="text-muted"><%=livre.isEmprunte()?" Oui":" Non"%></small></p>
		        <p class="card-text">La date de publication : <small class="text-muted"><%=livre.getDate_creation().toGMTString()%></small></p>
		        <p class="card-text">Categorie : <small class="text-muted"><%=livre.getCategory().getCategory_nom()%></small></p>
		        <div class="col col-xl-6">
					<form method="post" action="Livres" >
						<button type="submit" class="align-items-start col-4 m-2 btn btn-sm btn<%=livre.isEmprunte()?"":"-outline" %>-dark" <%=livre.isEmprunte()?"":"disabled"%>>retour</button>
						<input type="text" name="retour_id" value="<%=livre.getLivre_id()%>" hidden/>
					</form>
			  	</div>
		      </div>
		    </div>
		    
		  </div>
		</div>
  		
			<style>
				*{
					margin:0;
					padding:0;	
				}
			</style>
    </div>
</body>
</html>