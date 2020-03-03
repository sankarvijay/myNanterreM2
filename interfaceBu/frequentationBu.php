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
    <style>
#content
{
    margin-left:24px;
    margin-right:24px;
}

#tabs ul
{
    font: normal 14px arial, sans, sans-serif;
    -list-style-type: none;
    border-bottom: 1px solid gray;
    margin: 0;
    padding-left:0;
    padding-right:0;
    padding-bottom: 26px;
}

#tabs ul li 
{
    display: inline;
    float: left;
    height: 24px;
    min-width:80px;
    text-align:center;
    padding:0;
    margin: 1px 0px 0px 0px;
    border: 1px solid gray;
}

#tabs ul li.selected 
{
    border-bottom: 1px solid #fff;
    background-color: #fff;
}


#tabs ul li a 
{
    float: left;
    color: #666;
    text-decoration: none;
    padding: 4px;
    text-align:center;
    background-color:#eee;
    min-width:80px;
    border-bottom: 1px solid gray;
}

#tabs ul li a.selected
{
    color: #000;
    font-weight:bold;
    background-color: #fff;
    border-bottom: 1px solid #fff;
}

#tabs ul li a:hover
{
    color: #000;
    font-weight:bold;
    background-color: #fff;
}

#container 
{
    background: white;
    border:1px solid gray;
    border-top: none;
    height:100%;
    width:100%;
    padding:0;
    margin:0;
    left:0;
    top:0;	
}

iframe
{
    border:none;
    margin:0;
    padding:0;
}
    </style>

<script  type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"></script>

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-progressbar/0.9.0/bootstrap-progressbar.js"></script>

<script>
    
    function loadit( element)
{
	var container = document.getElementById('container');
	container.src=element.rel;

	var tabs=document.getElementById('tabs').getElementsByTagName("a");
	for (var i=0; i < tabs.length; i++)
	{
		if(tabs[i].rel == element.rel) 
			tabs[i].className="selected";
		else
			tabs[i].className="";
	}
}

function startit()
{
	var tabs=document.getElementById('tabs').getElementsByTagName("a");
	var container = document.getElementById('container');
	container.src = tabs[0].rel;
}

window.onload=startit;
</script>

</head>
<body>
    <div id="tabs">
    <ul>
        <li><a href="#" rel="affluence.php" class="selected"  onclick="loadit(this)">Affluence des salles</a></li> 
        <li><a href="#" rel="ajouterSalle.php" onClick="loadit(this)" >Ajouter une salle</a></li> 
        
    </ul>
</div>
<iframe id="container"></iframe>

    </body>
    	</html>