class PlaceHold{
protected void doReplace(File f, int options) throws IOException, TaskException {
    File parentDir = new File(new File(f.getAbsolutePath()).getParent());
    File temp = File.createTempFile("replace", ".txt", parentDir);
    FileReader r = null;
    FileWriter w = null;
    try {
        r = new FileReader(f);
        w = new FileWriter(temp);
        BufferedReader br = new BufferedReader(r);
        BufferedWriter bw = new BufferedWriter(w);
        PrintWriter pw = new PrintWriter(bw);
        boolean changes = false;
        final String message = (((((((("Replacing pattern '" + regex.getPattern()) + "' with '") + subs.getExpression()) + "' in '") + f.getPath()) + "'") + (byline ? " by line" : "")) + (flags.length() > 0 ? (" with flags: '" + flags) + "'" : "")) + ".";
        getContext().info(message);
        if (byline) {
            LineNumberReader lnr = new LineNumberReader(br);
            String line = null;
            while ((line = lnr.readLine()) != null) {
                String res = doReplace(regex, subs, line, options);
                if (!res.equals(line)) {
                    changes = true;
                }
                pw.println(res);
            } 
            pw.flush();
        } else {
            int flen = ((int) (f.length()));
            char tmpBuf[] = new char[flen];
            int numread = 0;
            int totread = 0;
            while ((numread != (-1)) && (totread < flen)) {
                numread = br.read(tmpBuf, totread, flen);
                totread += numread;
            } 
            String buf = new String(tmpBuf);
            String res = doReplace(regex, subs, buf, options);
            if (!res.equals(buf)) {
                changes = true;
            }
            pw.println(res);
            pw.flush();
        }
        r.close();
        r = null;
        w.close();
        w = null;
        if (changes) {
            f.delete();
            temp.renameTo(f);
        } else {
            temp.delete();
        }
    } finally {
        try {
            if (r != null) {
                r.close();
            }
        } catch (Exception e) {
        }
        try {
            if (w != null) {
                r.close();
            }
        } catch (Exception e) {
        }
    }
}
}