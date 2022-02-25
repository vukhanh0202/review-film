package com.citynow.api.admin;

import com.citynow.model.PostModel;
import com.citynow.model.UserModel;
import com.citynow.service.IUserService;
import com.citynow.service.impl.UserServiceImpl;
import com.citynow.utils.ConvertUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Represent API user under control of ADMIN
 * @author VuKhanh
 */
@WebServlet(urlPatterns = {"/admin-api-user"})
public class UserAPI extends HttpServlet {

    IUserService userService = new UserServiceImpl();

    /**
     * Change status of user
     * @param req
     * @param resp
     * @return Information user after change (JSON)
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();

        // Get json from ajax request
        String json = ConvertUtil.convertJsonToString(req.getReader());
        JSONObject dataJson = new JSONObject(json);

        Long idUser = dataJson.getLong("user_id");
        int status = dataJson.getInt("status");

        // Find user from database
        UserModel userModel = userService.findOne(idUser);
        userModel.setStatus(status);

        // Update status user to database
        mapper.writeValue(resp.getOutputStream(), userService.update(userModel));

    }
}
