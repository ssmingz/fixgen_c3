class PlaceHold {
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Usage: java Main <program id>");
      return;
    }
    String progID = args[0];
    Display display = new Display();
    Shell shell = new Shell(display);
    OleFrame frame = new OleFrame(shell, SWT.NONE);
    OleControlSite site = null;
    OleAutomation auto = null;
    try {
      site = new OleControlSite(frame, SWT.NONE, progID);
      auto = new OleAutomation(site);
    } catch (SWTException ex) {
      System.out.println("Unable to open type library for " + progID);
      return;
    }
    printTypeInfo(auto);
    auto.dispose();
    shell.dispose();
    display.dispose();
  }
}
