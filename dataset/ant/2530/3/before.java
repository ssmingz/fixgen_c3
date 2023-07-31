class PlaceHold {
  public Package[] getIncludedPackages() {
    int count = packagesIncluded.size();
    Package[] packages = new Package[count];
    for (int i = 0; i < count; i++) {
      packages[i] = ((Package) (packagesIncluded.elementAt(i)));
    }
    return packages;
  }
}
