class PlaceHold {
  public Boolean m10() {
    // 4
    boolean serviceLocator4 = fContainer.getServiceLocator4();
    if ((serviceLocator4 == false) && (!fContainerProvided)) {
      return C.findServiceLocator4();
    }
    return serviceLocator4;
  }
}
