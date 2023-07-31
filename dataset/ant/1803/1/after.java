class PlaceHold {
  public void addConfiguredManifest(Manifest newManifest) throws ManifestException {
    if (configuredManifest == null) {
      configuredManifest = newManifest;
    } else {
      configuredManifest.merge(newManifest, false, mergeClassPaths);
    }
    savedConfiguredManifest = configuredManifest;
  }
}
