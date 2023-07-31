class PlaceHold {
  public synchronized void removeAllBreakpoints() throws DebugException {
    _ensureReady();
    while (_breakpoints.size() > 0) {
      removeBreakpoint(_breakpoints.elementAt(0));
    }
  }
}
