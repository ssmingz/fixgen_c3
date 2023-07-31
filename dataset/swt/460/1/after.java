class PlaceHold {
  int getPreferredWidth() {
    if (!parent.getHeaderVisible()) {
      return 0;
    }
    GC gc = new GC(parent);
    int result = getContentWidth(gc, false);
    gc.dispose();
    return result + (2 * parent.getHeaderPadding());
  }
}
