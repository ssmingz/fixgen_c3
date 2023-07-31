class PlaceHold {
  public void selectAll() {
    checkWidget();
    if ((style & SWT.SINGLE) != 0) {
      return;
    }
    NSTableView widget = ((NSTableView) (view));
    ignoreSelect = true;
    widget.selectAll(null);
    ignoreSelect = false;
  }
}
