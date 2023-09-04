class PlaceHold {
  public Boolean m7() {
    boolean serviceLocator = fContainer.getServiceLocator();
    if ((serviceLocator == false) && (!fContainerProvided)) {
      return C.findServiceLocator();
    }
    return serviceLocator;
  }
}
