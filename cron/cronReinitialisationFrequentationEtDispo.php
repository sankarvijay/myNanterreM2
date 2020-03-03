
<?php

/* Attempt MySQL server connection. Assuming you are running MySQL
server with default setting (user 'root' with no password) */
try{
    $pdo = new PDO("mysql:host=sql171.main-hosting.eu;dbname=u749839367_m1", "u749839367_vijay", "!GGbW5O~");
    // Set the PDO error mode to exception
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch(PDOException $e){
    die("ERROR: Could not connect. " . $e->getMessage());
}
 
// Attempt update query execution
// Ce cron remet à 0 la disponibilité des produits et de la frequentation des cafétarias du Crous à minuit.
try{
    $sql = "UPDATE vente SET dispo=1 WHERE id_p>0";    
    $pdo->exec($sql);
    
    $sql2 = "UPDATE Crous SET frequentation=1 WHERE id_bat>0";    
    $pdo->exec($sql2);
    
    echo "Records were updated successfully.";
} catch(PDOException $e){
    die("ERROR: Could not able to execute $sql. " . $e->getMessage());
}
 
// Close connection
unset($pdo);
?>
