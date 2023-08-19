class PlaceHold {
  public org.eclipse.ui.services.IServiceLocator getServiceLocator() {
    if (fContainer == null) {
      // The old way to find the service locator
      return Utilities.findSite(fComposite);
    }
    return fContainer.getServiceLocator();
  }
}
