package com.javaweb.filter;


import com.javaweb.entity.UserEntity;
import com.javaweb.utils.CookieUtils;
import com.javaweb.utils.JwtTokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor // dùng để quản lý dependency injection tương tự @Autowired nhưng hỗ trợ Dễ  viết unit test, An toàn và rõ ràng hơn @Autowired

// OncePerRequestFilter  đảm bảo rằng filter (ở đây là JwtTokenFilter) chỉ được thực thi một lần duy nhất cho mỗi yêu cầu HTTP (request) đến máy chủ,
// Điều này rất quan trọng khi xử lý các yêu cầu như xác thực JWT, nơi bạn chỉ muốn xác thực 1 lần duy nhất cho mỗi request.
// ví dụ GET /api/dashboard -> forward to /api/user/data, Nếu không dùng OncePerRequestFilter, JwtTokenFilter có thể được gọi hai lần (một lần khi truy cập /api/dashboard và một lần khi forward đến /api/user/data)
// forward không phải là 1 request nên không cần xác thực lại token

public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenUtils jwtTokenUtils;

    private final UserDetailsService userDetailsService;

    private final CookieUtils cookieUtils;

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);


//    private static final Set<String> PUBLIC_ENDPOINTS_WITH_AUTH_OPTION = Set.of(
//            "/trang-chu", "/lien-he", "/tin-tuc", "/san-pham", "/gioi-thieu"
//    );

    public static final Set<String> WHITE_LIST = new HashSet<>();//  danh sách các endpoint hoặc pattern URL mà filter sẽ bỏ qua (không yêu cầu xác thực JWT).
    static {
        WHITE_LIST.add("/resources/**");
        WHITE_LIST.add("/login");
        WHITE_LIST.add("/trang-chu");
        WHITE_LIST.add("/logout");
        WHITE_LIST.add("/web/**");
        WHITE_LIST.add("/css/**");
        WHITE_LIST.add("/images/**");
        WHITE_LIST.add("/img/**");
        WHITE_LIST.add("/fonts/**");
        WHITE_LIST.add("/js/**");
        WHITE_LIST.add("/WEB-INF/**");
        WHITE_LIST.add("/lien-he");
        WHITE_LIST.add("/api/user/refresh-token");
        WHITE_LIST.add("/tin-tuc");
        WHITE_LIST.add("/api/customer/contact");
        WHITE_LIST.add("/san-pham");
        WHITE_LIST.add("/gioi-thieu");
        WHITE_LIST.add("/webapp/**");
        WHITE_LIST.add("/index.jsp");
        WHITE_LIST.add("/");
        WHITE_LIST.add("/sign-up");
        WHITE_LIST.add("/building/**");
        WHITE_LIST.add("/reset-password");
        WHITE_LIST.add("/api/auth/send-otp");
        WHITE_LIST.add("/api/auth/verify-otp");
        WHITE_LIST.add("/api/auth/reset-password");
        WHITE_LIST.add("/api/user/register");


    } //  final yêu cầu rằng giá trị của biến phải được khởi tạo ngay khi khai báo (hoặc trong khối khởi tạo tĩnh)

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try{
            String token = cookieUtils.getAccessToken(request);
            String refreshToken = cookieUtils.getRefreshToken(request); // Lấy refresh token

            String requestURI = request.getRequestURI();
            System.out.println(requestURI);
            logger.debug("JwtTokenFilter - Processing request: " + requestURI);

            if (shouldNotFilter(request)) {
                filterChain.doFilter(request, response);
                return;
            }

// Trường hợp cả Access Token và Refresh Token đều null
            if (token == null && refreshToken == null) {
                logger.warn("Access denied: Both tokens are missing");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.sendRedirect("/login");
                return;
            }

// Trường hợp Access Token null nhưng Refresh Token có giá trị
            if (token == null) {
                logger.warn("Access denied: Access token is missing");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return; // Không chuyển hướng đi đâu cả
            }

            // xử lý xác thực nếu token != null
            final String userName = jwtTokenUtils.extractUsername(token); //  giải mã token và lấy tên ngườidùng

//          userName != null : trong token phải có tên người dùng
//          SecurityContextHolder.getContext().getAuthentication() == null Đảm bảo chưa có thông tin xác thực trước đó trong SecurityContextHolder
//          Nếu SecurityContextHolder đã có thông tin xác thực:
//          Điều đó có nghĩa là request hiện tại đã được xử lý và xác thực từ trước bởi một bộ lọc khác hoặc trong một luồng xử lý trước đó.
//          Ghi đè lên thông tin đã tồn tại sẽ không cần thiết, làm tăng chi phí xử lý không cần thiết hoặc gây lỗi không mong muốn, tốn tài nguyên.
//          mỗi khi một request đến, SecurityContextHolder mặc định là rỗng

            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails  userEntity = userDetailsService.loadUserByUsername(userName);
                System.out.println("@#  " + userEntity);
                if (jwtTokenUtils.isValidateToken(token, userEntity)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userEntity,// principal (Tham số thứ nhất): Đây là thông tin về người dùng, thường là đối tượng UserDetails hoặc tên người dùng (String). Nó đại diện cho người dùng đang đăng nhập. Đây là một giá trị không thể thiếu.
                            null,      // credentials (Tham số thứ hai): là thông tin đăng nhập, thường là mật khẩu của người dùng. Trong một số trường hợp, nếu bạn không cần lưu trữ mật khẩu (ví dụ như khi sử dụng một phương thức xác thực khác như JWT hoặc OAuth), bạn có thể truyền null vào tham số này.
                            userEntity.getAuthorities() // authorities (Tham số thứ ba): Đây là danh sách quyền hạn của người dùng, chẳng hạn như ROLE_USER, ROLE_ADMIN, v.v. Đây là thông tin về quyền truy cập của người dùng trong hệ thống.
                    );
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // WebAuthenticationDetailsSource có tác dụng thiết lập các thông tin bổ sung vào đối tượng Authentication như: địa chỉ ip, session ID (nếu có)...buildDetails(request) lấy thông tin từ đối tượng HttpServletRequest
                    SecurityContextHolder.getContext().setAuthentication(authentication); // sau khi người dùng đã đăng nhập và xác thực JWT thành công, bạn cần lưu thông tin xác thực vào SecurityContext
                    System.out.println("Current Authentication: " + authentication);
                    System.out.println("User Roles in SecurityContextHolder: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
                    System.out.println("Thread before transaction: " + Thread.currentThread().getName());

                    System.out.println();
                    logger.info("JWT Token: " + token);
                    logger.info("Extracted username: " + userName);
                    logger.info("Is token valid: " + jwtTokenUtils.isValidateToken(token, userDetailsService.loadUserByUsername(userName)));

                }
            }


            filterChain.doFilter(request, response); // Tiến hành tiếp tục chuỗi các bộ lọc trong Spring Security.
            // filterChain: Là đối tượng đại diện cho chuỗi các bộ lọc (filter chain) trong Spring Security, doFilter(request, response): Phương thức này gọi tiếp các bộ lọc tiếp theo trong chuỗi.

