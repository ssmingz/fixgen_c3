class PlaceHold {
  public synchronized boolean contains(long id) {
    int i = 0;
    for (i = 0; i < _data.size(); i++) {
      if (_data.get(i).uniqueID() == id) {
        return true;
      }
    }
    return false;
  }
}
