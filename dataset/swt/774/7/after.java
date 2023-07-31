class PlaceHold {
  void releaseChildren(boolean destroy) {
    for (int i = 0; i < itemCount; i++) {
      ExpandItem item = items[i];
      if ((item != null) && (!item.isDisposed())) {
        item.release(false);
      }
    }
    super.releaseChildren(destroy);
  }
}
