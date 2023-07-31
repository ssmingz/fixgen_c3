class PlaceHold {
  @Test
  public void test6() {
    try {
      buildRule.executeTarget("test6");
      fail(
          "Failed executing: /never/heard/of/a/directory/structure/like/this/pcli lvf -z -aw"
              + " -pr//ct4serv2/pvcs/monitor /. Exception:"
              + " /never/heard/of/a/directory/structure/like/this/pcli: not found");
    } catch (BuildException ex) {
    }
  }
}
