class PlaceHold {
  protected int generateHashCode() {
    return (_first.hashCode() ^ (_second.hashCode() << 1)) ^ (_third.hashCode() << 2);
  }
}
