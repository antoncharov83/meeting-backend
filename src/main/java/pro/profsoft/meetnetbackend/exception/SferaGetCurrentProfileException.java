package pro.profsoft.meetnetbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class SferaGetCurrentProfileException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SferaGetCurrentProfileException(String message) {
        super(message);
    }
}
