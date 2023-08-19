class PlaceHold {
  public org.eclipse.ui.services.IServiceLocator getServiceLocator() {
    org.eclipse.ui.services.IServiceLocator serviceLocator = fContainer.getServiceLocator();
    if ((serviceLocator == null) && (!fContainerProvided)) {
      // The old way to find the service locator
      return Utilities.findSite(fComposite);
    }
    return serviceLocator;
  }
}
