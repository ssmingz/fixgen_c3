class PlaceHold {
  protected void checkAttributes() throws BuildException {
    if (server == null) {
      throw new BuildException("server attribute must be set!");
    }
    if (userid == null) {
      throw new BuildException("userid attribute must be set!");
    }
    if (password == null) {
      throw new BuildException("password attribute must be set!");
    }
    if ((action == LIST_FILES) && (listing == null)) {
      throw new BuildException("listing attribute must be set for list " + "action!");
    }
    if ((action == MK_DIR) && (remotedir == null)) {
      throw new BuildException("remotedir attribute must be set for " + "mkdir action!");
    }
    if ((action == CHMOD) && (chmod == null)) {
      throw new BuildException("chmod attribute must be set for chmod " + "action!");
    }
    if ((action == SITE_CMD) && (siteCommand == null)) {
      throw new BuildException("sitecommand attribute must be set for site " + "action!");
    }
    if (this.isConfigurationSet) {
      try {
        Class.forName("org.apache.commons.net.ftp.FTPClientConfig");
      } catch (ClassNotFoundException e) {
        throw new BuildException(
            "commons-net.jar >= 1.4.0 is required for at least one of the attributes specified.");
      }
    }
  }
}
