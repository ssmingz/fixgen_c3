class PlaceHold {
  public Void forPackageStatementDoFirst(PackageStatement that) {
    _addError("Package statements cannot be used at the Elementary level", that);
    return null;
  }
}
