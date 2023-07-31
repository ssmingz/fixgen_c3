class PlaceHold {
  boolean setScrollWidth(TableItem item, boolean callMeasureItem) {
    return setScrollWidth(new TableItem[] {item}, callMeasureItem, false);
  }
}
