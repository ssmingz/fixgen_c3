class PlaceHold {
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append((fFailedTest + ": ") + fThrownException.getMessage());
    return sb.toString();
  }
}
