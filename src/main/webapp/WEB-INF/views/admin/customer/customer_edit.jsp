<%--
  Created by IntelliJ IDEA.
  User: 84583
  Date: 23/01/2025
  Time: 23:40
  To change this template use File | Settings | File Templates.
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Title</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--</body>--%>
<%--</html>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url var="customerURL" value="/admin/customer-edit"/>
<html lang="en">
<head>
    <form:form modelAttribute="customerEdit" method="get" id="listForm">
        <c:if test="${empty customerEdit.id}">
            <title>Thêm khách hàng</title>
        </c:if>
        <c:if test="${not empty customerEdit.id}">
            <title>Thông tin khách hàng</title>
        </c:if>
    </form:form>
</head>

<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check("breadcrumbs", "fixed");
                } catch (e) { }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="javascript:void(0);" onclick="navigateWithRefresh('/admin/customer-list')" style="color: #000">Trang chủ</a>
                </li>
                <c:if test="${empty customerEdit.id}">
                    <li class="active">Thêm mới khách hàng</li>
                </c:if>
                <c:if test="${not empty customerEdit.id}">
                    <li class="active">Thông tin khách hàng</li>
                </c:if>
            </ul>
            <!-- /.breadcrumb -->
        </div>

        <div class="page-content" style="margin-left: 50px">
            <div class="page-header">
                <form:form modelAttribute="customerEdit" method="get" id="list-form">
                    <c:if test="${empty customerEdit.id}">
                        <h1 style="
                    font-size: 26px;
                    color: #000;
                    font-weight: 500;
                    margin-left: 0;
                  ">
                            Thêm khách hàng
                        </h1>
                    </c:if>
                    <c:if test="${not empty customerEdit.id}">
                        <h1 style="font-size: 26px; color: #000; font-weight: 500">
                            <i class="fa-solid fa-pen-to-square"></i> Thông tin khách hàng
                        </h1>
                    </c:if>
                </form:form>
            </div>
            <!-- /.page-header -->

            <div class="row" style="font-family: 'Inria Serif', sans-serif">
                <form:form id="listForm" action="${customerURL}" method="get"
                           modelAttribute="customerEdit">
                    <div class="col-xs-12">
                        <div role="form" class="form-horizontal" id="form-edit">
                            <div class="form-group" style="margin-top: 15px">
                                <label class="col-xs-2">Tên khách hàng <span class="text-danger">*</span></label>
                                <div class="col-xs-5">
                                    <form:input path="fullname" class="form-control"
                                                name="fullname" required="required"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-2">Số điện thoại <span class="text-danger">*</span></label>
                                <div class="col-xs-5">
                                    <form:input path="phone" class="form-control" name="phone" required="required"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-2">Email <span class="text-danger">*</span></label>
                                <div class="col-xs-5">
                                    <form:input path="email" class="form-control" name="email" required="required"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-2">Tên công ty <span class="text-danger">*</span></label>
                                <div class="col-xs-5">
                                    <form:input path="companyname" name="companyName"
                                                class="form-control" required="required"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-2">Nhu cầu <span class="text-danger">*</span></label>
                                <div class="col-xs-5">
                                    <form:input path="demand" name="demand" class="form-control" required="required"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-2">Tình trạng <span class="text-danger">*</span></label>
                                <div class="col-xs-2">
                                    <form:select  class="form-control" path="status">
                                        <form:option value="">---Chọn tình trạng---</form:option>
                                        <form:options items="${statusType}"/>
                                    </form:select>
                                </div>
                            </div>

                            <form:hidden path="createdBy" name="demand" class="form-control" />
                            <form:hidden path="createdDate" name="demand" class="form-control" />

                            <div class="form-group">
                                <div class="col-xs-2"></div>
                                    <div class="col-xs-9">
                                        <c:if test="${not empty customerEdit.id}">
                                            <button style="margin-right: 10px"
                                                    id="btnAddOrUpdateCustomer">
                                                <i class="fa-regular fa-pen-to-square"></i>
                                                <span> Cập nhật khách hàng</span>
                                            </button>
                                            <button id="btnCancel">
                                                <i class="fa-solid fa-trash-can"></i>
                                                <span> Hủy thao tác</span>
                                            </button>
                                        </c:if>
                                        <c:if test="${empty customerEdit.id}">
                                            <button style="margin-right: 10px"
                                                    id="btnAddOrUpdateCustomer">
                                                <i class="ace-icon glyphicon glyphicon-plus"></i>
                                                <span> Thêm khách hàng</span>
                                            </button>
                                            <button id="btnCancel">
                                                <i class="fa-solid fa-trash-can"></i>
                                                <span> Hủy thao tác</span>
                                            </button>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                    </div>
                    <form:hidden path="id" id="customerId"/>
                </form:form>
            </div>
            <c:if test="${not empty customerEdit.id}">
                <c:forEach var="item" items="${transactionType}">

                            <c:if test="${item.key == 'CSKH'}">
                                <h1 class="label label-xlg label-light arrowed-in-right" style="color: white; background-color: #8fb87e;
                            padding-right: 1.5rem;font-size: 1.5rem; margin-left: 12px">
                                        ${item.value}
                                </h1>
                                <div class="hidden-sm hidden-xs btn-group" style="margin-left: 2rem;">
