class Iterator {
  Iterator(Iterator that) {
    ((ModelList<ReducedToken>) (this)).super(that);
    _offset = that.getBlockOffset();
  }
}
