<%--
  Created by IntelliJ IDEA.
  User: 84583
  Date: 07/10/2024
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingListURL" value="/admin/building-list"/>
<html>
<head>
    <title>Danh sách tòa nhà</title>
</head>
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
                    <a href="#">Home</a>
                </li>
                <li class="active">Dashboard</li>
            </ul><!-- /.breadcrumb -->


        </div>

        <div class="page-content">
            <div class="page-header">
                <h1>
                    Danh sách tòa nhà
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        overview &amp; stats
                    </small>
                </h1>
            </div><!-- /.page-header -->


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
                                <form:form id = "listform" modelAttribute="modelSearch" action = "${buildingListURL}" method ="GET">
<%--                     modelSearch chứa dữ liệu từ form sẽ được bind (gắn kết) vào thanh param khi form được hiển thị và khi submit. --%>
<%--                     action="${buildingListURL}": Biến buildingListURL chứa URL đích mà dữ liệu từ form sẽ được gửi đến.--%>
<%--                     method="GET": Phương thức GET sẽ truyền dữ liệu qua URL dưới dạng query parameters, chứ không truyền qua body của HTTP request.--%>
<%--                     do phần tìm kiếm này không có gì bảo mật lắm nên ta gửi qua param, còn cái nào bảo mật thì gửi qua body --%>
                                    <div class = "row">
                                    <div class = "form-group">
                                        <div class = "col-xs-12">
                                            <div class = "col-xs-6">
                                                <div>
                                                    <label class = "name"> Tên tòa nhà </label>
<%--                                                    <input type = "text"  class = "form-control" name = "name" value = "${modelSearch.name}">   &lt;%&ndash; name phải giống từng kí tự với bên backend &ndash;%&gt;--%>
                                                        <form:input class = "form-control" path = "name"/>
                                                </div>
                                            </div>
                                            <div class = "col-xs-6">
                                                <div>
                                                    <label class = "name"> Diện tích sàn </label>
<%--                                                    <input type = "number"  class = "form-control" name = "floorarea" value = "${modelSearch.floorarea}">   &lt;%&ndash; name phải giống từng kí tự với bên backend &ndash;%&gt;--%>
                                                        <form:input class = "form-control" path = "floorarea"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class = "form-group">
                                        <div class = "col-xs-12">
                                            <div class = "col-xs-2">
                                                <div>
                                                    <label class = "name"> Quận </label>
                                                    <form:select type = "text" name = "district"  class = "form-control" path = "district">    <%-- name phải giống từng kí tự với bên backend --%>
                                                        <form:option value="">---Chọn Quận---</form:option>
                                                        <form:options items = "${districts}"/> <%-- đặt key làm thuộc tính value(của district) của thẻ <option> và sẽ là cái được gửi về server, còn value (của map) làm nội dung hiển thị của <option>.--%>
                                                    </form:select>
<%--                                                    <option value="QUAN_1">Quận 1</option>--%>
<%--                                                    <option value="QUAN_2">Quận 2</option>--%>
<%--               trong Spring Framework, khi sử dụng thẻ <form:options>, mặc định nó sẽ tạo ra các thẻ <option> theo cấu trúc như sau:--%>
<%--                            Key từ Map sẽ được sử dụng làm giá trị cho thuộc tính value của thẻ <option>.--%>
<%--                            Value từ Map sẽ được sử dụng làm nội dung hiển thị cho thẻ <option>.--%>

                                                </div>
                                            </div>
                                            <div class = "col-xs-5">
                                                <div>
                                                    <label class = "name"> Phường </label>
<%--                                                    <input type = "text"  class = "form-control" name = "ward" value = "${modelSearch.ward}">    &lt;%&ndash; name phải giống từng kí tự với bên backend &ndash;%&gt;--%>
                                                        <form:input class = "form-control" path = "ward"/>
                                                </div>
                                            </div>
                                            <div class = "col-xs-5">
                                                <div>
                                                    <label class = "name"> Đường </label>
