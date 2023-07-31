class PlaceHold {
  public boolean contains(T1 arg1, T2 arg2) {
    return _previous.containsKey(new IdentityPair<T1, T2>(arg1, arg2));
  }
}
