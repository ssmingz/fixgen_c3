class PlaceHold {
  void releaseChildren(boolean destroy) {
    MenuItem[] items = getItems();
    for (int i = 0; i < items.length; i++) {
      MenuItem item = items[i];
      if ((item != null) && (!item.isDisposed())) {
        item.release(false);
      }
    }
    super.releaseChildren(destroy);
  }
}
