class PlaceHold {
  public String toString() {
    StringBuffer buf = new StringBuffer();
    if (hasSelectors()) {
      buf.append("{noneselect: ");
      buf.append(super.toString());
      buf.append("}");
    }
    return buf.toString();
  }
}
