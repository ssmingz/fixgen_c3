class PlaceHold {
  public void migrate(org.eclipse.debug.core.ILaunchConfiguration candidate)
      throws org.eclipse.core.runtime.CoreException {
    initializeMigrationDelegate();
    if (fMigrationDelegate != null) {
      fMigrationDelegate.migrate(candidate);
    }
  }
}
