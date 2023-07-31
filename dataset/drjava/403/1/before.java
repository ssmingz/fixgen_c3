class PlaceHold {
  public synchronized void startup() throws DebugException {
    if (!isReady()) {
      _eventHandlerError = null;
      for (OpenDefinitionsDocument doc : _model.getOpenDefinitionsDocuments()) {
        doc.checkIfClassFileInSync();
      }
      _attachToVM();
      ThreadDeathRequest tdr = _eventManager.createThreadDeathRequest();
      tdr.setSuspendPolicy(EventRequest.SUSPEND_EVENT_THREAD);
      tdr.enable();
      EventHandlerThread eventHandler = new EventHandlerThread(this, _vm);
      eventHandler.start();
      _model.getInteractionsModel().addListener(_watchListener);
      Vector<Breakpoint> oldBreakpoints =
          new Vector<Breakpoint>(_model.getBreakpointManager().getRegions());
      _model.getBreakpointManager().clearRegions();
      for (int i = 0; i < oldBreakpoints.size(); i++) {
        Breakpoint bp = oldBreakpoints.get(i);
        setBreakpoint(
            new Breakpoint(
                bp.getDocument(), bp.getOffset(), bp.getLineNumber(), bp.isEnabled(), this));
      }
    } else {
      throw new IllegalStateException("Debugger has already been started.");
    }
  }
}
