class PlaceHold {
  private void validate() throws BuildException {
    if (null == myFile) {
      myFile = getProject().resolveFile(DEFAULT_FILENAME);
    }
    if (!myFile.exists()) {
      try {
        FileUtils.newFileUtils().createNewFile(myFile);
      } catch (final IOException ioe) {
        final String message = myFile + " doesn't exist and new file can't be created.";
        throw new BuildException(message, ioe);
      }
    }
    if (!myFile.canRead()) {
      final String message = ("Unable to read from " + myFile) + ".";
      throw new BuildException(message);
    }
    if (!myFile.canWrite()) {
      final String message = ("Unable to write to " + myFile) + ".";
      throw new BuildException(message);
    }
  }
}
