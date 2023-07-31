class PlaceHold {
  public boolean contains(T arg) {
    return _previous.containsKey(new IdentityWrapper<T>(arg));
  }
}
