class PlaceHold {
  public void deselectAll() {
    checkWidget();
    NSTableView widget = ((NSOutlineView) (view));
    ignoreSelect = true;
    widget.deselectAll(null);
    ignoreSelect = false;
  }
}
