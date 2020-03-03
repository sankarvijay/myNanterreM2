
<?php
setlocale(LC_TIME, "fr_FR");


/* Attempt MySQL server connection. Assuming you are running MySQL
server with default setting (user 'root' with no password) */
try{
    $pdo = new PDO("mysql:host=sql171.main-hosting.eu;dbname=u749839367_m1", "u749839367_vijay", "!GGbW5O~");
    // Set the PDO error mode to exception
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    } catch(PDOException $e){
        die("ERROR: Could not connect. " . $e->getMessage());
    }
?>

<!DOCTYPE html>
<html>
<head>
    <!-- Font Awesome -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
<!-- Bootstrap core CSS -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
<!-- Material Design Bootstrap -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.10.1/css/mdb.min.css" rel="stylesheet">
    <style>
        
body {
	background-color:;
}
html,
body {
	height: 100%;
}

.form-container {
	width: 600px;
	margin-left: auto;
	margin-right: auto;
	/*margin-top:90px;
	margin-bottom: 30px;*/
	padding: 30px;
	background-color: #fff;
	position: relative;
	    box-shadow: 0 16px 24px 2px rgba(0,0,0,0.14), 0 20px 30px 5px rgba(0,0,0,0.12), 0 8px 10px -5px rgba(0,0,0,0.3);
	position: absolute;
	top: 50%;
	left: 50%;

	-webkit-transform: translate(-50%, -50%);
	-ms-transform: translate(-50%, -50%);
	-o-transform: translate(-50%, -50%);
	transform: translate(-50%, -50%);
}

.form-container .row {
	margin-bottom: 0;
}


/* label color */
   .input-field label {
     color: #9c9c9c;
   }
   /* label focus color */
   .input-field input[type=text]:focus + label {
     color: #9c9c9c;
   }
   /* label underline focus color */
   .input-field input[type=text]:focus {
     border-bottom: 1px solid #9c9c9c;
     box-shadow: 0 1px 0 0 #9c9c9c;
   }
   /* valid color */
   .input-field input[type=text].valid {
     border-bottom: 1px solid #9c9c9c;
     box-shadow: 0 1px 0 0 #9c9c9c;
   }
   /* invalid color */
   .input-field input[type=text].invalid {
     border-bottom: 1px solid #9c9c9c;
     box-shadow: 0 1px 0 0 #9c9c9c;
   }
   /* icon prefix focus color */
   .input-field .prefix.active {
     color: #9c9c9c;
   }
  



.form-container h3 {
	font-size: 42px;
    letter-spacing: 0.1em;
    margin: 0;
    padding: 0;
    color: #ffffff;
    font-weight: 700;
    text-transform: uppercase;
    position: absolute;
    top: -46px;
}
@media  all and (max-width: 640px) {
	.form-container {
		position: static;
		-webkit-transform: translate(0, 0);
		-ms-transform: translate(0, 0);
		-o-transform: translate(0, 0);
		transform: translate(0, 0);
		width: 100%;
		margin-top: 70px;
		margin-bottom: 20px;
	}
	.form-container h3 {
	    font-size: 24px;
	    top: -26px;
	}
}

    </style>
<script  type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-progressbar/0.9.0/bootstrap-progressbar.js"></script>

</head>
<body>
    
<div class="imagebg"></div>
    	<div class="container">
    		<div class="form-container z-depth-5">
    			<h4 style="font-weight:bold;">Ajouter une nouvelle salle</h4>
    			<div class="row">
			    <form class="col s12" method="post" id="reused_form" >
			      <div class="row">
			        <div class="input-field col s12">
			          <label for="name" style="color:red;">Salle de la BU</label>
			            <input type="text" name="nom" required />
			        </div>
			      </div>
			      <br>
			       <div>
				    	<br>
                         <input type="submit" name="submit" style="width:20%;height:10%;color:red;" value="Submit">
				    </div>
				    <?
                        try{
                            if(isset($_POST['nom']) && $_POST['nom'] != "") 
                            {
                             // Create prepared statement
                        $sql2 = "INSERT INTO bibliotheque (nomSalle) VALUES (:nom)";
                        $stmt = $pdo->prepare($sql2);
                        
                        // Bind parameters to statement
                        $stmt->bindParam(':nom', $_POST['nom']);

                        // Execute the prepared statement
                        $stmt->execute();
               
                        if (isset($_POST['submit'])) {
                            echo "Votre salle a bien été ajoutée !";
                            header("refresh: 3"); 
                        } 
                            }
                        
                    } catch(PDOException $e){
                    }
				    ?>
				    	
        </div>

			    </form>

			  </div>
    		</div>
    	</div>
    	  
    	</body>
  	<footer style="width:100%;position:absolute;" class="page-footer font-small special-color-dark">
    <div class="footer-copyright text-center py-3">© 2020 Copyright:
    <a href="https://mdbootstrap.com/education/bootstrap/"> myNanterre</a>
  </div>
</footer>
    	</html>