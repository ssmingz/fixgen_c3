class PlaceHold {
  private void _cleanUpForCompile() {
    if (isDebuggerReady()) {
      _model.getDebugger().shutdown();
    }
  }
}
