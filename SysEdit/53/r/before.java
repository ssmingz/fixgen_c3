class PlaceHold {
  public Boolean m7() {
    // 1
    if (fContainer == null) {
      return C.findServiceLocator();
    }
    return fContainer.getServiceLocator();
  }
}
