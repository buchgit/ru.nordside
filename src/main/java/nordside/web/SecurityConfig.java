package nordside.web;

import nordside.service.UserService;
import nordside.web.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtFilter jwtFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

//                .and()
//                .requiresChannel()
////                .anyRequest().requiresSecure()
//                .antMatchers("/rest/user/**").requiresSecure()

                .and()
                .authorizeRequests()
                //.antMatchers("/rest/user/registration","/rest/user/auth","/rest/user/category/all","/rest/user/partner/all").permitAll()
                .antMatchers("/rest/user/auth").anonymous()
                //.antMatchers("/rest/user/auth").permitAll()
                .antMatchers("/rest/admin/**").hasRole("ADMIN")
                .antMatchers("/rest/user/cart/*","/rest/user/personal/*").hasRole("USER")
                .antMatchers("/rest/user/**").permitAll()

                .and()
                .addFilterBefore(jwtFilter, AnonymousAuthenticationFilter.class);

                //https протокол включить
//        http.requiresChannel()
//                .anyRequest().requiresInsecure();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/anonymous*")
//                .anonymous();
//
//        http.authorizeRequests()
//                .antMatchers("/login*")
//                .permitAll();
//
//        http.authorizeRequests()
//                .anyRequest()
//                .authenticated();

//http.requiresChannel()
//        .antMatchers("/login*").requiresSecure();


//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}