package com.javaweb.service.impl;

import com.javaweb.constant.SystemConstant;
import com.javaweb.converter.UserConverter;
import com.javaweb.customException.DuplicateUserException;
import com.javaweb.model.dto.PasswordDTO;
import com.javaweb.model.dto.RegisterDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.entity.RoleEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.exception.MyException;
import com.javaweb.repository.RoleRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IUserService;
import com.javaweb.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {


    private final UserRepository userRepository;


    private final RoleRepository roleRepository;


    private final PasswordEncoder passwordEncoder;


    private final UserConverter userConverter;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtils jwtTokenUtils;


    @Override
    public Optional<UserEntity> findOneByUserNameAndStatus(String name, int status) {
        return  userRepository.findOneByUserNameAndStatus(name, status);
    }

    @Override
    public List<UserDTO> getUsers(String searchValue, Pageable pageable) {
        Page<UserEntity> users = null;
        if (StringUtils.isNotBlank(searchValue)) {
            users = userRepository.findByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(searchValue, searchValue, 0, pageable);
        } else {
            users = userRepository.findByStatusNot(0, pageable);
        }
        List<UserEntity> newsEntities = users.getContent();
        List<UserDTO> result = new ArrayList<>();
        for (UserEntity userEntity : newsEntities) {
            UserDTO userDTO = userConverter.convertToDto(userEntity);
            userDTO.setRoleCode(userEntity.getRoles().get(0).getCode());
            result.add(userDTO);
        }
        return result;
    }

    @Override
    public Map<Long, String> getStaffs() {
        Map <Long, String> staffs = new HashMap<>();
        List<UserEntity> users = userRepository.findByStatusAndRoles_Code(1,"STAFF");
        for (UserEntity userEntity : users) {
            staffs.put(userEntity.getId(), userEntity.getFullName());
        }
        return staffs;
    }

    @Override
    public String login(String username, String password) throws Exception {
        Optional<UserEntity> user = userRepository.findOneByUserNameAndStatus(username, 1);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Sai Tên Đăng Nhập Hoặc Mật Khẩu");
        }
//      bước if else này  Kiểm tra thủ công chỉ là một phần nhỏ của quy trình xác thực. Nó không bao gồm việc tích hợp vào Spring Security (nơi yêu cầu đối tượng Authentication được thiết lập và lưu vào SecurityContextHolder).
        UserEntity userEntity = user.get();
        if (!passwordEncoder.matches(password, userEntity.getPassword())) {
            throw new BadCredentialsException("Sai Tên Đăng Nhập Hoặc Mật Khẩu");
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userEntity, password, userEntity.getAuthorities()); //  chứa thông tin username, password, và danh sách quyền
        System.out.println();
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);  // AuthenticationManager sẽ kiểm tra thông tin trong UsernamePasswordAuthenticationToken
//        Khi bạn gọi authenticationManager.authenticate(), Spring Security sẽ sử dụng AuthenticationProvider (thường là DaoAuthenticationProvider) để thực hiện xác thực.
//        sau đó AuthenticationProvider gọi UserDetailsService để tải thông tin người dùng (UserDetails) (DaoAuthenticationProvider được cấu hình mặc địng làm việc với UserDetailsService và PasswordEncoder.)
//        ban đầu UsernamePasswordAuthenticationToken đóng vai trò là thông tin của người dùng được nhập vào từ phía frontend, còn DaoAuthenticationProvider chịu trách nhiệm so sánh thông tin này với thông tin trong cơ sở dữ liệu ( thường thì chỉ so sánh mật khẩu thôi, còn Principal và Authorities thường cần đến sau khi xác thưc thành công, phân quyền),
//        quá trình xác thực sẽ là:  nếu xác thực đúng thì DaoAuthenticationProvider lại tạo một UsernamePasswordAuthenticationToken mới khác chứa thông tin ĐÃ XÁC THỰC bao gồm các quyền (authorities) của người dùng, và lưu UsernamePasswordAuthenticationToken mới này vào SecurityContextHolder trong đó credentials có thể bị xóa (nếu eraseCredentials được bật).
//        Các bộ lọc bảo mật hoặc phân quyền (@PreAuthorize, @Secured, ...) sẽ dựa vào SecurityContextHolder để quyết định quyền truy cập

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userEntity, null, userEntity.getAuthorities()));
        // mặc dù credentials có thể bị xóa (do eraseCredentials mặc định được bật) nhưng ở đây ta cứ chắc cú  tạo 1 UsernamePasswordAuthenticationToken mới và set credentials = null, mục đích là để tránh việc lưu mật khẩu trong SecurityContextHolder, Tránh rò rỉ mật khẩu trong trường hợp bộ nhớ bị truy cập trái phép, Giữ an toàn thông tin người dùng.
        return jwtTokenUtils.generateToken(userEntity);
