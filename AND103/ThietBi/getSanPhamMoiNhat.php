<?php

// Lấy về các sản phẩm mới nhất

include "connect.php";
$mangSanPhamMoiNhat = array();
$query = "SELECT * FROM sanpham ORDER BY ID DESC LIMIT 6";
$data = mysqli_query($conn, $query);
while($row=mysqli_fetch_assoc($data)){
    array_push($mangSanPhamMoiNhat, new sanPhamMoiNhat(
        $row['id'], $row['tensanpham'], $row['giasanpham'], $row['hinhanhsanpham'], $row['motasanpham'], $row['idsanpham']
    ));
}

echo json_encode($mangSanPhamMoiNhat); // Trả về kết quả

class SanPhamMoiNhat{
    function __construct($id, $tenSP, $giaSP, $hinhAnhSP, $moTaSP, $idSanPham){
        $this->id=$id;
        $this->tenSP=$tenSP;
        $this->giaSP=$giaSP;
        $this->hinhAnhSP=$hinhAnhSP;
        $this->moTaSP=$moTaSP;
        $this->idSanPham=$idSanPham;
    }
}