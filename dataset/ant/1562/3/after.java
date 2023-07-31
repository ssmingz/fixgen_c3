class PlaceHold {
  public String toString() {
    StringBuilder buf = new StringBuilder();
    if (hasSelectors()) {
      buf.append("{noneselect: ");
      buf.append(super.toString());
      buf.append("}");
    }
    return buf.toString();
  }
}
