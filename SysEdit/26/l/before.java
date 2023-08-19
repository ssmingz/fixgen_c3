class PlaceHold {
  private synchronized void initializeComparators() {
    if (fComparators == null) {
      org.eclipse.core.runtime.IExtensionPoint extensionPoint =
          org.eclipse.core.runtime.Platform.getExtensionRegistry()
              .getExtensionPoint(
                  org.eclipse.debug.core.DebugPlugin.getUniqueIdentifier(),
                  DebugPlugin.EXTENSION_POINT_LAUNCH_CONFIGURATION_COMPARATORS);
      org.eclipse.core.runtime.IConfigurationElement[] infos =
          extensionPoint.getConfigurationElements();
      fComparators = new HashMap(infos.length);
      for (int i = 0; i < infos.length; i++) {
        org.eclipse.core.runtime.IConfigurationElement configurationElement = infos[i];
        String attr = configurationElement.getAttribute("attribute"); // $NON-NLS-1$

        if (attr != null) {
          fComparators.put(
              attr,
              new org.eclipse.debug.internal.core.LaunchConfigurationComparator(
                  configurationElement));
        } else {
          // invalid status handler
          org.eclipse.core.runtime.IStatus s =
              new org.eclipse.core.runtime.Status(
                  org.eclipse.core.runtime.IStatus.ERROR,
                  org.eclipse.debug.core.DebugPlugin.getUniqueIdentifier(),
                  org.eclipse.debug.core.DebugException.INTERNAL_ERROR,
                  com.ibm.icu.text.MessageFormat.format(
                      "Invalid launch configuration comparator extension defined by plug-in {0} -"
                          + " attribute not specified.",
                      new String[] {configurationElement.getContributor().getName()}),
                  null); // $NON-NLS-1$

          org.eclipse.debug.core.DebugPlugin.log(s);
        }
      }
    }
  }
}
