class PlaceHold {
  boolean setScrollWidth(TreeItem item, boolean recurse, boolean callMeasureItem) {
    return setScrollWidth(new TreeItem[] {item}, recurse, callMeasureItem, false);
  }
}
