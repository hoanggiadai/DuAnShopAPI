<?php

// Lấy về toàn bộ sản phẩm của bảng sản phẩm

include "connect.php";
$page = $_GET['page']; // Mobile truyền sang
$idSP = $_POST['idSanPham'];
$space=5;
$limit=($page-1)*$space;
$mangSanPham = array();
$query = "SELECT * FROM sanpham WHERE idsanpham=$idSP LIMIT $limit, $space";
$data = mysqli_query($conn, $query);
while($row=mysqli_fetch_assoc($data)){
    array_push($mangSanPham, new sanPham(
        $row['id'], $row['tensanpham'], $row['giasanpham'], $row['hinhanhsanpham'], $row['motasanpham'], $row['idsanpham']
    ));
}

echo json_encode($mangSanPham); // Trả về kết quả

class SanPham{
    function __construct($id, $tenSP, $giaSP, $hinhAnhSP, $moTaSP, $idSanPham){
        $this->id=$id;
        $this->tenSP=$tenSP;
        $this->giaSP=$giaSP;
        $this->hinhAnhSP=$hinhAnhSP;
        $this->moTaSP=$moTaSP;
        $this->idSanPham=$idSanPham;
    }
}