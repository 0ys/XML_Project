package xml.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import xml.dto.BoxOffice;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

// SAX parser : Simple API for XML parser
// 문서를 읽으면서 태그의 시작, 종료 등 이벤트 기반으로 처리하는 방식임
// 빠르고 한번에 처리하기 때문에 다양한 탐색이 어려움

// SAXParserFactory로 생성한 SAXParser객체를 이용해서 XML 문서를 파싱함
// DefaultHandler를 상속받아 재정의하는 MyHandler(지금 이 코드)를 이벤트 발생 시 호출함
public class BoxOfficeSaxParser extends DefaultHandler implements BoxOfficeParser {
    // singleton
    private static BoxOfficeParser parser = new BoxOfficeSaxParser();
    private BoxOfficeSaxParser() {
        System.out.println("SAXParser");
    }
    public static BoxOfficeParser getParser() {
        return parser;
    }

    // 파싱된 내용을 저장할 List
    private List<BoxOffice> list;
    // 지금 처리할 객체의 내용
    int rank;
    String movieNm;
    String openDt;
    int audiAcc;

    // 방금 읽은 텍스트 내용
    private String content;

    @Override
    // getBoxOffice를 재정의하여 SAXParser를 구성하고 boxoffice.xml을 파싱하시오.
    public List<BoxOffice> getBoxOffice(InputStream resource) throws ParserConfigurationException, SAXException, IOException {
        list = new ArrayList<BoxOffice>();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(resource, this); // parsing 과정에서 데이터를 구성하고 list에 담아 반환함

        return list;
    }

    // 필요한 매서드를 재정의 하여 boxOffice.xml을 파싱
    @Override
    public void startDocument() throws SAXException {
        //System.out.println("문서 시작함");
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //System.out.println("태그 시작함 : " + qName);
    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch, start, length);
        this.content = str;
    }
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals("rank")) {
            this.rank = Integer.parseInt(this.content);
        } else if(qName.equals("movieNm")) {
            this.movieNm = this.content;
        } else if(qName.equals("openDt")) {
            this.openDt = this.content;
        } else if(qName.equals("audiAcc")) {
            this.audiAcc = Integer.parseInt(this.content);
        } else if(qName.equals("dailyBoxOffice")) {
            list.add(new BoxOffice(rank, movieNm, openDt, audiAcc));
        }
    }
}
