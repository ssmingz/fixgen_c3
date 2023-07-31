class PlaceHold {
  public boolean recallNextInteractionInHistory() {
    acquireWriteLock();
    try {
      if (hasHistoryNext()) {
        moveHistoryNext(getCurrentInteraction());
        return true;
      }
      _beep.run();
      return false;
    } finally {
      releaseWriteLock();
    }
  }
}
