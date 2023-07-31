class PlaceHold {
  int inputProc(int data, int rcvid, int message, int size) {
    if (embedded) {
      runDeferredEvents();
      runAsyncMessages();
    }
    return OS.Pt_CONTINUE;
  }
}
