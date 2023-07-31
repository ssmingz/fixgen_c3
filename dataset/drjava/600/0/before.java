class Iterator {
  Iterator(Iterator that) {
    super(that);
    _offset = that.getBlockOffset();
  }
}
