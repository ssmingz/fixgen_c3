class PlaceHold {
  public void setStylesheet(File stylesheet) throws Exception {
    xslStream = new FileInputStream(stylesheet);
    StreamSource src = new StreamSource(xslStream);
    src.setSystemId(getSystemId(stylesheet));
    templates = tfactory.newTemplates(src);
    transformer = templates.newTransformer();
    transformer.setErrorListener(this);
  }
}
