package idigitallegacy.root.application.exceptions;

import org.springframework.http.HttpStatus;

public class NotAuthorizedException extends HttpException {
    public NotAuthorizedException(String message){
        super(message);
        this.statusCode = HttpStatus.UNAUTHORIZED;
    }

    public NotAuthorizedException(){
        super("Unauthorized");
        setStatusCode(HttpStatus.UNAUTHORIZED);
    }
}