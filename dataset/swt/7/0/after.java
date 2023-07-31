class PlaceHold {
  public void setTopIndex(int index) {
    checkWidget();
    NSTableView widget = ((NSTableView) (view));
    int row = Math.max(0, Math.min(index, itemCount));
    NSPoint pt = new NSPoint();
    pt.x = scrollView.contentView().bounds().x;
    pt.y = widget.frameOfCellAtColumn(0, row).y;
    view.scrollPoint(pt);
  }
}
