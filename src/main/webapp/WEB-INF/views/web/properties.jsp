


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <meta name="author" content="Untree.co" />
  <link rel="shortcut icon" href="favicon.png" />

  <meta name="description" content="" />
  <meta name="keywords" content="bootstrap, bootstrap5" />

  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link
          href="https://fonts.googleapis.com/css2?family=Work+Sans:wght@400;500;600;700&display=swap"
          rel="stylesheet"
  />

  <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/icomoon/style.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/flaticon/font/flaticon.css" />


  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/aos.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tiny-slider.css">



</head>
<body>

<header>
  <!-- MENU  -->
  <div class="p-4" style = "font-size: 16px; font-family: 'Roboto', sans-serif;">
    <div class="row navbar" style="width: 100%; display: flex; align-items: center; justify-content: space-between; padding: 0.5rem 1rem; background-color: #fff; flex-wrap: wrap; font-size: 16px; font-family: 'Roboto', sans-serif;">
      <div class="col-12 col-md-3 logo-container" style="display: flex; align-items: center; justify-content: center;">
        <div class="logo">
          <a href="">
            <img src="https://bizweb.dktcdn.net/100/328/362/themes/894751/assets/logo.png?1676257083798"
                 alt="Logo" style="max-height: 50px;">
          </a>
        </div>
      </div>

      <div class="col-12 col-md-6 menu-container" style="display: flex; justify-content: center; font-size: 16px; font-family: 'Roboto', sans-serif;">
        <div class="item-menu" >
          <div class="nav nav1" style="display: flex; gap: 20px; font-size: 16px; font-family: 'Roboto', sans-serif;">
            <div class="nav-item" style="padding: 10px; font-size: 15.2px; font-family: 'Roboto', sans-serif;">
              <a class="nav-item-link" href="/trang-chu" style="font-size: 15.2px; font-family: 'Roboto', sans-serif; font-weight: bold; color: #35bf76; text-decoration: none;">
                <span style="font-size: 18px !important; font-family: 'Roboto', sans-serif;">Trang chủ</span>
              </a>
            </div>
            <div class="nav-item" style="padding: 10px; font-size: 16px; font-family: 'Roboto', sans-serif;" >
              <a class="nav-item-link" href="/gioi-thieu" style=" font-family: 'Roboto', sans-serif; font-weight: bold; color: #35bf76; text-decoration: none;">
                <span style="font-size: 18px !important; font-family: 'Roboto', sans-serif;">Giới thiệu</span>
              </a>
            </div>
            <div class="nav-item" style="padding: 10px; font-size: 15.2px; font-family: 'Roboto', sans-serif;">
              <a class="nav-item-link" href="/san-pham" style="font-size: 15.2px; font-family: 'Roboto', sans-serif; font-weight: bold; color: #35bf76; text-decoration: none;">
                <span style="font-size: 18px !important; font-family: 'Roboto', sans-serif;">Sản phẩm</span>
              </a>
            </div>
            <div class="nav-item" style="padding: 10px; font-size: 15.2px; font-family: 'Roboto', sans-serif;">
              <a class="nav-item-link" href="/tin-tuc" style="font-size: 15.2px; font-family: 'Roboto', sans-serif; font-weight: bold; color: #35bf76; text-decoration: none;">
                <span style="font-size: 18px !important; font-family: 'Roboto', sans-serif;">Tin tức</span>
              </a>
            </div>
            <div class="nav-item" style="padding: 10px; font-size: 15.2px; font-family: 'Roboto', sans-serif;">
              <a class="nav-item-link" href="/lien-he" style="font-size: 15.2px; font-family: 'Roboto', sans-serif; font-weight: bold; color: #35bf76; text-decoration: none;">
                <span style="font-size: 18px !important; font-family: 'Roboto', sans-serif;">Liên hệ</span>
              </a>
            </div>

          </div>
        </div>
      </div>

      <div class="col-12 col-md-3 button-container" style="display: flex; justify-content: flex-end;">
        <a href="https://zalo.me/${building.managerphone}" target="_blank" style="text-decoration: none;">
          <button class="btn btn-primary" style="background-color: #35bf76; border: none; padding: 10px 20px; font-size: 16px; border-radius: 5px; color: white; font-weight: bold;">
            Liên hệ tư vấn
          </button>
        </a>
      </div>
    </div>
  </div>

