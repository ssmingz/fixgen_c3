class PlaceHold {
  public String[] getVariables() throws TaskException {
    if (variables.size() == 0) {
      return null;
    }
    String[] result = new String[variables.size()];
    for (int i = 0; i < result.length; i++) {
      result[i] = ((Variable) (variables.elementAt(i))).getContent();
    }
    return result;
  }
}
