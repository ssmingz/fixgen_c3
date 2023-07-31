class PlaceHold {
  protected void checkConfiguration() {
    if (!havePerm) {
      throw new BuildException("Required attribute perm not set in chmod", location);
    }
    if (defaultSetDefined && (defaultSet.getDir(getProject()) != null)) {
      addFileset(defaultSet);
    }
    super.checkConfiguration();
  }
}