</header>


      <div class="site-mobile-menu site-navbar-target">
        <div class="site-mobile-menu-header">
          <div class="site-mobile-menu-close">
            <span class="icofont-close js-menu-toggle"></span>
          </div>
        </div>
        <div class="site-mobile-menu-body"></div>
      </div>


<%--      <div class="section">--%>
<%--        <div class="container">--%>
<%--          <div class="row justify-content-between">--%>
<%--            <div class="col-lg-7">--%>
<%--              <div class="img-property-slide-wrap">--%>
<%--                <div class="img-property-slide">--%>
<%--                  <img src="${pageContext.request.contextPath}/images/img_1.jpg" alt="Image" class="img-fluid" />--%>
<%--                  <img src="${pageContext.request.contextPath}/images/img_2.jpg" alt="Image" class="img-fluid" />--%>
<%--                  <img src="${pageContext.request.contextPath}/images/img_3.jpg" alt="Image" class="img-fluid" />--%>
<%--                </div>--%>
<%--              </div>--%>
<%--            </div>--%>
<%--            <div class="col-lg-4">--%>
<%--              <h2 class="heading text-primary">5232 California Ave. 21BC</h2>--%>
<%--              <p class="meta">California, United States</p>--%>
<%--              <p class="text-black-50">--%>
<%--                Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ratione--%>
<%--                laborum quo quos omnis sed magnam id, ducimus saepe, debitis error--%>
<%--                earum, iste dicta odio est sint dolorem magni animi tenetur.--%>
<%--              </p>--%>
<%--              <p class="text-black-50">--%>
<%--                Perferendis eligendi reprehenderit, assumenda molestias nisi eius--%>
<%--                iste reiciendis porro tenetur in, repudiandae amet libero.--%>
<%--                Doloremque, reprehenderit cupiditate error laudantium qui, esse--%>
<%--                quam debitis, eum cumque perferendis, illum harum expedita.--%>
<%--              </p>--%>
<%--              --%>
<%--              <div class="d-block agent-box p-5">--%>
<%--                <div class="img mb-4">--%>
<%--                  <img--%>
<%--                          src="${pageContext.request.contextPath}/images/person.jpg"--%>
<%--                          alt="Image"--%>
<%--                          class="img-fluid"--%>
<%--                  />--%>
<%--                </div>--%>
<%--                <div class="text">--%>
<%--                  <h3 class="mb-0">Alicia Huston</h3>--%>
<%--                  <div class="meta mb-3">Real Estate</div>--%>
<%--                  <p>--%>
<%--                    Lorem ipsum dolor sit amet consectetur adipisicing elit.--%>
<%--                    Ratione laborum quo quos omnis sed magnam id ducimus saepe--%>
<%--                  </p>--%>
<%--                  <ul class="list-unstyled social dark-hover d-flex">--%>
<%--                    <li class="me-1">--%>
<%--                      <a href="#"><span class="icon-instagram"></span></a>--%>
<%--                    </li>--%>
<%--                    <li class="me-1">--%>
<%--                      <a href="#"><span class="icon-twitter"></span></a>--%>
<%--                    </li>--%>
<%--                    <li class="me-1">--%>
<%--                      <a href="#"><span class="icon-facebook"></span></a>--%>
<%--                    </li>--%>
<%--                    <li class="me-1">--%>
<%--                      <a href="#"><span class="icon-linkedin"></span></a>--%>
<%--                    </li>--%>
<%--                  </ul>--%>
<%--                </div>--%>
<%--              </div>--%>


<%--            </div>--%>
<%--          </div>--%>
<%--        </div>--%>
<%--      </div>--%>


