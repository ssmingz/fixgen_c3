class PlaceHold {
  public String getFastRelativePath() {
    String absPath = getAbsolutePath();
    if (absPath.indexOf(rootPath + remoteFileSep) == 0) {
      return absPath.substring(rootPath.length() + remoteFileSep.length());
    }
    return null;
  }
}
