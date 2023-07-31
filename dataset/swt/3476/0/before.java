class PlaceHold {
  public void test_setTextLjava_lang_String() {
    String[] cases = new String[] {"", "fred", "fred0"};
    for (int i = 0; i < cases.length; i++) {
      combo.setText(cases[i]);
      assertTrue(":a:" + i, combo.getText().equals(cases[i]));
    }
    for (int i = 0; i < 5; i++) {
      combo.add("fred");
    }
    for (int i = 0; i < cases.length; i++) {
      combo.setText(cases[i]);
      assertTrue(":b:" + i, combo.getText().equals(cases[i]));
    }
    for (int i = 0; i < 5; i++) {
      combo.add("fred" + i);
    }
    for (int i = 0; i < cases.length; i++) {
      combo.setText(cases[i]);
      assertTrue(":c:" + i, combo.getText().equals(cases[i]));
    }
  }
}
