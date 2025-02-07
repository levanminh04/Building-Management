<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.javaweb.security.utils.SecurityUtils" %>
<nav class="navbar-expand-lg navbar-dark fixed-top">



		<%@ include file="/WEB-INF/views/token-utils.jsp" %>

		<div class="row navbar">
			<div class="col-12 col-md-3">
				<div class="logo">
					<a href="">
						<img src="https://bizweb.dktcdn.net/100/328/362/themes/894751/assets/logo.png?1676257083798"
							 alt="">
					</a>
				</div>
			</div>
			<div class="col-12 col-md-6">
				<div class="item-menu">
					<div class="nav nav1">
						<div class="nav-item p-2">
							<a class="nav-item-link" href="/trang-chu">
								<span style="color: var(--primary-color);">Trang chủ</span>
							</a>
						</div>
						<div class="nav-item p-2">
							<a href='<c:url value='/gioi-thieu'/>'>
								<%--<span>Giới thiệu</span>--%>
								<span style="color: var(--primary-color);">Giới thiệu</span>
							</a>
						</div>
						<div class="nav-item p-2">
							<%--<a class="nav-item-link" href="./Duan.html">--%>
							<a href='<c:url value='/san-pham'/>'>
								<%--<span>Sản phẩm</span>--%>
								<span style="color: var(--primary-color);">Sản phẩm</span>
							</a>
						</div>
						<div class="nav-item p-2">
							<a href='<c:url value='/tin-tuc'/>'>
								<%--<span>Tin tức</span>--%>
								<span style="color: var(--primary-color);">Tin tức</span>
							</a>
						</div>
						<div class="nav-item p-2">
							<a href='<c:url value='/lien-he'/>'>
								<%--<span>Liên hệ</span>--%>
									<span style="color: var(--primary-color);">Liên hệ</span>
							</a>
						</div>
					</div>
				</div>
			</div>
			<div class="nav-item p-2" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<%--<li class="nav-item active"><a class="nav-link" href="/trang-chu#">Trang chủ--%>
						<%--<span class="sr-only">(current)</span>--%>
					<%--</a></li>--%>
					<c:if test="${empty username}">
						<li class="nav-item p-2">
							<a class="nav-link" href="<c:url value='/login'/>">Đăng nhập</a>
						</li>
						<li class="nav-item p-2"><a class="nav-link" href="/sign-up">Đăng ký</a></li>
					</c:if>
					<c:if test="${not empty username}">
						<li class="nav-item"><a class="nav-link" href="javascript:void(0);" onclick="navigateWithRefresh('/admin/home')"  > Xin chào ${username}</a></li>
						            <li class="nav-item">
             						     <a class="nav-link" href="#" id="btnLogout">Thoát</a>
            						</li>
					</c:if>


				</ul>
			</div>



		</div>
</nav>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
	$('#btnLogout').click(function (event) {
		event.preventDefault();  // Ngăn chặn việc chuyển hướng mặc định của thẻ <a>

		// Gọi đến controller logout để xóa cookie JWT
		$.ajax({
			type: 'post',
			url: '/logout',  // Địa chỉ logout controller
			success: function (response) {
				console.log(response.message); // In ra thông điệp trả về từ server (nếu có)
				// Sau khi logout thành công, chuyển hướng người dùng
				window.location.href = '<c:url value="/login"/>';  // Hoặc trang bạn muốn chuyển hướng sau khi logout
			},
			error: function (response) {
				console.error('Logout failed:', response);  // In ra lỗi nếu có
				alert('Đã có lỗi khi thoát, vui lòng thử lại!');
			}
		});
	});
</script>