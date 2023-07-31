class PlaceHold {
  public void pack() {
    checkWidget();
    GC gc = new GC(parent);
    int width = gc.stringExtent(text).x;
    int index = parent.indexOf(this);
    for (int i = 0; i < parent.itemCount; i++) {
      TreeItem item = parent.items[i];
      if ((item != null) && item.cached) {
        width = Math.max(width, item.calculateWidth(index, gc, true));
      }
    }
    gc.dispose();
    setWidth(width + parent.getInsetWidth());
  }
}
