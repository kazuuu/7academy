package br.com.sevencomm.nerdevs.application.configs.security;
import br.com.sevencomm.nerdevs.application.configs.security.jwt.JwtAuthenticationFilter;
import br.com.sevencomm.nerdevs.application.configs.security.jwt.JwtAuthorizationFilter;
import br.com.sevencomm.nerdevs.application.configs.security.jwt.handler.AccessDeniedHandler;
import br.com.sevencomm.nerdevs.application.configs.security.jwt.handler.UnauthorizedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
@Autowired
@Qualifier("userDetailsService")
private UserDetailsService userDetailsService;
@Autowired
private UnauthorizedHandler unauthorizedHandler;
@Autowired
private AccessDeniedHandler accessDeniedHandler;
@Override
protected void configure(HttpSecurity http) throws Exception {
AuthenticationManager authManager = authenticationManager();
http
.cors().and()
.authorizeRequests()
.antMatchers(HttpMethod.GET, "/login").permitAll()
.antMatchers("/user/sign-up").permitAll()
.antMatchers("/user/forgot-password").permitAll()
.antMatchers("/vaga/join-vaga").permitAll()
.antMatchers("/vaga/get-vaga").permitAll()
.antMatchers("/vaga/is-joined").permitAll()
.antMatchers("/user/get-user-by-email").permitAll()
.antMatchers("/user/email-exists").permitAll()
.antMatchers("/empresa/get-empresa").permitAll()
.anyRequest().authenticated()
.and().csrf().disable()
.addFilter(new JwtAuthenticationFilter(authManager))
.addFilter(new JwtAuthorizationFilter(authManager, userDetailsService))
.exceptionHandling()
.accessDeniedHandler(accessDeniedHandler)
.authenticationEntryPoint(unauthorizedHandler)
.and()
.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
}
@Bean
CorsConfigurationSource corsConfigurationSource() {
CorsConfiguration configuration = new CorsConfiguration();
configuration.setAllowedOrigins(Arrays.asList("*"));
configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "UPDATE"));
configuration.setAllowCredentials(true);
configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
source.registerCorsConfiguration("/**", configuration);
return source;
}
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
}
}