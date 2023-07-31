class PlaceHold {
  public boolean containsProperties(String value) {
    if (value == null) {
      return false;
    }
    final int len = value.length();
    for (ParsePosition pos = new ParsePosition(0); pos.getIndex() < len; ) {
      if (parsePropertyName(value, pos) != null) {
        return true;
      }
      pos.setIndex(pos.getIndex() + 1);
    }
    return false;
  }
}
