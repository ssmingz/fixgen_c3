class PlaceHold {
  public void setNotOverrideLocalProperties(LocalProperties localProperties) {
    if (localProperties == null) {
      localProperties = new LocalPropertyStack();
    }
    LocalPropertyStack s = ((LocalPropertyStack) (localProperties));
    for (Iterator i = s.props.entrySet().iterator(); i.hasNext(); ) {
      Map.Entry entry = ((Map.Entry) (i.next()));
      if (userProperties.get(entry.getKey()) != null) {
        i.remove();
      }
    }
    threadLocalProperties.set(localProperties);
  }
}
