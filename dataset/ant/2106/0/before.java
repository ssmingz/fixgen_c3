class PlaceHold {
  public URL nextElement() {
    URL ret = this.nextResource;
    if (ret == null) {
      throw new NoSuchElementException();
    }
    findNextResource();
    return ret;
  }
}
