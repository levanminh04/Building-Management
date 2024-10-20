<%--
  Created by IntelliJ IDEA.
  User: 84583
  Date: 07/10/2024
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
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

                            <a href="/admin/building-list" class="btn btn-warning">
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
      </div>

    </div><!-- /.page-content -->
  </div>
</div><!-- /.main-content -->

<script src="assets/js/jquery.2.1.1.min.js"></script>

<script>
  $('#btnAddOrUpdateBuilding').click(function (event) {
      event.preventDefault(); // lệnh này quan trọng đấy
//       Khi bạn nhấn một nút <button> nằm trong một thẻ <form> (TH hợp này thì btnAddOrUpdateBuilding đang này trong form),
//       trình duyệt sẽ tự động xem đó là một yêu cầu để gửi (submit) form.
//       Hành động gửi này sẽ khiến toàn bộ trang được làm mới (reload) hoặc điều hướng đến một trang khác,
//       tùy thuộc vào thuộc tính action của form.
//       Nếu không có event.preventDefault();, khi bạn nhấn nút gửi, trình duyệt sẽ gửi tất cả dữ liệu từ các trường trong form đến URL mà bạn đã chỉ định trong thuộc tính action.
//       Khi bạn gán một hàm xử lý cho sự kiện click của nút, trình duyệt vẫn sẽ tiếp tục thực hiện hành động mặc định của nút đó,
//       tức là gửi form, trừ khi bạn yêu cầu nó không làm vậy.
//       Nếu có một yêu cầu điều hướng trong mã JavaScript (như window.location.href),
//       nó có thể không được thực hiện vì trang đã được làm mới trước khi yêu cầu đó được thực hiện.
//       => TÓM LẠI LÀ NẾU KHÔNG CÓ PREVENT THÌ MẤY LỆNH ĐIỀU HƯỚNG NÓ ÉO HOẠT ĐỘNG ĐÚNG ĐÂU
      // sử dụng JS để lấy data
    var data = {};  // tạo cấu trúc JSON
    var typecode = [];
    var formData = $('#formEdit').serializeArray();
// serializeArray() dùng để thu thập dữ liệu từ tất cả các trường trong một form HTML (ví dụ như input, textarea, select, v.v.) và trả về một mảng các đối tượng chứa tên và giá trị của từng trường trong form.
    $.each(formData, function (i, v) {
      if (v.name != 'typeCode') {
        data["" + v.name + ""] = v.value;
      }
      else {
        typecode.push(v.value);
      }
    })
    data['typeCode'] = typecode;

    // call API   sử dụng AJAX để send data to server
    if(typecode.length > 0){
        addOrUpdateBuilding(data);
    }
    else{
        // bổ sung thêm chức năng: nếu nhập thiếu thì vẫn giữ nguyễn data trên các field
        window.location.href = '<c:url value="/admin/building-edit?typecode=required"/>';

    }
  });
function addOrUpdateBuilding(data){
    $.ajax({
          type:"POST", // THÊM TÒA NHÀ THÌ DÙNG POST
          url: "/api/building", // thuộc tính url chỉ định địa chỉ endpoint (đường dẫn) mà yêu cầu AJAX sẽ gửi dữ liệu đến url này để server xử lý yêu cầu
          data:JSON.stringify(data), // biến data là dữ liệu sẽ được gửi đến server và được ép theo kiểu JSON
          contentType:"application/json",  // giống như một cái cờ báo hiệu, cho biết rằng dữ liệu gửi về server là kiểu JSON
          dataType:"JSON",    // chỉ định kiểu dữ liệu mà client mong muốn nhận từ server sau khi server xử lý yêu cầu và phản hồi lại

          success: function (respond) {
            console.log(respond);
            console.log("OK");
            window.location.href = '<c:url value="/admin/building-edit?message=success"/>';
          },
          error: function(respond){
            console.log(respond);
            window.location.href = '<c:url value="/admin/building-edit?message=failed"/>';
          }

    });
}
  // $('#btnCancel').click(function (){
  //     e.preventDefault();  // Ngăn form submit nếu nút ở trong form
  //     window.location.href = "/admin/building-list";
  // });
</script>

</body>
</html>
