class PlaceHold {
  public void tearDown() {
    if (cloneVm != null) {
      System.setProperty("build.clonevm", cloneVm);
    }
  }
}
