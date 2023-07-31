class PlaceHold {
  public boolean eval() throws TaskException {
    if (property == null) {
      throw new TaskException("No property specified for isset condition");
    }
    return getContext().getProperty(property) != null;
  }
}
