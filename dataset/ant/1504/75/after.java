class PlaceHold {
  public Path createFilepath() throws TaskException {
    if (this.filepath == null) {
      this.filepath = new Path(getProject());
    }
    return this.filepath.createPath();
  }
}
