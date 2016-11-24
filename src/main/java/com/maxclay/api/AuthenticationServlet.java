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
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

/**
 * @author maxclay
 */
public class AuthenticationServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        User user;
        UserService userService = new UserServiceImpl(new UserDaoImpl());
        try {

            user = retrieveUser(request);
            //TODO display error in case of creating user with existing email
            user = userService.register(user);
        } catch (Exception ex) {

            HttpSession session = request.getSession(false);
            session.setAttribute("error", "Errors occurred while registering user");
            response.sendRedirect("signup");
            return;
        }

        PrintWriter out = response.getWriter();
        out.println(user);
    }

    /**
     * Retrieves user's registration data from request's parameters and creates corresponding instance of
     * {@link com.maxclay.model.User} class.
     *
     * @param request {@link javax.servlet.http.HttpServletRequest} which contains user's registration data as
     *                parameters
     * @return instance of {@link com.maxclay.model.User} class which corresponds request's registration data
     * @throws ValidationException      thrown in case of corrupted or empty request's registration data parameters.
     * @throws NoSuchAlgorithmException thrown when a particular cryptographic algorithm is requested but is not
     *                                  available in the environment. Occurs while hashing user's password.
     */
    private User retrieveUser(HttpServletRequest request) throws ValidationException, NoSuchAlgorithmException {

        String userName = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeat_password");

        // TODO prevent SQL injections
        HttpSession session = request.getSession(false);
        validateFormFields(session, userName, email, password, repeatPassword);

        User user = new User();
        user.setEmail(email);
        user.setName(userName);
        String hashedPassword = SecureHashAlgorithm.hash(password);
        user.setPassword(hashedPassword);

        return user;
    }

    /**
     * Validates given form fields.
     *
     * @param session        required for storing errors attributes
     * @param userName       string representation of user's name
     * @param email          string representation of user's email
     * @param password       string representation of user's password
     * @param repeatPassword string representation of user's password, must be equals to the first one. Used for
     *                       avoiding typing mistakes while registration
     * @throws ValidationException thrown in case of corrupted or empty request's registration data parameters.
     */
    private void validateFormFields(HttpSession session,
                                    String userName,
                                    String email,
                                    String password,
                                    String repeatPassword) throws ValidationException {

        boolean isValid = true;
        if (userName == null || userName.isEmpty()) {

            session.setAttribute("userNameError", "Username is empty");
            isValid = false;
        }

        if (email == null || email.isEmpty()) {

            session.setAttribute("emailError", "Email is empty");
            isValid = false;
        }

        if (password == null || password.isEmpty()) {

            session.setAttribute("passwordError", "Password is empty");
            isValid = false;
        }

        if (repeatPassword == null || repeatPassword.isEmpty()) {

            session.setAttribute("repeatPasswordError", "Please repeat password");
            isValid = false;
        }

        if (password != null && repeatPassword != null && !repeatPassword.equals(password)) {

            session.setAttribute("repeatPasswordError", "Passwords don't match");
            isValid = false;
        }

        if (!isValid) {
            throw new ValidationException("Form parameters are invalid");
        }
    }
}
