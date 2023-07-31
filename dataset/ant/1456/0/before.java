class PlaceHold {
  @Override
  public void execute() throws BuildException {
    if (toUri == null) {
      throw exactlyOne(TO_ATTRS);
    }
    if ((fromUri == null) && (fileSets == null)) {
      throw exactlyOne(FROM_ATTRS, "one or more nested filesets");
    }
    try {
      if (isFromRemote && (!isToRemote)) {
        download(fromUri, toUri);
      } else if ((!isFromRemote) && isToRemote) {
        if (fileSets != null) {
          upload(fileSets, toUri);
        } else {
          upload(fromUri, toUri);
        }
      } else if (isFromRemote && isToRemote) {
        throw new BuildException(
            "Copying from a remote server to a remote server is not supported.");
      } else {
        throw new BuildException(
            ("'todir' and 'file' attributes " + "must have syntax like the following: ")
                + "user:password@host:/path");
      }
    } catch (Exception e) {
      if (getFailonerror()) {
        if (e instanceof BuildException) {
          BuildException be = ((BuildException) (e));
          if (be.getLocation() == null) {
            be.setLocation(getLocation());
          }
          throw be;
        } else {
          throw new BuildException(e);
        }
      } else {
        log("Caught exception: " + e.getMessage(), MSG_ERR);
      }
    }
  }
}
