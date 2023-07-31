class PlaceHold {
  public void deselectAll() {
    checkWidget();
    NSTableView widget = ((NSTableView) (view));
    widget.setDelegate(null);
    widget.deselectAll(null);
    widget.setDelegate(widget);
  }
}
