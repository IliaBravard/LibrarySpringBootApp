package library.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import library.services.RegisteredUserDetailsService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@SuppressWarnings("unused")
	@Autowired
	private DataSource source;

	/**
	 * This method creates a new instance of the user details service class.
	 * 
	 * @return the user details service instance
	 */
	@Bean
	public RegisteredUserDetailsService userDetails() {
		return new RegisteredUserDetailsService();
	}

	/**
	 * This method creates a new instance of the password encoder class.
	 * 
	 * @return the password encoder instance
	 */
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * This method creates a new instance of the authentication provider class.
	 * 
	 * @return the DAO authentication provider instance
	 */
	@Bean
	public DaoAuthenticationProvider provider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetails());
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}

	/**
	 * This method configures the authentication provider.
	 * 
	 * @param auth - the provider to be configured
	 * @throws Exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(provider());
	}

	/**
	 * This method configures the HTTP security.
	 * 
	 * @param http - the HTTP security to be modified
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().permitAll().and().formLogin().permitAll().loginPage("/login")
				.usernameParameter("email").defaultSuccessUrl("/loginSuccessful").permitAll().and().logout()
				.logoutUrl("/logout").permitAll();
	}
}