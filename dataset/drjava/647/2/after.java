class PlaceHold {
  public boolean contains(T1 arg1, T2 arg2, int threshold) {
    return _previous.count(_pairFactory.value(arg1, arg2)) >= threshold;
  }
}
