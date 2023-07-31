class PlaceHold {
  public void resetConsole() {
    _consoleDoc.reset("");
    Utilities.invokeLater(
        new Runnable() {
          public void run() {
            _notifier.consoleReset();
          }
        });
  }
}
