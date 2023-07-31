class PlaceHold {
  public synchronized boolean recallPreviousInteractionInHistory() {
    if (hasHistoryPrevious()) {
      moveHistoryPrevious(getCurrentInteraction());
      return true;
    }
    _beep.run();
    return false;
  }
}
