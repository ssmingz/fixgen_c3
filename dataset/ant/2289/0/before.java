class PlaceHold {
  protected int getSize() {
    Iterator it = createIterator();
    int size = 0;
    while (it.hasNext()) {
      it.next();
      size++;
    }
    return size;
  }
}
