class PlaceHold {
  public void execute() throws TaskException {
    validate();
    final File src = getSrc();
    final File dest = getDest();
    if (src.lastModified() > dest.lastModified()) {
      final String message =
          (("Expanding " + src.getAbsolutePath()) + " to ") + dest.getAbsolutePath();
      getLogger().info(message);
      extract();
    }
  }
}
