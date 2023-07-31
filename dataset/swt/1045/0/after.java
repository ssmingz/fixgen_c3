class PlaceHold {
  public void test_consistency_Segments() {
    if (!SwtTestUtil.isWindows) {
      if (SwtTestUtil.verbose) {
        System.out.println(
            "Excluded"
                + " test_consistency_Segments(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_widgets_Combo).");
      }
      return;
    }
    final SegmentListener sl1 =
        new SegmentListener() {
          public void getSegments(SegmentEvent event) {
            if ((event.lineText.length() & 1) == 1) {
              event.segments = new int[] {1, event.lineText.length()};
              event.segmentsChars = null;
            } else {
              event.segments = new int[] {0, 0, event.lineText.length()};
              event.segmentsChars = new char[] {':', '<', '>'};
            }
            listenerCalled = true;
          }
        };
    try {
      combo.addSegmentListener(null);
      fail("No exception thrown for addSegmentListener(null)");
    } catch (IllegalArgumentException e) {
    }
    combo.addSegmentListener(sl1);
    doSegmentsTest(true);
    combo.addSegmentListener(sl1);
    doSegmentsTest(true);
    combo.removeSegmentListener(sl1);
    doSegmentsTest(true);
    combo.removeSegmentListener(sl1);
    combo.setText(combo.getText());
    doSegmentsTest(false);
  }
}
