class PlaceHold {
  void debuggerStep(int flag) {
    if (inDebugMode()) {
      try {
        _model.getDebugManager().step(flag);
      } catch (DebugException de) {
        _showError(de, "Debugger Error", "Could not create a step request.");
      }
    }
  }
}
