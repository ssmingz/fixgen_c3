class PlaceHold {
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append(_loc.x);
    sb.append(' ');
    sb.append(_loc.y);
    return sb.toString();
  }
}
