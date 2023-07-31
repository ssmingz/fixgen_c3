class PlaceHold {
  protected boolean isUpToDate(File jarFile, File signedjarFile) {
    if (null == jarFile) {
      return false;
    }
    if (null != signedjarFile) {
      if (!jarFile.exists()) {
        return false;
      }
      if (!signedjarFile.exists()) {
        return false;
      }
      if (jarFile.equals(signedjarFile)) {
        return false;
      }
      if (FileUtils.newFileUtils().isUpToDate(jarFile, signedjarFile)) {
        return true;
      }
    } else if (lazy) {
      return isSigned(jarFile);
    }
    return false;
  }
}
