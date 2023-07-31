class PlaceHold {
  public void interactionStarted() {
    synchronized (_notifierLock) {
      interactionStartCount++;
      if (printEvents) {
        printStream.println("interactionStarted " + interactionStartCount);
      }
      _notifyLock();
    }
  }
}
