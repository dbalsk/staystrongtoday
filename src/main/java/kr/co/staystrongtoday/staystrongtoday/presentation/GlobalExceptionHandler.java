package kr.co.staystrongtoday.staystrongtoday.presentation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    //도메인 객체에서 유효성 검사 실패 시 예외처리핸들러
    public ResponseEntity<String> handleConstraintViolatedException(ConstraintViolationException ex){
        //1안 - 여러 필드에서의 예외를 컬렉션(set, list)으로 관리
        //한 필드에서 한가지 오류를 보여줄 때(중복을 피하기) Set사용(중복허용x)   ex) @NotBlank와 @Size 검증이 동시에 실패 -> 하나만 허용
/*        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        List<String> errors = constraintViolations.stream() //여러 예외를 리스트로 변환
                .map(error -> error.getMessage())
                .toList();
        ErrorMessage errorMessage = new ErrorMessage(errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);*/

        //2안 - 한 가지 예외만 문자열로 출력
        String errorMessage = ex.getConstraintViolations().stream()
                .map(error -> error.getMessage())
                .findFirst()
                .orElse("잘못된 요청입니다."); // 예외가 없을 경우 기본 메시지
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    //컨트롤러에서 유효성 검사 실패 시 예외처리핸들러
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        //1안 - 여러 필드에서의 예외를 컬렉션(set, list)으로 관리
        //한 필드에 대해 여러 오류를 보여줄 때 List사용(중복허용)   ex) @NotBlank와 @Size 검증이 동시에 실패 -> 별개의 오류 메시지로 출력
/*        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors(); //FieldError객체 -> 폼 기반 입력에서 발생한 유효성 검사를 필드별로 처리할 때 사용
        List<String> errors = fieldErrors.stream() //여러 예외를 리스트로 변환
                .map(fieldError -> fieldError.getDefaultMessage())
                .toList();
        ErrorMessage errorMessage = new ErrorMessage(errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);*/

        //2안 - 한 가지 예외만 문자열로 출력
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .findFirst()
                .orElse("잘못된 요청입니다."); // 예외가 없을 경우 기본 메시지
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex){
        //IllegalArgumentException는 단일 예외만 포함 (보통 직접 throw하여 예외발생)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
