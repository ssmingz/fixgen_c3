class PlaceHold {
  public void pack() {
    checkWidget();
    GC gc = new GC(parent);
    int width = gc.stringExtent(text).x;
    int index = parent.indexOf(this);
    for (int i = 0; i < parent.itemCount; i++) {
      TableItem item = parent.items[i];
      if (((item != null) && (!item.isDisposed())) && item.cached) {
        width = Math.max(width, item.calculateWidth(index, gc, true));
        if (isDisposed()) {
          gc.dispose();
          return;
        }
        if (gc.isDisposed()) {
          gc = new GC(parent);
        }
      }
    }
    gc.dispose();
    setWidth(width + parent.getInsetWidth());
  }
}
