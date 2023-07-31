class PlaceHold {
  public PrinterData open() {
    if (OS.GTK_VERSION < OS.VERSION(2, 10, 0)) {
      return Printer.getDefaultPrinterData();
    } else {
      byte[] titleBytes = Converter.wcsToMbcs(null, getText(), true);
      int topHandle = getParent().handle;
      while ((topHandle != 0) && (!OS.GTK_IS_WINDOW(topHandle))) {
        topHandle = OS.gtk_widget_get_parent(topHandle);
      }
      handle = OS.gtk_print_unix_dialog_new(titleBytes, topHandle);
      OS.gtk_print_unix_dialog_set_current_page(handle, -1);
      OS.gtk_print_unix_dialog_set_manual_capabilities(
          handle,
          (OS.GTK_PRINT_CAPABILITY_COLLATE | OS.GTK_PRINT_CAPABILITY_COPIES)
              | OS.GTK_PRINT_CAPABILITY_PAGE_SET);
      int settings = OS.gtk_print_settings_new();
      int page_setup = OS.gtk_page_setup_new();
      if (printerData.otherData != null) {
        Printer.restore(printerData.otherData, settings, page_setup);
      }
      switch (printerData.scope) {
        case PrinterData.ALL_PAGES:
          OS.gtk_print_settings_set_print_pages(settings, GTK_PRINT_PAGES_ALL);
          break;
        case PrinterData.PAGE_RANGE:
          OS.gtk_print_settings_set_print_pages(settings, GTK_PRINT_PAGES_RANGES);
          int[] pageRange = new int[2];
          pageRange[0] = printerData.startPage - 1;
          pageRange[1] = printerData.endPage - 1;
          OS.gtk_print_settings_set_page_ranges(settings, pageRange, 1);
          break;
        case PrinterData.SELECTION:
          OS.gtk_print_settings_set_print_pages(settings, GTK_PRINT_PAGES_ALL);
          break;
      }
      if (printerData.fileName != null) {
        if (printerData.printToFile) {
          byte[] buffer = Converter.wcsToMbcs(null, printerData.fileName, true);
          OS.gtk_print_settings_set(settings, GTK_PRINT_SETTINGS_OUTPUT_URI, buffer);
        }
        if ((printerData.driver != null) && (printerData.name != null)) {
          if (printerData.driver.equals("GtkPrintBackendFile")
              && printerData.name.equals("Print to File")) {
            byte[] buffer = Converter.wcsToMbcs(null, printerData.fileName, true);
            OS.gtk_print_settings_set(settings, GTK_PRINT_SETTINGS_OUTPUT_URI, buffer);
          }
        }
      }
      if (printerData.printToFile) {
        byte[] buffer = Converter.wcsToMbcs(null, "Print to File", true);
        OS.gtk_print_settings_set_printer(settings, buffer);
      }
      OS.gtk_print_settings_set_n_copies(settings, printerData.copyCount);
      OS.gtk_print_settings_set_collate(settings, printerData.collate);
      int orientation =
          (printerData.orientation == PrinterData.LANDSCAPE)
              ? OS.GTK_PAGE_ORIENTATION_LANDSCAPE
              : OS.GTK_PAGE_ORIENTATION_PORTRAIT;
      OS.gtk_print_settings_set_orientation(settings, orientation);
      OS.gtk_page_setup_set_orientation(page_setup, orientation);
      OS.gtk_print_unix_dialog_set_settings(handle, settings);
      OS.gtk_print_unix_dialog_set_page_setup(handle, page_setup);
      OS.g_object_unref(settings);
      OS.g_object_unref(page_setup);
      OS.gtk_window_set_modal(handle, true);
      PrinterData data = null;
      Display display = (getParent() != null) ? getParent().getDisplay() : Display.getCurrent();
      int signalId = 0;
      int hookId = 0;
      if ((getStyle() & SWT.RIGHT_TO_LEFT) != 0) {
        signalId = OS.g_signal_lookup(map, OS.GTK_TYPE_WIDGET());
        hookId =
            OS.g_signal_add_emission_hook(
                signalId, 0, ((LONG) (display.getData(GET_EMISSION_PROC_KEY))).value, handle, 0);
      }
      display.setData(ADD_IDLE_PROC_KEY, null);
      Object oldModal = null;
      if (OS.gtk_window_get_modal(handle)) {
        oldModal = display.getData(GET_MODAL_DIALOG);
        display.setData(SET_MODAL_DIALOG, this);
      }
      int response = OS.gtk_dialog_run(handle);
      if (OS.gtk_window_get_modal(handle)) {
        display.setData(SET_MODAL_DIALOG, oldModal);
      }
      if ((getStyle() & SWT.RIGHT_TO_LEFT) != 0) {
        OS.g_signal_remove_emission_hook(signalId, hookId);
      }
      if (response == OS.GTK_RESPONSE_OK) {
        int printer = OS.gtk_print_unix_dialog_get_selected_printer(handle);
        if (printer != 0) {
          settings = OS.gtk_print_unix_dialog_get_settings(handle);
          page_setup = OS.gtk_print_unix_dialog_get_page_setup(handle);
          data = Printer.printerDataFromGtkPrinter(printer);
          int print_pages = OS.gtk_print_settings_get_print_pages(settings);
          switch (print_pages) {
            case OS.GTK_PRINT_PAGES_ALL:
              data.scope = PrinterData.ALL_PAGES;
              break;
            case OS.GTK_PRINT_PAGES_RANGES:
              data.scope = PrinterData.PAGE_RANGE;
              int[] num_ranges = new int[1];
              int page_ranges = OS.gtk_print_settings_get_page_ranges(settings, num_ranges);
              int[] pageRange = new int[2];
              int length = num_ranges[0];
              int min = Integer.MAX_VALUE;
              int max = 0;
              for (int i = 0; i < length; i++) {
                OS.memmove(
                    pageRange, page_ranges + ((i * pageRange.length) * 4), pageRange.length * 4);
                min = Math.min(min, pageRange[0] + 1);
                max = Math.max(max, pageRange[1] + 1);
              }
              OS.g_free(page_ranges);
              data.startPage = (min == Integer.MAX_VALUE) ? 1 : min;
              data.endPage = (max == 0) ? 1 : max;
              break;
            case OS.GTK_PRINT_PAGES_CURRENT:
              data.scope = PrinterData.SELECTION;
              data.startPage = data.endPage = OS.gtk_print_unix_dialog_get_current_page(handle);
              break;
          }
          data.printToFile = data.name.equals("Print to File");
          if (data.printToFile) {
            int address = OS.gtk_print_settings_get(settings, GTK_PRINT_SETTINGS_OUTPUT_URI);
            int length = OS.strlen(address);
            byte[] buffer = new byte[length];
            OS.memmove(buffer, address, length);
            data.fileName = new String(Converter.mbcsToWcs(null, buffer));
          }
          data.copyCount = OS.gtk_print_settings_get_n_copies(settings);
          data.collate = OS.gtk_print_settings_get_collate(settings);
          data.orientation =
              (OS.gtk_page_setup_get_orientation(page_setup) == OS.GTK_PAGE_ORIENTATION_LANDSCAPE)
                  ? PrinterData.LANDSCAPE
                  : PrinterData.PORTRAIT;
          Callback printSettingsCallback = new Callback(this, "GtkPrintSettingsFunc", 3);
          int GtkPrintSettingsFunc = printSettingsCallback.getAddress();
          if (GtkPrintSettingsFunc == 0) {
            SWT.error(ERROR_NO_MORE_CALLBACKS);
          }
          index = 0;
          settingsData = new byte[1024];
          OS.gtk_print_settings_foreach(settings, GtkPrintSettingsFunc, 0);
          printSettingsCallback.dispose();
          index++;
          store("orientation", OS.gtk_page_setup_get_orientation(page_setup));
          store("top_margin", OS.gtk_page_setup_get_top_margin(page_setup, GTK_UNIT_MM));
          store("bottom_margin", OS.gtk_page_setup_get_bottom_margin(page_setup, GTK_UNIT_MM));
          store("left_margin", OS.gtk_page_setup_get_left_margin(page_setup, GTK_UNIT_MM));
          store("right_margin", OS.gtk_page_setup_get_right_margin(page_setup, GTK_UNIT_MM));
          int paper_size = OS.gtk_page_setup_get_paper_size(page_setup);
          storeBytes("paper_size_name", OS.gtk_paper_size_get_name(paper_size));
          storeBytes("paper_size_display_name", OS.gtk_paper_size_get_display_name(paper_size));
          storeBytes("paper_size_ppd_name", OS.gtk_paper_size_get_ppd_name(paper_size));
          store("paper_size_width", OS.gtk_paper_size_get_width(paper_size, GTK_UNIT_MM));
          store("paper_size_height", OS.gtk_paper_size_get_height(paper_size, GTK_UNIT_MM));
          store("paper_size_is_custom", OS.gtk_paper_size_is_custom(paper_size));
          data.otherData = settingsData;
          OS.g_object_unref(settings);
          printerData = data;
        }
      }
      display.setData(REMOVE_IDLE_PROC_KEY, null);
      OS.gtk_widget_destroy(handle);
      return data;
    }
  }
}
