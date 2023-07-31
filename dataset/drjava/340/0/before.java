class PlaceHold {
  public void testRightShiftAssign() throws ExceptionReturnedException {
    String text = "X=8; X >>= 3";
    Object res = _interpreter.interpret(text);
    assertEquals("X should have the Integer value 1", "1", res.toString());
    res = _interpreter.interpret("X");
    assertTrue("X should have been an Integer", res instanceof Integer);
    assertEquals("X should have the Integer value 1", "1", res.toString());
  }
}
