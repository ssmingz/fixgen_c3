class PlaceHold {
  private synchronized void initializeSourceLocators() {
    if (fSourceLocators == null) {
      IExtensionPoint extensionPoint =
          Platform.getExtensionRegistry()
              .getExtensionPoint(
                  DebugPlugin.getUniqueIdentifier(), DebugPlugin.EXTENSION_POINT_SOURCE_LOCATORS);
      IConfigurationElement[] infos = extensionPoint.getConfigurationElements();
      fSourceLocators = new HashMap(infos.length);
      for (int i = 0; i < infos.length; i++) {
        IConfigurationElement configurationElement = infos[i];
        String id = configurationElement.getAttribute("id"); // $NON-NLS-1$
        if (id != null) {
          fSourceLocators.put(id, configurationElement);
        } else {
          // invalid status handler
          IStatus s =
              new Status(
                  IStatus.ERROR,
                  DebugPlugin.getUniqueIdentifier(),
                  DebugException.INTERNAL_ERROR,
                  MessageFormat.format(
                      "Invalid source locator extension defined by plug-in \"{0}\": \"id\" not"
                          + " specified.",
                      new String[] {configurationElement.getContributor().getName()}),
                  null); //$NON-NLS-1$
          DebugPlugin.log(s);
        }
      }
    }
  }
}
