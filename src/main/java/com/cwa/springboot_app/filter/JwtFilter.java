package com.cwa.springboot_app.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cwa.springboot_app.configuration.JwtUtils;
import com.cwa.springboot_app.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter{

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{
           final String authHeader = request.getHeader("Authorization");
           String username =null;
           String jwt=null;
           if(authHeader!=null  && authHeader.startsWith("Bearer ")){
                jwt=authHeader.substring(7);
                username=jwtUtils.extractUsername(jwt);
           }
           if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
             UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
             
             if(jwtUtils.validateToken(jwt, userDetails)){
                UsernamePasswordAuthenticationToken authentificationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentificationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentificationToken);
             }
          }
          filterChain.doFilter(request, response);
    }

}
