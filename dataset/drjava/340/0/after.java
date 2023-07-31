class PlaceHold {
  public void testRightShiftAssign() throws InterpreterException {
    String text = "X=8; X >>= 3";
    Object res = interpret(text);
    assertEquals("X should have the Integer value 1", "1", res.toString());
    res = interpret("X");
    assertTrue("X should have been an Integer", res instanceof Integer);
    assertEquals("X should have the Integer value 1", "1", res.toString());
  }
}
