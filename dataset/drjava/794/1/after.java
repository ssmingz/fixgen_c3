class PlaceHold {
  public boolean recallPreviousInteractionInHistory() {
    if (hasHistoryPrevious()) {
      moveHistoryPrevious(getCurrentInteraction());
      return true;
    } else {
      _beep.run();
      return false;
    }
  }
}
