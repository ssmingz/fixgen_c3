class PlaceHold {
  public void setLocalProperties(LocalProperties localProperties) {
    if (localProperties == null) {
      localProperties = new LocalPropertyStack();
    }
    threadLocalProperties.set(localProperties);
  }
}
