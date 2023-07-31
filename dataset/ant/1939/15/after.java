class PlaceHold {
  public void setExecutable(String e) throws TaskException {
    throw new TaskException(getContext().getName() + " doesn\'t support the executable attribute");
  }
}
