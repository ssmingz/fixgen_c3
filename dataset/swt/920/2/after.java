class PlaceHold {
  public void deselectAll() {
    checkWidget();
    NSTableView widget = ((NSTableView) (view));
    ignoreSelect = true;
    widget.deselectAll(null);
    ignoreSelect = false;
  }
}
