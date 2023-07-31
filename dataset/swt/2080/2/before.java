class PlaceHold {
  public void setImage(Image image) {
    checkWidget();
    if ((image != null) && image.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    super.setImage(image);
    NSTableHeaderView headerView = ((NSTableView) (parent.view)).headerView();
    if (headerView == null) {
      return;
    }
    int index = ((NSOutlineView) (parent.view)).columnWithIdentifier(nsColumn);
    NSRect rect = headerView.headerRectOfColumn(index);
    headerView.setNeedsDisplayInRect(rect);
  }
}
