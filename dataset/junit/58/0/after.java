class PlaceHold {
  @Override
  public String toString() {
    return (getTestHeader() + ": ") + fThrownException.getMessage();
  }
}
