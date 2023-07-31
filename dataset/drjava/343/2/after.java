class PlaceHold {
  public boolean contains(T arg) {
    return _previous.containsKey(_wrapperFactory.value(arg));
  }
}
