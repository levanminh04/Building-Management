<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liên hệ</title>
</head>
<body>
<div class="page-wrapper">

    <div class="intro text-center mb-5">
        <div class="title-page">Liên hệ</div>
        <div class="row">
            <div class="col-xs-12 a-left">
                <ul class="desc-intro">
                    <li class="home">
                        <a href="./ViewHome.html"><span style="color:#fff">Trang chủ</span></a>
                        <span class="mx-1" style="color:#fff"> / </span>
                    </li>
                    <li class="intro-item"><span>Liên hệ</span></li>
                </ul>
            </div>
        </div>
    </div>

    <!-- CONTENT  -->
    <div class="map">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="google-map margin-bottom-30">
                        <div class="maps_iframe">
                            <iframe style="width: 100%;"
                                    src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3919.577731862711!2d106.7695372142139!3d10.850261760047032!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752f8d0e993b05%3A0x8abf4c480f8b822b!2zOTcgTWFuIFRoacOqbiwgUGjDuiBIaeG7h3AgSMaw4budYywgbmjhuq10IHPDumMgVHJ1w6JuIFbEg25nLCBWaeG7h3QgTmFt!5e0!3m2!1sen!2s!4v1652520257601!5m2!1sen!2s"
                                    width="600" height="450" style="border:0;" allowfullscreen=""
                                    loading="lazy"></iframe>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="box-lienhe mt-5">
        <div class="container">
            <div class="row">
                <div class="col-12 col-md-6">
                    <h2 class="title-lienhe"><strong>Công ty cổ phần bất động sản SkyLand</strong></h2>
                    <div class="desc-lienhe">
                        <p>Được thành lập vào ngày 20/08/2019 với niềm đam mê và khát vọng thành công trong lĩnh vực
                            bất động sản. Nhờ chiến lược rõ ràng và hướng đi đúng, SkyLand đã nhanh chóng phát triển
                            và đạt được những thành công nhất định.</p>
                        <ul class="margin-bottom-15 link" style="list-style-type: none;">
                            <li>
                                <span class="block_fonticon"><i class="fa fa-map-marker icon-lienhe"></i></span>
                                <span class="title-li"> 46 Man Thiện, TP. Thủ Đức, TP. HCM</span>
                            </li>
                            <li>
                                <span class="block_fonticon"><i class="fa fa-mobile icon-lienhe"></i></span>
                                <span class="title-li">
                                        Hotline: <a style="color: #434a6e;" class="fone" href="">0922227</a>

                                    </span>
                            </li>
                            <li>
                                <span class="block_fonticon"><i class="fa fa-envelope icon-lienhe"></i></span>
                                <span class="title-li">Email: <a style="color: #434a6e;"
                                                                 href="">levanminh031204@gmail.com</a></span>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-12 col-md-6">
                    <h2 class="title-lienhe"><strong>Liên hệ với chúng tôi</strong></h2>
                    <form id = "forminfo">
                        <div class="row">
                            <div class="col">
                                <input type="text" class="form-control" name = "fullname" placeholder="Họ và tên">
                            </div>
                            <div class="col">
                                <input type="text" class="form-control" name = "email" placeholder="Email">
                            </div>
                        </div>
                        <input type="text" class="form-control mt-3" name = "phone"  placeholder="Số điện thoại">
                        <input type="text" class="form-control mt-3" name = "demand"  placeholder="Nội dung">
                        <button class="btn btn-primary px-4 mt-3" id="btnSendContactInfo">
                            Gửi liên hệ
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- FOOTER  -->
    <footer class="footer">
        <div class="container">
            <div class="top-footer text-center mt-0">
                <div class="logo logo-footer pt-5">
                    <a href="./ViewHome.html"><img src="https://bizweb.dktcdn.net/100/328/362/themes/894751/assets/logo_footer.png?1676257083798" alt="logo-footer"></a>
                    <p class="desc-logo-footer mt-3">Với hơn 10 năm kinh nghiệm, SkyLand tự hào là sàn
                        mua
                        bán, giao dịch và quảng cáo
                        bất động sản hàng đầu tại Việt Nam</p>
                    <div class="item-footer mt-5">
                        <div class="row">
                            <div class="col-12 col-md-4 text-center">
                                <div class="icon-footer">
                                    <img src="https://bizweb.dktcdn.net/100/328/362/themes/894751/assets/place_maps.png?1676257083798" alt="">
                                </div>
                                <div class="content-center-footer">
                                    <p class="mb-1 mt-3">Trụ sở chính</p>
                                    <p class="desc-footer">Số 46 Man Thiện, TP Thủ Đức, TP HCM</p>
                                </div>
                            </div>
                            <div class="col-12 col-md-4 text-center">
                                <div class="icon-footer">
                                    <img src="https://bizweb.dktcdn.net/100/328/362/themes/894751/assets/place_phone.png?1676257083798 alt="">
                                </div>
                                <div class="content-center-footer">
                                    <p class="mb-1 mt-3">Hotline</p>
                                    <p class="desc-footer"><a class="a-text" href="#">0583122004</a></p>
                                </div>
                            </div>
                            <div class="col-12 col-md-4 text-center">
                                <div class="icon-footer">
                                    <img src="https://bizweb.dktcdn.net/100/328/362/themes/894751/assets/place_email.png?1676257083798g" alt="">
                                </div>
                                <div class="content-center-footer">
                                    <p class="mb-1 mt-3">Email</p>
                                    <p class="desc-footer"><a class="a-text" href="#">levanminh031204@gmail.com</a>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="text-center">
                    <div class="border-bottom mb-5 mt-4"></div>
                </div>
            </div>
            <div class="bottom-footer">
                <div class="row">
                    <div class="col-12 col-md-3">
                        <h4 class="title-item-bottom-footer">Thông tin công ty</h4>
                        <p class="desc-item-bottom-footer desc-1"><a class="a-text" href="">Trang
                            chủ</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Giới thiệu</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Dự án bất động
                            sản</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Tin tức</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Liên hệ</a></p>
                    </div>
                    <div class="col-12 col-md-3">
                        <h4 class="title-item-bottom-footer">Chính sách hoạt động</h4>
                        <p class="desc-item-bottom-footer desc-1"><a class="a-text" href="">Trang
                            chủ</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Giới thiệu</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Dự án bất động
                            sản</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Tin tức</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Liên hệ</a></p>
                    </div>
                    <div class="col-12 col-md-3">
                        <h4 class="title-item-bottom-footer">Hỗ trợ khách hàng</h4>
                        <p class="desc-item-bottom-footer desc-1"><a class="a-text" href="">Trang
                            chủ</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Giới thiệu</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Dự án bất động
                            sản</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Tin tức</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Liên hệ</a></p>
                    </div>
                    <div class="col-12 col-md-3">
                        <h4 class="title-item-bottom-footer">Kết nối với chúng tôi</h4>
                        <p class="desc-item-bottom-footer desc-1"><a class="a-text" href="">Trang
                            chủ</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Giới thiệu</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Dự án bất động
                            sản</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Tin tức</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Liên hệ</a></p>
                    </div>
                </div>
            </div>
        </div>
        <div class="bottom-footer-2">
            <div class="text-center desc-bottom-footer-2">@ Bản quyền thuộc về Happy Team |
                Cung cấp bởi <a class="a-text group-name" href="#">HappyTeam</a></div>
        </div>
    </footer>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<%@ include file="/WEB-INF/views/token-utils.jsp" %>

