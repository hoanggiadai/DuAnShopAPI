<?php

// Lấy dữ liệu trong bảng loại sản phẩm

include "connect.php";
$query = "SELECT * from loaisanpham"; // Lệnh truy vấn
$data = mysqli_query($conn, $query); // Thực thi truy vấn
$mangLoaiSP = array();
while($row = mysqli_fetch_assoc($data)) { // Đọc theo từng dòng
    array_push($mangLoaiSP, new LoaiSP($row['id'], $row['tenloaisanpham'], $row['hinhloaisanpham']));
}

echo json_encode($mangLoaiSP); // Trả về json cho người dùng
class LoaiSP {
    function __construct($id, $tenloaisanpham, $hinhloaisanpham){
        $this->id=$id;
        $this->tenloaisanpham=$tenloaisanpham;
        $this->hinhloaisanpham=$hinhloaisanpham;
    }
}