<%--                                                    <input type = "text"  class = "form-control" name = "street" value = "${modelSearch.street}">   &lt;%&ndash; name phải giống từng kí tự với bên backend &ndash;%&gt;--%>
                                                        <form:input class = "form-control" path = "street"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class = "form-group">
                                        <div class = "col-xs-12">
                                            <div class = "col-xs-4">
                                                <div>
                                                    <label class = "name"> Số tầng hầm </label>
<%--                                                    <input type = "number" name = "numberofbasement" value = "${modelSearch.numberofbasement}"  class = "form-control">    &lt;%&ndash; name phải giống từng kí tự với bên backend &ndash;%&gt;--%>
                                                        <form:input class = "form-control" path = "numberofbasement"/>
                                                </div>
                                            </div>
                                            <div class = "col-xs-4">
                                                <div>
                                                    <label class = "name"> Hướng </label>
<%--                                                    <input type = "text" name = "direction" value = "${modelSearch.direction}"  class = "form-control">   &lt;%&ndash; name phải giống từng kí tự với bên backend &ndash;%&gt;--%>
                                                        <form:input class = "form-control" path = "direction"/>
                                                </div>
                                            </div>
                                            <div class = "col-xs-4">
                                                <div>
                                                    <label class = "name"> Hạng </label>
<%--                                                    <input type = "number"  name = "level" value = "" class = "form-control"> &lt;%&ndash; name phải giống từng kí tự với bên backend &ndash;%&gt;--%>
                                                        <form:input class = "form-control" path = "level"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class = "form-group">
                                        <div class = "col-xs-12">
                                            <div class = "col-xs-3">
                                                <div>
                                                    <label class = "name"> Diện tích từ </label>
<%--                                                    <input type = "number" name = "minarea" value = "${modelSearch.minarea}"  class = "form-control">  &lt;%&ndash; name phải giống từng kí tự với bên backend &ndash;%&gt;--%>
                                                        <form:input class = "form-control" path = "minarea"/>
                                                </div>
                                            </div>
                                            <div class = "col-xs-3">
                                                <div>
                                                    <label class = "name"> Diện tích đến </label>
<%--                                                    <input type = "number" name = "maxarea" value = "${modelSearch.maxarea}"  class = "form-control">  &lt;%&ndash; name phải giống từng kí tự với bên backend &ndash;%&gt;--%>
                                                        <form:input class = "form-control" path = "maxarea"/>
                                                </div>
                                            </div>
                                            <div class = "col-xs-3">
                                                <div>
                                                    <label class = "name"> Giá thuê từ</label>
<%--                                                    <input type = "number" name = "minprice" value = "${modelSearch.minprice}"  class = "form-control">   &lt;%&ndash; name phải giống từng kí tự với bên backend &ndash;%&gt;--%>
                                                        <form:input class = "form-control" path = "minprice"/>
                                                </div>
                                            </div>
                                            <div class = "col-xs-3">
                                                <div>
                                                    <label class = "name"> Giá thuê đến </label>
<%--                                                    <input type = "number" name = "maxprice" value = ""  class = "form-control">  &lt;%&ndash; name phải giống từng kí tự với bên backend &ndash;%&gt;--%>
                                                        <form:input class = "form-control" path = "maxprice"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class = "form-group">
                                        <div class = "col-xs-12">
                                            <div class = "col-xs-5">
                                                <div>
                                                    <label class = "name"> Tên quản lý </label>
<%--                                                    <input type = "text"   name = "managername" value = "${modelSearch.managername}" class = "form-control">   &lt;%&ndash; name phải giống từng kí tự với bên backend &ndash;%&gt;--%>
                                                        <form:input class = "form-control" path = "managername"/>
                                                </div>
                                            </div>
                                            <div class = "col-xs-5">
                                                <div>
                                                    <label class = "name"> Điện thoại quản lý </label>
