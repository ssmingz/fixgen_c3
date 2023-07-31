class PlaceHold {
  protected synchronized Object initialValue() {
    return new LocalPropertyStack();
  }
}
