package xml.parser;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import xml.dto.BoxOffice;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// JSON : Javascript Object Notation
// 간결한 문법, 단순한 텍스트, 적은 용량으로 대부분의 언어, 대부분의 플랫폼에서 사용 가능
// 객체를 key-value 쌍으로 관리함

public class BoxOfficeJsonParser implements BoxOfficeParser {

    private static BoxOfficeJsonParser parser = new BoxOfficeJsonParser();

    public static BoxOfficeJsonParser getParser() {
        return parser;
    }

    private BoxOfficeJsonParser() {
        System.out.println("json");
    }

    private List<BoxOffice> list;

    @Override
    public List<BoxOffice> getBoxOffice(InputStream resource) throws JsonParseException, JsonMappingException, IOException  {
        list = new ArrayList<>();
        // json을 파싱해서 list를 구성하자
        ObjectMapper mapper = new ObjectMapper();
        // 날짜 변경과 관련된 룰 지정
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

        Map<String, Map<String, Object>> result = mapper.readValue(resource, new TypeReference<>() {});
        List<Map<String, Object>> officeList = (List) result.get("boxOfficeResult").get("dailyBoxOfficeList");
        list = officeList.stream().map(info -> mapper.convertValue(info, BoxOffice.class)).collect(Collectors.toList());

        return list;
    }
}
