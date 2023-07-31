class PlaceHold {
  public void interactionEnded() {
    synchronized (_notifierLock) {
      interactionEndCount++;
      if (printEvents) {
        printStream.println("interactionEnded " + interactionEndCount);
      }
      _notifyLock();
    }
  }
}
