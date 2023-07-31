class PlaceHold {
  public void setFont(Font value) {
    checkWidget();
    Font oldFont = getFont();
    super.setFont(value);
    Font font = getFont();
    if (font.equals(oldFont)) {
      return;
    }
    GC gc = new GC(this);
    fontHeight = gc.getFontMetrics().getHeight();
    itemHeight = Math.max(fontHeight, imageHeight) + (2 * getCellPadding());
    Point headerSize = header.getSize();
    int newHeaderHeight = Math.max(fontHeight, headerImageHeight) + (2 * getHeaderPadding());
    if (headerSize.y != newHeaderHeight) {
      header.setSize(headerSize.x, newHeaderHeight);
    }
    for (int i = 0; i < items.length; i++) {
      items[i].updateFont(gc);
    }
    gc.dispose();
    if (header.isVisible()) {
      header.redraw();
    }
    updateHorizontalBar();
    ScrollBar vBar = getVerticalBar();
    int pageSize = (getClientArea().height - getHeaderHeight()) / itemHeight;
    vBar.setThumb(pageSize);
    vBar.setPageIncrement(pageSize);
    topIndex = vBar.getSelection();
    redraw();
  }
}
