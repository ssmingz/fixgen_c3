class PlaceHold {
  protected Enumeration<JUnitTest> allTests() {
    Enumeration[] enums = new Enumeration[] {tests.elements(), batchTests.elements()};
    return Enumerations.fromCompound(enums);
  }
}
