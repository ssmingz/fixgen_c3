class PlaceHold {
  private void instrumentInvokeTouchSwitch() {
    mv.visitMethodInsn(
        INVOKESTATIC, TOUCH_COLLECTOR_CLASS, "touchSwitch", "(Ljava/lang/String;III)V");
  }
}
