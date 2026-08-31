package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserCreateRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserResponse createUser(String nickName, String email) {

    if (userRepository.findByEmail(email).isPresent()) {
      throw new IllegalArgumentException("사용중인 이매일");
    }

    User user = User.builder()
        .nickName(nickName)
        .email(email)
        .build();

    User saved = userRepository.save(user);

    return createUserResponse(saved);
  }

  public UserResponse findById(Long id) {
   User user = userRepository.findById(id)
       .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다"));

    return createUserResponse(user);
  }

  public List<UserResponse> findAll() {
    List<User> users = userRepository.findAll();

    List<UserResponse> userList = users.stream()
        .map(
            UserService::createUserResponse
        ).toList();
    return userList;
  }

  public void deleteById(Long id) {
    if(!userRepository.existsById(id)) {
      throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
    }
    userRepository.deleteById(id);
  }

  private static UserResponse createUserResponse(User user) {
    return new UserResponse(user.getId(), user.getNickName(), user.getEmail());
  }



}
