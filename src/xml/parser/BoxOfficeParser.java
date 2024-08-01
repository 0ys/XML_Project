package xml.parser;

import java.io.InputStream;
import java.util.List;

import xml.dto.BoxOffice;

public interface BoxOfficeParser {

    public abstract List<BoxOffice> getBoxOffice(InputStream resource) throws Exception;
}
