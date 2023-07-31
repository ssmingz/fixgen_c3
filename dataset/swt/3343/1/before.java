class PlaceHold {
  void releaseChildren(boolean destroy) {
    if (items != null) {
      for (int i = 0; i < itemCount; i++) {
        ToolItem item = items[i];
        if ((item != null) && (!item.isDisposed())) {
          item.releaseChildren(false);
        }
      }
      itemCount = 0;
      items = null;
    }
    super.releaseChildren(destroy);
  }
}
