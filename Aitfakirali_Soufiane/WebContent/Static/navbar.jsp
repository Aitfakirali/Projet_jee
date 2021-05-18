<%@page import="com.demo.beans.Etudiant"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<%  Etudiant et=(Etudiant)session.getAttribute("Etudiant");  %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand">FSBM Bibliotheque</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-3 mb-lg-0">
        <li class="nav-item border-end border-dark">
          <a class="nav-link d-flex align-items-center" aria-current="page" href="Home"> 
          	<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-book" viewBox="0 0 16 16">
			  <path d="M1 2.828c.885-.37 2.154-.769 3.388-.893 1.33-.134 2.458.063 3.112.752v9.746c-.935-.53-2.12-.603-3.213-.493-1.18.12-2.37.461-3.287.811V2.828zm7.5-.141c.654-.689 1.782-.886 3.112-.752 1.234.124 2.503.523 3.388.893v9.923c-.918-.35-2.107-.692-3.287-.81-1.094-.111-2.278-.039-3.213.492V2.687zM8 1.783C7.015.936 5.587.81 4.287.94c-1.514.153-3.042.672-3.994 1.105A.5.5 0 0 0 0 2.5v11a.5.5 0 0 0 .707.455c.882-.4 2.303-.881 3.68-1.02 1.409-.142 2.59.087 3.223.877a.5.5 0 0 0 .78 0c.633-.79 1.814-1.019 3.222-.877 1.378.139 2.8.62 3.681 1.02A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.293-.455c-.952-.433-2.48-.952-3.994-1.105C10.413.809 8.985.936 8 1.783z"/>
			</svg>
			<div class="ms-2">Accés Livres</div>
		  </a>
        </li>
        <%if(et != null){%>
        	<%if(et.getEtudiant_role().equals("ADMIN")){ %>
        	<li class="nav-item">
	          <a class="nav-link d-flex align-items-center" aria-current="page" href="Livres">
	          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-list-nested" viewBox="0 0 16 16">
				  <path fill-rule="evenodd" d="M4.5 11.5A.5.5 0 0 1 5 11h10a.5.5 0 0 1 0 1H5a.5.5 0 0 1-.5-.5zm-2-4A.5.5 0 0 1 3 7h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm-2-4A.5.5 0 0 1 1 3h10a.5.5 0 0 1 0 1H1a.5.5 0 0 1-.5-.5z"/>
				</svg>
	          <div class="ms-2">Gestion des livres</div>
	         </a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link d-flex align-items-center" aria-current="page" href="etudiants">
	          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-person-lines-fill" viewBox="0 0 16 16">
				  <path d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm-5 6s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zM11 3.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5zm.5 2.5a.5.5 0 0 0 0 1h4a.5.5 0 0 0 0-1h-4zm2 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1h-2zm0 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1h-2z"/>
			  </svg>
			  <div class="text ms-2">Gestion des etudiants</div>
			  </a>
	        </li>
	        <%}} %>
         </ul>
         
          <%if(et != null){ %>
         	<div class="nav-item dropdown">
	          <a class="nav-link dropdown-toggle d-flex align-items-center" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
				  <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
				  <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
				</svg>
				<div class="text ms-2"><%=et.getEtudiant_email() %></div>
	          </a>
	          <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
	           	<li><h6 class="dropdown-header"><%=et.getEtudiant_nom() %> <%=et.getEtudiant_prenom() %></h6></li>
	           	 <li><a class="dropdown-item" href="">Modifiez votre compte</a></li>
	            <li><a class="dropdown-item" href="Disconnect">Disconnect</a></li>
	          </ul>
	        </div>
	      <%} else{%>
	         <div class="nav-item">
	         	<ul class="navbar-nav me-auto mb-3 mb-lg-0">
		           	<li class="nav-item">
		          		<a class="nav-link" aria-current="page" href="Auth">Connexion</a>
		        	</li>	
		        </ul>
		     </div>
	     <%} %>
    </div>
  </div>
</nav>