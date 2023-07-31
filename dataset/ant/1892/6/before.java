class PlaceHold {
  public String toString() {
    try {
      String[] line = getCommandline();
      return StringUtil.join(line, " ");
    } catch (TaskException e) {
      return e.toString();
    }
  }
}
