class PlaceHold {
  public void interactionStarted() {
    synchronized (_notifierLock) {
      interactionStartCount++;
      if (printEvents) {
        System.out.println("interactionStarted " + interactionStartCount);
      }
      _notifyLock();
    }
  }
}
