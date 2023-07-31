class PlaceHold {
  public void interactionStarted() {
    Runnable doCommand =
        new Runnable() {
          public void run() {
            _interactionsPane.setEditable(false);
            _interactionsPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
          }
        };
    SwingUtilities.invokeLater(doCommand);
  }
}
