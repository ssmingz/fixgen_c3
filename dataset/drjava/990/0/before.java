class PlaceHold {
  void debuggerResume() throws DebugException {
    if (inDebugMode()) {
      _model.getDebugManager().resume();
      _removeThreadLocationHighlight();
    }
  }
}
