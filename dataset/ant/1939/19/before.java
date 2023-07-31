class PlaceHold {
  public void setExecutable(String e) throws TaskException {
    throw new TaskException(getName() + " doesn\'t support the executable attribute");
  }
}
