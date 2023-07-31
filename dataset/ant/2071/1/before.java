class PlaceHold {
  private Transformer newTransformer() throws Exception {
    InputStream xslStream = new BufferedInputStream(new FileInputStream(stylesheet));
    try {
      StreamSource src = new StreamSource(xslStream);
      src.setSystemId(getSystemId(stylesheet));
      Templates templates = getFactory().newTemplates(src);
      Transformer transformer = templates.newTransformer();
      transformer.setErrorListener(this);
      if (uriResolver != null) {
        transformer.setURIResolver(uriResolver);
      }
      for (int i = 0; i < params.size(); i++) {
        final String[] pair = ((String[]) (params.elementAt(i)));
        transformer.setParameter(pair[0], pair[1]);
      }
      for (int i = 0; i < outputProperties.size(); i++) {
        final String[] pair = ((String[]) (outputProperties.elementAt(i)));
        transformer.setOutputProperty(pair[0], pair[1]);
      }
      return transformer;
    } finally {
      try {
        xslStream.close();
      } catch (IOException ignored) {
      }
    }
  }
}
