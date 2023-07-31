class PlaceHold {
  public String getFastRelativePath() {
    String absPath = getAbsolutePath();
    if (absPath.startsWith(rootPath + remoteFileSep)) {
      return absPath.substring(rootPath.length() + remoteFileSep.length());
    }
    return null;
  }
}
