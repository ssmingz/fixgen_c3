class PlaceHold {
  public Boolean m7() {
    if (fContainer == null) {
      return C.findServiceLocator();
    }
    return fContainer.getServiceLocator();
  }
}
