<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Đăng ký</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" />
</head>
<body>
<div class="container">
  <div class="register-form">
    <div class="main-div">
      <div class="container-fluid">
        <section class="gradient-custom">
          <div class="page-wrapper">
            <div class="row d-flex justify-content-center align-items-center">
              <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card text-white" style="border-radius: 1rem; background-color: #35bf76;">
                  <div class="card-body p-5">
                    <div class="mb-md-5 mt-md-4 pb-5 text-center">
                      <h2 class="fw-bold mb-2 text-uppercase">Register</h2>
                      <p class="text-white-50 mb-5">Please enter your details to create an account!</p>

                      <form id="formRegister">
                        <div class="form-outline form-white mb-4">
                          <label class="form-label" for="fullname">Full Name</label>
                          <input type="text" class="form-control" id="fullname" name="fullname" placeholder="Họ tên" required>
                        </div>

                        <div class="form-outline form-white mb-4">
                          <label class="form-label" for="username">Tên Đăng Nhập</label>
                          <input type="text" class="form-control" id="username" name="username" placeholder="Tên đăng nhập" required>
                        </div>

                        <div class="form-outline form-white mb-4">
                          <label class="form-label" for="email">Email</label>
                          <input type="text" class="form-control" id="email" name="email" placeholder="Email">
                        </div>

                        <div class="form-outline form-white mb-4">
                          <label class="form-label" for="password">Mật khẩu</label>
                          <input type="password" class="form-control" id="password" name="password" placeholder="Mật khẩu" required>
                        </div>

                        <div class="form-outline form-white mb-4">
                          <label class="form-label" for="confirmPassword">Xác nhận mật khẩu</label>
                          <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Xác nhận mật khẩu" required>
                        </div>

                        <button type="button" class="btn btn-primary" id="btnHandleRegister">Đăng ký</button>
                      </form>

                      <div class="text-center">
                        <p class="mb-0 tex-center account">Already have an account? <a href="/login" class="text-white-50 fw-bold">Login here</a></p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>
    </div>
  </div>
</div>

<%@ include file="/WEB-INF/views/token-utils.jsp" %>

<script>
  $('#btnHandleRegister').click(function (event) {
    event.preventDefault();
    var data = {};
    var formData = $('#formRegister').serializeArray();
    $.each(formData, function (i, v) {
      data[v.name] = v.value;
    });

    if (!data['fullname'] || !data['username'] || !data['password'] || !data['confirmPassword']) {
      alert('Vui lòng nhập đầy đủ thông tin đăng ký.');
      return;
    }
    if (data['password'] !== data['confirmPassword']) {
      alert('Mật khẩu xác nhận không khớp.');
      return;
    }

    handleRegister(data);
  });

  function handleRegister(data) {
    $.ajax({
      type: "POST",
      url: "/api/user/register",
      data: JSON.stringify(data),
      contentType: "application/json",
      dataType: "JSON",
      success: function (response) {
        console.log("Registration successful!");
        alert("Đăng ký thành công!");
        window.location.href = "/login";
      },
      error: function (xhr) {
        console.error("Error:", xhr.responseText);
        const errorMessage = xhr.responseJSON?.error || 'Đăng ký thất bại!';
        alert(errorMessage);
      }
    });
  }
</script>

</body>
</html>
