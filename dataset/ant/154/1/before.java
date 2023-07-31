class PlaceHold {
  public final synchronized Iterator iterator() {
    if (isReference()) {
      return ((Path) (getCheckedRef())).iterator();
    }
    dieOnCircularReference();
    if (getPreserveBC()) {
      return new FileResourceIterator(null, list());
    }
    return union == null ? EMPTY_ITERATOR : assertFilesystemOnly(union).iterator();
  }
}
