class PlaceHold {
  public void interpreterReady(File wd) {
    assert duringInit() || EventQueue.isDispatchThread();
    interactionEnded();
    _runAction.setEnabled(true);
    _runProjectAction.setEnabled(_model.isProjectActive());
    _junitAction.setEnabled(true);
    _junitAllAction.setEnabled(true);
    _junitProjectAction.setEnabled(_model.isProjectActive());
    if (_showDebugger) {
      _toggleDebuggerAction.setEnabled(true);
    }
    _interactionsController.interruptConsoleInput();
  }
}
