package com.gapstars.assessment.shoppingcart.filter;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/** Filter Class to add MDC into application for optimizing the application logging */
@Component
public class MDCFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal
            (HttpServletRequest httpServletRequest,
             HttpServletResponse httpServletResponse,
             FilterChain filterChain) throws ServletException, IOException {

        String mdcUuid = ("MDC").concat(UUID.randomUUID().toString().replace("-",""));
        MDC.put("mdc", mdcUuid);
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
