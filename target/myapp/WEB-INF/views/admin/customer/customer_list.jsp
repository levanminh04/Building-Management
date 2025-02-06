<%--
  Created by IntelliJ IDEA.
  User: 84583
  Date: 07/10/2024
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerListURL" value="/admin/customer-list"/>
<html>
<%--<head>--%>
<%--    <title>Danh sách tòa nhà</title>--%>
<%--</head>--%>
<body>

<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="/trang-chu">Home</a>
                </li>
<%--                <li class="active">Dashboard</li>--%>
            </ul><!-- /.breadcrumb -->


        </div>

        <div class="page-content">
<%--            <div class="page-header">--%>
<%--                <h1>--%>
<%--                    Danh sách tòa nhà--%>
<%--                    <small>--%>
<%--                        <i class="ace-icon fa fa-angle-double-right"></i>--%>
<%--                        overview &amp; stats--%>
<%--                    </small>--%>
<%--                </h1>--%>
<%--            </div><!-- /.page-header -->--%>


            <div class="row">
                <div class="col-xs-12" style="min-height: 140.4px;">
                    <div class="widget-box ui-sortable-handle" style="opacity: 1;">
                        <div class="widget-header">
                            <h5 class="widget-title">TÌM KIẾM</h5>

                            <div class="widget-toolbar">

                                <a href="#" data-action="fullscreen" class="orange2">
                                    <i class="ace-icon fa fa-expand"></i>
                                </a>

                                <a href="#" data-action="reload">
                                    <i class="ace-icon fa fa-refresh"></i>
                                </a>

                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>


                            </div>
                        </div>

                        <div  class="widget-body" style="font-family: 'Times New Roman', Times, serif;">
                            <div class="widget-main">
                                <form:form id = "listform" modelAttribute="modelSearch" action = "${customerListURL}" method ="GET">
                                    <div class = "row">
                                        <div class = "form-group">
                                            <div class = "col-xs-12">
                                                <div class = "col-xs-6">
                                                    <div>
                                                        <label class = "name"> Tên khách hàng </label>
    <%--                                                    <input type = "text"  class = "form-control" name = "name" value = "${modelSearch.name}">   &lt;%&ndash; name phải giống từng kí tự với bên backend &ndash;%&gt;--%>
                                                            <form:input class = "form-control" path = "fullname"/>
                                                    </div>
                                                </div>
                                                <div class = "col-xs-6">
                                                    <div>
                                                        <label class = "name"> Số điện thoại </label>
    <%--                                                    <input type = "number"  class = "form-control" name = "floorarea" value = "${modelSearch.floorarea}">   &lt;%&ndash; name phải giống từng kí tự với bên backend &ndash;%&gt;--%>
                                                            <form:input class = "form-control" path = "phone"/>
                                                    </div>
                                                </div>
                                                <div class = "col-xs-6">
                                                    <div>
                                                        <label class = "name"> Email </label>
    <%--                                                    <input type = "number"  class = "form-control" name = "floorarea" value = "${modelSearch.floorarea}">   &lt;%&ndash; name phải giống từng kí tự với bên backend &ndash;%&gt;--%>
                                                            <form:input class = "form-control" path = "email"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class = "form-group">
                                            <div class = "col-xs-12">
                                                <div class = "col-xs-3">
                                                    <div>
                                                        <label class = "name"> Chọn nhân viên phụ trách </label>
                                                            <form:select class = "form-control" path = "staffid">
                                                                <form:option value="">---Chọn nhân viên</form:option>
                                                                <form:options items = "${staffList}"/>
                                                            </form:select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                    <div class = "form-group">
                                        <div class = "col-xs-12">
                                            <div class = "col-xs-6">
                                                <div>
                                                    <button type="button" class="btn btn-success" id = "btnSearchCustomer">
                                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16" data-darkreader-inline-fill="" style="--darkreader-inline-fill: currentColor;">
                                                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"></path>
                                                        </svg>
                                                        Tìm Kiếm
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                </form:form>
                            </div>
                        </div>

                        <div class = "pull-right" title = "Thêm khách hàng">
                            <a href="javascript:void(0);" onclick="navigateWithRefresh('/admin/customer-edit')">
                                <button class = "btn btn-info" id = "btnAddCustomer">
                                    <span>
                                        <i class="fa fa-plus-circle bigger-110 white"></i>
                                    </span>
                                </button>
                            </a>

                            <button class = "btn btn-warning" title = "xóa toàn bộ" id = "btnDeleteCustomers">
                                <span>
                                    <i class="fa fa-trash-o bigger-110 white"></i>
                                </span>
                            </button>

                        </div>
                    </div>
                </div>
            </div>
            <!-- mục tìm kiếm  -->

            <!-- phần table  -->
            <div class ="row">
                <div class="col-xs-12">
                    <table id="customerTable" style="font-family: 'Times New Roman', Timeris, sef;" style="margin: 3em 0 0em;" class="table table-striped table-bordered table-hover">
                        <thead>   <!-- thead là phần tên column, 1 th là 1 column  -->
                        <tr>
                            <th class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" name = "checklist" value = "" class="ace">
                                    <span class="lbl"></span>
                                </label>
                            </th>
                            <th>Tên khách hàng</th>
                            <th>Số điện thoại</th>
                            <th>Email</th>
                            <th>Nhu cầu</th>
                            <th>Người thêm</th>
                            <th>Ngày thêm</th>
                            <th>Tình trạng</th>
                            <th>thao tác</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="item" items = "${customerList}">
                            <tr>   <!-- 1 tr tương ứng với 1 hàng, 1 td tương ứng với 1 column  -->
                                <td class="center">
                                    <label class="pos-rel">
                                        <input type="checkbox" name = "checklist" value="${item.id}"  class="ace">
                                        <span class="lbl"></span>
                                    </label>
                                </td>

                                <td>${item.fullname}</td>
                                <td>${item.phone}</td>
                                <td>${item.email}</td>
                                <td>${item.demand}</td>
                                <td>${item.modifiedBy}</td>
                                <td>${item.modifiedDate}</td>
                                <td>${item.status}</td>
                                <td>
                                    <div class="hidden-sm hidden-xs btn-group">
                                        <a href="javascript:void(0);" onclick="navigateWithRefresh('/admin/customer-edit-${item.id}')">
                                            <button class="btn btn-xs btn-info" title="Sửa thông tin">
                                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                                            </button>
                                        </a>
                                        <security:authorize access="hasRole('ROLE_MANAGER')">
                                            <button class="btn btn-xs btn-success" title="Giao khách hàng" onclick="assignmentCustomer(${item.id})">
                                                <i class="ace-icon glyphicon glyphicon-list"></i>
                                            </button>

                                            <button class="btn btn-xs btn-danger" title="Xóa khách hàng" onclick="deleteCustomer(${item.id})">
                                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                            </button>
                                        </security:authorize>

                                    </div>

                                    <div class="hidden-md hidden-lg">
                                        <div class="inline pos-rel">
                                            <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
                                                <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                            </button>

                                            <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                                <li>
                                                    <a href="#" class="tooltip-info" data-rel="tooltip" title="" data-original-title="View">
                                                                        <span class="blue">
                                                                            <i class="ace-icon fa fa-search-plus bigger-120"></i>
                                                                        </span>
                                                    </a>
                                                </li>

                                                <li>
                                                    <a href="#" class="tooltip-success" data-rel="tooltip" title="" data-original-title="Edit">
                                                                        <span class="green">
                                                                            <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                                                        </span>
                                                    </a>
                                                </li>

                                                <li>
                                                    <a href="#" class="tooltip-error" data-rel="tooltip" title="" data-original-title="Delete">
                                                                        <span class="red">
                                                                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                                        </span>
                                                    </a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </td>
                        </tr>
                        </c:forEach>



                        </tbody>
                    </table>
                </div>
            </div>

        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->



            <%--modal--%>
