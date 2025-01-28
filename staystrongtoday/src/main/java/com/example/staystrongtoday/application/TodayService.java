package com.example.staystrongtoday.application;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.CopyOnWriteArrayList;


@Service
public class TodayService{
    // 메모리에서 관리할 리스트 (데이터베이스 연결 없이)
    private final List<String> encouragements = new ArrayList<>();

    public TodayService() {
        // 초기 응원 메시지 추가
        encouragements.add("오늘도 힘내세요! 😊");
        encouragements.add("당신은 할 수 있습니다! 💪");
        encouragements.add("오늘 하루도 멋지게 보내세요! ✨");
    }

    // 응원 메시지 조회
    public List<String> getEncouragements() {
        return encouragements;
    }
}
