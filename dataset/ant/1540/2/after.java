class PlaceHold {
  protected void bindToKeystore(final ExecTask cmd) {
    if (null != keystore) {
      addValue(cmd, "-keystore");
      String loc;
      File keystoreFile = getProject().resolveFile(keystore);
      if (keystoreFile.exists()) {
        loc = keystoreFile.getPath();
      } else {
        loc = keystore;
      }
      addValue(cmd, loc);
    }
    if (null != storetype) {
      addValue(cmd, "-storetype");
      addValue(cmd, storetype);
    }
  }
}
