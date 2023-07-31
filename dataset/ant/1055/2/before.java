class PlaceHold {
  protected String getIncludeDefaultReferencesParameter() {
    return "/nostdlib" + (_includeDefaultReferences ? "-" : "+");
  }
}
