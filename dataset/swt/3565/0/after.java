class PlaceHold {
  void scrollClipViewToPoint(int id, int sel, int clipView, NSPoint point) {
    if (shouldScroll) {
      super.scrollClipViewToPoint(id, sel, clipView, point);
      if ((((style & SWT.CHECK) != 0) && (columnCount > 0))
          && (((NSOutlineView) (view)).headerView() != null)) {
        if (point.x <= getCheckColumnWidth()) {
          headerView.setNeedsDisplayInRect(headerView.headerRectOfColumn(1));
        }
      }
    }
  }
}
