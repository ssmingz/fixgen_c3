class PlaceHold {
  private void instrumentInvokeTouchJump() {
    mv.visitMethodInsn(
        INVOKEVIRTUAL, "net/sourceforge/cobertura/coveragedata/ClassData", "touchJump", "(IIZ)V");
    mv.visitIntInsn(SIPUSH, -1);
    mv.visitVarInsn(ISTORE, myVariableIndex + 1);
  }
}
