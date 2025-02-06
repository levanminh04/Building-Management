<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Unauthorized</title>
  <meta http-equiv="refresh" content="5;url=/login" /> <!-- Tự động chuyển hướng sau 5 giây -->
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f8d7da;
      color: #721c24;
      display: flex;
      align-items: center;
      justify-content: center;
      height: 100vh;
      margin: 0;
    }
    .container {
      text-align: center;
      background-color: #f5c6cb;
      border: 1px solid #f5c2c7;
      border-radius: 10px;
      padding: 20px 40px;
    }
    h1 {
      font-size: 48px;
      margin: 0 0 10px 0;
    }
    p {
      font-size: 18px;
      margin: 10px 0;
    }
    a {
      display: inline-block;
      margin-top: 20px;
      text-decoration: none;
      color: #0056b3;
      font-weight: bold;
    }
    a:hover {
      text-decoration: underline;
    }
  </style>
  <script>
    // Nếu muốn chuyển hướng bằng JavaScript thay vì <meta>, có thể dùng đoạn sau:
    setTimeout(function () {
      window.location.href = "/login";
    }, 5000); // Chuyển hướng sau 5 giây
  </script>
</head>
<body>
<div class="container">
  <h1>401 - Unauthorized</h1>
  <p>${message}</p> <!-- Hiển thị thông báo lỗi từ backend -->
  <p>Redirecting to <a href="/login">Login Page</a> in 5 seconds...</p>
</div>
</body>
</html>
