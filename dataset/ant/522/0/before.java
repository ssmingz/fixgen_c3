class PlaceHold {
  public void setIgnore(String ignoreString) {
    project.log(
        "The ignore attribute is deprecated." + "Please use the excludes attribute.", MSG_WARN);
    if ((ignoreString != null) && (ignoreString.length() > 0)) {
      Vector tmpExcludes = new Vector();
      StringTokenizer tok = new StringTokenizer(ignoreString, ", ", false);
      while (tok.hasMoreTokens()) {
        addExclude().setName(("**/" + tok.nextToken().trim()) + "/**");
      }
    }
  }
}
