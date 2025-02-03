<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<%@ page import="com.javaweb.security.utils.SecurityUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="navbar" class="navbar navbar-default ace-save-state" style="background-color:#35bf76">
    <div class="navbar-container ace-save-state" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <small>
                    <i class="fa fa-leaf"></i>
                    Trang quản trị
                </small>
            </a>
        </div>
        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="light-10">
                    <security:authorize access = "isAuthenticated()">

                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        Xin chào, <%=SecurityUtils.getPrincipal().getUsername()%>
                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>

                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
<%--                            <a href="/admin/profile-<%=SecurityUtils.getPrincipal().getUsername()%>">--%>

                                <a href="javascript:void(0);" onclick="navigateWithRefresh('/admin/profile-<%=SecurityUtils.getPrincipal().getUsername()%>')">


                                <i class="ace-icon fa fa-user"></i>
                                <%--<spring:message code="label.account.information"/>--%>
                                Thông tin tài khoản
                            </a>
                        </li>
                        <li>
                            <a href="javascript:void(0);" onclick="navigateWithRefresh('/admin/profile-password')">
                                <i class="ace-icon fa fa-key"></i>
                                <%--<spring:message code="label.password.change"/>--%>
                                Đổi mật khẩu
                            </a>
                        </li>
                        <li class="divider"></li>
                            <li>
                                <a href="javascript:void(0);" id="logout-btn">
                                    <i class="ace-icon fa fa-power-off"></i>
                                    Thoát
                                </a>
                            </li>
                    </ul>


                </security:authorize>
                </li>
            </ul>
        </div>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<%@ include file="/WEB-INF/views/token-utils.jsp" %>
<script>
    $(document).on('click', '#logout-btn', function () {
        // Gửi yêu cầu đăng xuất
        $.ajax({
            url: '/logout',
            type: 'post',
            success: function (response) {
                // Xử lý khi đăng xuất thành công
                window.location.href = '/login'; // Chuyển hướng tới trang đăng nhập
            },
            error: function (xhr, status, error) {
                console.error("Đăng xuất thất bại:", error);
                alert("Có lỗi xảy ra khi đăng xuất. Vui lòng thử lại.");
            }
        });
    });
</script>
