package com.javaweb.utils;



import com.javaweb.customException.InvalidParamException;
import com.javaweb.entity.UserEntity;
import com.javaweb.filter.JwtTokenFilter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Component
public class JwtTokenUtils {

    // Tạo JWT Token từ thông tin người dùng ( username, id)
    // JWT bao gồm 3 phần  Header: Chứa thông tin về thuật toán ký và loại token. Payload: Chứa các tuyên bố (claims) - thông tin mà bạn muốn chia sẻ giữa các bên. Signature : Được tạo ra bằng cách ký Header và Payload với một khóa bí mật (secret key) hoặc private key.
    // access token
    public String generateToken(UserEntity userEntity) throws Exception {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userName", userEntity.getUsername());
        claims.put("id", userEntity.getId()); //  custom claims
        claims.put("jti", UUID.randomUUID().toString()); // thêm yêu tố ngẫu nhiên vào đê tạo sự khác biệt giữa refresh token và access token
        try {
            System.out.println("hom nay la:  " + System.currentTimeMillis());
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(userEntity.getUsername()) // "Subject" là một registered claim (thông tin) cơ bản trong JWT, setSubject() thường được dùng để lưu trữ thông tin nhận dạng của người dùng trong token, chẳng hạn như username hoặc userId. Đây là cách phổ biến để xác định người dùng mà token này đại diện.
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact(); // compact() sẽ Trả về token dạng string

        } catch (Exception e) {
            throw new InvalidParamException(e.getMessage());
        }
    }

    public String generateRefreshToken(UserEntity userEntity) throws Exception {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userName", userEntity.getUsername());
        claims.put("id", userEntity.getId()); //  custom claims
        claims.put("jti", UUID.randomUUID().toString());
        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(userEntity.getUsername()) // "Subject" là một registered claim (thông tin) cơ bản trong JWT, setSubject() thường được dùng để lưu trữ thông tin nhận dạng của người dùng trong token, chẳng hạn như username hoặc userId. Đây là cách phổ biến để xác định người dùng mà token này đại diện.
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact(); // compact() sẽ Trả về token dạng string

        } catch (Exception e) {
            throw new InvalidParamException(e.getMessage());
        }
    }

    public String generateResetToken(String email, Duration expirationTime) throws Exception {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(email) // "Subject" là một registered claim (thông tin) cơ bản trong JWT, setSubject() thường được dùng để lưu trữ thông tin nhận dạng của người dùng trong token, chẳng hạn như username hoặc userId. Đây là cách phổ biến để xác định người dùng mà token này đại diện.
                    .setExpiration(new java.util.Date(System.currentTimeMillis() + expirationTime.toMillis()))
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact(); // compact() sẽ Trả về token dạng string

        } catch (Exception e) {
            throw new InvalidParamException(e.getMessage());
        }
    }

    // Hàm getSignInKey() trả về một đối tượng Key (khóa) được sử dụng trong các thuật toán mã hóa HMAC, cụ thể là khóa đã được mã hóa bằng SHA
    private Key getSignInKey() { //Khóa bí mật chỉ được sử dụng để tạo chữ ký (signature)
        byte[] bytes = Decoders.BASE64.decode("TaqlmGv1iEDMRiFp/pHuID1+T84IABfuA0xXh4GhiUI=");   //  bWFuY2hlc3Rlcl91bml0ZWQ (base64) = manchester_united
        // Decoders.BASE64.decode(...):Hàm này nhận một chuỗi base64 và giải mã nó thành một mảng byte.
        return Keys.hmacShaKeyFor(bytes); // Hàm này sử dụng mảng byte (bytes) vừa giải mã để tạo ra một khóa HMAC
    }


    // Khi JWT được gửi từ client đến server, Server sẽ sử dụng cùng một khóa bí mật mà nó đã dùng khi ký token ban đầu, và áp dụng HMAC SHA-256 hoặc thuật toán tương tự để tạo ra chữ ký mới từ phần header và payload đã mã hóa.
    // sau đó server so sánh chữ ký mà nó vừa tạo ra với chữ ký (signature) trong JWT.
    // Nếu chúng khớp nhau, điều đó có nghĩa là token hợp lệ và chưa bị thay đổi.


    // giải mã JWT xem có hợp lệ không
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey()) // đặt khóa bí mật mà server sẽ sử dụng để xác thực chữ ký của token
                .build() // Sau khi thiết lập các tham số cần thiết (ví dụ như khóa bí mật), phương thức build() sẽ khởi tạo một đối tượng parser cho phép phân tích và giải mã JWT.
                .parseClaimsJws(token) // Phương thức này sẽ giải mã token JWT được truyền vào. Token phải có chữ ký hợp lệ (được ký với khóa bí mật đã chỉ định).
                .getBody(); // Phương thức getBody() trích xuất phần payload từ JWT. Payload này chứa các claims (dữ liệu về người dùng hoặc thông tin xác thực khác).
    }

