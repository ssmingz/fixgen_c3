class PlaceHold {
  int FocusPrevElement() {
    getDisplay()
        .asyncExec(
            new Runnable() {
              public void run() {
                traverse(TRAVERSE_TAB_PREVIOUS);
              }
            });
    return XPCOM.NS_OK;
  }
}
