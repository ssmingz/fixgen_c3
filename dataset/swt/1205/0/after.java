class PlaceHold {
  int FocusNextElement() {
    getDisplay()
        .asyncExec(
            new Runnable() {
              public void run() {
                traverse(TRAVERSE_TAB_NEXT);
              }
            });
    return XPCOM.NS_OK;
  }
}
