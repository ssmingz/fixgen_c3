class PlaceHold {
  public void interpretCurrentInteraction() {
    synchronized (_interpreterLock) {
      if (_document.inProgress()) {
        return;
      }
      _notifyInteractionStarted();
      String text = _document.getCurrentInteraction();
      _document.setInProgress(true);
      _document.addToHistory(text);
      _docAppend("\n", DEFAULT_STYLE);
      String toEval = text.trim();
      if (toEval.startsWith("java ")) {
        toEval = _testClassCall(toEval);
      }
      interpret(toEval);
    }
  }
}