<%--                                                    <input type = "text" name = "managerphone" value = "${modelSearch.managerphonenumber}"  class = "form-control">  &lt;%&ndash; name phải giống từng kí tự với bên backend &ndash;%&gt;--%>
                                                        <form:input class = "form-control" path = "managerphone"/>
                                                </div>
                                            </div>
                                            <div class = "col-xs-2">
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
                                    <div class = "form-group">
                                        <div class = "col-xs-12">
                                            <div class = "col-xs-6">
                                                <div>
                                                    <form:checkboxes items = "${typeCodes}" path = "typecode" />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class = "form-group">
                                        <div class = "col-xs-12">
                                            <div class = "col-xs-6">
                                                <div>
                                                    <button type="button" class="btn btn-success" id = "btnSearchBuilding">
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

                        <div class = "pull-right" title = "Thêm tòa nhà">
                            <a href="/admin/building-edit">
                                <button class = "btn btn-info" id = "btnAddBuilding">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-add" viewBox="0 0 16 16">
                                        <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
                                        <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                        <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                    </svg>
                                </button>
                            </a>

                            <button class = "btn btn-warning" title = "xóa toàn bộ" id = "btnDeleteBuildings">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-fill-dash" viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"/>
                                    <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v7.256A4.5 4.5 0 0 0 12.5 8a4.5 4.5 0 0 0-3.59 1.787A.5.5 0 0 0 9 9.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .39-.187A4.5 4.5 0 0 0 8.027 12H6.5a.5.5 0 0 0-.5.5V16H3a1 1 0 0 1-1-1zm2 1.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5m3 0v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5m3.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zM4 5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5M7.5 5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm2.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5M4.5 8a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                </svg>
                            </button>

                        </div>
                    </div>
                </div>
            </div>
            <!-- mục tìm kiếm  -->

            <!-- phần table  -->
            <div class ="row">
                <div class="col-xs-12">
                    <table id="buildingTable" style="font-family: 'Times New Roman', Timeris, sef;" style="margin: 3em 0 0em;" class="table table-striped table-bordered table-hover">
                        <thead>   <!-- thead là phần tên column, 1 th là 1 column  -->
                        <tr>
                            <th class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" name = "checklist" value = "" class="ace">
                                    <span class="lbl"></span>
                                </label>
                            </th>
                            <th>Tên tòa nhà</th>
                            <th>Địa chỉ</th>
                            <th>Số tầng hầm</th>
                            <th>Tên quản lý</th>
                            <th>Số điện thoại</th>
                            <th>DT sàn</th>
                            <th>DT trống</th>
                            <th>DT thuê</th>
                            <th>Phí dịch vụ</th>
                            <th>Phí môi giới</th>
                            <th>thao tác</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="item" items = "${buildingList}">
                            <tr>   <!-- 1 tr tương ứng với 1 hàng, 1 td tương ứng với 1 column  -->
                                <td class="center">
                                    <label class="pos-rel">
                                        <input type="checkbox" name = "checklist" value="${item.id}"  class="ace">
                                        <span class="lbl"></span>
                                    </label>
                                </td>

                                <td>${item.name}</td>
                                <td>${item.address}</td>
                                <td>${item.numberOfBasement}</td>
                                <td>${item.managerName}</td>
                                <td>${item.managerPhone}</td>
                                <td>${item.floorArea}</td>
                                <td>${item.serviceFee}</td>
                                <td>${item.rentArea}</td>
                                <td></td>
                                <td>${item.brokerageFee}</td>
                                <td>
                                    <div class="hidden-sm hidden-xs btn-group">
                                        <button class="btn btn-xs btn-success" title="Giao tòa nhà" onclick="AssignmentBuilding(${item.id})">
                                            <i class="ace-icon glyphicon glyphicon-list"></i>
                                        </button>

                                        <button class="btn btn-xs btn-danger" title="Xóa" onclick="deleteBuilding(${item.id})">
                                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                        </button>

                                        <a href="/admin/building-edit-${item.id}">
                                            <button class="btn btn-xs btn-info" title="Sửa tòa nhà">
                                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                                            </button>
                                        </a>

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

