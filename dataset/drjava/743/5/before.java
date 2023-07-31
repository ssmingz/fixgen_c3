class PlaceHold {
  public synchronized void removeBreakpoint(final Breakpoint breakpoint) throws DebugException {
    _ensureReady();
    _breakpoints.remove(breakpoint);
    Vector<BreakpointRequest> requests = breakpoint.getRequests();
    if ((requests.size() > 0) && (_eventManager != null)) {
      try {
        for (int i = 0; i < requests.size(); i++) {
          _eventManager.deleteEventRequest(requests.get(i));
        }
      } catch (VMMismatchException vme) {
        _log("VMMismatch when removing breakpoint.", vme);
      } catch (VMDisconnectedException vmde) {
        _log("VMDisconnected when removing breakpoint.", vmde);
      }
    }
    _pendingRequestManager.removePendingRequest(breakpoint);
    breakpoint.getDocument().removeBreakpoint(breakpoint);
    _notifier.breakpointRemoved(breakpoint);
  }
}
