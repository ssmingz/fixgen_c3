class PlaceHold {
  protected void clean(BuildHelper helper) {
    helper.runDepends(this, "clean", "");
    System.out.println("clean: ");
  }
}
