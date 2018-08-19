package com.hediapps.data.service;

import java.util.stream.Collectors;

import com.hediapps.authentication.dto.AuthorityDto;
import com.hediapps.authentication.dto.UserDto;
import com.hediapps.authentication.mapper.UserMapper;
import com.hediapps.authentication.model.AuthorityEntity;
import com.hediapps.authentication.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service("authenticationService")
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final CredentialRepository credentialRepository;
    private final AuthorityRepository authorityRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity user = credentialRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return UserMapper.INSTANCE.userEntityToUserDto(user);
    }

    @Override
    public void save(UserDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntity = UserMapper.INSTANCE.userDtoToUserEntity(user);
        if (!CollectionUtils.isEmpty(user.getAuthorities())) {
            userEntity.setAuthorities(user.getAuthorities().stream()
                    .map(this::findAuthority)
                    .collect(Collectors.toList()));
        }

        credentialRepository.save(userEntity);
    }

    private AuthorityEntity findAuthority(AuthorityDto authorityDto) {
        return authorityRepository.findByAuthority(authorityDto.getAuthority());
    }
}
