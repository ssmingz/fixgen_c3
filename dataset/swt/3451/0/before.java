class PlaceHold {
  void wakeThread() {
    int handler = OS.gcnew_NoArgsDelegate(jniRef, "wakeThreadHandler");
    OS.Dispatcher_BeginInvoke(dispatcher, DispatcherPriority_Send, handler);
    OS.GCHandle_Free(handler);
  }
}
