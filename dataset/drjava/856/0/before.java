class PlaceHold {
  private void _cleanUpForCompile() {
    if (isDebuggerEnabled()) {
      _model.getDebugger().shutdown();
    }
  }
}
