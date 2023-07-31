class PlaceHold {
  int absOffset() {
    int off = _offset;
    ModelList<ReducedToken>.Iterator it = _cursor.copy();
    if (!it.atStart()) {
      it.prev();
    }
    while (!it.atStart()) {
      off += it.current().getSize();
      it.prev();
    }
    it.dispose();
    return off;
  }
}