<%--                                    <button class="btn btn-xs btn-info" title="Thêm giao dịch"--%>
<%--                                            style="background-color: red; border-radius: 50px" >--%>
<%--                                        <i class="fa-solid fa-circle-plus" style="font-size: 1.5rem; padding: 3px"></i>--%>
<%--                                    </button>--%>
                                    <button class="btn btn-success" title="Thêm giao dịch" onclick="addTransaction('${item.key}', ${customerEdit.id})" style="background-color: #5DCB90 !important; border-color: #5DCB90 !important">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-square-dotted" viewBox="0 0 16 16">
  <path d="M2.5 0q-.25 0-.487.048l.194.98A1.5 1.5 0 0 1 2.5 1h.458V0zm2.292 0h-.917v1h.917zm1.833 0h-.917v1h.917zm1.833 0h-.916v1h.916zm1.834 0h-.917v1h.917zm1.833 0h-.917v1h.917zM13.5 0h-.458v1h.458q.151 0 .293.029l.194-.981A2.5 2.5 0 0 0 13.5 0m2.079 1.11a2.5 2.5 0 0 0-.69-.689l-.556.831q.248.167.415.415l.83-.556zM1.11.421a2.5 2.5 0 0 0-.689.69l.831.556c.11-.164.251-.305.415-.415zM16 2.5q0-.25-.048-.487l-.98.194q.027.141.028.293v.458h1zM.048 2.013A2.5 2.5 0 0 0 0 2.5v.458h1V2.5q0-.151.029-.293zM0 3.875v.917h1v-.917zm16 .917v-.917h-1v.917zM0 5.708v.917h1v-.917zm16 .917v-.917h-1v.917zM0 7.542v.916h1v-.916zm15 .916h1v-.916h-1zM0 9.375v.917h1v-.917zm16 .917v-.917h-1v.917zm-16 .916v.917h1v-.917zm16 .917v-.917h-1v.917zm-16 .917v.458q0 .25.048.487l.98-.194A1.5 1.5 0 0 1 1 13.5v-.458zm16 .458v-.458h-1v.458q0 .151-.029.293l.981.194Q16 13.75 16 13.5M.421 14.89c.183.272.417.506.69.689l.556-.831a1.5 1.5 0 0 1-.415-.415zm14.469.689c.272-.183.506-.417.689-.69l-.831-.556c-.11.164-.251.305-.415.415l.556.83zm-12.877.373Q2.25 16 2.5 16h.458v-1H2.5q-.151 0-.293-.029zM13.5 16q.25 0 .487-.048l-.194-.98A1.5 1.5 0 0 1 13.5 15h-.458v1zm-9.625 0h.917v-1h-.917zm1.833 0h.917v-1h-.917zm1.834-1v1h.916v-1zm1.833 1h.917v-1h-.917zm1.833 0h.917v-1h-.917zM8.5 4.5a.5.5 0 0 0-1 0v3h-3a.5.5 0 0 0 0 1h3v3a.5.5 0 0 0 1 0v-3h3a.5.5 0 0 0 0-1h-3z"/>
