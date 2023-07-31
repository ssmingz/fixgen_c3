class PlaceHold {
  void releaseWidget() {
    for (int i = 0; i < items.length; i++) {
      ToolItem item = items[i];
      if ((item != null) && (!item.isDisposed())) {
        item.releaseWidget();
        item.releaseHandle();
      }
    }
    items = null;
    super.releaseWidget();
  }
}
