package com.example.staystrongtoday.presentation;

import com.example.staystrongtoday.application.TodayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodayController {
    private TodayService todayservice;

    @Autowired//의존성을 주입받음.
    TodayController(TodayService todayservice){
        this.todayservice=todayservice;
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to the Stay Strong Today website!";
    }

    // 버튼 클릭 시 응원 메시지 조회
    @GetMapping("/encouragements")
    public List<String> getEncouragements() {
        return todayservice.getEncouragements();
    }

}