</svg>
                                            Add
                                    </button>
                                </div>
                                <div class="col-xs-12" bis_skin_checked="1">
                                    <table id="tableList" class="table table-striped table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th>Ngày tạo</th>
                                                <th>Người tạo</th>
                                                <th>Ngày sửa</th>
                                                <th>Người sửa</th>
                                                <th>Chi tiết giao dịch</th>
                                                <th class="center">Thao tác</th>
                                            </tr>
                                        </thead>

                                        <tbody>
                                            <%--duyet tat ca cac toa nha duoc tra ra tu controler--%>
                                        <c:forEach var="it" items="${CSKH}">
                                            <tr class="">
                                                <td>${it.createdDate}</td>
                                                <td>${it.createdBy}</td>
                                                <td>${it.modifiedDate}</td>
                                                <td>${it.modifiedBy}</td>
                                                <td>${it.note}</td>
                                                <td class="center">
                                                    <div class="hidden-sm hidden-xs btn-group">
                                                        <button class="btn btn-xs btn-info" title="sửa giao dịch" onclick="updateTransaction('CSKH', '${customerEdit.id}', '${it.id}', '${fn:escapeXml(it.createdDate)}', '${it.createdBy}')">
                                                        <%--, '${fn:escapeXml(it.modifiedDate)}', '${it.modifiedBy}', '${it.createdBy}', '${fn:escapeXml(it.createdDate)}')">--%>
                                                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </c:if>

                            <div></div>

                            <c:if test="${item.key == 'DDX'}">
                                <h1 class="label label-xlg label-light arrowed-in-right" style="color: white; background-color: #8fb87e;
                                    padding-right: 1.5rem;font-size: 1.5rem; margin-left: 12px">
                                        ${item.value}
                                </h1>
                                <div class="hidden-sm hidden-xs btn-group" style="margin-left: 2rem;">

                                    <button class="btn btn-success" title="Thêm giao dịch" onclick="addTransaction('${item.key}', ${customerEdit.id})" style="background-color: #5DCB90 !important; border-color: #5DCB90 !important">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-square-dotted" viewBox="0 0 16 16">
