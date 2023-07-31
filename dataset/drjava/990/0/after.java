class PlaceHold {
  void debuggerResume() throws DebugException {
    if (inDebugMode()) {
      _model.getDebugger().resume();
      _removeThreadLocationHighlight();
    }
  }
}