<script>

    $('#btnSendContactInfo').click(function (event) {
        event.preventDefault();
        var data = {};
        var formData = $('#forminfo').serializeArray();
        $.each(formData, function (i, v) {
            data[v.name] = v.value;
        })
        data['status'] = 'Chưa xử lý'
        if (!data['fullname'] || !data['phone']) {
            alert('Vui lòng không bỏ trống họ và tên và số điện thoại.');
            return;
        }
        else{
            SendContactInfo(data)
        }

    });

    function SendContactInfo(data){
        $.ajax({
            type:"POST", // THÊM TÒA NHÀ THÌ DÙNG POST
            url: "/api/customer/contact", // thuộc tính url chỉ định địa chỉ endpoint (đường dẫn) mà yêu cầu AJAX sẽ gửi dữ liệu đến url này để server xử lý yêu cầu
            data:JSON.stringify(data), // biến data là dữ liệu sẽ được gửi đến server và được ép theo kiểu JSON
            contentType:"application/json",  // giống như một cái cờ báo hiệu, cho biết rằng dữ liệu gửi về server là kiểu JSON
            dataType:"JSON",    // chỉ định kiểu dữ liệu mà client mong muốn nhận từ server sau khi server xử lý yêu cầu và phản hồi lại

            success: function (respond) {
                console.log(respond);
                console.log("OK");
                alert('Gửi liên hệ thành công.');
                window.location.href = '<c:url value="/lien-he?message=success"/>';
            },
            error: function(respond){
                console.log(respond);
                window.location.href = '<c:url value="/lien-he?message=failed"/>';
            }

        });
    }

    // document.getElementById('sendContactInfo').addEventListener('click', function () {
    //     // Lấy giá trị từ các trường
    //     const fullName = document.getElementById('fullName').value.trim();
    //     const phoneNumber = document.getElementById('phoneNumber').value.trim();
    //
    //     // Kiểm tra nếu thiếu thông tin
    //     if (!fullName || !phoneNumber) {
    //         alert('Vui lòng điền đầy đủ Họ và tên và Số điện thoại.');
    //         return; // Dừng lại, không gửi form
    //     }
    //
    //     // Nếu tất cả trường hợp lệ, bạn có thể thực hiện tiếp hành động gửi form (ví dụ gọi API)
    //     alert('Gửi liên hệ thành công!');
    // });




</script>


</body>
</html>
