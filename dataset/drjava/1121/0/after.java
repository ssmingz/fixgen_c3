class SnapshotIterable {
  public SnapshotIterable(Iterator<? extends T> iterator) {
    _values = new ArrayList<T>(0);
    while (iterator.hasNext()) {
      _values.add(iterator.next());
    }
  }
}
