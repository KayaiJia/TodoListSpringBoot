package myssm.ioc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/21 16:55
 */
public class XmlClassPathApplicationContext implements BeanFactory {
    private Map<String, Object> beanMap = new HashMap<>();
    private String path = "applicationContext.xml";

    public XmlClassPathApplicationContext(){
        this("applicationContext.xml");
    }
    public XmlClassPathApplicationContext(String path) {
        if ("".equals(path) || path.isEmpty()){
            throw new RuntimeException("没有配置文件");
        }
        InputStream is = null;
        try {
            is = getClass().getClassLoader().getResourceAsStream(path);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(is);
            NodeList beanNodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element beanElement = (Element) beanNode;
                    String beanID = beanElement.getAttribute("id");
                    String beanClass = beanElement.getAttribute("class");
                    Object beanObj = Class.forName(beanClass).getDeclaredConstructor().newInstance();
                    beanMap.put(beanID, beanObj);
                }
            }
            for (int i=0;i< beanNodeList.getLength();i++){
                Node beanNode = beanNodeList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element beanElement = (Element) beanNode;
                    String beanID = beanElement.getAttribute("id");
                    NodeList beanChildNodes = beanElement.getChildNodes();
                    for(int j=0;j<beanChildNodes.getLength();j++){
                        Node beanChildNode = beanChildNodes.item(j);
                        if (beanChildNode.getNodeType() == Node.ELEMENT_NODE && "property".equals(beanChildNode.getNodeName())){
                            Element propertyElement = (Element) beanChildNode;
                            String propertyName = propertyElement.getAttribute("name");
                            String propertyRef = propertyElement.getAttribute("ref");
                            Object refObj = beanMap.get(propertyRef);
                            Object beanObj = beanMap.get(beanID);
                            Field propertyField = beanObj.getClass().getDeclaredField(propertyName);
                            propertyField.setAccessible(true);
                            propertyField.set(beanObj,refObj);
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 通过id获取bean的class
     *
     * @param id : 要获取的id
     * @return java.lang.Object
     * @author kayai
     * @date 2022/4/21 16:54
     */
    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
