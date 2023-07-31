class PlaceHold {
  public boolean remove(T1 first, T2 second) {
    return ((Relation<T1, T2>) (_delegate)).remove(first, second);
  }
}
