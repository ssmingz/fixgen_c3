class PlaceHold {
  public String getValue() {
    if (values.size() == 0) {
      return null;
    }
    String fullValue = "";
    for (Enumeration e = getValues(); e.hasMoreElements(); ) {
      String value = ((String) (e.nextElement()));
      fullValue += value + " ";
    }
    return fullValue.trim();
  }
}
