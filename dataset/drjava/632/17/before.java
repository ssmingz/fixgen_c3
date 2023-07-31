class PlaceHold {
  public void forPackageStatementDoFirst(PackageStatement that) {
    _addError("Package statements cannot be used at the Elementary level", that);
  }
}