//    Claims là một tập hợp các thông tin mà bạn có thể sử dụng trong ứng dụng để xác minh và lấy các thông tin người dùng, chẳng hạn như:
//
//    Các custom claims:
//       Dữ liệu tùy chỉnh được thêm vào, như id, username, ...
//    Các registered claims:
//       sub: Subject (thường là ID người dùng).
//       exp: Expiration time (thời gian hết hạn của token).
//       iat: Issued at (thời gian phát hành token).

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) { // Một hàm (function) để xử lý dữ liệu claims và trả về một giá trị có kiểu T.
        final Claims claims = this.extractAllClaims(token); // Phương thức gọi this.extractAllClaims(token) để giải mã token JWT và lấy các claims từ token như thông tin người dùng, thời gian hết hạn...
//        System.out.println("ID type: " + claims.get("id").getClass().getName());
        return claimsResolver.apply(claims);
    } // Function<Claims, T> là một functional interface trong Java nhận vào đối tượng Claims và trả về giá trị của kiểu T.
    // từ khóa final được sử dụng để đảm bảo rằng biến claims sẽ không bị thay đổi sau khi được gán giá trị từ phương thức extractAllClaims(token). nếu claims  bị thay đổi có thể dẫn đến sai sót trong quá trình xác thực jwt


    // Kiểm tra xem token có hết hạn không
    public boolean isTokenExpired(String token) {
        Date expiration = this.extractClaims(token, Claims::getExpiration); // cú pháp của Method Reference
        return expiration.before(new Date()); // new Date() trả về ngày hiện tại
    }
    //    Claims::getExpiration có thể truyền vào làm tham số nhờ vào method reference và cách hoạt động của Function<Claims, T>
//
    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public Long extractUserId(String token) {  // JWT nó tự ép kiểu id về INTEGER nếu số đó có thể biểu diễn dưới dạng Integer, lưu ý khi UserEntity khai báo id với kiểu Long
        return extractClaims(token, claims -> ((Number) claims.get("id")).longValue());
    }

    public String extractEmail(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    // Kiểm tra xem token có hợp lệ không
    public boolean isValidateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public boolean isValidateResetToken(String token, String email) {
        String mail = extractEmail(token);
        return mail.equals(email) && !isTokenExpired(token);
    }

    public boolean isTokenUserNameValid(String token, String userName) {
        String usernameToken = extractUsername(token);
        return usernameToken.equals(userName);
    }
//    Một JWT chỉ hỗ trợ sẵn kiểm tra xem token có bị thay đổi chỉnh sửa hay không(do được ký bằng chữ ký số).
//    JWT không tự kiểm tra xem người dùng sử dụng token có khớp với tài khoản đang truy cập hay không cũng như Không kiểm tra ngày hết hạn (exp). Bạn cần tự cấu hình logic kiểm tra này.
//    Nếu chỉ dựa vào JWT mà không kiểm tra logic người dùng, có thể dẫn đến vấn đề bảo mật như sử dụng token bị đánh cắp.
}
