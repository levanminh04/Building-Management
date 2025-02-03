package com.javaweb.controller.admin;

import com.javaweb.entity.UserEntity;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.IUserService;
import com.javaweb.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController(value = "homeControllerOfAdmin")
@RequiredArgsConstructor
public class HomeController {

	private final JwtTokenUtils jwtTokenUtils;
	private final IUserService userService;

	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public ModelAndView homePage(){ // @RequestHeader(value = "Authorization", required = false) String token) {
		ModelAndView mav = new ModelAndView();

		// Kiểm tra token có hợp lệ không
//		if (token == null || !token.startsWith("Bearer ")) {
//			mav.setViewName("errors/unauthorized"); // Giao diện hiển thị lỗi không hợp lệ
//			mav.addObject("message", "Token không được cung cấp hoặc không hợp lệ");
//			return mav;
//		}
//
//		token = token.substring(7); // Loại bỏ tiền tố "Bearer "
//
//		// Lấy username từ token
//		String username = jwtTokenUtils.extractUsername(token);
//		Optional<UserEntity> user = userService.findOneByUserNameAndStatus(username, 1);
//		if (user.isEmpty()) {
//			mav.setViewName("errors/unauthorized");
//			mav.addObject("message", "Sai Tên Đăng Nhập Hoặc Mật Khẩu");
//			return mav;
//		}

		// Kiểm tra tính hợp lệ của token
//		UserEntity userEntity = user.get();
//		if (!jwtTokenUtils.isValidateToken(token, userEntity)) {
//			mav.setViewName("errors/unauthorized");
//			mav.addObject("message", "Token không hợp lệ");
//			return mav;
//		}

		// Lấy danh sách quyền của người dùng
		List<String> roles = SecurityUtils.getAuthorities();

		// Kiểm tra quyền và chuyển hướng giao diện
		for (String role : roles) {
			if (role.equals("ROLE_STAFF") || role.equals("ROLE_MANAGER") ) {
				mav.setViewName("admin/home"); // Giao diện dành cho admin
				return mav;
			}
		}

		// Không đủ quyền
		mav.setViewName("errors/forbidden");
		mav.addObject("message", "Không đủ quyền truy cập");
		return mav;
	}
}
