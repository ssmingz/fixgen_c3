class PlaceHold {
  public static Test suite() {
    TestSuite suite = new TestSuite("Test for J2EE checks");
    suite.addTest(new TestSuite(EntityBeanCheckTest.class));
    suite.addTest(new TestSuite(EntityBeanEjbCreateCheckTest.class));
    suite.addTest(new TestSuite(EntityBeanEjbHomeCheckTest.class));
    suite.addTest(new TestSuite(EntityBeanEjbPostCreateCheckTest.class));
    suite.addTest(new TestSuite(EntityBeanEjbSelectCheckTest.class));
    suite.addTest(new TestSuite(EntityBeanFindByPrimaryKeyCheckTest.class));
    suite.addTest(new TestSuite(EntityBeanFinderCheckTest.class));
    suite.addTest(new TestSuite(EntityBeanMatchEjbCreateCheckTest.class));
    suite.addTest(new TestSuite(HomeInterfaceCheckTest.class));
    suite.addTest(new TestSuite(LocalHomeInterfaceCheckTest.class));
    suite.addTest(new TestSuite(MessageBeanCheckTest.class));
    suite.addTest(new TestSuite(RemoteInterfaceCheckTest.class));
    suite.addTest(new TestSuite(SessionBeanCheckTest.class));
    suite.addTest(new TestSuite(SessionBeanEjbCreateCheckTest.class));
    suite.addTest(new TestSuite(FinalStaticCheckTest.class));
    suite.addTest(new TestSuite(ThisParameterCheckTest.class));
    suite.addTest(new TestSuite(ThisReturnCheckTest.class));
    return suite;
  }
}
