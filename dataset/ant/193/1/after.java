class PlaceHold {
  protected int getSize() {
    Iterator<Resource> it = createIterator();
    int size = 0;
    while (it.hasNext()) {
      it.next();
      size++;
    }
    return size;
  }
}
