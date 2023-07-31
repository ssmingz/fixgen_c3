class PlaceHold {
  private Source getSource(InputStream is, File infile) throws Exception {
    Source src = null;
    if (entityResolver != null) {
      if (getFactory().getFeature(SAXSource.FEATURE)) {
        SAXParserFactory spFactory = SAXParserFactory.newInstance();
        spFactory.setNamespaceAware(true);
        XMLReader reader = spFactory.newSAXParser().getXMLReader();
        reader.setEntityResolver(entityResolver);
        src = new SAXSource(reader, new InputSource(is));
      } else {
        throw new IllegalStateException("xcatalog specified, but " + "parser doesn't support SAX");
      }
    } else {
      src = new StreamSource(is);
    }
    src.setSystemId(getSystemId(infile));
    return src;
  }
}
