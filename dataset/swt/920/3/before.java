class PlaceHold {
  public void deselect(int index) {
    checkWidget();
    if ((0 <= index) && (index < itemCount)) {
      NSTableView widget = ((NSTableView) (view));
      widget.setDelegate(null);
      widget.deselectRow(index);
      widget.setDelegate(widget);
    }
  }
}
