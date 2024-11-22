package com.course.fleet.management.api.service.impl;

import static com.course.fleet.management.api.constants.CustomErrorMessage.USER_NOT_FOUND;

import com.course.fleet.management.api.domain.User;
import com.course.fleet.management.api.dto.request.UserUpdateAttributeRequestDTO;
import com.course.fleet.management.api.dto.request.UserUpdateRequestDTO;
import com.course.fleet.management.api.entity.UserEntity;
import com.course.fleet.management.api.exception.customExceptions.UserNotFoundException;
import com.course.fleet.management.api.mapper.UserMapper;
import com.course.fleet.management.api.repository.UserRepository;
import com.course.fleet.management.api.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public User create(User user) {
    return save(user);
  }

  @Override
  public List<User> getAll() {
    final List<UserEntity> userEntities = userRepository.findAll();
    return UserMapper.INSTANCE.toUserList(userEntities);
  }

  @Override
  public User getUser(Long userId) {

    return findById(userId);
  }

  @Override
  public User updateUser(UserUpdateRequestDTO userUpdateRequestDTO, Long id) {
    User currentUser = findById(id);
    User updatedUser = UserMapper.INSTANCE.toUserUpdate(userUpdateRequestDTO, currentUser);

    return save(updatedUser);
  }

  @Override
  public User updateUserAttribute(
      UserUpdateAttributeRequestDTO userUpdateAttributeRequestDTO, Long id) {
    User currentUser = findById(id);
    User updatedUser =
        UserMapper.INSTANCE.toUserUpdateAttribute(userUpdateAttributeRequestDTO, currentUser);

    return save(updatedUser);
  }

  @Override
  public void deleteUser(Long userId) {
    Optional<User> user = Optional.ofNullable(findById(userId));
    user.ifPresent(value -> userRepository.deleteById(value.getId()));
  }

  private User save(User user) {
    UserEntity userEntity = UserMapper.INSTANCE.toUserEntity(user);
    userEntity = userRepository.save(userEntity);

    return UserMapper.INSTANCE.toUser(userEntity);
  }

  private User findById(Long userId) {
    UserEntity userEntity =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

    return UserMapper.INSTANCE.toUser(userEntity);
  }
}
