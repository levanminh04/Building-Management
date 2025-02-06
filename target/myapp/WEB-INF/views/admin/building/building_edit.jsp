<%--
  Created by IntelliJ IDEA.
  User: 84583
  Date: 07/10/2024
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/dropzone/5.9.3/min/dropzone.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/dropzone/5.9.3/min/dropzone.min.js"></script>

<c:url var="buildingEditURL" value="/admin/building-edit"/>
<html>
<head>
  <title>Thêm tòa nhà</title>
</head>
<body>
<div class="main-content">
  <div class="main-content-inner">
    <div class="breadcrumbs" id="breadcrumbs">
      <script type="text/javascript">
        try { ace.settings.check('breadcrumbs', 'fixed') } catch (e) { }
      </script>

      <ul class="breadcrumb">
        <li>
          <i class="ace-icon fa fa-home home-icon"></i>
          <a href="#">Home</a>
        </li>
        <li class="active">Dashboard</li>
      </ul><!-- /.breadcrumb -->


    </div>

    <div class="page-content">


      <div class="page-header">
        <h1>
          Thêm/Sửa tòa nhà
          <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            overview &amp; stats
          </small>
        </h1>
      </div><!-- /.page-header -->

      <div class="row" style="font-family: 'Times New Roman', Times, serif;">
          <form:form modelAttribute="buildingEdit" action="${buildingEditURL}" id = "formEdit"  method="GET" >
              <div class="col-xs-12">
                <form class="form-horizontal" role="form" >
                    <div class="form-group">
                      <label class="col-xs-3"> Tên tòa nhà </label>
                      <div class="col-xs-9">
                        <form:input  class="form-control"  path="name"/>
<%--                        <form:errors path="name" cssClass="text-danger" />--%>
                      </div>
                    </div>
                    <div class="form-group">

                      <label class="col-xs-3"> Quận </label>
                      <div class="col-xs-5">

                        <form:select type = "text" name = "district"  class = "form-control" path = "district" >    <%-- name phải giống từng kí tự với bên backend --%>
                            <form:option value="">---Chọn Quận---</form:option>
                            <form:options items = "${districts}"/>
                        </form:select>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-xs-3"> Phường </label>
                      <div class="col-xs-9">
                        <form:input  class="form-control" path = "ward"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-xs-3"> Đường </label>
                      <div class="col-xs-9">
                        <form:input  class="form-control" path ="street"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-xs-3"> Kết cấu </label>
                      <div class="col-xs-9">

                        <form:input  class="form-control" path ="structure"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-xs-3"> Số tầng hầm </label>
                      <div class="col-xs-9">
                        <form:input  class="form-control" path="numberOfBasement"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-xs-3"> Diện tích sàn </label>
                      <div class="col-xs-9">
                        <form:input  class="form-control" path ="floorArea"/>
                      </div>
                    </div>

                    <div class="form-group">
                      <label class="col-xs-3"> Hướng </label>
                      <div class="col-xs-9">
                        <form:input type="text" class="form-control" path="direction"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-xs-3"> Hạng </label>
                      <div class="col-xs-9">
                        <form:input  class="form-control" path="level"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-xs-3"> Diện tích thuê </label>
                      <div class="col-xs-9">

                        <form:input  class="form-control" path ="rentArea"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-xs-3"> Giá thuê </label>
                      <div class="col-xs-9">

                        <form:input  class="form-control" path ="rentPrice"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-xs-3"> Mô tả giá </label>
                      <div class="col-xs-9">

                        <form:input  class="form-control" path ="rentPriceDescription"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-xs-3"> Phí dịch vụ </label>
                      <div class="col-xs-9">

                        <form:input  class="form-control" path ="serviceFee"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-xs-3"> Phí ô tô </label>
                      <div class="col-xs-9">
                        <form:input  class="form-control" path ="carFee"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-xs-3"> Phí mô tô </label>
                      <div class="col-xs-9">

                        <form:input  class="form-control" path ="motorbikeFee"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-xs-3"> Phí ngoài giờ </label>
                      <div class="col-xs-9">
                        <form:input  class="form-control" path ="extraFee"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-xs-3"> Tiền điện </label>
                      <div class="col-xs-9">
                        <form:input  class="form-control" path ="electricFee"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-xs-3"> Đặt cọc </label>
                      <div class="col-xs-9">
                        <form:input  class="form-control" path ="deposit"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-xs-3"> Thanh toán </label>
                      <div class="col-xs-9">
                        <form:input  class="form-control" path ="payment"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-xs-3"> Thời hạn thuê </label>
                      <div class="col-xs-9">
                        <form:input  class="form-control" path ="rentTime"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-xs-3"> Thời gian trang trí </label>
                      <div class="col-xs-9">
                        <form:input  class="form-control" path ="decorationTime"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-xs-3"> Tên quản lý </label>
                      <div class="col-xs-9">
                        <form:input  class="form-control" path ="managerName"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-xs-3"> SĐT quản lý </label>
                      <div class="col-xs-9">

                        <form:input  class="form-control" path ="managerPhone"/>
                      </div>
                    </div>

                    <div class="form-group">
                      <label class="col-xs-3"> Phí môi giới </label>
                      <div class="col-xs-9">

                        <form:input  class="form-control" path ="brokerageFee"/>
                      </div>
                    </div>

                    <div class="form-group">
                      <label class="col-xs-3"> Loại tòa nhà </label>
                      <div class="col-xs-6">
                        <div class="col-xs-6">
                          <form:checkboxes items = "${typeCodes}" path = "typeCode" />
                        </div>
                      </div>
                    </div>


                    <div class="form-group">
                      <label class="col-xs-3"> Ghi chú </label>
                      <div class="col-xs-9">
                        <form:input  class="form-control" path ="note"/>
                      </div>
                    </div>

                    <div class="form-group">
                        <label for="files" class="col-sm-3 col-form-label">Hình ảnh tòa nhà</label>
                        <div class="col-sm-9">
                            <input type="file" class="form-control" id="files" name="buildingImages" accept="image/*" multiple>
                        </div>
                    </div>



                    <div class="form-group">
                      <label class="col-xs-3"></label>
                         <div class="col-xs-9">
                             <c:if test = "${not empty buildingEdit.id}" >
                                <button class="btn btn-info" id="btnAddOrUpdateBuilding">
                                  <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18"  fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 16 16">
                                    <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"></path>
                                    <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4"></path>
                                  </svg>
                                  <span>Cập nhật tòa nhà</span>
                                </button>


                             </c:if>

                             <c:if test = "${ empty buildingEdit.id}" >
                                <button class="btn btn-info" id="btnAddOrUpdateBuilding">
                                  <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18"  fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 16 16">
                                    <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"></path>
                                    <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4"></path>
                                  </svg>
                                  <span>Thêm tòa nhà</span>
                                </button>

                             </c:if>

                            <a href="javascript:void(0);" onclick="navigateWithRefresh('/admin/building-list')" class="btn btn-warning">
                                  <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18"  fill="currentColor" class="bi bi-x-circle" viewBox="0 0 16 16">
                                    <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"></path>
                                    <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708"></path>
                                  </svg>
                                  <span>Hủy thao tác</span>
                             </a>
