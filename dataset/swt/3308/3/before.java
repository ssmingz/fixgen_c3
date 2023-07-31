class PlaceHold {
  void releaseWidget() {
    ToolItem[] items = getItems();
    for (int i = 0; i < items.length; i++) {
      ToolItem item = items[i];
      if (!item.isDisposed()) {
        item.releaseWidget();
        item.releaseHandle();
      }
    }
    items = null;
    super.releaseWidget();
    if (tooltipsHandle != 0) {
      OS.g_object_unref(tooltipsHandle);
    }
    tooltipsHandle = 0;
  }
}
