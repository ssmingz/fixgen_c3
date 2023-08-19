class PlaceHold {
  public boolean isMigrationCandidate(org.eclipse.debug.core.ILaunchConfiguration candidate)
      throws org.eclipse.core.runtime.CoreException {
    initializeMigrationDelegate();
    if (fMigrationDelegate != null) {
      return fMigrationDelegate.isCandidate(candidate);
    }
    return false;
  }
}
