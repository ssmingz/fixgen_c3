class PlaceHold {
  public void deselect(int index) {
    checkWidget();
    if ((0 <= index) && (index < itemCount)) {
      NSTableView widget = ((NSTableView) (view));
      ignoreSelect = true;
      widget.deselectRow(index);
      ignoreSelect = false;
    }
  }
}
