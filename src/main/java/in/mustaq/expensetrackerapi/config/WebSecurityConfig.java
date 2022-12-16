package in.mustaq.expensetrackerapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import in.mustaq.expensetrackerapi.security.JwtRequestFilter;
import in.mustaq.expensetrackerapi.service.CustomUserDetialsService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetialsService customUserDetialsService;

	@Bean
	public JwtRequestFilter authenticationJwtTokenFilter() {
		return new JwtRequestFilter();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/*
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/login","/register").permitAll()
		.anyRequest().authenticated()
		.and()
		.httpBasic();
		*/
	// Managing session making session stateless(do not maintain any session), we using other mechanism to validating users.
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/loginn","/register","/")
			.permitAll()
			.anyRequest().authenticated()
			.and().formLogin()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		http.httpBasic();
		
		
	}

	// First approach
	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * 
	 * auth.inMemoryAuthentication()
	 * .withUser("admin").password("12345").authorities("admin") .and()
	 * .withUser("user").password("12345").authorities("user") .and()
	 * .passwordEncoder(NoOpPasswordEncoder.getInstance());
	 * 
	 * }
	 */

	// Second approach
	/*
	 * 
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * 
	 * InMemoryUserDetailsManager userDetailsManager = new
	 * InMemoryUserDetailsManager();
	 * 
	 * UserDetails user1 =
	 * User.withUsername("mustaq").password("12345").authorities("admin").build();
	 * UserDetails user2 =
	 * User.withUsername("chandu").password("12345").authorities("user").build();
	 * 
	 * userDetailsManager.createUser(user1); userDetailsManager.createUser(user2);
	 * 
	 * auth.userDetailsService(userDetailsManager); }
	 * 
	 * 
	 * @Bean public PasswordEncoder passwordEncoder() { return
	 * NoOpPasswordEncoder.getInstance(); }
	 */

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetialsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
