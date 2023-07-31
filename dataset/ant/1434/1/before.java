class PlaceHold {
  public OutputStream getOutputStream() throws IOException {
    if (isReference()) {
      return ((Resource) (getCheckedRef())).getOutputStream();
    }
    if (isExists()) {
      throw new ImmutableResourceException();
    }
    return new PropertyOutputStream(getProject(), getName());
  }
}
