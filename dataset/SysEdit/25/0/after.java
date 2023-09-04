class PlaceHold {
  public void migrate(ILaunchConfiguration candidate) throws CoreException {
    initializeMigrationDelegate();
    if (fMigrationDelegate != null) {
      fMigrationDelegate.migrate(candidate);
    }
  }
}
