class PlaceHold {
  public void systemErrPrint(final String s) {
    Utilities.invokeLater(
        new Runnable() {
          public void run() {
            _docAppend(_consoleDoc, s, SYSTEM_ERR_STYLE);
          }
        });
  }
}
