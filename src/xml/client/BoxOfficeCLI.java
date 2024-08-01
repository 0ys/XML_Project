package xml.client;

import xml.dto.BoxOffice;
import xml.parser.BoxOfficeDomParser;
import xml.parser.BoxOfficeJsonParser;
import xml.parser.BoxOfficeParser;
import xml.parser.BoxOfficeSaxParser;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public class BoxOfficeCLI {
    private BoxOfficeParser parser = null;
    private InputStream resource = null;

    public BoxOfficeCLI() {
        this.resource = BoxOfficeCLI.class.getResourceAsStream("../res/boxoffice.xml"); // 내부 리소스
        //this.resource = new URL("\thttp://kobis.or.kr/kobisopenapi/webservice/rest/...&targetDt=20240801").openStream(); // 외부 리소스
    }

    public Optional<List<BoxOffice>> readBoxOfficeList(char type) throws Exception {
        // resource와 parser를 구성해서 정보를 가져와보자.
        if(type == 'S'){ // SAXParser
            this.parser = BoxOfficeSaxParser.getParser();
        } else if(type == 'D'){ // DOMParser
            this.parser = BoxOfficeDomParser.getParser();
        } else if(type == 'J'){ // DOMParser
            this.resource = BoxOfficeCLI.class.getResourceAsStream("../res/boxoffice.json"); // json파일
            this.parser = BoxOfficeJsonParser.getParser();
        }

        return Optional.ofNullable(this.parser.getBoxOffice(resource));

    }

    public static void main(String[] args) {
        BoxOfficeCLI cli = new BoxOfficeCLI();
        try {
            Optional<List<BoxOffice>> result = cli.readBoxOfficeList('J');
            result.ifPresentOrElse(list -> list.forEach(System.out::println), () -> System.out.println("unknown type"));
        } catch (Exception e) {
            System.out.println("오류 발생!: " + e.getMessage());
        }
    }
}
