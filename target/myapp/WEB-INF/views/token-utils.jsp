

<%@include file="/common/taglib.jsp" %>

<script type="text/javascript">


    // Hàm điều hướng với logic refresh token
    function navigateWithRefresh(url) {
        $.ajax({
            url: url,
            method: 'GET',
            success: function (response) {
                // Backend đã trả về view, chỉ cần chèn nội dung trả về (nếu cần)
                console.log('Request succeeded, received response:', response);
                window.location.href = url;
            },
            error: function (xhr, textStatus, errorThrown) {
                console.log(xhr);

                // Kiểm tra lỗi và xử lý phù hợp
                if (xhr.status === 401) {
                    handleAjaxError(xhr, textStatus, errorThrown, () => navigateWithRefresh(url));
                } else {
                    handleAjaxError(xhr, textStatus, errorThrown); // Xử lý lỗi khác qua handleAjaxError
                }
            }

        });
    }

    // Hàm gửi yêu cầu refresh token tới backend
    function refreshToken() {
        return $.ajax({
            url: '/api/user/refresh-token',
            method: 'POST',
            // Cookie sẽ tự động được gửi kèm theo request
            success: function(data) {
                console.log('Token refreshed successfully:', data);
            },
            error: function(xhr, status, error) {
                console.error('Error refreshing token:', error);
                throw new Error('Failed to refresh token');
            }
        });
    }

    // Hàm xử lý lỗi AJAX
    function handleAjaxError(jqXHR, textStatus, errorThrown, originalFunction) {
        const status = jqXHR.status;
        const responseText = jqXHR.responseText || "";
        const responseJSON = jqXHR.responseJSON || {};

        switch (status) {
            case 401: // Token hết hạn
                console.log("Access Token expired. Refreshing...");
                refreshToken()
                    .then(() => {
                        console.log("Token refreshed. Retrying original request...");
                        if (typeof originalFunction === "function") {
                            originalFunction(); // Gọi lại hàm gốc nếu refresh thành công
                        } else {
                            console.error("originalFunction is not a valid function.");
                        }
                    })
                    .catch(() => {
                        console.log("Refresh token failed. Redirecting to login...");
                        window.location.href = "/login"; // Chuyển hướng nếu refresh token thất bại
                    });
                break;

            case 403: // Forbidden
                console.error("Access forbidden. You do not have permission to perform this action.");
                alert("You do not have permission to perform this action.");
                break;

            case 404: // Resource not found
                console.error("Resource not found:", responseText);
                alert("The requested resource was not found. Please try again later.");
                break;

            case 500: // Server error
                console.error("Server error:", responseText || errorThrown);
                alert("An unexpected error occurred on the server. Please try again later.");
                break;

            default: // Other errors
                console.error("AJAX error:", textStatus, errorThrown, responseJSON);
                alert("An error occurred: " + (responseJSON.message || "Please try again."));
                break;
        }
    }


</script>
