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
    header.setFont(font);
    for (int i = 0; i < columns.length; i++) {
      columns[i].updateFont(gc);
    }
    for (int i = 0; i < itemsCount; i++) {
      items[i].updateFont(gc);
    }
    gc.dispose();
    if (header.isVisible()) {
      header.redraw();
    }
    updateHorizontalBar();
    ScrollBar vBar = getVerticalBar();
    int thumb = (getClientArea().height - getHeaderHeight()) / itemHeight;
    vBar.setThumb(thumb);
    vBar.setPageIncrement(thumb);
    topIndex = vBar.getSelection();
    vBar.setVisible(thumb < vBar.getMaximum());
    redraw();
  }
}
