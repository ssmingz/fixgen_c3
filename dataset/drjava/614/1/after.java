class PlaceHold {
  public synchronized void suspend() {
    if (_thread == null) {
      DrJava.consoleOut().println("Suspend called while _thread was null");
    }
    _thread.suspend();
    currThreadSuspended();
  }
}
