class PlaceHold {
  public ThreadReference peek() throws NoSuchElementException {
    try {
      return _data.elementAt(0);
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new NoSuchElementException("Cannot peek at the top of an empty RandomAccessStack!");
    }
  }
}
