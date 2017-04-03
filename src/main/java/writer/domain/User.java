package writer.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

@Document
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String username;
    private String password;
    private List<GrantedAuthority> grantedAuthorities;

    //region CONSTRUCTORS
    public User() {}
    public User(String emailAddress, String username, String password, String firstName, String lastName, String[] grandtedAuthorities) {
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.grantedAuthorities = AuthorityUtils.createAuthorityList(grandtedAuthorities);
    }

    public User(String username,String password,String[] authorities) {
        this.username = username;
        this.password = password;
        this.grantedAuthorities = AuthorityUtils.createAuthorityList(authorities);
    }
    //endregion

    //region IMPLEMENTED FROM UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    //endregion

}