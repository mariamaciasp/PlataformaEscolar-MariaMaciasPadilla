package dam.PlataformaEscolar.seguridad;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final CustomSuccessHandler customSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // @formatter:off

        http
                .authorizeRequests()
                .antMatchers("/css/**","/js/**","/files/**", "/h2-console/**","/inicio").permitAll()
                .antMatchers("/admin/**").hasAnyRole("JEFEDEESTUDIOS")
                .antMatchers("/profesor/**").hasAnyRole("PROFESOR")
                .antMatchers("/alumno/**").hasAnyRole("ALUMNO")
                .anyRequest().authenticated()
                .and()
        .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(customSuccessHandler)
                .and()
        .logout()
                .logoutUrl("/logout")
                .permitAll()
                .and()
        .rememberMe()
                .key("uniqueAndSecret").tokenValiditySeconds(86400)
                .and()
        .exceptionHandling()
                .accessDeniedPage("/acceso-denegado");

        // Añadimos esto para poder seguir accediendo a la consola de H2
        // con Spring Security habilitado.
        http.csrf().disable();
        http.headers().frameOptions().disable();

    }




}
