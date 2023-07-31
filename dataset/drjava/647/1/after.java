class PlaceHold {
  public boolean contains(T1 arg1, T2 arg2) {
    return _previous.containsKey(_pairFactory.value(arg1, arg2));
  }
}
