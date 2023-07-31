class PlaceHold {
  void releaseWidget() {
    for (int i = 0; i < itemCount; i++) {
      ToolItem item = items[i];
      if (!item.isDisposed()) {
        item.releaseResources();
      }
    }
    items = null;
    super.releaseWidget();
  }
}
