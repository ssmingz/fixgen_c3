class PlaceHold {
  synchronized void currThreadResumed() throws DebugException {
    if (printMessages) {
      System.out.println("In currThreadResumed()");
    }
    _notifier.currThreadResumed();
  }
}
