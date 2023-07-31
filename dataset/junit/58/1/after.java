class PlaceHold {
  @Override
  public String toString() {
    return (fFailedTest + ": ") + fThrownException.getMessage();
  }
}
