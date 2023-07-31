class PlaceHold {
  public boolean recallPreviousInteractionInHistory() {
    acquireWriteLock();
    try {
      if (hasHistoryPrevious()) {
        moveHistoryPrevious(getCurrentInteraction());
        return true;
      }
      _beep.run();
      return false;
    } finally {
      releaseWriteLock();
    }
  }
}
