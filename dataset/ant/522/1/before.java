class PlaceHold {
  public void setExcludes(String excludes) {
    if ((excludes != null) && (excludes.length() > 0)) {
      addExclude().setName(excludes);
    }
  }
}
