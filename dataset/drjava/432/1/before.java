class PlaceHold {
  public void systemOutPrint(final String s) {
    Utilities.invokeLater(
        new Runnable() {
          public void run() {
            _docAppend(_consoleDoc, s, SYSTEM_OUT_STYLE);
          }
        });
  }
}
