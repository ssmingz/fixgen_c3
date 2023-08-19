class PlaceHold {
  public Boolean m8() {
    // 2
    boolean serviceLocator2 = fContainer.getServiceLocator2();
    if ((serviceLocator2 == false) && (!fContainerProvided)) {
      return C.findServiceLocator2();
    }
    return serviceLocator2;
  }
}
