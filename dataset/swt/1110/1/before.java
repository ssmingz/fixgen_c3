class PlaceHold {
  public void removeAll() {
    checkWidget();
    setRedraw(false);
    setFocusItem(null, false);
    while (items.length > 0) {
      items[0].dispose(true);
    }
    ScrollBar vBar = getVerticalBar();
    ScrollBar hBar = getHorizontalBar();
    vBar.setMaximum(1);
    hBar.setMaximum(1);
    vBar.setVisible(false);
    hBar.setVisible(false);
    setRedraw(true);
  }
}
