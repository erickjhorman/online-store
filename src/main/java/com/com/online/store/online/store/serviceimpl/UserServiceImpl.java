package com.com.online.store.online.store.serviceimpl;

import com.com.online.store.online.store.model.User;
import com.com.online.store.online.store.repository.UserRepository;
import com.com.online.store.online.store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
