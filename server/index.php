<?php
if (isset($_GET['name'])){
   $filename ="txt/".$_GET['name'];
   echo $_GET['name'];
   $file = fopen( $filename, "w" );
   if( $file == false ){
      echo ( "Error in opening new file" ); exit();
   }
} elseif (isset($_GET['name1'])){
   $filename ="txt/".$_GET['name1'];
   $file = fopen( $filename, "a" );
   $chat = $_GET['chatter'].": ".$_GET['message']."\n";
   fwrite( $file, $chat );
   fclose($file);
} elseif (isset($_GET['connected'])){
   $filename ="txt/".$_GET['name2'];
   $file = fopen( $filename, "a" );
   $chat = "- " .$_GET['connected']." connected -\n";
   fwrite( $file, $chat );
   fclose($file);
} elseif (isset($_GET['disconnected'])){
   $filename ="txt/".$_GET['name2'];
   $file = fopen( $filename, "a" );
   $chat = "- " .$_GET['disconnected']." disconnected -\n";
   fwrite( $file, $chat );
   fclose($file);
} elseif(isset($_GET['check'])){
   echo file_exists("txt/".$_GET['check']);
}
