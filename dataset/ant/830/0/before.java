class PlaceHold {
  public void test6() {
    expectBuildException(
        "test6",
        "Failed executing: /never/heard/of/a/directory/structure/like/this/pcli lvf -z -aw"
            + " -pr//ct4serv2/pvcs/monitor /. Exception:"
            + " /never/heard/of/a/directory/structure/like/this/pcli: not found");
  }
}
