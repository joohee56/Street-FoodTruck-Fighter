package com.ssafy.foodtruck.model.service;

import com.ssafy.foodtruck.db.entity.User;
import com.ssafy.foodtruck.dto.UserDto;
import com.ssafy.foodtruck.exception.InvalidEmailAndPasswordException;
import com.ssafy.foodtruck.util.JWToken;
import com.ssafy.foodtruck.util.JwtTokenUtil;
import com.ssafy.foodtruck.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenUtil jwtTokenUtil;
    private final RedisUtil redisUtil;

    //response.success(JwtTokenUtil.getToken(loginInfo.getEmail()), "login success", HttpStatus.OK)

    public JWToken login(UserDto userDto) {
        // 로그인 요청한 유저로부터 입력된 패스워드 와 디비에 저장된 유저의 암호화된 패스워드가 같은지 확인.(유효한 패스워드인지 여부 확인)
        if (checkRightPw(userDto)) {
            // 유효한 패스워드가 맞는 경우, 로그인 성공으로 응답.(액세스 토큰을 포함하여 응답값 전달)
//            JwtTokenUtil.getToken(loginInfo.getEmail())
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());
            Authentication auth = authenticationManagerBuilder.getObject().authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            return jwtTokenUtil.createToken(userDto, auth);
        }
        throw new InvalidEmailAndPasswordException();
    }

    public boolean checkRightPw(UserDto userDto) {
        User user = userService.getUserByEmail(userDto.getEmail());
        return passwordEncoder.matches(userDto.getPassword(), user.getPassword());
    }

    public void logout(String refreshToken) {
//        redisUtil.delete(refreshToken);
    }

    public JWToken reissue(String refreshToken) {
        String email = jwtTokenUtil.getEmailFromRefreshToken(refreshToken);

        // email이 없을때
        if(email==null){
            //예외처리
            return null;
        }

        // 해당 리프레시 토큰으로 이메일을 뽑아왔는데 뽑아온 이메일로 다시 리프레시 토큰을 가져와서 비교하면 당연히 같지. 왜 있는 코드일까?
//        if(!refreshToken.equals(redisUtil.get(email))){
//            //예외처리
//            return null;
//        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return jwtTokenUtil.reissueAccessToken(email, auth);
    }
}