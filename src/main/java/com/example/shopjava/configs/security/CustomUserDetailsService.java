package com.example.shopjava.configs.security;

import com.example.shopjava.entities.user.cart.Cart;
import com.example.shopjava.entities.user.Favorite;
import com.example.shopjava.entities.user.Role;
import com.example.shopjava.entities.user.User;
import com.example.shopjava.repos.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            log.error("User with such email wasn't found: " + email);
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }

    public String signUp(String email, String password, String repeatPSW, HttpServletRequest request) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            log.warn("User with such email already exists" + email);
            return "User with such email already exists";
        }
        user = new User();
        user.setEmail(email);
        if (!password.equals(repeatPSW)) {
            return "You haven't repeated password correctly";
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setRole(Role.USER);
        addUserCartAndFavorites(user);
        return "";
    }

    public String signIn(String email, String password, HttpServletRequest request) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            log.warn("Wrong email was inputted: " + email);
            return "Email was wrong";
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "Password was wrong";
        }

        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(user.getEmail(), password);
        Authentication auth = authenticationManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = request.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
        return "";
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @PostConstruct
    public void createDefaultAdmin() {
        User user = userRepository.findByEmail("somemail@gmail.com");
        if (user != null) {
            log.info("Admin has already exists");
            return;
        }
        user = new User();
        user.setEmail("somemail@gmail.com");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("somePassword");
        user.setPassword(encodedPassword);
        user.setRole(Role.ADMIN);
        addUserCartAndFavorites(user);
        log.info("Admin was created");
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    private void addUserCartAndFavorites(User user) {
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        Cart cart = new Cart(0, 0);
        cart.setUser(user);
        user.setFavorite(favorite);
        user.setCart(cart);
        userRepository.save(user);
    }
}
