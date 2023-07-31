class PlaceHold {
  @Override
  public URL nextElement() {
    final URL ret = this.nextResource;
    if (ret == null) {
      throw new NoSuchElementException();
    }
    findNextResource();
    return ret;
  }
}
