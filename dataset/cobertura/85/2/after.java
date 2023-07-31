class PlaceHold {
  public void visitLineNumber(int line, Label start) {
    currentLine = line;
    currentJump = 0;
    instrumentGetClassData();
    mv.visitIntInsn(SIPUSH, line);
    mv.visitMethodInsn(INVOKESTATIC, TOUCH_COLLECTOR_CLASS, "touch", "(Ljava/lang/String;I)V");
    super.visitLineNumber(line, start);
  }
}