<%--                             Không nên sử dụng <a>--%>
<%--                                                    <button>
                                                        </button>                             --%>
<%--                                               </a>--%>
                    <%--Trong HTML, thẻ <a> là một thẻ inline và thẻ <button> là thẻ block-level. --%>
                    <%--Theo chuẩn HTML5, thẻ <a> không nên bao quanh một phần tử block-level như <button>. --%>
                    <%--Điều này dẫn đến những xung đột về cách trình duyệt xử lý hành vi của các thẻ đó. --%>
                    <%--Khi bạn nhấn vào nút <button>, trình duyệt có thể không hiểu rõ rằng hành động chính của bạn là --%>
                    <%--nhấn vào nút hay liên kết bao quanh, gây ra sự cố không chuyển hướng được.--%>

                         </div>
                    </div>


                    <form:hidden path = "id" id = "buildingID" />
                  </form>
               </div>
             </form:form>
              <div class="form-group">

      </div>


    </div>

    </div><!-- /.page-content -->
  </div>
</div><!-- /.main-content -->

<script src="assets/js/jquery.2.1.1.min.js"></script>
<%@ include file="/WEB-INF/views/token-utils.jsp" %>
<script>

$('#btnAddOrUpdateBuilding').click(function (event) {
    event.preventDefault(); // Quan trọng để ngăn hành vi submit mặc định của form

    var formData = new FormData(); // phải sử dụng đối tượng FormData thì mới truyền được kiểu multipartfile, không truyền được theo kiểu json, formdata sẽ tự đông binding dữ liệu vào DTO truyền vào controller, không cần sử dụng @Requestbody (yêu cầu dạng json)
    var typeCode = [];

    // Thu thập dữ liệu từ các trường trong form
    $('#formEdit').serializeArray().forEach(function (field) {
        if (field.name !== 'typeCode') {
            formData.append(field.name, field.value); // Thêm các trường không phải typeCode
        } else {
            typeCode.push(field.value);
        }
    });

    // Thêm typeCode vào FormData
    if (typeCode.length > 0) {
        typeCode.forEach(function (code) {
            formData.append('typeCode', code); // Lặp và thêm từng giá trị của typeCode
        });
    } else {
        // Nếu typeCode bị thiếu, điều hướng lại trang với thông báo lỗi
        alert("không được bỏ trống typecode!")
        return;
    }

    // Thu thập các file từ input (nếu có)
    var files = $('#files')[0].files; // Giả sử có input type="file" id="files"
    if (files.length > 0) {
        for (var i = 0; i < files.length; i++) {
            formData.append('files', files[i]); // Thêm từng file vào FormData
        }
    }

    // Gửi FormData qua AJAX
    addOrUpdateBuilding(formData);
});

function addOrUpdateBuilding(formData) {
    $.ajax({
        type: "POST", // Thêm tòa nhà thì dùng POST
        url: "/api/building", // Đường dẫn đến endpoint của server
        data: formData, // Gửi dữ liệu dưới dạng FormData
        processData: false, // Không xử lý dữ liệu (để FormData tự xử lý)
        contentType: false, // Để jQuery tự động cấu hình Content-Type
        success: function (respond) {
            console.log(respond);
            console.log("Thêm/Cập nhật tòa nhà thành công.");
            alert("Thêm/Cập nhật tòa nhà thành công.")
            navigateWithRefresh('/admin/building-edit?message=success');

        },
        error: function (xhr, textStatus, errorThrown) {
            handleAjaxError(xhr, textStatus, errorThrown, () => addOrUpdateBuilding(formData));
        }
    });
}




</script>

</body>
</html>
