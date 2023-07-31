class PlaceHold {
  void onResize(Event event) {
    clientArea = getClientArea();
    ScrollBar vBar = getVerticalBar();
    int clientHeight = (clientArea.height - getHeaderHeight()) / itemHeight;
    int thumb = Math.min(clientHeight, itemsCount);
    vBar.setThumb(thumb);
    vBar.setPageIncrement(thumb);
    int index = vBar.getSelection();
    if (index != topIndex) {
      topIndex = index;
      redraw();
    }
    boolean visible = clientHeight < itemsCount;
    if (visible != vBar.getVisible()) {
      vBar.setVisible(visible);
      clientArea = getClientArea();
    }
    ScrollBar hBar = getHorizontalBar();
    int hBarMaximum = hBar.getMaximum();
    thumb = Math.min(clientArea.width, hBarMaximum);
    hBar.setThumb(thumb);
    hBar.setPageIncrement(thumb);
    horizontalOffset = hBar.getSelection();
    visible = clientArea.width < hBarMaximum;
    if (visible != hBar.getVisible()) {
      hBar.setVisible(visible);
      clientArea = getClientArea();
    }
    int headerHeight = Math.max(fontHeight, headerImageHeight) + (2 * getHeaderPadding());
    header.setSize(clientArea.width, headerHeight);
  }
}
