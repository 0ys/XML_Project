package xml.client;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import com.ssafy.day11.dto.BoxOffice;
import com.ssafy.day11.parser.BoxOfficeDomParser;
import com.ssafy.day11.parser.BoxOfficeJsonParser;
import com.ssafy.day11.parser.BoxOfficeParser;
import com.ssafy.day11.parser.BoxOfficeSaxParser;

public class BoxOfficeCLI {
    private BoxOfficeParser parser = null;
    private InputStream resource = null;

    public BoxOfficeCLI() {

    }

    public Optional<List<BoxOffice>> readBoxOfficeList(char type) throws Exception {
        // TODO: resource와 parser를 구성해서 정보를 가져와보자.

        // END

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
