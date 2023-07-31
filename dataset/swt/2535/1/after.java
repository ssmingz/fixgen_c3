class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    int handler = OS.gcnew_SelectionChangedEventHandler(jniRef, "HandleSelectionChanged");
    OS.Selector_SelectionChanged(handle, handler);
    OS.GCHandle_Free(handler);
    handler = OS.gcnew_MouseButtonEventHandler(jniRef, "HandleMouseDoubleClick");
    if (handler == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.Control_MouseDoubleClick(handle, handler);
    OS.GCHandle_Free(handler);
  }
}
