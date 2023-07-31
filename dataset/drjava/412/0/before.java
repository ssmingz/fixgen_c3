class PlaceHold {
  public void replReturnedResult(String result) {
    _docAppend(result + "\n", DEFAULT_STYLE);
    _interactionIsOver();
  }
}
