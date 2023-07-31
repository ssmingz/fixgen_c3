class PlaceHold {
  public void test_layout() {
    PerformanceMeter meter = createMeter("layout composites");
    int samples;
    for (samples = 0; samples < 10; samples++) {
      Shell shell = new Shell(display);
      shell.setLayout(new GridLayout());
      String curText = "";
      Label changedLabel;
      Composite parent = shell;
      GridData data;
      for (int i = 0; i < 10; i++) {
        Composite c = new Composite(parent, SWT.BORDER);
        data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.verticalAlignment = GridData.FILL;
        c.setLayoutData(data);
        c.setLayout(new GridLayout(2, false));
        Composite c1 = new Composite(c, SWT.BORDER);
        data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.verticalAlignment = GridData.FILL;
        data.widthHint = data.heightHint = 2;
        c1.setLayoutData(data);
        Composite c2 = new Composite(c, SWT.BORDER);
        data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.verticalAlignment = GridData.FILL;
        data.widthHint = data.heightHint = 2;
        c2.setLayoutData(data);
        Composite c3 = new Composite(c, SWT.BORDER);
        data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.verticalAlignment = GridData.FILL;
        data.widthHint = data.heightHint = 2;
        c3.setLayoutData(data);
        Composite c4 = new Composite(c, SWT.BORDER);
        data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.verticalAlignment = GridData.FILL;
        c4.setLayoutData(data);
        c4.setLayout(new GridLayout());
        parent = c4;
      }
      changedLabel = new Label(parent, SWT.NONE);
      data = new GridData();
      data.horizontalAlignment = GridData.FILL;
      data.verticalAlignment = GridData.FILL;
      data.grabExcessHorizontalSpace = true;
      data.grabExcessVerticalSpace = true;
      changedLabel.setLayoutData(data);
      shell.open();
      while (display.readAndDispatch())
        ;
      meter.start();
      for (int numlayouts = 0; numlayouts < 20; numlayouts++) {
        shell.layout(true);
        curText = ("!!!" + curText) + "!!!\n!";
        changedLabel.setText(curText);
      }
      meter.stop();
      shell.dispose();
      while (display.readAndDispatch())
        ;
    }
    disposeMeter(meter);
  }
}
