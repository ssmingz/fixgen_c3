class PlaceHold {
  public void test_getItems() {
    int[] cases = new int[] {0, 10, 100};
    TreeItem[][] items = new TreeItem[cases.length][];
    for (int j = 0; j < cases.length; j++) {
      items[j] = new TreeItem[cases[j]];
    }
    for (int j = 0; j < cases.length; j++) {
      for (int i = 0; i < cases[j]; i++) {
        TreeItem ti = new TreeItem(treeItem, 0);
        items[j][i] = ti;
      }
      assertEquals(items[j], treeItem.getItems());
      for (int i = 0; i < cases[j]; i++) {
        items[j][i].dispose();
      }
      assertEquals(0, treeItem.getItemCount());
    }
  }
}
