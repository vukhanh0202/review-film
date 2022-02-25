package com.citynow.api.user;

import com.citynow.constant.Constant;
import com.citynow.model.UserModel;
import com.citynow.service.IRoleService;
import com.citynow.service.IUserService;
import com.citynow.service.impl.RoleServiceImpl;
import com.citynow.service.impl.UserServiceImpl;
import com.citynow.utils.ConvertUtil;
import com.citynow.utils.SessionUtil;
import com.citynow.utils.ValidateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;

/**
 * Represent API user
 * @author VuKhanh
 */
@WebServlet(urlPatterns = {"/api-user"})
public class UserAPI extends HttpServlet {

    IUserService userService = new UserServiceImpl();

    IRoleService roleService = new RoleServiceImpl();

    /**
     * Create a new user
     * @param req
     * @param resp
     * @return Information user after created
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        // Get data json form ajax request -> mapping to user model
        UserModel userModel = mapper.readValue(ConvertUtil.convertJsonToString(req.getReader()), UserModel.class);
        // Set role default for a new account is USER
        userModel.setRole(roleService.findOne(Constant.ROLE_USER));
        // Set value default for a new account
        userModel.setAvatar("");
        userModel.setStatus(Constant.USER_ACTIVE_STATUS);
        userModel.setQuantityUpvote(0L);
        userModel.setQuantityPost(0L);
        userModel.setDateOfBirth(new Date(System.currentTimeMillis()));
        userModel.setPhone("");

        // Validate password
        boolean regexPassword = ValidateUtil.validate("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*[!@#$&*]).{8,}$",
                userModel.getPassword());
        if (regexPassword) {
            userModel.setPassword(BCrypt.hashpw(userModel.getPassword(), BCrypt.gensalt()));
        } else {
            userModel.setPassword(null);
        }

        // Validate username
        boolean regexUsername = ValidateUtil.validate("^[a-z0-9]+([_-]?[a-z0-9]?)*$", userModel.getUsername());
        if (!regexUsername) {
            userModel.setUsername(null);
        }

        // Validate email
        boolean regexEmail = ValidateUtil.validate("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
                userModel.getEmail());
        if (!regexEmail) {
            userModel.setEmail(null);
        }
        if (userService.fineOneByUsername(userModel.getUsername()) !=null)
        {
            mapper.writeValue(resp.getOutputStream(), "{}");
        }else{
            mapper.writeValue(resp.getOutputStream(), userService.save(userModel));
        }
    }

    /**
     * Update information user
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "LOGIN");
        ObjectMapper mapper = new ObjectMapper();

        req.setCharacterEncoding("UTF-8");

        // Get data json form ajax request
        String json = ConvertUtil.convertJsonToString(req.getReader());
        JSONObject dataJson = new JSONObject(json);
        String oldPassword = null;
        Long id = -1L;
        try {
            // Get password from data json
            oldPassword = dataJson.getString("oldpassword");
        } catch (Exception e) {
        }
        try {
            id = dataJson.getLong("id");
        } catch (Exception e) {
        }
        UserModel userModel;

        // Check if old password not empty -> do change password
        if (oldPassword != null) {
            userModel = (UserModel) SessionUtil.getInstance().getValue(req, "LOGIN");
            if (userModel != null && BCrypt.checkpw(oldPassword, userModel.getPassword())) {
                String newPassword = dataJson.getString("password");
                boolean matchFound = ValidateUtil.validate("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*[!@#$&*]).{8,}$", newPassword);
                if (matchFound) {
                    userModel.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
                    mapper.writeValue(resp.getOutputStream(), userService.update(userModel));
                } else {
                    mapper.writeValue(resp.getOutputStream(), "{}");
                }
            }
        } else if (id.equals(model.getId())) {
            // if old password empty and user is login match account need change -> Accept change info user
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json");
            // Get data json form ajax request -> mapping to user model
            userModel = mapper.readValue(json, UserModel.class);
            UserModel oldUser = userService.findOne(userModel.getId());
            userModel.setValue(oldUser);

            // Validate email
            boolean regexEmail = ValidateUtil.validate("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
                    userModel.getEmail());
            if (!regexEmail) {
                userModel.setEmail(null);
            }

            // Validate phone
            boolean regexPhone = ValidateUtil.validate("^\\+?\\d{1,3}?[- .]?\\(?(?:\\d{2,3})\\)?[- .]?\\d\\d\\d[- .]?\\d\\d\\d\\d$",
                    userModel.getPhone());
            if (!regexPhone && userModel.getPhone() != "") {
                userModel.setEmail(null);
            }
            userModel = userService.update(userModel);
            SessionUtil.getInstance().putValue(req, "LOGIN", userModel);
            mapper.writeValue(resp.getOutputStream(), userModel);
        } else {
            // if old password empty and user is login match account need change -> do nothing
            mapper.writeValue(resp.getOutputStream(), "{}");
        }
    }
}
