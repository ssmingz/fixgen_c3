class PlaceHold {
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append((getTestHeader() + ": ") + fThrownException.getMessage());
    return sb.toString();
  }
}
