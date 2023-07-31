class PlaceHold {
  public synchronized void resume() {
    if (_thread == null) {
      DrJava.consoleOut().println("Resume called while _thread was null");
    }
    _thread.resume();
    currThreadResumed();
  }
}