<path d="M2.5 0q-.25 0-.487.048l.194.98A1.5 1.5 0 0 1 2.5 1h.458V0zm2.292 0h-.917v1h.917zm1.833 0h-.917v1h.917zm1.833 0h-.916v1h.916zm1.834 0h-.917v1h.917zm1.833 0h-.917v1h.917zM13.5 0h-.458v1h.458q.151 0 .293.029l.194-.981A2.5 2.5 0 0 0 13.5 0m2.079 1.11a2.5 2.5 0 0 0-.69-.689l-.556.831q.248.167.415.415l.83-.556zM1.11.421a2.5 2.5 0 0 0-.689.69l.831.556c.11-.164.251-.305.415-.415zM16 2.5q0-.25-.048-.487l-.98.194q.027.141.028.293v.458h1zM.048 2.013A2.5 2.5 0 0 0 0 2.5v.458h1V2.5q0-.151.029-.293zM0 3.875v.917h1v-.917zm16 .917v-.917h-1v.917zM0 5.708v.917h1v-.917zm16 .917v-.917h-1v.917zM0 7.542v.916h1v-.916zm15 .916h1v-.916h-1zM0 9.375v.917h1v-.917zm16 .917v-.917h-1v.917zm-16 .916v.917h1v-.917zm16 .917v-.917h-1v.917zm-16 .917v.458q0 .25.048.487l.98-.194A1.5 1.5 0 0 1 1 13.5v-.458zm16 .458v-.458h-1v.458q0 .151-.029.293l.981.194Q16 13.75 16 13.5M.421 14.89c.183.272.417.506.69.689l.556-.831a1.5 1.5 0 0 1-.415-.415zm14.469.689c.272-.183.506-.417.689-.69l-.831-.556c-.11.164-.251.305-.415.415l.556.83zm-12.877.373Q2.25 16 2.5 16h.458v-1H2.5q-.151 0-.293-.029zM13.5 16q.25 0 .487-.048l-.194-.98A1.5 1.5 0 0 1 13.5 15h-.458v1zm-9.625 0h.917v-1h-.917zm1.833 0h.917v-1h-.917zm1.834-1v1h.916v-1zm1.833 1h.917v-1h-.917zm1.833 0h.917v-1h-.917zM8.5 4.5a.5.5 0 0 0-1 0v3h-3a.5.5 0 0 0 0 1h3v3a.5.5 0 0 0 1 0v-3h3a.5.5 0 0 0 0-1h-3z"/>
</svg>
                                        Add
                                    </button>
                                </div>
                                <div class="col-xs-12" bis_skin_checked="1">
                                    <table id="tableList1" class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>Ngày tạo</th>
                                            <th>Người tạo</th>
                                            <th>Ngày sửa</th>
                                            <th>Người sửa</th>
                                            <th>Chi tiết giao dịch</th>
                                            <th class="center">Thao tác</th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                            <%--duyet tat ca cac toa nha duoc tra ra tu controler--%>
                                        <c:forEach var="it" items="${DDX}">
                                            <tr class="">
                                                <td>${it.createdDate}</td>
                                                <td>${it.createdBy}</td>
                                                <td>${it.modifiedDate}</td>
                                                <td>${it.modifiedBy}</td>
                                                <td>${it.note}</td>
                                                <td class="center">
                                                    <div class="hidden-sm hidden-xs btn-group">
                                                        <button class="btn btn-xs btn-info" title="sửa giao dịch" onclick="updateTransaction('DDX', '${customerEdit.id}', '${it.id}', '${fn:escapeXml(it.createdDate)}', '${it.createdBy}')">
                                                        <%--'${fn:escapeXml(it.modifiedDate)}', '${it.modifiedBy}', '${it.createdBy}', '${fn:escapeXml(it.createdDate)}')">--%>
                                                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </c:if>

                </c:forEach>
            </c:if>
        </div>

        <div class="modal fade" role="dialog" id="addOrUpdateTransactionModel">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Nhập giao dịch</h4>
                    </div>
                    <div class="form-group" bis_skin_checked="1" style="padding: 20px">
                        <label class="col-sm-3 control-label no-padding-right"> Chi tiết giao dịch </label>
                        <div class="col-sm-9" bis_skin_checked="1" style="padding: 5px; border: solid #aaa 1px">
                            <input id="note" value="" style="width: 100%; border: none"/>
                        </div>

                    </div>
                    <input type="hidden" id="customerId" value=""/>
                    <input type="hidden" id="code" value=""/>
                    <input type="hidden" id="transactionId" value=""/>
                    <input type="hidden" id="createdDate" value=""/>
                    <input type="hidden" id="createdBy" value=""/>
                    <input type="hidden" id="modifiedDate" value=""/>
                    <input type="hidden" id="modifiedBy" value=""/>
                    <div class="modal-footer">
                        <button type="button" class="my-button-apply" id="btnAddOrUpdateTransaction" >OK</button>
                        <button type="button" class="my-button-cancel" data-dismiss="modal" id="closeModel">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- /.page-content -->
    </div>
</div>

<%@ include file="/WEB-INF/views/token-utils.jsp" %>

