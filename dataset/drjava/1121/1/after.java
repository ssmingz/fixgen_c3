class SnapshotIterable {
  public SnapshotIterable(Iterable<? extends T> iterable) {
    _values = new ArrayList<T>(0);
    for (T e : iterable) {
      _values.add(e);
    }
  }
}
