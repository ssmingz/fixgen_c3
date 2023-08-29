class PlaceHold {
  public IServiceLocator getServiceLocator() {
    IServiceLocator serviceLocator = fContainer.getServiceLocator();
    if (serviceLocator == null && !fContainerProvided) {
      // The old way to find the service locator
      return Utilities.findSite(fComposite);
    }
    return serviceLocator;
  }
}