<div class="modal fade" id="assignmentBuildingModal" role="dialog" style = "font-family: 'Times New Roman', Times, serif;">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">

                <div class ="row">
                    <div class="col-xs-12">
                        <table  style="font-family: 'Times New Roman', Timeris, sef;"  class="table table-striped table-bordered table-hover" id = "staffList">
                            <thead>   <!-- thead là phần tên column, 1 th là 1 column  -->
                            <tr>
                                <th class="center">
                                    <label class="pos-rel">
                                        <span class="lbl"></span>
                                        Chọn
                                    </label>
                                </th>
                                <th class="center">Tên nhân viên</th>

                            </tr>

                            </thead>

                            <tbody>
<%--                                        đổ dữ liệu trên database vào đây--%>

<%--                                        không gắn cứng như này :               --%>

<%--                            <tr>   <!-- 1 tr tương ứng với 1 hàng, 1 td tương ứng với 1 column  -->--%>
<%--                                <td class="center">--%>
<%--                                    <label class="pos-rel">--%>
<%--                                        <input type="checkbox" class="ace" id = "checkbox_1" value = "1"  >--%>
<%--                                        <span class="lbl"></span>--%>
<%--                                    </label>--%>
<%--                                </td>--%>

<%--                                <td class="center">Lê Văn Minh</td>--%>
<%--                            </tr>--%>


                            </tbody>
                        </table>

                        <input type = "hidden" id = "buildingid" name = "building" value ="1">

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id = "btnAssignmentBuilding">Giao tòa nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>

    </div>
</div>

<script src="assets/js/jquery.2.1.1.min.js"></script>


