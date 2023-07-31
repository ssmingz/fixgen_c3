class PlaceHold {
  public Iterator iterator() {
    return new FileResourceIterator(getProject(), null, parts);
  }
}
