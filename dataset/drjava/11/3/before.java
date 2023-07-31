class PlaceHold {
  private void _testFindNextSucceeds(
      FindReplaceMachine frm, ContinueCommand cont, int start, int found) {
    try {
      int findOffset = frm.findNext().getFoundOffset();
      assertEquals("findNext return value", found, findOffset);
      _assertOffsets(frm, start, found);
      assertTrue("on find text", frm.isOnMatch());
    } catch (Exception e) {
      fail("Threw exception: " + e);
    }
  }
}
