class PlaceHold {
  public void interactionEnded() {
    synchronized (_notifierLock) {
      interactionEndCount++;
      if (printEvents) {
        System.out.println("interactionEnded " + interactionEndCount);
      }
      _notifyLock();
    }
  }
}
