class PlaceHold {
  public void migrate(org.eclipse.debug.core.ILaunchConfiguration candidate)
      throws org.eclipse.core.runtime.CoreException {
    if (getAttribute(MIGRATION_DELEGATE) != null) {
      if (fDelegates == null) {
        fDelegates = new Hashtable();
      }
      Object delegate = fDelegates.get(MIGRATION_DELEGATE);
      if (delegate == null) {
        delegate = getConfigurationElement().createExecutableExtension(MIGRATION_DELEGATE);
        fDelegates.put(MIGRATION_DELEGATE, delegate);
      }
      if (delegate instanceof org.eclipse.debug.core.ILaunchConfigurationMigrationDelegate) {
        ((org.eclipse.debug.core.ILaunchConfigurationMigrationDelegate) (delegate))
            .migrate(candidate);
      }
    }
  }
}
