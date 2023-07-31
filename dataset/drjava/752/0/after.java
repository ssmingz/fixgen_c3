class PlaceHold {
  public boolean inProgress() {
    return !_document.hasPrompt();
  }
}
