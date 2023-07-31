class PlaceHold{
public void startTestSuite(JUnitTest suite) {
    doc = getDocumentBuilder().newDocument();
    rootElement = doc.createElement(TESTSUITE);
    rootElement.setAttribute(ATTR_NAME, suite.getName());
    Element propsElement = doc.createElement(PROPERTIES);
    rootElement.appendChild(propsElement);
    Properties props = suite.getProperties();
    if (props != null) {
        Enumeration e = props.propertyNames();
        while (e.hasMoreElements()) {
            String name = ((String) (e.nextElement()));
            Element propElement = doc.createElement(PROPERTY);
            propElement.setAttribute(ATTR_NAME, name);
            propElement.setAttribute(ATTR_VALUE, props.getProperty(name));
            propsElement.appendChild(propElement);
        } 
    }
}
}