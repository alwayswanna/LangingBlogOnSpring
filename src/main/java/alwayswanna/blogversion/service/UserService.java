package alwayswanna.blogversion.service;

import alwayswanna.blogversion.models.User;
import alwayswanna.blogversion.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private AdminRepo adminRepo;
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminRepo.findByUsername(username);
    }
}
