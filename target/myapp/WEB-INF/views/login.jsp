
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Đăng nhập</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" />


</head>
<body>
<div class="container">
	<div class="login-form">
		<div class="main-div">
			<div class="container-fluid">
				<section class="gradient-custom">
					<div class="page-wrapper">
						<div class="row d-flex justify-content-center align-items-center">
							<div class="col-12 col-md-8 col-lg-6 col-xl-5">
								<div class="card text-white" style="border-radius: 1rem; background-color: #35bf76;">
									<div class="card-body p-5">
										<div class="mb-md-5 mt-md-4 pb-5 text-center">
											<h2 class="fw-bold mb-2 text-uppercase">Login</h2>
											<p class="text-white-50 mb-5">Please enter your login and password!</p>
											<form id="formLogin">
												<div class="form-outline form-white mb-4">
													<label class="form-label" for="username">Email</label>
													<input type="text" class="form-control" id="username" name = "username" placeholder="Tên đăng nhập" required>
												</div>

												<div class="form-outline form-white mb-4">
													<label class="form-label" for="password">Password</label>
													<input type="password" class="form-control" id="password" name = "password" placeholder="Mật khẩu" required>
												</div>

												<div class="form-check d-flex justify-content-center mb-5">
													<div><input class="form-check-input me-2" type="checkbox" value="" id="form2Example3cg" /></div>
													<div><label class="form-check-label">
														Remember Password
													</label></div>
												</div>
												<p class="small mb-2 pb-lg-2"><a class="text-white-50" href="/reset-password">Forgot password?</a></p>

												<button type="button" class="btn btn-primary" id="btnHandleLogin">Đăng nhập</button>

											</form>
											<div class="d-flex justify-content-center text-center mt-2 pt-1">
												<a href="#!" class="login-extension text-white"><i class="fab fa-facebook-f fa-lg"></i></a>
												<a href="#!" class="login-extension text-white"><i class="fab fa-twitter fa-lg mx-4 px-2"></i></a>
												<a href="#!" class="login-extension text-white"><i class="fab fa-google fa-lg"></i></a>
											</div>
										</div>
										<div class="text-center">
											<p class="mb-0 tex-center account">Don't have an account? <a href="/sign-up" class="text-white-50 fw-bold">Sign Up</a></p>
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

	// $(document).ajaxSend(function (event, jqXHR) {
	// 	const token = localStorage.getItem('token');
	// 	if (token) {
	// 		jqXHR.setRequestHeader('Authorization', 'Bearer ' + token);
	// 	}
	// });



	$('#btnHandleLogin').click(function (event) {
		event.preventDefault();
		var data = {};  // tạo cấu trúc JSON
		var formData = $('#formLogin').serializeArray();
		$.each(formData, function (i, v) {
			data["" + v.name + ""] = v.value;
		})
		if (!data['username'] || !data['password']) {
			alert('Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu.');
			return;
		}
		else{
			handleLogin(data)
		}
	});
	function handleLogin(data) {
		$.ajax({
			type: "POST",
			url: "/login",
			data: JSON.stringify(data), // Dữ liệu gửi đến server
			contentType: "application/json",
			dataType: "JSON",

			success: function (respond) {
				// Sau khi server gửi cookie (JWT) về, bạn chỉ cần xử lý điều hướng
				console.log("Login successful!");
				window.location.href = respond.redirectUrl; // Chuyển hướng người dùng
			},
			error: function (xhr) {
				console.error("Error:", xhr.responseText);
				const errorMessage = xhr.responseJSON?.error || 'Đăng nhập thất bại!';
				alert(errorMessage);
			}
		});
	}

	// function handleLogin(data) {
	// 	$.ajax({
	// 		type:"POST",
	// 		url: "/login",
	// 		data:JSON.stringify(data), // biến data là dữ liệu sẽ được gửi đến server và được ép theo kiểu JSON
	// 		contentType:"application/json",  // giống như một cái cờ báo hiệu, cho biết rằng dữ liệu gửi về server là kiểu JSON
	// 		dataType:"JSON",    // chỉ định kiểu dữ liệu mà client mong muốn nhận từ server sau khi server xử lý yêu cầu và phản hồi lại
	//
	// 		success: function (respond) {
	// 			localStorage.setItem('token', respond.token); // Lưu JWT vào localStorage
	// 			console.log(localStorage.getItem('token'));
	// 			$.ajax({
	// 				type: "GET",
	// 				url: respond.redirectUrl,
	// 				headers: {
	// 					"Authorization": "Bearer " + respond.token
	// 				},
	// 				success: function () {
	// 					window.location.href = respond.redirectUrl; // Chuyển hướng thành công
	// 				},
	// 				error: function (xhr) {
	// 					alert('Không thể chuyển hướng: ' + (xhr.responseText || 'Lỗi không xác định.'));
	// 				}
	// 			});
	//
	// 		},
	// 		error: function(xhr){
	// 			console.log(JSON.stringify(data));
	// 			console.log('Error:', xhr.responseText);
	// 			const errorMessage = xhr.responseJSON?.error || 'Đăng nhập thất bại!';
	// 			alert(errorMessage);
	//
	// 		}
	//
	// 	});
	//
	// }
</script>

</body>
</html>
