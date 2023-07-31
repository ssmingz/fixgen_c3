class PlaceHold {
  public void pack() {
    checkWidget();
    GC gc = new GC(parent);
    int width = gc.stringExtent(text).x;
    if ((parent.style & SWT.VIRTUAL) == 0) {
      int index = parent.indexOf(this);
      width = Math.max(width, calculateWidth(parent.getItems(null), index, gc, width));
    }
    gc.dispose();
    setWidth(width);
  }
}
