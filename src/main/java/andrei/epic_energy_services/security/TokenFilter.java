package andrei.epic_energy_services.security;


import andrei.epic_energy_services.exceptions.NotFoundException;
import andrei.epic_energy_services.exceptions.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;


@Component
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenTools tokenTools;

    // @Autowired
    // private UsersService usersService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Send an Unauthorized error response.
     */
    private void sendUnauthorizedErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(401);
        response.setContentType("application/json");

        // ErrorsToSendDTO error = new ErrorsToSendDTO(message);
        // response.getWriter().write(objectMapper.writeValueAsString(error));
    }

    /**
     * NOTE:
     * You should take care of standardizing the exceptions thrown at this level
     * (Security Filter Chain) with those thrown at the Controller level.
     * <p>
     * here we are in the Security Filter Chain layer, and
     * we do not have access to the Spring exception catching abstraction.
     * Therefore we need to must build our error responses manually,
     * ideally using the same JSON class, and therefore the same JSON response,
     * that we use inside the exceptions that are thrown in the controllers.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException, UnauthorizedException {

        // verify that header contains access token
        String authHeader = request.getHeader("Authorization");

        boolean authHeaderNotExists = authHeader == null;

        // authorization header does not exist
        if (authHeaderNotExists) {
            this.sendUnauthorizedErrorResponse(response, "Missing Authorization header. The requested resource was defined "
                    + "to require user authentication expressed "
                    + "through the Authorization header in the request, however "
                    + "no such header was found. Therefore this resource cannot be accessed."
                    + "Either this resource should not be protected, "
                    + "or the Authorization header is missing.");
            return;
        }

        boolean tokenNotExists = !authHeader.startsWith("Bearer ");

        // access token does not exist
        if (tokenNotExists) {
            this.sendUnauthorizedErrorResponse(response, "Missing access token in Authorization header. "
                    + "The requested resource was defined to require user authentication expressed "
                    + "through the Authorization header in the request, however "
                    + "the access token was not found in such header. Therefore this resource "
                    + "cannot be accessed."
                    + "Either this resource should not be protected, or the access token is missing."
                    + "Make sure that the access token is in Authorization header with "
                    + "exactly this format 'Bearer mytoken'");
            return;
        }


        // we get the token
        String accessToken = authHeader.replace("Bearer ", "");

        // if token verification fails
        try {
            // verify that token is valid etc.
            // tokenTools.verifyToken(accessToken);

        } catch (UnauthorizedException ex) {
            this.sendUnauthorizedErrorResponse(response, "Access token is expired, malformed or has been tampered with. "
                    + "The user should try to do a new login, and see if that solves the problem.");
            return;
        }


        // ******** AUTHORIZATION ************

        // 1. extract user's ID from token
        // UUID userId = this.tokenTools.extractIdFromToken(accessToken);

        // User currentUser;

        // 2. find user in DB
        try {
            // currentUser = this.usersService.findById(userId);
        } catch (NotFoundException ex) {
            this.sendUnauthorizedErrorResponse(response, "Access token was valid but "
                            + "the user associated to it no longer exists. User ID was: '"
                    // + userId + "'. Maybe the user was deleted?"

            );
            return;
        }

        // 3. now we need to make this user available to the Security Context
        // Authentication authentication = new UsernamePasswordAuthenticationToken(currentUser, null, currentUser.getAuthorities());
        // we now set the current user of this request
        // SecurityContextHolder.getContext().setAuthentication(authentication);

        // System.out.println("do filter internal called!");

        filterChain.doFilter(request, response);

    }


    // we can specify which paths not to filter,
    // for example the paths starting with /auth   

    /**
     * In this method we specify in which cases
     * our security filter should not be called.
     * For example, we might want to disable this security filter
     * for all endpoints starting with /auth
     */
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        AntPathMatcher matcher = new AntPathMatcher();
        String path = request.getServletPath();

        boolean isAuthPath = matcher.match("/auth/**", path);
        boolean isRoot = matcher.match("/", path);
        boolean isComuni = matcher.match("/comuni/**", path);
        boolean isUtenti = matcher.match("/utenti/**", path);


        return isAuthPath || isRoot || isComuni || isUtenti;
    }

}