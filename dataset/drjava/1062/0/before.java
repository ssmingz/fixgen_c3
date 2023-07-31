class PlaceHold {
  private void _cleanUpForCompile() {
    if (inDebugMode()) {
      _model.getDebugger().shutdown();
    }
  }
}
