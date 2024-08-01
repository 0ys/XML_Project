package xml.parser;

import java.io.InputStream;
import java.util.List;

import com.ssafy.day11.dto.BoxOffice;

public interface BoxOfficeParser {

    public abstract List<BoxOffice> getBoxOffice(InputStream resource) throws Exception;
}
