In `com.insecureshop.ChooserActivity`, we can find the following method being defined.
```java
private final Uri makeTempCopy(Uri fileUri, String original_filename) {
        try {
            StringBuilder sb = new StringBuilder();
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            Intrinsics.checkExpressionValueIsNotNull(externalStorageDirectory, "Environment.getExternalStorageDirectory()");
            sb.append(externalStorageDirectory.getAbsolutePath());
            sb.append(File.separator);
            sb.append("insecureshop");
            String path = sb.toString();
            File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File fileTemp = new File(path, original_filename);
            fileTemp.createNewFile();
            Uri fromFile = Uri.fromFile(fileTemp);
            InputStream openInputStream = getContentResolver().openInputStream(fileUri);
            OutputStream openOutputStream = getContentResolver().openOutputStream(fromFile);
            byte[] bArr = new byte[8192];
            while (true) {
                Integer len = openInputStream != null ? Integer.valueOf(openInputStream.read(bArr)) : null;
                if (len != null && len.intValue() == -1) {
                }
                if (len != null) {
                    int it = len.intValue();
                    if (openOutputStream != null) {
                        openOutputStream.write(bArr, 0, it);
                    }
                }
            }
        } catch (Exception e) {
            return null;
        }
    }
```
which takes a file defined from the property fileUri: Uri and creates a copy of it to the external storage where other applications also have the ability to read/write. In the onCreate of the same class we have the following lines:
```java
Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
        if (intent.getExtras() != null) {
            Parcelable parcelableExtra = getIntent().getParcelableExtra("android.intent.extra.STREAM");
            if (parcelableExtra == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.net.Uri");
            }
            Uri uri = Uri.fromFile(new File(((Uri) parcelableExtra).toString()));
            Intrinsics.checkExpressionValueIsNotNull(uri, "Uri.fromFile(File(uri.toString()))");
            makeTempCopy(uri, getFilename(uri));
        }
```
It means that when the activity receives an Intent of type EXTRA_STREAM then it will invoke the makeTempCopy on it. Thisis a perfect candidate for a third application on the device to extract sensitive information from the InsecureShop, for which it would not have access otherwise.

