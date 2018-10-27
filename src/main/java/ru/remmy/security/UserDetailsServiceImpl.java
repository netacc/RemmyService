package ru.remmy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.remmy.hibernate.dao.DAOUser;
import ru.remmy.hibernate.entities.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 06.09.15.
 */

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private DAOUser userDao;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority("ROLE_USER"));
        User user = userDao.getUsersByName(username);
        if (user == null)
            throw new UsernameNotFoundException("Login error");
        UserDetailsImpl userDetails = new UserDetailsImpl((long) user.getId(), user.getName(), user.getLogin(), user.getPassword(), list);
        userDetails.setCurrentUser(user);
        return userDetails;
    }
}
