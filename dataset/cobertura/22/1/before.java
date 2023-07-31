class PlaceHold {
  private void instrumentInvokeTouchSwitch() {
    mv.visitMethodInsn(
        INVOKEVIRTUAL, "net/sourceforge/cobertura/coveragedata/ClassData", "touchSwitch", "(III)V");
  }
}
