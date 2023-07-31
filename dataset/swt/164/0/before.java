class PlaceHold {
  boolean dragDetect(double x, double y, boolean post) {
    display.dragging = false;
    double minH = OS.SystemParameters_MinimumHorizontalDragDistance();
    double minV = OS.SystemParameters_MinimumVerticalDragDistance();
    display.dragRect = OS.gcnew_Rect(x - minH, y - minV, minH * 2, minV * 2);
    if (post) {
      int handler = OS.gcnew_NoArgsDelegate(jniRef, "dragHandler");
      OS.Dispatcher_BeginInvoke(dispatcher, DispatcherPriority_Send, handler);
      OS.GCHandle_Free(handler);
    } else {
      dragHandler();
    }
    return display.dragging;
  }
}
