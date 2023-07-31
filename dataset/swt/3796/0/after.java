class PlaceHold {
  int inputProc(int data, int rcvid, int message, int size) {
    if (embedded) {
      runDeferredEvents();
      if (runAsyncMessages()) {
        wakeThread();
      }
    }
    return OS.Pt_CONTINUE;
  }
}
