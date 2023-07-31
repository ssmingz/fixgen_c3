class PlaceHold {
  private void _testFindNextSucceeds(
      FindReplaceMachine frm,
      ContinueCommand cont,
      int start,
      int found,
      OpenDefinitionsDocument doc) {
    try {
      FindResult fr = frm.findNext();
      OpenDefinitionsDocument d = fr.getDocument();
      if (frm.getDocument() != d) {
        frm.setDocument(d);
        frm.setPosition(found);
      }
      Utilities.clearEventQueue();
      assertEquals("findNext return value", found, fr.getFoundOffset());
      _assertOffsets(frm, start, found);
      assertTrue("on find text", frm.onMatch());
    } catch (Exception e) {
      fail("Threw exception: " + e);
    }
  }
}
