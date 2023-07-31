class PlaceHold {
  public boolean recallNextInteractionInHistory() {
    if (hasHistoryNext()) {
      moveHistoryNext(getCurrentInteraction());
      return true;
    } else {
      _beep.run();
      return false;
    }
  }
}
