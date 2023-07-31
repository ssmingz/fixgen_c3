class PlaceHold {
  public void pack() {
    checkWidget();
    GC gc = new GC(parent);
    int width = gc.stringExtent(text).x;
    if ((parent.style & SWT.VIRTUAL) == 0) {
      int index = parent.indexOf(this);
      for (int i = 0; i < parent.itemCount; i++) {
        TableItem item = parent.items[i];
        if (item != null) {
          width = Math.max(width, item.calculateWidth(index, gc));
        }
      }
    }
    gc.dispose();
    setWidth(width);
  }
}
