class PlaceHold {
  public synchronized Vector<DebugThreadData> getCurrentThreadData() throws DebugException {
    _ensureReady();
    List listThreads = _vm.allThreads();
    Iterator<ThreadReference> iter = listThreads.iterator();
    Vector<DebugThreadData> threads = new Vector<DebugThreadData>();
    while (iter.hasNext()) {
      try {
        threads.addElement(new DebugThreadData(iter.next()));
      } catch (ObjectCollectedException e) {
      }
    }
    return threads;
  }
}
