class PlaceHold {
  void HandleKeyDown(int sender, int e) {
    if (!checkEvent(e)) {
      return;
    }
    super.HandleKeyDown(sender, e);
    if (isDisposed()) {
      return;
    }
    int key = OS.KeyEventArgs_Key(e);
    if (key == OS.Key_Return) {
      int source = OS.RoutedEventArgs_OriginalSource(e);
      Widget widget = display.getWidget(source);
      OS.GCHandle_Free(source);
      if (widget instanceof TableItem) {
        Event event = new Event();
        event.item = ((TableItem) (widget));
        postEvent(DefaultSelection, event);
      }
    }
  }
}
