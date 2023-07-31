class PlaceHold {
  public void delete() throws BuildException {
    try {
      if (link == null) {
        handleError("Must define the link name for symlink!");
        return;
      }
      log("Removing symlink: " + link);
      deleteSymlink(link);
    } catch (FileNotFoundException fnfe) {
      handleError(fnfe.toString());
    } catch (IOException ioe) {
      handleError(ioe.toString());
    } finally {
      setDefaults();
    }
  }
}
