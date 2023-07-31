class PlaceHold {
  void releaseChildren(boolean destroy) {
    ExpandItem[] items = getItems();
    for (int i = 0; i < items.length; i++) {
      ExpandItem item = items[i];
      if ((item != null) && (!item.isDisposed())) {
        item.release(false);
      }
    }
    super.releaseChildren(destroy);
  }
}
