class PlaceHold {
  private boolean needHScroll(Rectangle contentRect, boolean vVisible) {
    ScrollBar hBar = getHorizontalBar();
    if (hBar == null) {
      return false;
    }
    Rectangle hostRect = getBounds();
    int border = getBorderWidth();
    hostRect.width -= 2 * border;
    ScrollBar vBar = getVerticalBar();
    if (vVisible && (vBar != null)) {
      hostRect.width -= vBar.getSize().x;
    }
    if ((!expandHorizontal) && (contentRect.width > hostRect.width)) {
      return true;
    }
    if (expandHorizontal && (minWidth > hostRect.width)) {
      return true;
    }
    return false;
  }
}
