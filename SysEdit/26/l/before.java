class PlaceHold {
  private synchronized void initializeComparators() {
    if (fComparators == null) {
      IExtensionPoint extensionPoint =
          Platform.getExtensionRegistry()
              .getExtensionPoint(
                  DebugPlugin.getUniqueIdentifier(),
                  DebugPlugin.EXTENSION_POINT_LAUNCH_CONFIGURATION_COMPARATORS);
      IConfigurationElement[] infos = extensionPoint.getConfigurationElements();
      fComparators = new HashMap(infos.length);
      for (int i = 0; i < infos.length; i++) {
        IConfigurationElement configurationElement = infos[i];
        String attr = configurationElement.getAttribute("attribute"); // $NON-NLS-1$
        if (attr != null) {
          fComparators.put(attr, new LaunchConfigurationComparator(configurationElement));
        } else {
          // invalid status handler
          IStatus s =
              new Status(
                  IStatus.ERROR,
                  DebugPlugin.getUniqueIdentifier(),
                  DebugException.INTERNAL_ERROR,
                  MessageFormat.format(
                      "Invalid launch configuration comparator extension defined by plug-in {0} -"
                          + " attribute not specified.",
                      new String[] {configurationElement.getContributor().getName()}),
                  null); //$NON-NLS-1$
          DebugPlugin.log(s);
        }
      }
    }
  }
}