<div class="modal fade" id="confirmationModal" role="dialog" style="font-family: 'Times New Roman', Times, serif;">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Xác nhận</h4>
            </div>

            <div class="modal-body">
                <input type = "hidden" id = "delete_customerid" name = "customer" value ="1">
            </div>

            <div class="modal-body text-center">
                <p style="font-size: 20px; font-weight: bold;">Bạn có chắc chắn muốn xóa thông tin này?</p>
            </div>
            <div class="modal-footer text-center">
                <button type="button" class="btn btn-danger" id="btnConfirmDelete">Có</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Không</button>
            </div>

        </div>

    </div>
</div>


<div class="modal" tabindex="-1" role="dialog" id="assignmentCustomerModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Danh sách nhân viên</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <table id="staffList" class="table table-striped table-bordered table-hover" style="text-align: center;">
                    <thead>
                    <tr style="font-weight: 700;">
                        <td>Chọn</td>
                        <td>Tên nhân viên</td>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <input type="hidden" id="customerId" value="" name="customerId">
            </div>
            <div class="modal-footer">
                <button type="button" title="Giao tòa nhà" id="btnAssignmentAssignmentModal" style="background-color: #5DCC91; border: 1px solid #fff; color: #fff;">
                    <span>Giao khách hàng</span>
                </button>
                <button type="button" title="Đóng" data-dismiss="modal" style=" border: 1px solid #000; color: #000; background-color: #fff;">
                    <span>Đóng</span>
                </button>
            </div>
        </div>
    </div>
</div>

<script src="assets/js/jquery.2.1.1.min.js"></script>

<%@ include file="/WEB-INF/views/token-utils.jsp" %>

