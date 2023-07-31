class PlaceHold {
  void releaseWidget() {
    MenuItem[] items = getItems();
    for (int i = 0; i < items.length; i++) {
      MenuItem item = items[i];
      if (!item.isDisposed()) {
        item.releaseWidget();
        item.releaseHandle();
      }
    }
    if (parent != null) {
      parent.remove(this);
    }
    super.releaseWidget();
    parent = null;
    cascade = null;
  }
}
