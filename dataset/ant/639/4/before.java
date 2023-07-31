class PlaceHold {
  public void setReferenceFiles(Path path) {
    if (referenceFiles == null) {
      referenceFiles = new Path(this.project);
    }
    referenceFiles.append(path);
  }
}
