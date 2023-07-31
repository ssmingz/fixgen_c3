class PlaceHold {
  private static void getSwitchDataIteratively(LineData data) {
    for (int i = 0; i < 2000; i++) {
      Thread.yield();
      data.getSwitchData(i, new SwitchData(1));
    }
  }
}