<div class="section">
    <div class="container">
        <div class="row justify-content-between">
            <div class="col-lg-7">
                <div class="img-property-slide-wrap">
                    <div class="img-property-slide">
                        <c:forEach var="image" items="${building.image_url}">
                            <img src="${image}" alt="Property Image" class="img-fluid" />
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <h2 class="heading text-primary" style="font-size: 35px; font-family: 'Roboto', sans-serif; font-weight: bold; color: #3399FF;" >${building.name}</h2>
                <p class="text-black-50">${building.street} ${building.ward} ${building.district}</p>
                <p class="text-black-50">${building.note}</p>

                <div class="d-block agent-box p-5">
                    <div class="img mb-4">
                        <img src="${pageContext.request.contextPath}/images/person.jpg" alt="Agent Image" class="img-fluid" />
                    </div>
                    <div class="text">
                        <h3 class="mb-0">${building.managername}</h3>
                        <div class="meta mb-3">Real Estate</div>
                        <p>${building.managerphone}</p>
                        <p>
                          Chuyên môi giới, định giá, sàn giao dịch bất động sản; Kinh doanh bất động sản; Tư vấn bất động sản; Cho thuê bất động sản; Quản lý bất động sản; Đại lý bán hàng hưởng hoa hồng
                        </p>
                        <ul class="list-unstyled social dark-hover d-flex">
                            <li class="me-1">
                              <a href="https://zalo.me/${building.managerphone}"><span class="icon-instagram"></span></a>
                            </li>
                            <li class="me-1">
                              <a href="https://zalo.me/${building.managerphone}"><span class="icon-twitter"></span></a>
                            </li>
                            <li class="me-1">
                              <a href="https://zalo.me/${building.managerphone}"><span class="icon-facebook"></span></a>
                            </li>
                            <li class="me-1">
                              <a href="https://zalo.me/${building.managerphone}"><span class="icon-linkedin"></span></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>





<div class="site-footer" style="font-size: 16px; font-family: 'Roboto', sans-serif;">
  <div class="container">
    <div class="row">
      <div class="col-lg-4" >
        <div class="widget" style="font-size: 16px; font-family: 'Roboto', sans-serif;">
          <h3 style="font-size: 18px; font-family: 'Roboto', sans-serif;">Liên hệ </h3>
          <address>Số 46 Man Thiện, TP Thủ Đức, TP HCM</address>
          <ul class="list-unstyled links">
            <li><a href="tel://0583122004">0583122004</a></li>
            <li>
              <a href="mailto:levanminh031204@gmail.com">levanminh031204@gmail.com</a>
            </li>
          </ul>
        </div>
      </div>

      <div class="col-lg-4">
        <div class="widget">
          <h3 style="font-size: 18px; font-family: 'Roboto', sans-serif;">Thông tin công ty</h3>
          <ul class="list-unstyled links">
            <li><a href="#">Trang chủ</a></li>
            <li><a href="#">Giới thiệu</a></li>
            <li><a href="#">Dự án bất động sản</a></li>
            <li><a href="#">Tin tức</a></li>
            <li><a href="#">Liên hệ</a></li>
          </ul>
        </div>
      </div>

      <div class="col-lg-4">
        <div class="widget">
          <h3 style="font-size: 18px; font-family: 'Roboto', sans-serif;">Kết nối với chúng tôi</h3>
          <ul class="list-unstyled social">
            <li><a href="#"><span class="icon-instagram"></span></a></li>
            <li><a href="#"><span class="icon-twitter"></span></a></li>
            <li><a href="#"><span class="icon-facebook"></span></a></li>
            <li><a href="#"><span class="icon-linkedin"></span></a></li>
            <li><a href="#"><span class="icon-pinterest"></span></a></li>
            <li><a href="#"><span class="icon-dribbble"></span></a></li>
          </ul>
        </div>
      </div>
    </div>

    <div class="row mt-5">
      <div class="col-12 text-center">
        <p>
          Copyright &copy; <script>document.write(new Date().getFullYear());</script>.
          All Rights Reserved. &mdash; Designed by <a href="#">Happy Team</a>
        </p>
      </div>
    </div>
  </div>
</div>



      <div id="overlayer"></div>
      <div class="loader">
        <div class="spinner-border" role="status">
          <span class="visually-hidden">Loading...</span>
        </div>
      </div>

      <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
      <script src="${pageContext.request.contextPath}/js/tiny-slider.js"></script>
      <script src="${pageContext.request.contextPath}/js/aos.js"></script>
      <script src="${pageContext.request.contextPath}/js/custom.js"></script>


</body>

</html>
