class PlaceHold {
  void releaseChildren(boolean destroy) {
    MenuItem[] items = getItems();
    for (int i = 0; i < items.length; i++) {
      MenuItem item = items[i];
      if ((item != null) && (!item.isDisposed())) {
        item.releaseChildren(false);
      }
    }
    super.releaseChildren(destroy);
  }
}
