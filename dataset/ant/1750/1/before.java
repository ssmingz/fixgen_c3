class PlaceHold {
  private void checkPackageAndSourcePath() {
    if ((packageList != null) && (sourcePath == null)) {
      String msg = "sourcePath attribute must be set when " + "specifying packagelist.";
      throw new BuildException(msg);
    }
  }
}
