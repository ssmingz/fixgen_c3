class PlaceHold {
  public void setReferenceFiles(Path path) throws TaskException {
    if (_referenceFiles == null) {
      _referenceFiles = new Path(this.project);
    }
    _referenceFiles.append(path);
  }
}
