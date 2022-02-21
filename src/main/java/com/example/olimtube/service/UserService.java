package com.example.olimtube.service;

import com.example.olimtube.model.*;
import com.example.olimtube.repository.CategoryRepository;
import com.example.olimtube.repository.UserCatecoryRepository;
import com.example.olimtube.repository.UserRepository;
import com.example.olimtube.repository.VideoRepository;
import com.example.olimtube.requestDto.LoginDto;
import com.example.olimtube.requestDto.SignupRequestDto;
import com.example.olimtube.responseDto.*;
import com.example.olimtube.security.JwtTokenProvider;
import com.example.olimtube.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final VideoRepository videoRepository;
    private final UserCatecoryRepository userCatecoryRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional
    public User registerUser(SignupRequestDto requestDto, String profile) {
// 회원 ID 중복 확인
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }
        if (requestDto.getUsername() == null) {
            throw new NullPointerException("아이디를 입력해주세요");
        }
        if (Objects.equals(requestDto.getUsername(), "")) {
            throw new NullPointerException("아이디를 입력해주세요!!!!!!!!!");
        }
        if (requestDto.getPassword() == null) {
            throw new NullPointerException("비밀번호를 입력해주세요");
        }
        if (Objects.equals(requestDto.getPassword(), "")) {
            throw new NullPointerException("비밀번호를 입력해주세요!!!!!!!!!!!!");
        }

// 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());

// 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }
        User user = new User(username, password, profile, role);
        return userRepository.save(user);
    }


    //중복확인 서비스
    //CheckId에 중복확인성공시 반환하는 객체들이 있다. 중복된 아이디가 있을경우 fals를 이용하여 오류를 내주고 그렇지 않으면 가능하다고 성공시켜줍니다.
    public CheckIdResponseDto checkId(SignupRequestDto requestDto) {
        CheckIdResponseDto checkIdResponseDto = new CheckIdResponseDto();
        Optional<User> member = userRepository.findByUsername(requestDto.getUsername());
        if (member.isPresent()) {
            checkIdResponseDto.setOk(false);
            checkIdResponseDto.setMsg("중복된 ID가 존재합니다.");
        } else {
            checkIdResponseDto.setOk(true);
            checkIdResponseDto.setMsg("사용 가능한 ID 입니다.");
        }
        return checkIdResponseDto;
    }


    //로그인 서비스
    //존재하지 않거나 비밀번호가 맞지 않을시 오류를 내주고 그렇지 않을경우 토큰을 발행합니다.
    public LoginResponseDto login(LoginDto loginDto) {
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        {
            User member = userRepository.findByUsername(loginDto.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID 입니다."));
            if (!passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
                throw new IllegalArgumentException("비밀번호를 다시 확인해 주세요.");
            }
            loginResponseDto.setToken(jwtTokenProvider.createToken(member.getUsername()));
            loginResponseDto.setUsername(member.getUsername());
            loginResponseDto.setProfile(member.getProfile());
            return loginResponseDto;
        }
    }

    public UserInfoResponseDto userInfo(UserDetailsImpl userDetails) {
        UserInfoResponseDto userInfoResponseDto = new UserInfoResponseDto();
        User user = userDetails.getUser();
        if (user == null)
            throw new NullPointerException("유저 정보가 없습니다.");
        userInfoResponseDto.setId(user.getId());
        userInfoResponseDto.setUsername(user.getUsername());
        userInfoResponseDto.setProfile(user.getProfile());
        userInfoResponseDto.setIs_login(false);
        return userInfoResponseDto;
    }


    @Transactional
    public int subscribe(Long video_id, UserDetailsImpl userDetails) {
        Video video = videoRepository.getById(video_id);
        User user = userDetails.getUser();
        Category category = video.getCategory();
        int categoryNumber = category.getCategoryNumber();
        List<UserCatecory> userCatecories = userCatecoryRepository.findAllByUserId(user.getId());
        for (UserCatecory userCatecory:userCatecories){
            if (userCatecory.getCategoryNumber() == categoryNumber){
                throw new IllegalArgumentException("이미 구독된 채널입니다!");
            }
        }
        String categoryImg = category.getCategoryImg();
        UserCatecory userCatecory =new UserCatecory(categoryNumber,categoryImg,user);
        userCatecoryRepository.save(userCatecory);
        return userCatecory.getCategoryNumber();


    }

    public List<UserCategoryResponseDto> showSubscribes(UserDetailsImpl userDetails) {
        List<UserCatecory> userCatecories = userCatecoryRepository.findAllByUserId(userDetails.getUser().getId());
        List<UserCategoryResponseDto> userCategoryResponseDtoArrayList = new ArrayList<>();
        for (UserCatecory userCatecory:userCatecories){
            UserCategoryResponseDto userCategoryResponseDto = new UserCategoryResponseDto(userCatecory.getCategoryNumber(), userCatecory.getCategoryImg());
            userCategoryResponseDtoArrayList.add(userCategoryResponseDto);
        }
        return userCategoryResponseDtoArrayList;
    }
}
