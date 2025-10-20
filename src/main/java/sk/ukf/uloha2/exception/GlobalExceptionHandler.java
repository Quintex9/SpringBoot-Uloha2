package sk.ukf.uloha2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sk.ukf.uloha2.dto.ApiResponse;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ResponseEntity<ApiResponse<List<String>>> handleValidation(MethodArgumentNotValidException ex){
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.add(error.getDefaultMessage()));

        ApiResponse<List<String>> response = new ApiResponse<>(errors, "Validačné chyby");

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGeneral(Exception ex){
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("Nastala chyba: " + ex.getMessage()));

    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleObjectNotFound(ObjectNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> handleEmailExists(EmailAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResponse.error(ex.getMessage()));
    }
}
