class PlaceHold {
  public void test_getItems() {
    int[] cases = new int[] {0, 10, 100};
    TreeItem[][] items = new TreeItem[cases.length][];
    for (int j = 0; j < cases.length; j++) {
      items[j] = new TreeItem[cases[j]];
    }
    for (int j = 0; j < cases.length; j++) {
      for (int i = 0; i < cases[j]; i++) {
        TreeItem ti = new TreeItem(tree, 0);
        items[j][i] = ti;
      }
      assertEquals(items[j], tree.getItems());
      tree.removeAll();
      assertEquals(0, tree.getItemCount());
    }
    makeCleanEnvironment(false);
    for (int j = 0; j < cases.length; j++) {
      for (int i = 0; i < cases[j]; i++) {
        TreeItem ti = new TreeItem(tree, 0);
        ti.setText(String.valueOf(i));
      }
      TreeItem[] items2 = tree.getItems();
      for (int i = 0; i < items2.length; i++) {
        assertEquals(String.valueOf(i), items2[i].getText());
      }
      tree.removeAll();
      assertEquals(0, tree.getItemCount());
    }
  }
}
