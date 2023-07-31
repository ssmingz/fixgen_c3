class PlaceHold {
  public void selectAll() {
    checkWidget();
    if ((style & SWT.SINGLE) != 0) {
      return;
    }
    checkItems();
    NSOutlineView widget = ((NSOutlineView) (view));
    ignoreSelect = true;
    widget.selectAll(null);
    ignoreSelect = false;
  }
}
