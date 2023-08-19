class PlaceHold {
  private synchronized void initializeSourceLocators() {
    if (fSourceLocators == null) {
      org.eclipse.core.runtime.IExtensionPoint extensionPoint =
          org.eclipse.core.runtime.Platform.getExtensionRegistry()
              .getExtensionPoint(
                  org.eclipse.debug.core.DebugPlugin.getUniqueIdentifier(),
                  DebugPlugin.EXTENSION_POINT_SOURCE_LOCATORS);
      org.eclipse.core.runtime.IConfigurationElement[] infos =
          extensionPoint.getConfigurationElements();
      fSourceLocators = new HashMap(infos.length);
      org.eclipse.core.runtime.IConfigurationElement configurationElement = null;
      String id = null;
      for (int i = 0; i < infos.length; i++) {
        configurationElement = infos[i];
        id = configurationElement.getAttribute(ID);
        if (id != null) {
          fSourceLocators.put(id, configurationElement);
        } else {
          // invalid status handler
          org.eclipse.core.runtime.IStatus s =
              new org.eclipse.core.runtime.Status(
                  org.eclipse.core.runtime.IStatus.ERROR,
                  org.eclipse.debug.core.DebugPlugin.getUniqueIdentifier(),
                  org.eclipse.debug.core.DebugException.INTERNAL_ERROR,
                  com.ibm.icu.text.MessageFormat.format(
                      "Invalid source locator extension defined by plug-in \"{0}\": \"id\" not"
                          + " specified.",
                      new String[] {configurationElement.getContributor().getName()}),
                  null); // $NON-NLS-1$

          org.eclipse.debug.core.DebugPlugin.log(s);
        }
      }
    }
  }
}
