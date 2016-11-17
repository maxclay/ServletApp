package com.maxclay.api;

import com.maxclay.dao.impl.UserDaoImpl;
import com.maxclay.model.User;
import com.maxclay.service.UserService;
import com.maxclay.service.impl.UserServiceImpl;
import com.maxclay.util.SecureHashAlgorithm;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import static com.maxclay.util.HttpStatus.BAD_REQUEST;
import static com.maxclay.util.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * @author maxclay
 */
public class AuthenticationServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String userName = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String hashedPassword = null;

        try {
            hashedPassword = SecureHashAlgorithm.hash(password);
        } catch (NoSuchAlgorithmException ex) {

            System.err.println("Can't perform password hashing: " + ex);
            response.setStatus(INTERNAL_SERVER_ERROR);
        }

        User user = new User();
        user.setEmail(email);
        user.setName(userName);
        user.setPassword(hashedPassword);

        UserService userService = new UserServiceImpl(new UserDaoImpl());

        try {
            user = userService.register(user);
        } catch (IllegalArgumentException ex) {

            System.err.println("Can't register user: " + ex);
            response.setStatus(BAD_REQUEST);
        }

        PrintWriter out = response.getWriter();
        out.println(user);
    }
}
