class PlaceHold {
  void menuPrint() {
    PrintDialog dialog = new PrintDialog(shell, SWT.NULL);
    PrinterData data = dialog.open();
    if (data == null) {
      return;
    }
    if (data.printToFile) {
      data.fileName = "print.out";
    }
    textToPrint = text.getText();
    printer = new Printer(data);
    Thread printingThread =
        new Thread("Printing") {
          public void run() {
            print(printer);
            printer.dispose();
          }
        };
    printingThread.start();
  }
}
