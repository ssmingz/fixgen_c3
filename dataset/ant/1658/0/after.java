class PlaceHold {
  protected Enumeration<JUnitTest> getIndividualTests() {
    final int count = batchTests.size();
    final Enumeration[] enums = new Enumeration[count + 1];
    for (int i = 0; i < count; i++) {
      final BatchTest batchtest = batchTests.elementAt(i);
      enums[i] = batchtest.elements();
    }
    enums[enums.length - 1] = tests.elements();
    return Enumerations.fromCompound(enums);
  }
}
