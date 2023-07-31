class PlaceHold {
  private boolean needVScroll(Rectangle contentRect, boolean hVisible) {
    ScrollBar vBar = getVerticalBar();
    if (vBar == null) {
      return false;
    }
    Rectangle hostRect = getBounds();
    int border = getBorderWidth();
    hostRect.height -= 2 * border;
    ScrollBar hBar = getHorizontalBar();
    if (hVisible && (hBar != null)) {
      hostRect.height -= hBar.getSize().y;
    }
    if ((!expandHorizontal) && (contentRect.height > hostRect.height)) {
      return true;
    }
    if (expandHorizontal && (minHeight > hostRect.height)) {
      return true;
    }
    return false;
  }
}
