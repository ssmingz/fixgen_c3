class PlaceHold {
  public void addPropertyref(PropertyRef ref) {
    assertNotReference();
    setChecked(false);
    ptyRefs.add(ref);
  }
}
