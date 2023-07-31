class PlaceHold {
  public void test_fastStringDrawing() {
    PerformanceMeter meter = createMeter("Draw strings using GC.drawString()");
    int samples;
    Performance performance = Performance.getDefault();
    performance.tagAsGlobalSummary(meter, "Draw strings using GC.drawString()", CPU_TIME);
    for (samples = 0; samples < 10; samples++) {
      int width = 640;
      int height = 480;
      Shell shell = new Shell(display);
      shell.setLayout(new GridLayout());
      Canvas c = new Canvas(shell, SWT.NONE);
      GridData data = new GridData();
      data.widthHint = width;
      data.heightHint = height;
      c.setLayoutData(data);
      shell.pack();
      shell.open();
      while (display.readAndDispatch())
        ;
      Color color1 = new Color(display, 0xff, 0, 0xff);
      Color color2 = new Color(display, 0, 0xff, 0xff);
      Font font1 = new Font(display, "Helvetica", 20, SWT.NONE);
      Font font2 = new Font(display, "Helvetica", 10, SWT.BOLD);
      String testString = "The quick brown SWT jumped foxily over the lazy dog.";
      int x1 = 0;
      int y1 = height / 2;
      int x2 = width / 2;
      int y2 = 0;
      meter.start();
      GC gc = new GC(c);
      for (int i = 0; i < 2000; i++) {
        x1 = (x1 + 5) % width;
        y1 = (y1 + 5) % height;
        x2 = (x2 + 5) % width;
        y2 = (y2 + 5) % height;
        gc.setFont((i & 1) == 0 ? font1 : font2);
        gc.setForeground((i & 1) == 0 ? color1 : color2);
        gc.stringExtent(testString);
        gc.drawString(testString, x1, y2);
        gc.drawString(testString, x1, y1, true);
      }
      gc.dispose();
      meter.stop();
      shell.dispose();
      color1.dispose();
      color2.dispose();
      font1.dispose();
      font2.dispose();
      while (display.readAndDispatch())
        ;
    }
    disposeMeter(meter);
  }
}
