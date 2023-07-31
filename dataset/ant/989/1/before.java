class PlaceHold {
  protected void process(final URL sourceID, final SAXConfigurationHandler handler)
      throws Exception {
    final SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    final SAXParser saxParser = saxParserFactory.newSAXParser();
    final XMLReader reader = saxParser.getXMLReader();
    reader.setFeature("http://xml.org/sax/features/validation", false);
    reader.setErrorHandler(handler);
    final ReactorPIHandler reactorHandler = new ReactorPIHandler();
    reader.setContentHandler(reactorHandler);
    try {
      reader.parse(sourceID.toString());
    } catch (final StopParsingException spe) {
    }
    Transformer transformer = null;
    final int size = reactorHandler.getPICount();
    for (int i = 0; i < size; i++) {
      final String target = reactorHandler.getTarget(i);
      final String data = reactorHandler.getData(i);
      if (target.equals("xsl-param")) {
        handleParameter(data);
      } else if (target.equals("xsl-params")) {
        handleParameters(data, sourceID);
      } else if (target.equals("xsl-stylesheet")) {
        if (null != transformer) {
          throw new SAXException("Build file can not contain " + "two xsl-stylesheet PIs");
        }
        final TransformerFactory factory = TransformerFactory.newInstance();
        final String stylesheet = getStylesheet(data, sourceID);
        transformer = factory.newTransformer(new StreamSource(stylesheet));
      }
    }
    if (null == transformer) {
      reader.setContentHandler(handler);
      reader.parse(sourceID.toString());
    } else {
      final String[] names = m_parameters.getNames();
      for (int i = 0; i < names.length; i++) {
        final String name = names[i];
        final String value = m_parameters.getParameter(name);
        transformer.setParameter(name, value);
      }
      final SAXResult result = new SAXResult(handler);
      transformer.transform(new StreamSource(sourceID.toString()), result);
    }
  }
}
