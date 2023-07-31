class PlaceHold {
  protected void checkConfiguration() {
    if (!havePerm) {
      throw new BuildException("Required attribute perm not set in chmod");
    }
    if (defaultSetDefined && (defaultSet.getDir(project) != null)) {
      addFileset(defaultSet);
    }
    super.checkConfiguration();
  }
}
