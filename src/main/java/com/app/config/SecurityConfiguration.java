package com.app.config;

import com.app.config.handler.CustomAuthenticationSuccessHandler;
import com.app.config.handler.CustomLogoutSuccessHandler;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
        @Autowired
        CustomAuthenticationSuccessHandler customSuccessHandler;
    
	private static final String SQL_LOGIN = "select username, password, active as enabled "
			+ "from user_app where username = ?";
	/*
	private static final String SQL_PERMISSION = "select u.username, r.nama as authority "
			+ "from pu_user u join pu_user_role ur on u.id = ur.id_user " + "join pu_role r on ur.id_authority = r.id "
			+ "where u.username = ?";
			
 	*/
	
	private static final String SQL_PERMISSION = "select u.username, r.nama as authority "
			+ "from user_app u join role r on u.id_role = r.id "
			+ "where u.username = ?";

	@Autowired
	private DataSource ds;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(ds).passwordEncoder(passwordEncoder()).usersByUsernameQuery(SQL_LOGIN)
				.authoritiesByUsernameQuery(SQL_PERMISSION);
	}
        
	@Bean
        public AuthenticationSuccessHandler successHandler() {
            CustomAuthenticationSuccessHandler handler = new CustomAuthenticationSuccessHandler();
            handler.setUseReferer(true);
            return handler;
        }
        
         @Bean
        public LogoutHandler logoutHandler() {
            CustomLogoutSuccessHandler handler = new CustomLogoutSuccessHandler();
            return handler;
        }
        
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().contentTypeOptions().disable().authorizeRequests().antMatchers("/templates/**").permitAll()
				.antMatchers("/uploads/**").permitAll()
				.antMatchers("/assets/**").permitAll()
				.antMatchers("/rest/**").authenticated()
                                .antMatchers("/api/**").permitAll()
				//.antMatchers("/admin/generate/**").permitAll()
				//.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
				.antMatchers("/admin/**").authenticated()
				.antMatchers("/js/**").permitAll()
				//.anyRequest().authenticated()
				.and()
				.formLogin().loginPage("/login").permitAll()
                                .successHandler(customSuccessHandler)
				//.defaultSuccessUrl("/admin/welcome_page")
                                .and()
                                .logout().addLogoutHandler(logoutHandler())
                                .and().csrf().disable();
		/*
		 * .and() .addFilterAfter(new CsrfAttributeToCookieFilter(),
		 * CsrfFilter.class) .csrf().csrfTokenRepository(csrfTokenRepository());
		 */
	}
	/*
	 * private CsrfTokenRepository csrfTokenRepository() {
	 * HttpSessionCsrfTokenRepository repository = new
	 * HttpSessionCsrfTokenRepository();
	 * repository.setHeaderName("X-XSRF-TOKEN"); return repository; }
	 */
}
