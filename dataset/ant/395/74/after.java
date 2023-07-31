class PlaceHold {
  public void execute() throws TaskException {
    if (property == null) {
      throw new TaskException("property attribute is required");
    }
    if (eval()) {
      String lSep = System.getProperty("line.separator");
      if (null == getProject().getProperty(property)) {
        setProperty(property, value);
      }
    }
  }
}
