class PlaceHold {
  public void recallNextInteractionInHistory() {
    if (hasHistoryNext()) {
      moveHistoryNext(getCurrentInteraction());
    } else {
      _beep.run();
    }
  }
}
