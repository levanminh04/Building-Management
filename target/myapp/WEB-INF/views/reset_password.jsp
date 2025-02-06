<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Reset Password</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" />
</head>
<body>
<div class="container">
    <div class="reset-password-form">
        <div class="main-div">
            <div class="container-fluid">
                <section class="gradient-custom">
                    <div class="page-wrapper">
                        <div class="row d-flex justify-content-center align-items-center">
                            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                                <div class="card text-white" style="border-radius: 1rem; background-color: #35bf76;">
                                    <div class="card-body p-5">
                                        <div class="mb-md-5 mt-md-4 pb-5 text-center" id="emailSection">
                                            <h2 class="fw-bold mb-2 text-uppercase">Reset Password</h2>
                                            <p class="text-white-50 mb-5">Enter your email to receive an OTP!</p>
                                            <form id="formEmail">
                                                <div class="form-outline form-white mb-4">
                                                    <label class="form-label" for="email">Email</label>
                                                    <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required>
                                                </div>
                                                <button type="button" class="btn btn-success" id="btnSendOTP">Send OTP</button>
                                            </form>
                                        </div>

                                        <div class="mb-md-5 mt-md-4 pb-5 text-center d-none" id="otpSection">
                                            <h2 class="fw-bold mb-2 text-uppercase">Enter OTP</h2>
                                            <p class="text-white-50 mb-5">Please enter the 6-digit OTP sent to your email!</p>
                                            <form id="formOTP">
                                                <div class="form-outline form-white mb-4">
                                                    <label class="form-label" for="otpCode">OTP Code</label>
                                                    <input type="number" class="form-control" id="otpCode" name="otpCode" placeholder="Enter 6-digit OTP" minlength="6" maxlength="6" required>
                                                </div>
                                                <button type="button" class="btn btn-success" id="btnVerifyOTP">Verify OTP</button>
                                            </form>
                                        </div>

                                        <div class="mb-md-5 mt-md-4 pb-5 text-center d-none" id="resetSection">
                                            <h2 class="fw-bold mb-2 text-uppercase">Reset Password</h2>
                                            <p class="text-white-50 mb-5">Enter your new password!</p>
                                            <form id="formResetPassword">
                                                <input type="hidden" id="hiddenEmail">
                                                <input type="hidden" id="hiddenResetToken">
                                                <div class="form-outline form-white mb-4">
                                                    <label class="form-label" for="newPassword">New Password</label>
                                                    <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="Enter new password" required>
                                                </div>
                                                <div class="form-outline form-white mb-4">
                                                    <label class="form-label" for="confirmPassword">Confirm Password</label>
                                                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm new password" required>
                                                </div>
                                                <button type="button" class="btn btn-success" id="btnResetPassword">Reset Password</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('#btnSendOTP').click(function () {
            var email = $('#email').val().trim();
            if (!email) {
                alert('Please enter your email.');
                return;
            }

            $.ajax({
                type: "POST",
                url: "/api/auth/send-otp",
                data: JSON.stringify({ email: email }),
                contentType: "application/json",
                success: function () {
                    alert("OTP sent successfully!");
                    $('#emailSection').addClass('d-none');
                    $('#otpSection').removeClass('d-none');
                    $('#hiddenEmail').val(email);
                },
                error: function () {
                    alert("Failed to send OTP. Please try again.");
                }
            });
        });

        $('#btnVerifyOTP').click(function () {
            var otpCode = $('#otpCode').val().trim();
            if (!otpCode || otpCode.length !== 6) {
                alert('Please enter a valid 6-digit OTP.');
                return;
            }

            var email = $('#hiddenEmail').val();
            $.ajax({
                type: "POST",
                url: "/api/auth/verify-otp",
                data: JSON.stringify({ email: email, otpCode: otpCode }),
                contentType: "application/json",
                success: function (resetToken) {
                    console.log(resetToken)
                    alert("OTP verified successfully!");
                    $('#otpSection').addClass('d-none');
                    $('#resetSection').removeClass('d-none');
                    $('#hiddenResetToken').val(resetToken.resetToken);

                },
                error: function () {
                    alert("OTP không hợp lệ.");
                }
            });
        });

        $('#btnResetPassword').click(function () {
            var newPassword = $('#newPassword').val().trim();
            var confirmPassword = $('#confirmPassword').val().trim();
            var email = $('#hiddenEmail').val();
            var resetToken = $('#hiddenResetToken').val();
            console.log(resetToken)
            if (!newPassword || !confirmPassword) {
                alert('Please fill in all required fields.');
                return;
            }

            if (newPassword !== confirmPassword) {
                alert('Passwords do not match.');
                return;
            }

            $.ajax({
                type: "POST",
                url: "/api/auth/reset-password",
                data: JSON.stringify({ email: email, newPassword: newPassword, resetToken: resetToken }),
                contentType: "application/json",
                success: function () {
                    alert("Password reset successfully!");
                    window.location.href = "/login";
                },
                error: function () {
                    alert("Password reset failed. Please try again.");
                }
            });
        });
    });
</script>

</body>
</html>
