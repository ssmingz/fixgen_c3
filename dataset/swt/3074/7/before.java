class PlaceHold {
  public void deselect(int start, int end) {
    checkWidget();
    if ((start == 0) && (end == (itemCount - 1))) {
      deselectAll();
    } else {
      int length = (end - start) + 1;
      NSTableView widget = ((NSTableView) (view));
      widget.setDelegate(null);
      for (int i = 0; i < length; i++) {
        widget.deselectRow(i);
      }
      widget.setDelegate(widget);
    }
  }
}
