class PlaceHold {
  void releaseChildren(boolean destroy) {
    if (items != null) {
      for (int i = 0; i < items.length; i++) {
        TabItem item = items[i];
        if ((item != null) && (!item.isDisposed())) {
          item.releaseChildren(false);
        }
      }
      items = null;
    }
    super.releaseChildren(destroy);
  }
}
