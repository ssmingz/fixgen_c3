class PlaceHold {
  public String getFastRelativePath() {
    String absPath = getAbsolutePath();
    if (absPath.indexOf(rootPath + task.getSeparator()) == 0) {
      return absPath.substring(rootPath.length() + task.getSeparator().length());
    }
    return null;
  }
}
