class PlaceHold {
  public void dispose() {
    _interpreterControl.killInterpreter(null);
    super.dispose();
  }
}
