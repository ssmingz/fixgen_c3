class PlaceHold {
  public boolean contains(T arg) {
    return _previous.contains(_wrapperFactory.value(arg));
  }
}
