class PlaceHold {
  public void test_deselectII() {
    int number = 5;
    String[] items = new String[number];
    for (int i = 0; i < number; i++) {
      items[i] = "fred" + i;
    }
    list.setItems(items);
    list.setSelection(items);
    int[][] cases =
        new int[][] {
          new int[] {3, 1},
          new int[] {-3, -2},
          new int[] {-2, -1},
          new int[] {-1, -1},
          new int[] {10, 1}
        };
    for (int i = 0; i < cases.length; ++i) {
      deselectII_helper(items, cases[i][0], cases[i][1], new int[] {0, 1, 2, 3, 4});
    }
    if (fCheckSWTPolicy) {
      deselectII_helper(items, -1, 3, new int[] {4});
      deselectII_helper(items, -1, 30, new int[] {});
    }
    deselectII_helper(items, 1, 3, new int[] {0, 4});
    deselectII_helper(items, 1, 1, new int[] {0, 2, 3, 4});
    String[] items2 = new String[] {"item0", "item1", "item2", "item3"};
    list.setItems(items2);
    list.setSelection(items2);
    assertArrayEquals(":a:", items2, list.getSelection());
    list.deselect(0, 0);
    assertArrayEquals(":b:", list.getSelectionIndices(), new int[] {1, 2, 3});
    list.deselect(0, 0);
    assertArrayEquals(":bbb:", list.getSelectionIndices(), new int[] {1, 2, 3});
    list.deselect(2, 3);
    assertArrayEquals(":bb:", list.getSelectionIndices(), new int[] {1});
    list.setSelection(items2);
    list.deselect(0, 2);
    assertArrayEquals(":dddd:", list.getSelectionIndices(), new int[] {3});
    list.setSelection(items2);
    list.deselect(2, 0);
    assertArrayEquals(":ddddd:", list.getSelectionIndices(), new int[] {0, 1, 2, 3});
    setSingleList();
    list.setItems(items2);
    list.deselectAll();
    list.select(0);
    list.deselect(-3, -2);
    assertArrayEquals(list.getSelectionIndices(), new int[] {0});
    list.deselect(-2, -1);
    assertArrayEquals(list.getSelectionIndices(), new int[] {0});
    list.deselect(-1, -1);
    assertArrayEquals(":e:", list.getSelectionIndices(), new int[] {0});
    list.setSelection(new String[] {"item3"});
    assertArrayEquals(list.getSelection(), new String[] {"item3"});
    list.deselect(1, 1);
    assertArrayEquals(list.getSelection(), new String[] {"item3"});
    list.deselect(0, 0);
    assertArrayEquals(list.getSelection(), new String[] {"item3"});
    list.deselect(3, 3);
    assertArrayEquals(list.getSelection(), new String[] {});
    list.setSelection(new String[] {"item3"});
    list.deselect(1, 2);
    assertArrayEquals(list.getSelection(), new String[] {"item3"});
    list.setSelection(new String[] {"item3"});
    list.deselect(0, 2);
    assertArrayEquals(list.getSelectionIndices(), new int[] {3});
    list.setSelection(new String[] {"item3"});
    list.deselect(1, 3);
    assertArrayEquals(list.getSelectionIndices(), new int[] {});
  }
}
