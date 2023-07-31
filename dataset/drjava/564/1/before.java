class PlaceHold {
  public synchronized boolean recallNextInteractionInHistory() {
    if (hasHistoryNext()) {
      moveHistoryNext(getCurrentInteraction());
      return true;
    }
    _beep.run();
    return false;
  }
}
