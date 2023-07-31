class PlaceHold {
  public void test_getRowCount() {
    toolBar = new ToolBar(shell, SWT.WRAP);
    int number = 5;
    ToolItem[] items = new ToolItem[number];
    for (int i = 0; i < number; i++) {
      items[i] = new ToolItem(toolBar, 0);
    }
    assertTrue(":a:" + toolBar.getRowCount(), toolBar.getRowCount() == number);
    toolBar = new ToolBar(shell, 0);
    number = 5;
    items = new ToolItem[number];
    for (int i = 0; i < number; i++) {
      items[i] = new ToolItem(toolBar, 0);
    }
    assertTrue(":a:", toolBar.getRowCount() == 1);
  }
}
