class PlaceHold {
  private static String getXSLTProcessorLocation() {
    Transformer transformer = getXSLTProcessor();
    if (transformer == null) {
      return null;
    }
    String location = getClassLocation(transformer.getClass());
    return location;
  }
}
