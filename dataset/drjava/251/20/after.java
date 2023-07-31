class PlaceHold {
  public synchronized ThreadReference peekAt(int i) throws NoSuchElementException {
    try {
      return _data.get(i);
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new NoSuchElementException(("Cannot peek at element " + i) + " of this stack!");
    }
  }
}
