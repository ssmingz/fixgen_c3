class PlaceHold {
  public void removeFromDebugger() {
    if (_debugger.isAvailable() && _debugger.isReady()) {
      try {
        while (_breakpoints.size() > 0) {
          _debugger.removeBreakpoint(_breakpoints.elementAt(0));
        }
      } catch (DebugException de) {
        throw new UnexpectedException(de);
      }
    } else {
      clearBreakpoints();
    }
  }
}
