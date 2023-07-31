class PlaceHold {
  public void setExcludesfile(String excludesfile) {
    if ((excludesfile != null) && (excludesfile.length() > 0)) {
      File excl = project.resolveFile(excludesfile);
      if (!excl.exists()) {
        project.log(("Excludesfile " + excludesfile) + " not found.", MSG_ERR);
      } else {
        readPatterns(excl, excludeList);
      }
    }
  }
}
