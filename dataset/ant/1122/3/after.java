class PlaceHold {
  public String[] mapFileName(String xmlFile) {
    final int dotPos = xmlFile.lastIndexOf('.');
    if (dotPos > 0) {
      xmlFile = xmlFile.substring(0, dotPos);
    }
    return new String[] {xmlFile + targetExtension};
  }
}
