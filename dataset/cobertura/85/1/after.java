class PlaceHold {
  private void instrumentInvokeTouchJump() {
    mv.visitMethodInsn(
        INVOKESTATIC, TOUCH_COLLECTOR_CLASS, "touchJump", "(Ljava/lang/String;IIZ)V");
    mv.visitIntInsn(SIPUSH, -1);
    mv.visitVarInsn(ISTORE, myVariableIndex + 1);
  }
}
