class PlaceHold {
  public boolean isMigrationCandidate(ILaunchConfiguration candidate) throws CoreException {
    if (getAttribute(MIGRATION_DELEGATE) != null) {
      if (fDelegates == null) {
        fDelegates = new Hashtable();
      }
      Object delegate = fDelegates.get(MIGRATION_DELEGATE);
      if (delegate == null) {
        delegate = getConfigurationElement().createExecutableExtension(MIGRATION_DELEGATE);
        fDelegates.put(MIGRATION_DELEGATE, delegate);
      }
      if (delegate instanceof ILaunchConfigurationMigrationDelegate) {
        return ((ILaunchConfigurationMigrationDelegate) delegate).isCandidate(candidate);
      }
    }
    return false;
  }
}
