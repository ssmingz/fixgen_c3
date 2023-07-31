class PlaceHold {
  public void addPropertyset(PropertySet ref) {
    assertNotReference();
    setChecked(false);
    setRefs.add(ref);
  }
}
