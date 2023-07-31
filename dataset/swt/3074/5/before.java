class PlaceHold {
  public void selectAll() {
    checkWidget();
    if ((style & SWT.SINGLE) != 0) {
      return;
    }
    NSTableView widget = ((NSTableView) (view));
    widget.setDelegate(null);
    widget.selectAll(null);
    widget.setDelegate(widget);
  }
}
