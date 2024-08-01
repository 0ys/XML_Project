package xml.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import xml.dto.BoxOffice;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

// DOM Parser : Document Object Model parser
// 문서를 다 읽고 난 후(메모리에 올림) 문서 구조 전체를 자료구조에 저장하여 탐색하는 방식임
// 다양한 탐색이 가능하지만 느리고 무거우며 큰 문서를 처리하기 어려움

// DocumentBuilderFactory로 DocumentBuilder를 생성하여 DOM Tree를 구성함
// DOM Tree는 문서를 구성하는 모든 요소를 Node(태그, 속성, 값)으로 구성하며,
// 태그들은 root 노드(주소록)을 시작으로 부모-자식의 관계를 구성함
public class BoxOfficeDomParser implements BoxOfficeParser {

    private static BoxOfficeDomParser parser = new BoxOfficeDomParser();

    public static BoxOfficeDomParser getParser() {
        return parser;
    }

    private BoxOfficeDomParser() {
        System.out.println("DOMParser");
    }

    private List<BoxOffice> list;

    @Override
    public List<BoxOffice> getBoxOffice(InputStream resource) throws ParserConfigurationException, SAXException, IOException {
        list = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        // 문서 로딩 완료 --> 원하는 요소들 골라내기
        Document doc = builder.parse(resource);
        // 최 상위 element
        Element root = doc.getDocumentElement();
        parse(root);
        return list;
    }

    private void parse(Element root) {
        // root에서 dailyBoxOffice를 추출한 후 BoxOffice를 생성해 list에 저장
        NodeList list = root.getElementsByTagName("dailyBoxOffice");
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            this.list.add(getBoxOffice(node));
        }
    }

    private static BoxOffice getBoxOffice(Node node) {
        // node 정보를 이용해서 BoxOffice를 구성하고 반환
        NodeList list = node.getChildNodes();
        int rank = -1, audiAcc = -1;
        String movieNm = null, openDt = null;
        for (int i = 0; i < list.getLength(); i++) {
            Node child = list.item(i);
            String name = child.getNodeName();
            String value = child.getTextContent();

            if (name.equals("rank")) {
                rank = Integer.parseInt(value);
            } else if (name.equals("movieNm")) {
                movieNm = value;
            } else if (name.equals("openDt")) {
                openDt = value;
            } else if (name.equals("audiAcc")) {
                audiAcc = Integer.parseInt(value);
            }
        }
         return new BoxOffice(rank, movieNm, openDt, audiAcc);
    }
}
