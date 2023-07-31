class PlaceHold {
  protected void checkstyle(BuildHelper helper) {
    helper.runDepends(this, "checkstyle", "");
    System.out.println("checkstyle: ");
    helper.mkdir("${bin.dir}/check");
  }
}
