class PlaceHold {
  @Before
  public void setUp() {
    buildRule.configureProject(TASKDEFS_DIR + "schemavalidate.xml");
  }
}