//          Sau khi xác thực JWT thành công, Spring Security sẽ sử dụng thông tin trong SecurityContext để kiểm tra quyền truy cập (requestMatchers(...).permitAll()) và chuyển đến controller nếu yêu cầu hợp lệ. còn nếu Với token = null: Khi này SecurityContextHolder.getContext().getAuthentication() vẫn là null (nghĩa là không có thông tin người dùng hợp lệ),mà không có thông tin thì sẽ không xác thực được quyền truy cập vào các endpoint
//          Một khi bạn đã gán đối tượng Authentication vào SecurityContext, các bộ lọc khác như UsernamePasswordAuthenticationFilter  sẽ không làm lại bước xác thực (authentication)
            System.out.println("Authentication in SecurityContext after filter: " + SecurityContextHolder.getContext().getAuthentication());


        }catch(Exception e){
            e.printStackTrace();
            logger.error("JwtTokenFilter encountered an error: ", e);
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED); // lỗi 401 unauthorized
        }


    }
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        AntPathMatcher pathMatcher = new AntPathMatcher();
//        String requestUri = request.getRequestURI();
//
//        // Nếu là tài nguyên tĩnh hoặc đường dẫn mặc định, bỏ qua filter
//        boolean isStaticResource = WHITE_LIST.stream().anyMatch(p -> pathMatcher.match(p, requestUri));
//
//        // Chỉ bỏ qua filter nếu là tài nguyên tĩnh hoặc đường dẫn mặc định
//        return isStaticResource && !requestUri.equals("/trang-chu");
//    }
@Override
protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    AntPathMatcher pathMatcher = new AntPathMatcher();
    return WHITE_LIST.stream().anyMatch(p -> pathMatcher.match(p, request.getRequestURI()));
}
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        AntPathMatcher pathMatcher = new AntPathMatcher();
//        String requestUri = request.getRequestURI();
//
//        // Danh sách các tài nguyên tĩnh cần bỏ qua hoàn toàn
//        boolean isStaticResource = WHITE_LIST.stream().anyMatch(p -> pathMatcher.match(p, requestUri));
//
//        // Danh sách các endpoint công khai cần xác thực tùy chọn
//        boolean isPublicEndpointWithOptionalAuth = PUBLIC_ENDPOINTS_WITH_AUTH_OPTION.stream()
//                .anyMatch(endpoint -> pathMatcher.match(endpoint, requestUri));
//
//        // Chỉ bỏ qua filter nếu là tài nguyên tĩnh và không phải endpoint công khai với xác thực tùy chọn
//        return isStaticResource && !isPublicEndpointWithOptionalAuth;
//    }



}
