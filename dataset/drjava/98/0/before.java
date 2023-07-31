class PlaceHold {
  public void interactionEnded() {
    Runnable doCommand =
        new Runnable() {
          public void run() {
            _interactionsPane.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            _interactionsPane.setEditable(true);
            _interactionsController.moveToEnd();
            if (_interactionsPane.hasFocus()) {
              _interactionsPane.getCaret().setVisible(true);
            }
          }
        };
    SwingUtilities.invokeLater(doCommand);
  }
}
