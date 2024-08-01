package xml.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.day11.dto.BoxOffice;

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
        // TODO: json을 파싱해서 list를 구성하시오.

        // END
        return list;
    }
}
