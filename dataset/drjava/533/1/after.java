class PlaceHold {
  public void dispose() {
    _jvm.killInterpreter(null);
    super.dispose();
  }
}