//        Khi AuthenticationManager xác thực thành công, Người dùng được coi là đã đăng nhập, khi này mới cấp token
    }


    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntities = userRepository.getAllUsers();
        List<UserDTO> results = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            UserDTO userDTO = userConverter.convertToDto(userEntity);
            userDTO.setRoleCode(userEntity.getRoles().get(0).getCode());
            results.add(userDTO);
        }
        return results;
    }

    @Override
    public int countTotalItems() {
        return userRepository.countTotalItem();
    }




    @Override
    public int getTotalItems(String searchValue) {
        int totalItem = 0;
        if (StringUtils.isNotBlank(searchValue)) {
            totalItem = (int) userRepository.countByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(searchValue, searchValue, 0);
        } else {
            totalItem = (int) userRepository.countByStatusNot(0);
        }
        return totalItem;
    }

    @Override
    public UserDTO findOneByUserName(String userName) {
        UserEntity userEntity = userRepository.findOneByUserName(userName);
        UserDTO userDTO = userConverter.convertToDto(userEntity);
        return userDTO;
    }

    @Override
    public UserDTO findUserById(long id) {
        UserEntity entity = userRepository.findById(id).get();
        List<RoleEntity> roles = entity.getRoles();
        UserDTO dto = userConverter.convertToDto(entity);
        roles.forEach(item -> {
            dto.setRoleCode(item.getCode());
        });
        return dto;
    }

    @Override
    @Transactional
    public UserDTO insert(UserDTO newUser) {
        RoleEntity role = roleRepository.findOneByCode(newUser.getRoleCode());
        UserEntity userEntity = userConverter.convertToEntity(newUser);
        userEntity.setRoles(Stream.of(role).collect(Collectors.toList()));
        userEntity.setStatus(1);
        userEntity.setPassword(passwordEncoder.encode(SystemConstant.PASSWORD_DEFAULT));
        return userConverter.convertToDto(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public UserDTO update(Long id, UserDTO updateUser) {
        RoleEntity role = roleRepository.findOneByCode(updateUser.getRoleCode());
        UserEntity oldUser = userRepository.findById(id).get();
        UserEntity userEntity = userConverter.convertToEntity(updateUser);
        userEntity.setUserName(oldUser.getUsername());
        userEntity.setStatus(oldUser.getStatus());
        userEntity.setRoles(Stream.of(role).collect(Collectors.toList()));
        userEntity.setPassword(oldUser.getPassword());
        return userConverter.convertToDto(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public void updatePassword(long id, PasswordDTO passwordDTO) throws MyException {
        UserEntity user = userRepository.findById(id).get();
        if (passwordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword())
                && passwordDTO.getNewPassword().equals(passwordDTO.getConfirmPassword())) {
            user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new MyException(SystemConstant.CHANGE_PASSWORD_FAIL);
        }
    }

    @Override
    @Transactional
    public UserDTO resetPassword(long id) {
        UserEntity userEntity = userRepository.findById(id).get();
        userEntity.setPassword(passwordEncoder.encode(SystemConstant.PASSWORD_DEFAULT));
        return userConverter.convertToDto(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public UserDTO updateProfileOfUser(String username, UserDTO updateUser) {
        UserEntity oldUser = userRepository.findOneByUserName(username);
        oldUser.setFullName(updateUser.getFullName());
        return userConverter.convertToDto(userRepository.save(oldUser));
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        for (Long item : ids) {
            UserEntity userEntity = userRepository.findById(item).get();
            userEntity.setStatus(0);
            userRepository.save(userEntity);
        }
    }

    @Override
    public void resetPassword(String email, String newPassword) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User không tồn tại."));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Transactional
    public void createUser(RegisterDTO newUser) {
        // Kiểm tra xem username hoặc email đã tồn tại chưa
        if (userRepository.existsByUserName(newUser.getUsername())) {
            throw new DuplicateUserException("Username is already taken!");
        }
        if (userRepository.existsByEmail(newUser.getEmail())) {
            throw new DuplicateUserException("Email is already registered!");
        }

        // Tìm role mặc định
        RoleEntity role = roleRepository.findOneByCode("STAFF");
        if (role == null) {
            throw new IllegalStateException("Default role not found.");
        }

        // Chuyển đổi DTO thành Entity
        UserEntity userEntity = userConverter.convertToEntity(newUser);
        userEntity.setRoles(Collections.singletonList(role));
        userEntity.setStatus(1);
        userEntity.setPassword(passwordEncoder.encode(newUser.getPassword()));

        userRepository.save(userEntity);
    }

}
