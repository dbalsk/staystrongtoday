package kr.co.staystrongtoday.staystrongtoday.presentation;

import java.util.List;

public class ErrorMessage {
    //에러메세지를 더 명확하게 나타내기 위해 직접 클래스 정의하여 사용
    //2025.03.09 현재는 단순 문자열만 반환하도록 구현해야 하기에 사용 x
    private List<String> errors;

    public ErrorMessage(List<String> errors){
        this.errors = errors;
    }

    public List<String> getErrors(){
        return errors;
    }
}
