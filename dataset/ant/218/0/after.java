class PlaceHold {
  public String getValue() {
    if (values.size() == 0) {
      return null;
    }
    String fullValue = "";
    for (Enumeration<String> e = getValues(); e.hasMoreElements(); ) {
      String value = e.nextElement();
      fullValue += value + " ";
    }
    return fullValue.trim();
  }
}
