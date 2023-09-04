class PlaceHold {
  protected synchronized Control createDialogArea(Composite parent2) {

    Composite parent = (Composite) super.createDialogArea(parent2);

    getShell().setText(Utilities.getString(fBundle, "title")); // $NON-NLS-1$

    Splitter vsplitter = new Splitter(parent, SWT.VERTICAL);
    vsplitter.setLayoutData(
        new GridData(
            GridData.HORIZONTAL_ALIGN_FILL
                | GridData.GRAB_HORIZONTAL
                | GridData.VERTICAL_ALIGN_FILL
                | GridData.GRAB_VERTICAL));

    vsplitter.addDisposeListener(
        new DisposeListener() {
          public void widgetDisposed(DisposeEvent e) {
            if (fCompareConfiguration != null) {
              fCompareConfiguration.dispose();
              fCompareConfiguration = null;
            }
            if (fDateImage != null) {
              fDateImage.dispose();
              fDateImage = null;
            }
            if (fTimeImage != null) {
              fTimeImage.dispose();
              fTimeImage = null;
            }
          }
        });

    if (fAddMode) {
      // we need two panes: the left for the elements, the right one for the editions
      Splitter hsplitter = new Splitter(vsplitter, SWT.HORIZONTAL);

      fMemberPane = new CompareViewerPane(hsplitter, SWT.BORDER | SWT.FLAT);
      fMemberPane.setText(Utilities.getString(fBundle, "memberPaneTitle")); // $NON-NLS-1$

      int flags = SWT.H_SCROLL | SWT.V_SCROLL;
      if (fMultiSelect) flags |= SWT.CHECK;
      fMemberTable = new Table(fMemberPane, flags);
      fMemberTable.addSelectionListener(
          new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
              if (e.detail == SWT.CHECK) {
                if (e.item instanceof TableItem) {
                  TableItem ti = (TableItem) e.item;
                  Object data = ti.getData();
                  if (ti.getChecked()) fArrayList.add(data);
                  else fArrayList.remove(data);

                  if (fCommitButton != null) fCommitButton.setEnabled(fArrayList.size() > 0);

                  fMemberTable.setSelection(new TableItem[] {ti});
                }
              }
              handleMemberSelect(e.item);
            }
          });
      fMemberPane.setContent(fMemberTable);
      fMemberTable.setFocus();

      fEditionPane = new CompareViewerPane(hsplitter, SWT.BORDER | SWT.FLAT);
    } else {
      if (fStructureCompare) {
        // we need two panes: the left for the elements, the right one for the structured diff
        Splitter hsplitter = new Splitter(vsplitter, SWT.HORIZONTAL);

        fEditionPane = new CompareViewerPane(hsplitter, SWT.BORDER | SWT.FLAT);
        fStructuredComparePane =
            new CompareViewerSwitchingPane(hsplitter, SWT.BORDER | SWT.FLAT, true) {
              protected Viewer getViewer(Viewer oldViewer, Object input) {
                if (input instanceof ICompareInput)
                  return CompareUI.findStructureViewer(
                      oldViewer, (ICompareInput) input, this, getCompareConfiguration());
                return null;
              }
            };
        fStructuredComparePane.addSelectionChangedListener(
            new ISelectionChangedListener() {
              public void selectionChanged(SelectionChangedEvent e) {
                feedInput2(e.getSelection());
              }
            });
      } else {
        // only a single pane showing the editions
        fEditionPane = new CompareViewerPane(vsplitter, SWT.BORDER | SWT.FLAT);
      }
      if (fTitleArg == null) fTitleArg = fTargetPair.getItem().getName();
      String titleFormat = Utilities.getString(fBundle, "treeTitleFormat"); // $NON-NLS-1$
      String title = MessageFormat.format(titleFormat, new String[] {fTitleArg});
      fEditionPane.setText(title);
      if (fTitleImage != null) fEditionPane.setImage(fTitleImage);
    }

    fEditionTree = new Tree(fEditionPane, SWT.H_SCROLL | SWT.V_SCROLL);
    fEditionTree.addSelectionListener(
        new SelectionAdapter() {
          //				public void widgetDefaultSelected(SelectionEvent e) {
          //					handleDefaultSelected();
          //				}
          public void widgetSelected(SelectionEvent e) {
            feedInput(e.item);
          }
        });
    fEditionPane.setContent(fEditionTree);

    // now start the thread (and forget about it)
    if (fThread != null) {
      fThread.start();
      fThread = null;
    }

    fContentPane =
        new CompareViewerSwitchingPane(vsplitter, SWT.BORDER | SWT.FLAT) {
          protected Viewer getViewer(Viewer oldViewer, Object input) {
            return CompareUI.findContentViewer(oldViewer, input, this, getCompareConfiguration());
          }
        };
    vsplitter.setWeights(new int[] {30, 70});

    IPreferenceStore store = getCompareConfiguration().getPreferenceStore();
    if (store != null) {
      if (store.getBoolean(ComparePreferencePage.SHOW_MORE_INFO)) {
        statusLabel = new Label(parent, SWT.NONE);
        statusLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
      }
    }

    applyDialogFont(parent);
    return parent;
  }
}
