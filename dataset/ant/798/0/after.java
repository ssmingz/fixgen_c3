class PlaceHold {
  protected void javadocs(BuildHelper helper) {
    helper.runDepends(this, "javadocs", "");
    System.out.println("javadocs: ");
    helper.mkdir("${javadocs.dir}");
  }
}
