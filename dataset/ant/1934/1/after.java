class PlaceHold {
  private static String getXSLTProcessorLocation() {
    Transformer transformer = getXSLTProcessor();
    if (transformer == null) {
      return null;
    }
    URL location = getClassLocation(transformer.getClass());
    return location != null ? location.toString() : null;
  }
}
