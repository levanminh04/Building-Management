$(document).ready(function () {
    bindEventCheckAllCheckBox('checkAll');
    enableOrDisableDeleteAll();
    autoCheckCheckboxAll('checkAll');
});

function bindEventCheckAllCheckBox(id) {
    $('#' + id).on('change', function () {
        if ((this).checked) {
            $(this).closest('table').find('input[type=checkbox]').prop('checked', true);
        } else {
            $(this).closest('table').find('input[type=checkbox]').prop('checked', false);
            $('#btnDelete').prop('disabled', true);
        }
    });
}

function enableOrDisableDeleteAll() {
    $('input[type=checkbox]').click(function () {
        if ($('input[type=checkbox]:checked').length > 0) {
            $('#btnDelete').prop('disabled', false);
        } else {
            $('#btnDelete').prop('disabled', true);
        }
    });
}

function autoCheckCheckboxAll(id) {
    var totalCheckbox = $('#' + id).closest('table').find('tbody input[type=checkbox]').length;
    $('#' + id).closest('table').find('tbody input[type=checkbox]').each(function () {
        var tableObj = $('#' + id).closest('table');
        $(this).on('change', function () {
            var totalCheckboxChecked = $(tableObj).find('tbody input[type=checkbox]:checked').length;
            if (totalCheckboxChecked == totalCheckbox) {
                $('#' + id).prop('checked', true);
            } else {
                $('#' + id).prop('checked', false);
            }
        });
    });
}



$(document).ready(function () {
    // Chèn CSS tùy chỉnh vào trang
    var customCSS = `
        @media screen and (max-width: 768px) {
            #sidebar {
                position: fixed;
                left: 0;
                top: 0;
                height: 100vh;
                width: 250px;
                background-color: #2a2a2a;
                z-index: 1000;
                transition: transform 0.3s ease-in-out;
                transform: translateX(-100%);
            }

            #sidebar.active {
                transform: translateX(0);
            }

            .main-content {
                margin-left: 0 !important;
            }

            .sidebar-toggle {
                display: block;
                position: absolute;
                top: 15px;
                left: 15px;
                background-color: #35bf76;
                padding: 8px 12px;
                border-radius: 5px;
                color: #fff;
                cursor: pointer;
            }
        }
    `;
    var styleSheet = document.createElement("style");
    styleSheet.type = "text/css";
    styleSheet.innerText = customCSS;
    document.head.appendChild(styleSheet);

    // Xử lý sự kiện nhấn nút để mở sidebar
    $(".sidebar-toggle").click(function () {
        $("#sidebar").toggleClass("active");
    });
});