<script type="text/javascript">

    function assignmentCustomer(customerId){
        $('#assignmentCustomerModal').modal();
        loadStaff(customerId);
        $('#customerId').val(customerId);
    }

    function loadStaff(customerId){
        $.ajax({
            type: "GET",
            url: "/api/customer/" + customerId + '/staffs',
            dataType: "JSON",
            success: function(response) {
                var row = '';
                $.each(response.data, function (index, item){
                    row += '<tr>';
                    row += '<td class="text-center"><input type="checkbox" value=' + item.staffId + ' id="checkbox_' + item.staffId + '" class = "check-box-element"' + item.checked + '/></td>';
                    row += '<td class="text-center">' + item.fullName + '</td>';
                    row += '</tr>';
                });
                $('#staffList tbody').html(row);
                console.info("success")
            },
            error: function (xhr, textStatus, errorThrown) {
                handleAjaxError(xhr, textStatus, errorThrown, () => loadStaff(customerId));
            }
        });
    }
    $('#btnAssignmentAssignmentModal').click(function(e){
        e.preventDefault();
        var data = {};
        data['customerId'] = $('#customerId').val();
        var staffs = $('#staffList').find('tbody input[type=checkbox]:checked').map(function(){
            return $(this).val();
        }).get();
        data['staffs'] = staffs;

        UpdateAssignmentCustomer(data);

        console.log("Ok");
    })
    function UpdateAssignmentCustomer(data){
        $.ajax({
            type: "POST",
            url: "/api/customer/" + 'assignment',
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "JSON",
            success: function(response) {
                alert("Giao thành công!")
                window.location.href = "/admin/customer-list?message=success";
                console.info("success")
            },
            <%--error: function(response){--%>
            <%--    console.info("Giao không thành công")--%>
            <%--    window.location.href = "<c:url value="/admin/customer-list?message=error"/>"--%>
            <%--}--%>
            error: function (xhr, textStatus, errorThrown) {
                handleAjaxError(xhr, textStatus, errorThrown, () => UpdateAssignmentCustomer(data));
            }
        });
    }


    $('#btnSearchCustomer').click(function (e){
        $('#listform').submit();
        e.preventDefault();
    })


    function deleteCustomer(customerID){   // nút xóa trong bảng, xóa đơn cột
        var data = [customerID]; // đơn cột nhưng vẫn cứ quy về thành 1 mảng cho đồng bộ với trường hợp xóa đa cột, xử lý chung trong 1 hàm luôn

        $('#confirmationModal').modal()
        // $('#customerid').val(id);
        $('#btnConfirmDelete').click(function (e) {
            deleteCustomers(data);
        });

    }


    $('#btnDeleteCustomers').click(function (e){  // nút xóa all cột
        e.preventDefault();
        var data = $('#customerTable').find('tbody input[type = checkbox]:checked').map(function (){
            return  $(this).val();
        }).get();
        $('#confirmationModal').modal()
        // $('#customerid').val(id);
        $('#btnConfirmDelete').click(function (e) {
            deleteCustomers(data);
        });

    });
    function deleteCustomers(data){
        // call API   sử dụng AJAX để send data to server
        $.ajax({
          type:"DELETE", // XÓA TÒA NHÀ THÌ DÙNG DELETE
          url: "/api/customer/" + data, // thuộc tính url chỉ định địa chỉ endpoint (đường dẫn) mà yêu cầu AJAX sẽ gửi dữ liệu đến url này để server xử lý yêu cầu
          data:JSON.stringify(data),

          contentType:"application/json",
          dataType:"JSON",

          success: function (respond) {
            alert('Xóa thành công!');
            navigateWithRefresh('/admin/customer-list?message=success');

          },
            error: function (xhr, textStatus, errorThrown) {
                handleAjaxError(xhr, textStatus, errorThrown, () => deleteCustomers(data));
            }
            // alert("Có lỗi xảy ra khi xóa!")
        });
    }

</script>



<script type="text/javascript"> // tạo chức năng khi nhấn vào checkbox tổng thì select all
    jQuery(function($) {
        // Bỏ chọn tất cả các checkbox khi tải trang
        $('th input[type=checkbox], td input[type=checkbox]').prop('checked', false);

        // Chọn/bỏ chọn tất cả các hàng theo checkbox trong tiêu đề
        $('#customerTable > thead > tr > th input[type=checkbox]').eq(0).on('click', function() {
            var th_checked = this.checked; // kiểm tra trạng thái của checkbox tiêu đề

            $(this).closest('table').find('tbody > tr').each(function() {
                var row = this;
                // Chọn hoặc bỏ chọn tất cả các checkbox trong bảng
                $(row).find('input[type=checkbox]').prop('checked', th_checked);
            });
        });

        // Chọn/bỏ chọn một hàng khi checkbox của nó được click
        $('#customerTable').on('click', 'td input[type=checkbox]', function() {
            var row = $(this).closest('tr');
            var checked = this.checked; // kiểm tra trạng thái của checkbox
            $(row).find('input[type=checkbox]').prop('checked', checked);
        });
    });
</script>



</body>
</html>
