class PlaceHold {
  void wakeThread() {
    int handler = OS.gcnew_NoArgsDelegate(jniRef, "wakeThreadHandler");
    int operation = OS.Dispatcher_BeginInvoke(dispatcher, DispatcherPriority_Send, handler);
    OS.GCHandle_Free(operation);
    OS.GCHandle_Free(handler);
  }
}
