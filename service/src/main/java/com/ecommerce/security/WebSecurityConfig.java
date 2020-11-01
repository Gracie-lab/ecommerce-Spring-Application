package com.ecommerce.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserPrincipalService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationFilter();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/apiV1/user/**", "/", "/api").permitAll()
                .antMatchers(("/api/applicant/apply")).permitAll()
                .antMatchers("/api/data/**").permitAll()
                .antMatchers("/api/data/get/*").permitAll()
                .antMatchers("/api/data/add/*").permitAll()
                .antMatchers("/api/interviewInvite/accept/*").permitAll()
                .antMatchers("/api/infoSession/selfInvite/**").permitAll()
                .antMatchers("/api/partner/*").permitAll()
                .antMatchers("/api/infoSession/attend/*").permitAll()
                .antMatchers("/api/admin/applicant/**").permitAll()
                .antMatchers("/api/admin/changeApplicantEmail/**").permitAll()
                .antMatchers("/api/interviewInvite/reschedule/**").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/api/applicant/acceptAdmissionOffer/*").permitAll()
                .antMatchers("/api/applicant/declineAdmissionOffer/*").permitAll()
                .antMatchers("/api/infoSession/getAvailableInfoSessionsSelfInvite").permitAll()
                .antMatchers("/api/elder/confirmPayment/**").permitAll()
                .antMatchers("/api/admin/setActiveCohort/**").permitAll()
                .antMatchers("/api/elder/declinePayment/**").permitAll()
                .antMatchers("/api/admin/sendLoanInformation/**").permitAll()
                .antMatchers("/api/admin/sendInfoSessionEmail/**").permitAll()
                .antMatchers("/api/admin/sendInfoSessionEmail").permitAll()
                .antMatchers("/api/admin/setMostRecentlyCreatedCohortID/**").permitAll()
                .antMatchers("/api/admin/sendMailBlastToList/**").permitAll()
                .antMatchers("/api/admin/updateApplicantPhoneNumber/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Primary
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
