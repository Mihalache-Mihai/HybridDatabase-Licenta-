package com.licenta.user;

import com.licenta.models.Credentials;
import com.licenta.models.Employee;
import com.licenta.repository.ApplicationUserRepository;
import com.licenta.repository.EmployeeRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    private ApplicationUserRepository applicationUserRepository;
    private EmployeeRepository employeeRepository;

    public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository,EmployeeRepository employeeRepository) {
        super();
        this.applicationUserRepository = applicationUserRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Credentials credentials = applicationUserRepository.findByUsername(username);
        if(credentials == null){
            throw new UsernameNotFoundException(username);
        }
        Employee e = employeeRepository.findByCredentials(credentials);
        return new AppUserPrincipal(e);
    }
}
