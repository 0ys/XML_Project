package xml.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public record BoxOffice(Integer rank, String movieNm, String openDt, Integer audiAcc) {

    public Date toDate() {
        Date dateObj = null;
        // TODO: 문자열 형태의 날짜를 Date로 변환해서 반환하시오.

        // END
        return dateObj;
    }

    @Override
    public String toString() {
        return "BoxOffice [순위=" + rank + ", 제목=" + movieNm + ", 관객수=" + audiAcc + ", 개봉일=" + openDt + "]";
    }
    
    
}
