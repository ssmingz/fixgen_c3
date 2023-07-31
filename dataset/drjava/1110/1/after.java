class PlaceHold {
  public void interpreterResetting() {
    if (!_waitingForFirstInterpreter) {
      _document.acquireWriteLock();
      try {
        _document.insertBeforeLastPrompt("Resetting Interactions..." + _newLine, ERROR_STYLE);
        _document.setInProgress(true);
      } finally {
        _document.releaseWriteLock();
      }
      try {
        _createNewDebugPort();
      } catch (IOException ioe) {
      }
      _notifyInterpreterResetting();
    }
  }
}
