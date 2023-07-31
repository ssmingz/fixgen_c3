class PlaceHold {
  public void resetConsole() {
    try {
      _consoleDoc.remove(0, _consoleDoc.getLength());
    } catch (BadLocationException impossible) {
    }
    _notifyListeners(
        new EventNotifier() {
          public void notifyListener(GlobalModelListener l) {
            l.consoleReset();
          }
        });
  }
}
