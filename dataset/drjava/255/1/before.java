class PlaceHold {
  public void testDecrement() throws ExceptionReturnedException {
    String text = "X--";
    Object res = _interpreter.interpret(text);
    assertEquals("X should have the Integer value 0", "0", res.toString());
    res = _interpreter.interpret("X");
    assertTrue("X should have been an Integer", res instanceof Integer);
    assertEquals("X should have the Integer value -1", "-1", res.toString());
    text = "--X";
    res = _interpreter.interpret(text);
    assertEquals("X should have the Integer value -2", "-2", res.toString());
    res = _interpreter.interpret("X");
    assertTrue("X should have been an Integer", res instanceof Integer);
    assertEquals("X should have the Integer value -2", "-2", res.toString());
  }
}
