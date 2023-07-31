class PlaceHold {
  public int hashCode() {
    return (name.hashCode() ^ height) ^ style;
  }
}
