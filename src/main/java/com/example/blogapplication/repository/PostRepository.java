package com.example.blogapplication.repository;

import com.example.blogapplication.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/*
* JpaRepository là một phần của Spring Data JPA và cung cấp các phương thức để thực hiện các thao tác cơ bản với cơ sở dữ liệu,
* chẳng hạn như thêm, sửa, xóa, truy vấn dữ liệu.
*
* Post: Đối số đầu tiên của JpaRepository xác định entity mà giao diện PostRepository sẽ làm việc với.
* Trong trường hợp này, đó là Post entity.
*
* Long: Đối số thứ hai là kiểu dữ liệu của khóa chính của entity.
* Trong trường hợp này, Long là kiểu của thuộc tính id trong entity Post.
* */
public interface PostRepository extends JpaRepository<Post,Long> {
}