<script>
    function AssignmentBuilding(id) {
        $('#assignmentBuildingModal').modal()
        loadStaffs(id);
        $('#buildingid').val(id); // nếu val() không truyền tham số thì là lấy giá trị  // đây là cái biến hidden đấy
                                  // nếu val(x) có truyền tham số thì là gán giá trị mới
        console.log($('#buildingid').val());
    }
    function loadStaffs(id){
        $.ajax({

          url: "/api/building/" + id + '/staffs', // thuộc tính url chỉ định địa chỉ endpoint (đường dẫn) mà yêu cầu AJAX sẽ gửi dữ liệu đến url này để server xử lý yêu cầu
          type: "GET", //
          //data:JSON.stringify(data),   // gửi qua param nên không cần 2 dòng này đâu, gửi qua body mới cần
          //contentType:"application/json",
          dataType:"JSON",

          success: function (respond) {  // biến respond do mình tự đặt
              var row = '';
              $.each(respond.data, function (index, item){  // index, item, thích đặt như nào cũng được

                  row += '<tr>';
                    row += '<td class="center">' ;
                        row += '<label class="pos-rel">';
                            row += '<input type="checkbox" class="ace" id = "checkbox_' + item.staffId +'"'+  'value = "' + item.staffId + '"' + item.checked + '>';
                            row += '<span class="lbl"></span>';
                        row += '</label>';
                    row += '</td>';
                    row += '<td class="center">' + item.fullName + '</td>';
                  row += '</tr>';
              });
              $('#staffList tbody').html(row);
              console.info(row);
          },
          error: function(respond){
              console.log("failed");

            // console.log(respond);
            //   console.log(respond.message);
            //   console.log(respond.detail);
              window.location.href = "/admin/building-list?message=error";
          }

        });
    }

    $('#btnAssignmentBuilding').click(function (e) {
        e.preventDefault(); // ngăn ngừa sự kiện mặc định để ta xử lý theo cách riêng
        var data = {};
        data['buildingId'] = $('#buildingid').val();
        var staffs = $('#staffList').find('tbody input[type = checkbox]:checked').map(function () { //  chỉ chon ra những thằng được tích chọn
            return $(this).val(); // $(this) đại diện cho checkbox hiện tại trong danh sách đã chọn , .val() để lấy cái staffid
        }).get();
        data['staffs'] = staffs;

        assignment(data);

        console.log("ok");
    });

     function assignment(data){
        $.ajax({
          type:"POST", // CẬP NHẬT TÒA NHÀ THÌ DÙNG POST
          url: "/api/building/" + 'assignment',
          data:JSON.stringify(data),
          contentType:"application/json",
          dataType:"JSON",

          success: function (respond) {
            window.location.href = "/admin/building-list?message=success";
          },
          error: function(respond){
              console.log(respond);
            console.log("Giao không thành công");
            console.log(respond);
              console.log(respond.message);
              console.log(respond.detail);
            window.location.href = "/admin/building-list?message=error";
          }

        });
     }


    $('#btnSearchBuilding').click(function (e){
        e.preventDefault();
        $('#listform').submit();  // submit() giúp gửi form từ phía client đến máy chủ.
        //bạn có thể kiểm tra thuộc tính action của thẻ <form>. Thuộc tính action chỉ định URL mà form sẽ gửi yêu cầu khi nó được submit.
    });

    function deleteBuilding(buildingID){   // nút xóa trong bảng, xóa đơn cột
        var data = [buildingID]; // đơn cột nhưng vẫn cứ quy về thành 1 mảng cho đồng bộ với trường hợp xóa đa cột, xử lý chung trong 1 hàm luôn
        deleteBuildings(data);
    }

    $('#btnDeleteBuildings').click(function (e){  // nút xóa all cột
        e.preventDefault();
        var data = $('#buildingTable').find('tbody input[type = checkbox]:checked').map(function (){
            return  $(this).val(); // lấy cái buildingid
        }).get();
        deleteBuildings(data);
    });
    function deleteBuildings(data){
        // call API   sử dụng AJAX để send data to server
        $.ajax({
          type:"DELETE", // XÓA TÒA NHÀ THÌ DÙNG DELETE
          url: "/api/building/" + data, // thuộc tính url chỉ định địa chỉ endpoint (đường dẫn) mà yêu cầu AJAX sẽ gửi dữ liệu đến url này để server xử lý yêu cầu
          data:JSON.stringify(data),
          contentType:"application/json",
          dataType:"JSON",
    
          success: function (respond) {
            // $("#h11").html(result);
            window.location.href = "/admin/building-list?message=success";
          },
          error: function(respond){
            console.log(respond);
          }

        });
    }

</script>



<script type="text/javascript"> // tạo chức năng khi nhấn vào checkbox tổng thì select all
    jQuery(function($) {
        // Bỏ chọn tất cả các checkbox khi tải trang
        $('th input[type=checkbox], td input[type=checkbox]').prop('checked', false);

        // Chọn/bỏ chọn tất cả các hàng theo checkbox trong tiêu đề
        $('#buildingTable > thead > tr > th input[type=checkbox]').eq(0).on('click', function() {
            var th_checked = this.checked; // kiểm tra trạng thái của checkbox tiêu đề

            $(this).closest('table').find('tbody > tr').each(function() {
                var row = this;
                // Chọn hoặc bỏ chọn tất cả các checkbox trong bảng
                $(row).find('input[type=checkbox]').prop('checked', th_checked);
            });
        });

        // Chọn/bỏ chọn một hàng khi checkbox của nó được click
        $('#buildingTable').on('click', 'td input[type=checkbox]', function() {
            var row = $(this).closest('tr');
            var checked = this.checked; // kiểm tra trạng thái của checkbox
            $(row).find('input[type=checkbox]').prop('checked', checked);
        });
    });
</script>



</body>
</html>
