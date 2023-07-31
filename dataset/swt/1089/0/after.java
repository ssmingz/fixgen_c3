class PlaceHold {
  public Shell open(Display display) {
    Class clazz = HoverHelp.class;
    try {
      if (images == null) {
        images = new Image[HoverHelp.imageLocations.length];
        for (int i = 0; i < HoverHelp.imageLocations.length; ++i) {
          InputStream stream = clazz.getResourceAsStream(imageLocations[i]);
          ImageData source = new ImageData(stream);
          ImageData mask = source.getTransparencyMask();
          images[i] = new Image(display, source, mask);
          try {
            stream.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    } catch (Exception ex) {
      System.err.println(
          getResourceString("error.CouldNotLoadResources", new Object[] {ex.getMessage()}));
      return null;
    }
    Shell shell = new Shell();
    createPartControl(shell);
    shell.addDisposeListener(
        new DisposeListener() {
          public void widgetDisposed(DisposeEvent e) {
            if (images != null) {
              for (int i = 0; i < images.length; i++) {
                final Image image = images[i];
                if (image != null) {
                  image.dispose();
                }
              }
              images = null;
            }
          }
        });
    shell.pack();
    shell.open();
    return shell;
  }
}
