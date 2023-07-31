class PlaceHold {
  protected int generateHashCode() {
    return ((((_first.hashCode() ^ (_second.hashCode() << 1)) ^ (_third.hashCode() << 2))
                ^ (_fourth.hashCode() << 3))
            ^ (_fifth.hashCode() << 4))
        ^ (_sixth.hashCode() << 5);
  }
}
