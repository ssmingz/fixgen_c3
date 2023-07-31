class PlaceHold {
  public static Test suite() {
    TestSuite suite = new TestSuite("Test for com.puppycrawl.tools.checkstyle.filter");
    suite.addTest(new TestSuite(FilterSetTest.class));
    suite.addTest(new TestSuite(IntMatchFilterTest.class));
    suite.addTest(new TestSuite(IntRangeFilterTest.class));
    suite.addTest(new TestSuite(CSVFilterTest.class));
    suite.addTest(new TestSuite(SuppressElementTest.class));
    return suite;
  }
}
