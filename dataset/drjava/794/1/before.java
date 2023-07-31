class PlaceHold {
  public void recallPreviousInteractionInHistory() {
    if (hasHistoryPrevious()) {
      moveHistoryPrevious(getCurrentInteraction());
    } else {
      _beep.run();
    }
  }
}
