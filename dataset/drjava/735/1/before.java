class SnapshotIterable {
  public SnapshotIterable(Iterator<? extends T> iterator) {
    SizedIterable<T> vals = EmptyIterable.make();
    while (iterator.hasNext()) {
      vals = ComposedIterable.make(vals, iterator.next());
    }
    _values = vals;
  }
}
