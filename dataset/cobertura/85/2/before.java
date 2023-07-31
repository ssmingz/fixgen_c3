class PlaceHold {
  public void visitLineNumber(int line, Label start) {
    currentLine = line;
    currentJump = 0;
    instrumentGetClassData();
    mv.visitIntInsn(SIPUSH, line);
    mv.visitMethodInsn(
        INVOKEVIRTUAL, "net/sourceforge/cobertura/coveragedata/ClassData", "touch", "(I)V");
    super.visitLineNumber(line, start);
  }
}
