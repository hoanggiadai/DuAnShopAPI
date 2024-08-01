<?php

// Kết nối với CSDL

$host = "localhost"; $u = "root"; $p = ""; $db = "ThietBi";
$conn = mysqli_connect($host, $u, $p, $db);
mysqli_query($conn, "SET NAMES 'utf8'");
