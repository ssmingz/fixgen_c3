class JPDABreakpoint {
  public JPDABreakpoint(
      OpenDefinitionsDocument doc, int offset, boolean isEnabled, JPDADebugger manager)
      throws DebugException {
    super(manager, doc, offset);
    assert EventQueue.isDispatchThread();
    _doc = doc;
    try {
      _position = doc.createPosition(offset);
    } catch (BadLocationException e) {
      throw new UnexpectedException(e);
    }
    _suspendPolicy = EventRequest.SUSPEND_EVENT_THREAD;
    _isEnabled = isEnabled;
    update();
    if ((_manager != null) && _manager.isReady()) {
      Vector<ReferenceType> refTypes = _manager.getReferenceTypes(_className, _lineNumber);
      _initializeRequests(refTypes);
      setEnabled(isEnabled);
    }
  }
}
