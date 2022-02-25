package com.citynow.filter;


import com.citynow.constant.Constant;
import com.citynow.model.PostModel;
import com.citynow.model.UserModel;
import com.citynow.service.IPostService;
import com.citynow.service.impl.PostServiceImpl;
import com.citynow.utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter before access any page
 */
@WebFilter({"/*"})
public class Authentication implements Filter {

    IPostService postService = new PostServiceImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        UserModel model = (UserModel) SessionUtil.getInstance().getValue(request, "LOGIN");

        if (url.startsWith("/admin")) {
            // Filter account admin
            if (model != null) {
                if (model.getRole().getCode().equals("ADMIN")) {
                    // ADMIN accept to access
                    chain.doFilter(servletRequest, servletResponse);
                } else if (model.getRole().getCode().equals("USER")) {
                    // USER not accept
                    response.sendRedirect(request.getContextPath() + "/login?message=not_permission");
                }
            } else {
                // NOT LOGIN redirect to login page
                response.sendRedirect(request.getContextPath() + "/login?message=not_login");
            }
        } else if (url.startsWith("/list-posts") || url.startsWith("/change-password")) {
            // CHECK LOGIN
            if (model != null) {
                // LOGIN accept to access
                chain.doFilter(servletRequest, servletResponse);
            } else {
                // NOT LOGIN redirect to login page
                response.sendRedirect(request.getContextPath() + "/login?message=not_login");
            }
        } else if (url.startsWith("/profile")) {
            // Check login
            if (model != null) {
                try {
                    // LOGIN
                    Long id = Long.parseLong(request.getParameter("id"));
                    if (model.getId().equals(id) || model.getRole().getCode().equals("ADMIN")) {
                        // CHECK INFOR USER -> TRUE accept to access
                        chain.doFilter(servletRequest, servletResponse);
                    } else {
                        // CHECK INFOR USER -> FALSE redirect to login page
                        response.sendRedirect(request.getContextPath() + "/login?message=not_permission");
                    }
                } catch (Exception e) {
                    response.sendRedirect(request.getContextPath() + "/home");
                }
            } else {
                // NOT LOGIN redirect to login page
                response.sendRedirect(request.getContextPath() + "/login?message=not_login");
            }
        } else if (url.startsWith("/post")) {
            // Check login
            if (model != null && model.getStatus() != Constant.USER_BLOCK_STATUS) {
                try {
                    Long id = Long.parseLong(request.getParameter("id"));
                    Long idUserPost = postService.findOne(id).getUser().getId();

                    if (model.getId().equals(idUserPost)) {
                        // CHECK INFOR USER -> TRUE accept to access
                        chain.doFilter(servletRequest, servletResponse);
                    } else {
                        // CHECK INFOR USER -> FALSE redirect to login page
                        response.sendRedirect(request.getContextPath() + "/login?message=not_permission");
                    }
                } catch (Exception e) {
                    chain.doFilter(servletRequest, servletResponse);
                }
            }else if (model != null && model.getStatus() == Constant.USER_BLOCK_STATUS) {
                response.sendRedirect(request.getContextPath() + "/home?message=account-block");
            } else {
                // NOT LOGIN redirect to login page
                response.sendRedirect(request.getContextPath() + "/login?message=not_login");
            }
        } else if (url.startsWith("/detail-post")) {
            try {
                Long id = Long.parseLong(request.getParameter("id"));
                PostModel postModel = postService.findOne(id);

                if (postModel.getStatus() == Constant.POST_APPROVE_STATUS || model.getRole().getCode().equals("ADMIN")) {
                    // CHECK INFOR USER -> TRUE accept to access
                    chain.doFilter(servletRequest, servletResponse);
                } else {
                    // CHECK INFOR USER -> FALSE redirect to login page
                    response.sendRedirect(request.getContextPath() + "/home");
                }
            } catch (Exception e) {
            }
        } else {
            // Accept to access without login
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
