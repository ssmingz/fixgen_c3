class PlaceHold {
  public void test_consistency_KeyExpand() {
    Vector<String> events = new Vector<String>();
    createTableTree(events, true);
    tableTree.setSelection(new TableTreeItem[] {tableTree.getItems()[0]});
    int code = SWT.ARROW_RIGHT;
    if (SwtTestUtil.isGTK) {
      code = SWT.KEYPAD_ADD;
    }
    consistencyEvent(0, code, 0, 0, KEY_PRESS, events);
  }
}
