package com.example.transactionmanagementdemo.security;

//import com.example.authentication.domain.entity.User;
//import com.example.authentication.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.lang.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Component
@PropertySource("classpath:application.properties")
public class JwtProvider {
    @Value("${security.jwt.token.key}")
    private String key;
//    @Autowired
//    @Lazy
//    private UserService userService;
    public Optional<AuthUserDetail> resolveToken(HttpServletRequest request){
        String prefixedToken = request.getHeader("Authorization"); // extract token value by key "Authorization"
        if (!Strings.hasText(prefixedToken)){
            return Optional.empty();
        }

        String token = prefixedToken.substring(7); // remove the prefix "Bearer "

        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody(); // decode
        Integer id = Integer.parseInt(claims.getId());
        String username = claims.getSubject();
        Date expiration = claims.getExpiration();
        List<LinkedHashMap<String, String>> permissions = (List<LinkedHashMap<String, String>>) claims.get("permissions");

        // convert the permission list to a list of GrantedAuthority
        List<GrantedAuthority> authorities = permissions.stream()
                .map(p -> new SimpleGrantedAuthority(p.get("authority")))
                .collect(Collectors.toList());
        //User user = userService.getUserByUsername(username);


        //return a userDetail object with the permissions the user has
        return Optional.of(AuthUserDetail.builder()
                .user_id(id)
                .username(username)
                .authorities(authorities)
                .expir_date(expiration)
                .build());

    }

    /**
     * Generate token from data declaration
     *
     * @param authUserDetail data declaration
     * @return token
     */
    public  String createToken(AuthUserDetail authUserDetail)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("permissions", authUserDetail.getAuthorities());



        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, key)
                .setClaims(map)  //Custom information can also be omitted
                .setIssuer("system")     //Issuer
                .setSubject(authUserDetail.getUsername())   //jwtsubject
                .setIssuedAt(new Date())   //Current time
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // Expiration time
                .compact();
        return token;
    }

    /**
     * Generate token from data declaration
     *
     * @param token token
     * @return data declaration
     */
    public  Claims parseToken(String token)
    {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     *get user id from token
     * @param authToken
     * @return
     */
    public  String getUsername(String authToken) {
        Claims claims = parseToken(authToken);
        String username = (String) claims.getSubject();
        return username;
    }


}
