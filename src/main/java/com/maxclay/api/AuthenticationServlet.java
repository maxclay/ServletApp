package com.maxclay.api;

import com.maxclay.dao.impl.UserDaoImpl;
import com.maxclay.exception.ValidationException;
import com.maxclay.model.User;
import com.maxclay.service.UserService;
import com.maxclay.service.impl.UserServiceImpl;
import com.maxclay.util.SecureHashAlgorithm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.AccessControlException;

/**
 * @author maxclay
 */
public class AuthenticationServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user;
        UserService userService = new UserServiceImpl(new UserDaoImpl());
        HttpSession session = request.getSession(true);
        try {

            validateFormFields(session, email, password);
            user = userService.getByEmail(email);

            String hashedProvidedPassword = SecureHashAlgorithm.hash(password);
            if (!hashedProvidedPassword.equals(user.getPassword())) {
                throw new AccessControlException("Wrong password");
            }

        } catch (Exception ex) {

            session.setAttribute("error", "Errors occurred while authenticating user");
            response.sendRedirect("login");
            return;
        }

        session.setAttribute("username", user.getName());
        response.sendRedirect("vendors");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(true);
        session.removeAttribute("username");
        response.sendRedirect("login");
    }

    /**
     * Validates given form fields.
     *
     * @param session  required for storing errors attributes
     * @param email    string representation of user's email
     * @param password string representation of user's password
     *                 avoiding typing mistakes while registration
     * @throws ValidationException thrown in case of corrupted or empty request's registration data parameters.
     */
    private void validateFormFields(HttpSession session,
                                    String email,
                                    String password) throws ValidationException {

        boolean isValid = true;

        if (email == null || email.isEmpty()) {

            session.setAttribute("emailError", "Email is empty");
            isValid = false;
        }

        if (password == null || password.isEmpty()) {

            session.setAttribute("passwordError", "Password is empty");
            isValid = false;
        }

        if (!isValid) {
            throw new ValidationException("Form parameters are invalid");
        }
    }
}
