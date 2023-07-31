class PlaceHold {
  public void interpreterResetting() {
    if (!_waitingForFirstInterpreter) {
      _document.insertBeforeLastPrompt("Resetting Interactions..." + _newLine, ERROR_STYLE);
      _document.setInProgress(true);
      try {
        _createNewDebugPort();
      } catch (IOException ioe) {
      }
      _notifyInterpreterResetting();
    }
  }
}
