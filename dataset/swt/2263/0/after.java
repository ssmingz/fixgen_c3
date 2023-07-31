class PlaceHold {
  public void test_removeI() {
    try {
      combo.remove(0);
      fail("No exception thrown for illegal index argument");
    } catch (IllegalArgumentException e) {
    }
    try {
      combo.remove(3);
      fail("No exception thrown for illegal index argument");
    } catch (IllegalArgumentException e) {
    }
    combo.add("string0");
    try {
      combo.remove(-1);
      fail("No exception thrown for illegal index argument");
    } catch (IllegalArgumentException e) {
    }
    combo.removeAll();
    int number = 5;
    for (int i = 0; i < number; i++) {
      combo.add("fred" + i);
    }
    for (int i = 0; i < number; i++) {
      assertEquals("Wrong number of items", number - i, combo.getItemCount());
      combo.remove(0);
    }
    for (int i = 0; i < number; i++) {
      combo.add("fred");
    }
    for (int i = 0; i < number; i++) {
      assertEquals("Wrong number of items", number - i, combo.getItemCount());
      combo.remove(0);
    }
    for (int i = 0; i < number; i++) {
      combo.add("fred" + i);
    }
    for (int i = 0; i < number; i++) {
      assertEquals("index " + i, number - i, combo.getItemCount());
      combo.select(0);
      assertEquals("index " + i, 0, combo.getSelectionIndex());
      combo.remove(0);
      if ((SwtJunit.isWindows || SwtJunit.isGTK) || SwtJunit.isCarbon) {
        assertEquals("index " + i, -1, combo.getSelectionIndex());
      } else if (i < (number - 1)) {
        assertEquals("index " + i, 0, combo.getSelectionIndex());
      } else {
        assertEquals("index " + i, -1, combo.getSelectionIndex());
      }
    }
    for (int i = 0; i < number; i++) {
      combo.add("fred" + i);
    }
    for (int i = 0; i < number; i++) {
      assertEquals("index " + i, number - i, combo.getItemCount());
      combo.remove((number - i) - 1);
    }
  }
}
