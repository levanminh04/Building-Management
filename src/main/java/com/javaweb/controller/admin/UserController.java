package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.dto.UserLoginDTO;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.IUserService;
import com.javaweb.service.impl.RoleService;
import com.javaweb.utils.JwtTokenUtils;
import com.javaweb.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController(value = "usersControllerOfAdmin")
public class UserController {


	private final JwtTokenUtils jwtTokenUtils;

	@Autowired
	private IUserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private MessageUtils messageUtil;

	@PostMapping(value="/login")
	public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO userDTO){
		try{
			String token = userService.login(userDTO.getUsername(), userDTO.getPassword());
			System.out.println();
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			System.out.println();
			String redirectUrl = "/trang-chu";
			List<String> roles = SecurityUtils.getAuthorities();

			for (String role : roles) {
				if(role.equals("ROLE_STAFF") || role.equals("MANAGER")){
					redirectUrl = "/admin/home";
					break;
				}
			}
			System.out.println( "hom nay la: "+ new Date(System.currentTimeMillis()));

			// Tạo HttpOnly Cookie chứa JWT

			ResponseCookie jwtCookie = ResponseCookie.from("token", token)
					.httpOnly(true) // Cookie chỉ có thể được truy cập bởi server
					.secure(true) // Bật khi sử dụng HTTPS
					.maxAge(Duration.ofMinutes(1))
					.path("/") // Đảm bảo cookie có sẵn trên tất cả đường dẫn
					.build();

			String newRefreshToken = jwtTokenUtils.generateRefreshToken((UserEntity) userDetails );
			ResponseCookie newRefreshTokenCookie = ResponseCookie.from("refreshToken", newRefreshToken)
					.httpOnly(true)
					.secure(true)
					.maxAge(Duration.ofDays(7))
					.path("/")  // không gửi kèm refresh Token trong mọi request
					.build();
			return ResponseEntity.ok()
					.header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
					.header(HttpHeaders.SET_COOKIE, newRefreshTokenCookie.toString())
					.body(Map.of("redirectUrl", redirectUrl));

		}
		catch (Exception e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

//	@RequestMapping(value = "/admin/user-list", method = RequestMethod.GET)
//	public ModelAndView getNews(@ModelAttribute(SystemConstant.MODEL) UserDTO model, HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView("admin/user/list");
//
//		// Lấy số trang từ request (mặc định là 1 nếu không có)
//		int page = DisplayTagUtils.getPage(request);
//
//		// Gán giá trị phân trang cho model
//		model.setPage(page);
//
//		// Thực hiện truy vấn với giá trị phân trang
//		List<UserDTO> users = userService.getUsers(
//				model.getSearchValue(),
//				PageRequest.of(page - 1, model.getMaxPageItems())
//		);
//
//		model.setListResult(users);
//		model.setTotalItems(userService.countTotalItems());
//		mav.addObject(SystemConstant.MODEL, model);
//
//		// Thêm thông báo nếu cần
//		initMessageResponse(mav, request);
//
//		return mav;
//	}
	@RequestMapping(value = "/admin/user-list", method = RequestMethod.GET)
	public ModelAndView getNews(@ModelAttribute(SystemConstant.MODEL) UserDTO model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/user/list");

		// Lấy toàn bộ danh sách người dùng từ service
		List<UserDTO> users = userService.getAllUsers(); // Thay đổi phương thức service để trả về toàn bộ dữ liệu

		// Cập nhật model với danh sách đầy đủ
		model.setListResult(users);
		model.setTotalItems(users.size()); // Tổng số bản ghi chính là kích thước của danh sách

		// Thêm model vào ModelAndView
		mav.addObject(SystemConstant.MODEL, model);

		// Gọi hàm khởi tạo thông báo nếu cần
		initMessageResponse(mav, request);

		return mav;
	}
	@RequestMapping(value = "/admin/user-edit", method = RequestMethod.GET)
	public ModelAndView addUser(@ModelAttribute(SystemConstant.MODEL) UserDTO model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/user/edit");
		model.setRoleDTOs(roleService.getRoles());
		initMessageResponse(mav, request);
		mav.addObject(SystemConstant.MODEL, model);
		return mav;
	}

	@RequestMapping(value = "/admin/profile-{username}", method = RequestMethod.GET)
	public ModelAndView updateProfile(@PathVariable("username") String username, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/user/profile");
		UserDTO model = userService.findOneByUserName(username);
		initMessageResponse(mav, request);
		model.setRoleDTOs(roleService.getRoles());
		mav.addObject(SystemConstant.MODEL, model);
		return mav;
	}

	@RequestMapping(value = "/admin/user-edit-{id}", method = RequestMethod.GET)
	public ModelAndView updateUser(@PathVariable("id") Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/user/edit");
		UserDTO model = userService.findUserById(id);
		model.setRoleDTOs(roleService.getRoles());
		initMessageResponse(mav, request);
		mav.addObject(SystemConstant.MODEL, model);
		return mav;
	}

	@RequestMapping(value = "/admin/profile-password", method = RequestMethod.GET)
	public ModelAndView updatePassword(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/user/password");
		UserDTO model = userService.findOneByUserName(SecurityUtils.getPrincipal().getUsername());
		initMessageResponse(mav, request);
		mav.addObject(SystemConstant.MODEL, model);
		return mav;
	}

	private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
		String message = request.getParameter("message");
		if (message != null && StringUtils.isNotEmpty(message)) {
			Map<String, String> messageMap = messageUtil.getMessage(message);
			mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
			mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
		}
	}
}
