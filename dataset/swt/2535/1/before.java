class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    int handler = OS.gcnew_SelectionChangedEventHandler(jniRef, "HandleSelectionChanged");
    OS.Selector_SelectionChanged(handle, handler);
    OS.GCHandle_Free(handler);
  }
}