<script>
    $("#btnAddOrUpdateCustomer").click(function (e) {
        var data = {};
        var formData = $("#listForm").serializeArray();

        $.each(formData, function (i, v) {
            if (v.name === 'createdDate') {
                // Chuyển đổi định dạng HH:mm, dd/MM/yyyy sang ISO 8601
                var inputDate = v.value;
                var parts = inputDate.split(', ');
                if (parts.length === 2) {
                    var timePart = parts[0]; // HH:mm
                    var datePart = parts[1]; // dd/MM/yyyy

                    var dateParts = datePart.split('/');
                    if (dateParts.length === 3) {
                        var day = parseInt(dateParts[0], 10);
                        var month = parseInt(dateParts[1], 10) - 1; // Tháng trong JavaScript bắt đầu từ 0
                        var year = parseInt(dateParts[2], 10);

                        var timeParts = timePart.split(':');
                        if (timeParts.length === 2) {
                            var hours = parseInt(timeParts[0], 10);
                            var minutes = parseInt(timeParts[1], 10);

                            var isoDate = new Date(year, month, day, hours, minutes).toISOString();
                            data['createdDate'] = isoDate; // Lưu định dạng ISO 8601
                        }
                    }
                }
            } else {
                data[v.name] = v.value;
            }
        });


        console.log("OK");
        if(data['fulName'] === "" || data['phone'] === ""){
            alert("Vui lòng điền đầy đủ thông tin!")
        }
        else{
            AddOrUpdateCustomer(data);

        }

        e.preventDefault();
    });
    function AddOrUpdateCustomer(data) {
        $.ajax({
            type: "POST",
            url: "/api/customer",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "JSON",
            success: function (response) {
                if (data['id'] !== "") {
                    alert('Cập nhật thành công!');
                    window.window.location.href = "/admin/customer-list";
                } else {
                    alert('Thêm mới thành công!');
                    window.window.location.href = "/admin/customer-list";
                }
            },
            error: function (xhr, textStatus, errorThrown) {
                handleAjaxError(xhr, textStatus, errorThrown, () => AddOrUpdateCustomer(data));
            }
        });
    }
    $("#btnCancel").click(function (e) {
        navigateWithRefresh('/admin/customer-list');
        e.preventDefault();
    });


    function addTransaction(code, customerId){
        $('#note').val('');
        $('#addOrUpdateTransactionModel').modal();
        $('#customerId').val(customerId);
        $('#code').val(code);
    }
    function updateTransaction(code, customerId, transactionId , createdDate, createdBy ){//, modifiedDate, modifiedBy, createdBy, createdDate){

        $('#addOrUpdateTransactionModel').modal();
        loadOldNote(transactionId);
        $('#customerId').val(customerId);
        $('#transactionId').val(transactionId);
        $('#code').val(code);
        // $('#modifiedDate').val(modifiedDate);
        // $('#modifiedBy').val(modifiedBy);
        // if(!transactionId){
            $('#createdBy').val(createdBy);
            $('#createdDate').val(createdDate);
        // }

    }

        function loadOldNote(transactionId){
            $.ajax({
                type: "GET",
                url: "/api/transaction/" + transactionId,
                dataType: "JSON",
                success: function(response) {
                    console.log(response);
                    $('#note').val(response['note']);
                },
                error: function (xhr, textStatus, errorThrown) {
                    handleAjaxError(xhr, textStatus, errorThrown, () => loadOldNote(transactionId));
                }
            });
        }


        $('#btnAddOrUpdateTransaction').click(function (e){
            e.preventDefault();
            var data = {};
            data['id'] = $('#transactionId').val();
            data['code'] = $('#code').val();
            data['note'] = $('#note').val().trim();
            data['customerId'] = $('#customerId').val();

            if(data['id']){
                data['createdBy'] = $('#createdBy').val();
                data['createdDate'] = $('#createdDate').val();
            }

            if(data['note'] != ''){
                doAddOrUpdateTransaction(data);
            }
            else{
                addPlaceholder();
            }
        });
        function addPlaceholder() {
            var input = $('#note');
            input.attr('placeholder', 'Vui lòng điền chi tiết giao dịch');

        }
        $('#closeModel').click(function (e){
            e.preventDefault();
            var input = $('#note');
            input.attr('placeholder', '');
        })
        function doAddOrUpdateTransaction(data){
            $.ajax({
                type: "POST",
                url: "/api/transaction",
                data: JSON.stringify(data),
                contentType: "application/json",
                dataType: "JSON",
                success: function(response) {
                    alert("success!");
                    window.location.reload();

                },
                error: function (xhr, textStatus, errorThrown) {
                    handleAjaxError(xhr, textStatus, errorThrown, () => doAddOrUpdateTransaction(data));
                }
            });
        }


</script>
</body>
</html>