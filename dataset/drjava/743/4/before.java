class PlaceHold {
  synchronized void reachedBreakpoint(BreakpointRequest request) {
    Object property = request.getProperty("debugAction");
    if ((property != null) && (property instanceof Breakpoint)) {
      final Breakpoint breakpoint = ((Breakpoint) (property));
      printMessage(
          ((("Breakpoint hit in class " + breakpoint.getClassName()) + "  [line ")
                  + breakpoint.getLineNumber())
              + "]");
      _notifier.breakpointReached(breakpoint);
    } else {
      _log("Reached a breakpoint without a debugAction property: " + request);
    }
  }
}
