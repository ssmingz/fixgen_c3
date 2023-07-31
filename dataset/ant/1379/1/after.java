class PlaceHold {
  public String getFastRelativePath() {
    String absPath = getAbsolutePath();
    if (absPath.startsWith(rootPath + task.getSeparator())) {
      return absPath.substring(rootPath.length() + task.getSeparator().length());
    }
    return null;
  }
}
