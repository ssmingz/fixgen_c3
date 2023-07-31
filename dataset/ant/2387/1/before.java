class PlaceHold {
  public Iterator iterator() {
    return new FileResourceIterator(null, parts);
  }
}
