 			
 			<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
			  <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="staticBackdropLabel"><h2 class="display-6">Inscription</h2></h5>
			        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			      </div>
			      <div class="modal-body">
			       <div class="col col-xl-12 m-auto  shadow p-3 mb-5 bg-body rounded">
			 				<form class="row g-3 test-validation" action="register" method="post" novalidate>
			 					<div class="col col-sm-6 col-md-6">
								    <label for="inputEmail4" class="form-label">Nom</label>
								    <input type="text" name="nom" class="form-control" id="inputNom4" required>
								    <div class="invalid-feedback">
								      le nom ne doit pas être vide
								    </div>
								  </div>
								  <div class="col col-sm-6 col-md-6">
								    <label for="inputPassword4" class="form-label">Prenom</label>
								    <input type="text" name="prenom" class="form-control" id="inputPrenom4" required>
								    <div class="invalid-feedback">
								      le prenom ne doit pas être vide
								    </div>
								  </div>
								  <div class="col col-sm-6 col-md-6">
								    <label for="inputEmail4" class="form-label">Addresse Email</label>
								    <input type="email" name="email" placeholder="example@gmail.com" class="form-control" id="inputEmail4" required>
								    <div class="invalid-feedback">
								      l'email ne doit pas être vide et doit être formé comme e-mail
								    </div>
								  </div>
								  <div class="col col-sm-6  col-md-6">
								    <label for="inputPassword4" class="form-label">Mot de passe</label>
								    <input type="password" name="password" class="form-control" id="inputPassword4" min=8 required>
								    
								    <div class="invalid-feedback">
								      le mot de passe doit comporter plus de 8 caractères
								    </div>
								  </div>
								  <div class="col col-sm-12 col-12">
								    <label for="inputAddress" class="form-label">Addresse</label>
								    <input type="text" name="addresse" class="form-control" id="inputAddresse" placeholder="Rue 4 N° 35" required>
								    <div class="invalid-feedback">
								      l'addresse ne doit pas être vide 
								    </div>
								  </div>
								  <div class="col col-sm-5  col-md-5">
								    <label for="inputCity" class="form-label">Telephone</label>
								    <input type="text" name="telephone" class="form-control" id="inputTelephone" min=10 required>
								    <div class="invalid-feedback">
								      le telephone doit comporter plus de 10 caractères
								    </div>
								  </div>
								  <div class="col col-sm-4  col-md-4">
								    <label for="inputCity" class="form-label">Filiere</label>
								    <input type="text" name="filiere" class="form-control" id="inputFiliere" required>
								    <div class="invalid-feedback">
								      la filiere ne doit pas être vide 
								    </div>
								  </div>
								  <div class="col col-sm-3  col-md-3">
								    <label for="inputZip" class="form-label">Age</label>
								    <input type="Number" name="age" min=0 max=50 class="form-control" id="inputAge">
								  </div>
								  <div class="col col-sm-12  col-12 ps-3 m-3">
								    <button type="submit" class="btn btn-dark">Inscrivez-vous</button>
								  </div>
								</form>           
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
			      </div>
			    </div>
			  </div>
			</div>
 			