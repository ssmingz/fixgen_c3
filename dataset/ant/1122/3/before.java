class PlaceHold {
  @Override
  public String[] mapFileName(String xmlFile) {
    int dotPos = xmlFile.lastIndexOf('.');
    if (dotPos > 0) {
      xmlFile = xmlFile.substring(0, dotPos);
    }
    return new String[] {xmlFile + targetExtension};
  }
}
