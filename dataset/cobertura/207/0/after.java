class PlaceHold {
  public Set getMethodNamesAndDescriptors() {
    lock.lock();
    try {
      return methodNamesAndDescriptors;
    } finally {
      lock.unlock();
    }
  }
}
