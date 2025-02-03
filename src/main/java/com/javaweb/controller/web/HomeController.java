package com.javaweb.controller.web;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingResponse;
import com.javaweb.service.BuildingService;
import com.javaweb.utils.CookieUtils;
import com.javaweb.utils.DistrictCode;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController (value = "homeControllerOfWeb")
@RequiredArgsConstructor
public class HomeController {
    private static final Logger logger =  LoggerFactory.getLogger(HomeController.class);

    private final CookieUtils cookieUtils;

    private final BuildingService buildingService;

    private final BuildingConverter buildingConverter;

	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	public ModelAndView homePage( HttpServletRequest request){//BuildingSearchRequest buildingSearchRequest, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("web/home");

        Map<String, Object> model = new HashMap<String, Object>();
        String token = cookieUtils.getAccessToken(request);
        String username = "";
        if(token != null) {
            username = cookieUtils.getUsername(request);
        }
        model.put("username", username);
        List<BuildingEntity> buildingEntities = buildingService.getAllBuildings();
        List<BuildingResponse> buildings = new ArrayList<>();

        for (BuildingEntity buildingEntity : buildingEntities) {
            buildings.add(buildingConverter.toBuildingResponse(buildingEntity));
        }

        mav.addAllObjects(model);
        mav.addObject("buildings", buildings);
        mav.addObject("districts", districtCode.type());
        return mav;
	}




    @GetMapping(value="/gioi-thieu")
    public ModelAndView introducceBuiding(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("web/introduce");
        Map<String, Object> model = new HashMap<String, Object>();
        String token = cookieUtils.getAccessToken(request);
        String username = "";
        if(token != null) {
            username = cookieUtils.getUsername(request);
        }
        model.put("username", username);
        mav.addAllObjects(model);
        return mav;
    }

    @GetMapping(value="/san-pham")
    public ModelAndView buidingList(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("web/list");
        Map<String, Object> model = new HashMap<String, Object>();
        String token = cookieUtils.getAccessToken(request);
        String username = "";
        if(token != null) {
            username = cookieUtils.getUsername(request);
        }
        List<BuildingEntity> buildingEntities = buildingService.getAllBuildings();
        List<BuildingResponse> buildings = new ArrayList<>();

        for (BuildingEntity buildingEntity : buildingEntities) {
            buildings.add(buildingConverter.toBuildingResponse(buildingEntity));
        }

        mav.addObject("buildings", buildings);
        model.put("username", username);
        mav.addAllObjects(model);
        return mav;
    }

    @GetMapping(value="/tin-tuc")
    public ModelAndView news(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("web/news");
        Map<String, Object> model = new HashMap<String, Object>();
        String token = cookieUtils.getAccessToken(request);
        String username = "";
        if(token != null) {
            username = cookieUtils.getUsername(request);
        }
        model.put("username", username);
        mav.addAllObjects(model);
        return mav;
    }

    @GetMapping(value="/lien-he")
    public ModelAndView contact(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("web/contact");
        Map<String, Object> model = new HashMap<String, Object>();
        String token = cookieUtils.getAccessToken(request);
        String username = "";
        if(token != null) {
            username = cookieUtils.getUsername(request);
        }
        model.put("username", username);
        mav.addAllObjects(model);
        return mav;
    }

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("login");
        Map<String, Object> model = new HashMap<String, Object>();
        String token = cookieUtils.getAccessToken(request);
        String username = "";
        if(token != null) {
            username = cookieUtils.getUsername(request);
        }
        model.put("username", username);
        mav.addAllObjects(model);
		return mav;
	}

    //   CHÚ Ý:
    // Spring Security 5.x trở lên (được dùng mặc định trong Spring Boot 3.x) áp dụng StrictHttpFirewall, vốn kiểm tra rất nghiêm ngặt các URL để ngăn ngừa lỗ hổng bảo mật.
    // URL chứa // sẽ bị chặn ngay lập tức. Điều này có thể không xảy ra trên Spring Boot 2.x do các quy tắc bớt nghiêm ngặt hơn.
    // SỬ DỤNG ModelAndView("web/contact"); không được dùng ("/web/contact");, nếu thêm '/' là bị lỗi lin quan đến '//' ngay


	@RequestMapping(value = "/access-denied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/login?accessDenied");
	}

    @RequestMapping(value = "/sign-up", method = RequestMethod.GET)
    public ModelAndView register(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("signup");
        return mav;
    }

    @RequestMapping(value = "/reset-password", method = RequestMethod.GET)
    public ModelAndView resetPassword(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("reset_password");
        return mav;
    }
    @RequestMapping(value = "/building/{id}", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, @PathVariable Long id) {
        ModelAndView mav = new ModelAndView("web/properties");
        BuildingEntity building = buildingService.getBuildingById(id);
        BuildingResponse buildingResponse = buildingConverter.toBuildingResponse(building);
        if (building != null) {
            mav.addObject("building", buildingResponse);
            return mav;
        }
        return mav;
    }


}
