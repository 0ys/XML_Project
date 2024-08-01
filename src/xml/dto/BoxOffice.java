package xml.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// 자바 16부터 record 클래스를 사용하여 DTO를 구현할 수 있음
// 데이터를 보관하는데 사용되는 불변 객체를 간단하게 정의 가능
// - 불변성 : 객체는 모두 final, 객체 생성 시 정의되며 이후는 변경 불가
// - 간결성 : 변수 선언 외에 필요한 모든 코드는 컴파일 시점에 자동 생성
// - 상속 불가

// 객체 생성 : BoxOffice info = new BoxOffice(rank, movieNm, openDt, audiAcc);
// 객체 활용 : info.rank(), info.movieNm, info.toDate(), info.audiAcc() 처럼 변수 이름으로 getter, setter 사용

@JsonIgnoreProperties(ignoreUnknown = true) // json 파싱에 필요함
public record BoxOffice(Integer rank, String movieNm, String openDt, Integer audiAcc) {
    // 부가적인 로직 추가 가능!
    public Date toDate() {
        Date dateObj = null;
        // 문자열 형태의 날짜를 Date로 변환해서 반환하시오.
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
            dateObj = formatter.parse(openDt);
        } catch (ParseException e) {
            e.printStackTrace();
            dateObj = new Date();
        }
        return dateObj;
    }

    @Override
    public String toString() {
        return "BoxOffice [순위=" + rank + ", 제목=" + movieNm + ", 관객수=" + audiAcc + ", 개봉일=" + openDt + "]";
    }
    
    
}
