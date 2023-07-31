class PlaceHold {
  public synchronized void removeAllBreakpoints() {
    while (_breakpoints.size() > 0) {
      removeBreakpoint(_breakpoints.elementAt(0));
    }
  }
}
