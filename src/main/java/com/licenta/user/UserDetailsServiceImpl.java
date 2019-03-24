package com.licenta.user;

import com.licenta.models.ApplicationUser;
import com.licenta.repository.ApplicationUserMongoRepository;
import com.licenta.repository.ApplicationUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    private ApplicationUserRepository applicationUserRepository;
    private ApplicationUserMongoRepository applicationUserMongoRepository;

    public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository, ApplicationUserMongoRepository applicationUserMongoRepository) {
        this.applicationUserRepository = applicationUserRepository;
        this.applicationUserMongoRepository = applicationUserMongoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if(applicationUser == null){
            throw new UsernameNotFoundException(username);
        }
        ApplicationUser applicationUser1 = applicationUserMongoRepository.findByUsername(username);
        if(applicationUser1 == null){
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(),applicationUser.getPassword(),emptyList());
    }
}
