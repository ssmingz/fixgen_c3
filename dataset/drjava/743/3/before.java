class PlaceHold {
  public synchronized void setBreakpoint(final Breakpoint breakpoint) throws DebugException {
    _ensureReady();
    breakpoint.getDocument().checkIfClassFileInSync();
    _breakpoints.add(breakpoint);
    breakpoint.getDocument().addBreakpoint(breakpoint);
    _notifier.breakpointSet(breakpoint);
  }
}
