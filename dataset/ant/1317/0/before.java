class PlaceHold {
  public boolean containsProperties(String value) {
    if (value == null) {
      return false;
    }
    for (ParsePosition pos = new ParsePosition(0); pos.getIndex() < value.length(); ) {
      if (parsePropertyName(value, pos) != null) {
        return true;
      }
      pos.setIndex(pos.getIndex() + 1);
    }
    return false;
  }
}
