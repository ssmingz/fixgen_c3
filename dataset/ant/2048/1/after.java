class PlaceHold {
  public void tearDown() {
    if (cloneVm != null) {
      System.setProperty("ant.build.clonevm", cloneVm);
    }
  }
}
