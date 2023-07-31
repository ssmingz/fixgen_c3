class PlaceHold {
  public boolean contains(T arg) {
    return _previous.contains(new IdentityWrapper<T>(arg));
  }
}
