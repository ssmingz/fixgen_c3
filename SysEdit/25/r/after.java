class PlaceHold {
  public boolean isMigrationCandidate(ILaunchConfiguration candidate) throws CoreException {
    initializeMigrationDelegate();
    if (fMigrationDelegate != null) {
      return fMigrationDelegate.isCandidate(candidate);
    }
    return false;
  }
}
