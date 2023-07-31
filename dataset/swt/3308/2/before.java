class PlaceHold {
  void releaseWidget() {
    for (int i = 0; i < itemCount; i++) {
      ToolItem item = items[i];
      if (!item.isDisposed()) {
        item.releaseWidget();
        item.releaseHandle();
      }
    }
    items = null;
    super.releaseWidget();
  }
}
