class PlaceHold {
  public void interpreterResetting() {
    if (!_waitingForFirstInterpreter) {
      _document.insertBeforeLastPrompt(" Resetting Interactions ...\n", ERROR_STYLE);
      _document.setInProgress(true);
      try {
        _createNewDebugPort();
      } catch (IOException ioe) {
      }
      _notifyInterpreterResetting();
    }
  }
}
