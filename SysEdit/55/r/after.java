class PlaceHold {
  public Boolean m9() {
    // 3
    boolean serviceLocator3 = fContainer.getServiceLocator3();
    if ((serviceLocator3 == false) && (!fContainerProvided)) {
      return C.findServiceLocator3();
    }
    return serviceLocator3;
  }
}
