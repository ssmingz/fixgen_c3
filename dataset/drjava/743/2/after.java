class PlaceHold {
  synchronized void currThreadResumed() throws DebugException {
    if (printMessages) {
      System.out.println("In currThreadResumed()");
    }
    Utilities.invokeLater(
        new Runnable() {
          public void run() {
            _notifier.currThreadResumed();
          }
        });
  }
}
